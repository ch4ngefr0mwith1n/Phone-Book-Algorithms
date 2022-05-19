package phonebook;

import java.util.Objects;
/*
    - implementiranje "Builder" dizajn pattern-a
    - https://hyperskill.org/learn/step/5130
 */
public class Contact implements Comparable<Contact>{
    private String phoneNumber;
    private String firstName;
    private String lastName;

    static class Builder {
        private Contact contact;

        Builder() {
            contact = new Contact();
        }

        public Builder phonenumber(String phoneNumber) {
            contact.phoneNumber = phoneNumber;
            return this;
        }

        public Builder firstname(String firstName) {
            contact.firstName = firstName;
            return this;
        }

        public Builder lastname(String lastName) {
            contact.lastName = lastName;
            return this;
        }

        public Contact build() {
            return this.contact;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;

        /*
        if (lastName == null) {
            return Objects.equals(firstName, contact.firstName);
        }
         */

        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Contact anotherContact) {
        int result = getFirstName().compareTo(anotherContact.getFirstName());

        if (result == 0 && getLastName() != null && anotherContact.getLastName() != null) {
            result = getLastName().compareTo(anotherContact.getLastName());
        }

        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
