package com.irs.springbootflashattributeexample.integracion.repositorios;

import com.irs.springbootflashattributeexample.integracion.entidades.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
