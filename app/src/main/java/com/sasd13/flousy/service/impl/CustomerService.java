package com.sasd13.flousy.service.impl;

import com.sasd13.flousy.dao.ICustomerDAO;
import com.sasd13.flousy.service.ICustomerService;
import com.sasd13.flousy.service.ServiceResult;

import java.util.Collections;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class CustomerService implements ICustomerService {

    private ICustomerDAO customerDAO;

    public CustomerService(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public ServiceResult<Void> create(Customer customer) {
        customerDAO.create(customer);

        return ServiceResult.SUCCESS;
    }

    @Override
    public ServiceResult<Void> update(Customer customer) {
        customerDAO.update(customer);

        return ServiceResult.SUCCESS;
    }

    @Override
    public ServiceResult<Customer> read(String intermediary) {
        Customer customer = customerDAO.read(intermediary);

        return new ServiceResult<>(
                true,
                Collections.<String, String>emptyMap(),
                customer
        );
    }
}
