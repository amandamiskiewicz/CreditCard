package pl.amiskiewicz.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.amiskiewicz.ecommerce.catalog.ProductCatalog;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        System.out.println("Here we go!");

        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.addProduct("Legoset 9231", "Nice One");
        productCatalog.addProduct("Cobi 1802", "Nice One");

        return productCatalog;
    }
}
