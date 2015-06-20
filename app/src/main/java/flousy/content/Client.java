package flousy.content;

import flousy.content.finance.ListIncomes;
import flousy.content.finance.PaymentsAccount;
import flousy.content.spend.SpendsAccount;

/**
 * Created by Samir on 19/06/2015.
 */
public class Client extends Person {

    private String id;
    private String email;
    private String password;
    private Phone phone;
    private ListIncomes listIncomes;
    private PaymentsAccount paymentsAccount;
    private SpendsAccount spendsAccount;

    public Client() {}

    public Client(String firstName, String lastName, String email, Phone phone) {
        super(firstName, lastName);

        this.email = email;
        this.phone = phone;
        this.listIncomes = new ListIncomes();
        this.paymentsAccount = new PaymentsAccount();
        this.spendsAccount = new SpendsAccount();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public ListIncomes getListIncomes() {
        return this.listIncomes;
    }

    public void setListIncomes(ListIncomes listIncomes) {
        this.listIncomes = listIncomes;
    }

    public PaymentsAccount getPaymentsAccount() {
        return this.paymentsAccount;
    }

    public void setPaymentsAccount(PaymentsAccount paymentsAccount) {
        this.paymentsAccount = paymentsAccount;
    }

    public SpendsAccount getSpendsAccount() {
        return this.spendsAccount;
    }

    public void setSpendsAccount(SpendsAccount spendsAccount) {
        this.spendsAccount = spendsAccount;
    }
}
