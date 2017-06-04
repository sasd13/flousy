package com.sasd13.flousy.model;

import com.sasd13.flousy.util.converter.DateConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;

@Entity(generateGettersSetters = false)
public class Account {

    @Property(nameInDb = "_id")
    @Id(autoincrement = true)
    private long id;

    @Property(nameInDb = "_number")
    @NotNull
    @Unique
    private String number;

    @Property(nameInDb = "_dateopening")
    @NotNull
    @Convert(converter = DateConverter.class, columnType = Long.class)
    private Date dateOpening;

    @ToOne(joinProperty = "customerId")
    private Customer customer;
    private long customerId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateOpening() {
        return dateOpening;
    }

    public void setDateOpening(Date dateOpening) {
        this.dateOpening = dateOpening;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
