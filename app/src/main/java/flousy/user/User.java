package flousy.user;

/**
 * Created by Samir on 07/03/2015.
 */
public class User extends Person {
    private String email;
    private String password;
    private int image;

    public User() {
        super();
    }

    public User(String firstName, String lastName,  String phoneNumber, String email, String password, int image) {
        super(firstName, lastName, phoneNumber);

        this.email = email;
        this.password = password;
        this.image = image;
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

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
