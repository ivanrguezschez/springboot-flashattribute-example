package com.irs.springbootflashattributeexample.negocio.mappers;

import com.irs.springbootflashattributeexample.integracion.entidades.Customer;
import com.irs.springbootflashattributeexample.negocio.dtos.CustomerDto;
import com.irs.springbootflashattributeexample.util.Mapper;
import org.springframework.stereotype.Component;

@Component("customerMapper")
public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    public CustomerMapper() {
        super();
    }

    @Override
    public Customer toSource(CustomerDto target) {
        if (target != null) {
            return Customer.builder()
                    .id(target.getId())
                    .firstName(target.getFirstName().trim())
                    .lastName(target.getLastName().trim())
                    .age(target.getAge())
                    .email(target.getEmail().trim())
                    .build();
        }

        return null;
    }

    @Override
    public CustomerDto toTarget(Customer source) {
        if (source != null) {
            return CustomerDto.builder()
                    .id(source.getId())
                    .firstName(source.getFirstName().trim())
                    .lastName(source.getLastName().trim())
                    .age(source.getAge())
                    .email(source.getEmail().trim())
                    .build();
        }

        return null;
    }
}
