package com.sasd13.flousy.model;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.EnumOperationType;
import com.sasd13.flousy.util.DateConverter;
import com.sasd13.flousy.util.OperationTypeConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

public class Operation {

    @Property(nameInDb = "_id")
    @Id(autoincrement = true)
    private long id;

    @Property(nameInDb = "_label")
    @NotNull
    private String label;

    @Property(nameInDb = "_type")
    @NotNull
    @Convert(converter = OperationTypeConverter.class, columnType = String.class)
    private EnumOperationType type;

    @Property(nameInDb = "_amount")
    @NotNull
    private double amount;

    @Property(nameInDb = "_daterealization")
    @NotNull
    @Convert(converter = DateConverter.class, columnType = Long.class)
    private Date dateRealization;

    @ToOne()
    private Account account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EnumOperationType getType() {
        return type;
    }

    public void setType(EnumOperationType type) {
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
