package com.sasd13.flousy.model;

import com.sasd13.flousy.util.converter.DateConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

@Entity(generateGettersSetters = false)
public class Operation {

    @Property(nameInDb = "_id")
    @Id(autoincrement = true)
    private long id;

    @Property(nameInDb = "_label")
    @NotNull
    private String label;

    @Property(nameInDb = "_type")
    @NotNull
    private String type;

    @Property(nameInDb = "_amount")
    @NotNull
    private double amount;

    @Property(nameInDb = "_daterealization")
    @NotNull
    @Convert(converter = DateConverter.class, columnType = Long.class)
    private Date dateRealization;

    @ToOne(joinProperty = "accountId")
    private Account account;
    private long accountId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateRealization() {
        return dateRealization;
    }

    public void setDateRealization(Date dateRealization) {
        this.dateRealization = dateRealization;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
