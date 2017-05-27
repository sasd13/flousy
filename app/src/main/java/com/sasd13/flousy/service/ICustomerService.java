package com.sasd13.flousy.service;

import com.sasd13.flousy.bean.Customer;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public interface ICustomerService {

    ServiceResult<Void> create(Customer customer);

    ServiceResult<Void> update(Customer customer);

    ServiceResult<Customer> read(String intermediary);
}
