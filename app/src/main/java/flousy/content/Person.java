package flousy.content;

/**
 * Created by Samir on 07/03/2015.
 */
public class Person {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Person() {
        this.firstName = null;
        this.lastName = null;
        this.phoneNumber = null;
    }

    public Person(String firstName, String lastName,  String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
