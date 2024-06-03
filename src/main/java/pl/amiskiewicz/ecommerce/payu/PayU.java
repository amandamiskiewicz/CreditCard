package pl.amiskiewicz.ecommerce.payu;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PayU {

    RestTemplate http;

    public PayU(RestTemplate http) {
        this.http = http;
    }

    public OrderCreateResponse handle(OrderCreateRequest orderCreateRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", String.format("Bearer %s", getToken()));
        HttpEntity<OrderCreateRequest> request = new HttpEntity<>(orderCreateRequest,headers);

        //Authorize
        ResponseEntity<OrderCreateResponse> orderCreateResponseResponse = http.postForEntity("https://secure.snd.payu.com/api/v2_1/orders",
                orderCreateRequest,
                OrderCreateResponse.class
        );

        //Create order
        return orderCreateResponseResponse.getBody();
    }

    private String getToken() {
        String body = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
                "300746",
                "2ee86a66e5d97e3fadc400c9f19b065d");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> request = new HttpEntity<>(body,headers);

        ResponseEntity<AccessTokenResponse> atResponse = http.postForEntity(
                "https://secure.snd.payu.com/pl/standard/user/oauth/authorize",
                request,
                AccessTokenResponse.class
        );

        return atResponse.getBody().getAccess_token();
    }
}
