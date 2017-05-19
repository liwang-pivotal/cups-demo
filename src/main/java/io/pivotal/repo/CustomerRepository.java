package io.pivotal.repo;

import io.pivotal.domain.Customer;

import org.springframework.data.gemfire.repository.GemfireRepository;

public interface CustomerRepository extends GemfireRepository<Customer, String> {
}
