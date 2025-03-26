package com.irs.springbootflashattributeexample.negocio.servicios.impl;

import com.irs.springbootflashattributeexample.integracion.entidades.Customer;
import com.irs.springbootflashattributeexample.integracion.repositorios.CustomerRepository;
import com.irs.springbootflashattributeexample.negocio.dtos.CustomerDto;
import com.irs.springbootflashattributeexample.negocio.servicios.CustomerService;
import com.irs.springbootflashattributeexample.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final Mapper<Customer, CustomerDto> customerMapper;

    @Override
    public List<CustomerDto> findAll() {
        if (log.isInfoEnabled()) {
            log.info("Buscando todos los clientes");
        }

        return this.customerMapper.toTarget(this.customerRepository.findAll());
    }

    @Override
    public CustomerDto findById(Long id) {
        if (log.isInfoEnabled()) {
            log.info("Buscando cliente por id {}", id);
        }
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Customer %s not found", id)));

        return this.customerMapper.toTarget(customer);
    }

    @Override
    public void save(CustomerDto customer) {
        if (log.isInfoEnabled()) {
            log.info("Guardando cliente {}", customer);
        }
        this.customerRepository.save(this.customerMapper.toSource(customer));
    }

    @Override
    public void update(CustomerDto customer) {
        if (log.isInfoEnabled()) {
            log.info("Actualizando cliente {}", customer);
        }
        this.customerRepository.save(this.customerMapper.toSource(customer));
    }

    @Override
    public void remove(Long id) {
        if (log.isInfoEnabled()) {
            log.info("Eliminando cliente por id {}", id);
        }
        this.customerRepository.deleteById(id);
    }
}
