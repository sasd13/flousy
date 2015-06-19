package flousy.content;

/**
 * Created by Samir on 19/06/2015.
 */
public class Client extends Person {

    private String id;
    private String email;
    private Phone phone;

    public Client() {}

    public Client(String firstName, String lastName, String email, Phone phone) {
        super(firstName, lastName);

        this.email = email;
        this.phone = phone;
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

    public Phone getPhone() {
        return this.phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
