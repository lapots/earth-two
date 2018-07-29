package com.lapots.breed.quest.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}
