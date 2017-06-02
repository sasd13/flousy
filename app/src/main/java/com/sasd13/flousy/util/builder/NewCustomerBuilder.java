package com.sasd13.flousy.util.builder;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.javaex.pattern.builder.IBuilder;

/**
 * Created by ssaidali2 on 02/06/2017.
 */

public class NewCustomerBuilder implements IBuilder<Customer> {

    @Override
    public Customer build() {
        Customer customer = new Customer();

        return customer;
    }
}
