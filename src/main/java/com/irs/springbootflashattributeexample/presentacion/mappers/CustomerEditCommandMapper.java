package com.irs.springbootflashattributeexample.presentacion.mappers;

import com.irs.springbootflashattributeexample.negocio.dtos.CustomerDto;
import com.irs.springbootflashattributeexample.presentacion.comandos.CustomerEditCommand;
import com.irs.springbootflashattributeexample.util.Mapper;
import org.springframework.stereotype.Component;

@Component("customerEditCommandMapper")
public class CustomerEditCommandMapper implements Mapper<CustomerEditCommand, CustomerDto>  {

    public CustomerEditCommandMapper() {
        super();
    }

    @Override
    public CustomerEditCommand toSource(CustomerDto target) {
        if (target != null) {
            return CustomerEditCommand.builder()
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
    public CustomerDto toTarget(CustomerEditCommand source) {
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
