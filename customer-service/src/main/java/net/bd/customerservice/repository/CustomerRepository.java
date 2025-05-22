package net.bd.customerservice.repository;

import net.bd.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
// Cette annotation  fournie par spring data REST il permet de domander a spring de demarer automatiquement un web service restFull
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
