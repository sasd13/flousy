package flousy.bean;

public class Customer {

    private long id;
    private String firstName, lastName, email, password;
    private Account account;

    public Customer() {
        this.account = new Account();

        this.account.setCustomer(this);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
            return this.firstName;
        }

    public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

    public String getLastName() {
            return this.lastName;
        }

    public void setLastName(String lastName) {
            this.lastName = lastName;
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

    public Account getAccount() {
        return this.account;
    }

    @Override
    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "firstname: " + this.lastName + ", "
                + "lastname: " + this.lastName + ", "
                + "email: " + this.email + ", "
                + "password: " + this.password + "]";
    }
}
