package net.bd.customerservice;

import net.bd.customerservice.config.CustomerConfigParams;
import net.bd.customerservice.entities.Customer;
import net.bd.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


       @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                            .name("Othmane").email("othmane.boudali01@gmail.com")
                            .build());
            customerRepository.save(Customer.builder()
                    .name("Salwa").email("salwa.boudali01@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Hasnaa").email("hasnaa.boudali01@gmail.com")
                    .build());
            customerRepository.findAll().forEach(c->{
                System.out.println("======================");
                System.out.println(c.getName());
                System.out.println(c.getEmail());
                System.out.println("======================");

            });
        };
       }
}
