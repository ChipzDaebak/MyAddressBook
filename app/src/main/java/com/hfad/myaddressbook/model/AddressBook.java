package com.hfad.myaddressbook.model;

public class AddressBook {
    private final int addressBookID;
    private final String firstName, lastName, city, state;
    private final String phone, email, picture;

    public AddressBook(int addressBookID, String firstName, String lastName,
                       String city, String state, String phone, String email, String picture) {
        this.addressBookID = addressBookID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.email = email;
        this.picture = picture;
    }

    public int getAddressBookID() {
        return addressBookID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }
}
