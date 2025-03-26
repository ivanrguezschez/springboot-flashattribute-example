package com.irs.springbootflashattributeexample.negocio.servicios;

import com.irs.springbootflashattributeexample.negocio.dtos.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAll();

    CustomerDto findById(final Long id);

    void save(final CustomerDto customer);

    void update(final CustomerDto customer);

    void remove(final Long id);
}
