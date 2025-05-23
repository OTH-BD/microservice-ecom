package net.bd.billingservice;

import net.bd.billingservice.entities.Bill;
import net.bd.billingservice.entities.ProductItem;
import net.bd.billingservice.feign.CustomerRestClient;
import net.bd.billingservice.feign.ProductRestClient;
import net.bd.billingservice.module.Customer;
import net.bd.billingservice.module.Product;
import net.bd.billingservice.repository.BillRepository;
import net.bd.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository, ProductItemRepository productItemRepository , CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
      return args -> {
          Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
          Collection<Product> products = productRestClient.getAllProducts().getContent();

          customers.forEach(customer -> {
              Bill bill = Bill.builder().billingDate(new Date()).customerId(customer.getId()).build();
              billRepository.save(bill);

              products.forEach(product -> {
                  ProductItem productItem = ProductItem.builder()
                          .bill(bill)
                          .productId(product.getId())
                          .quantity(new Random().nextInt(10))
                          .unitPrice(product.getPrice())
                          .build();
                  productItemRepository.save(productItem);
              });
          });
      };
    }

}
