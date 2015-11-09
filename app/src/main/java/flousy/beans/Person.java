package flousy.beans;

/**
 * Created by Samir on 07/03/2015.
 */
public class Person {

    private String firstName, lastName;

    public Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "["
                + "firstname: " + this.firstName + ", "
                + "lastname: " + this.lastName + "]";
    }
}
