package com.example.einarjonsson.myapplication;

/**
 * Created by einarjonsson on 16/03/2016.
 */
public class Contact {

    //declares the private variables for each class
    private String _name, _phone, _email, _address;

    //creates a new instance of Contact and sets its variables
    public Contact (String name, String phone, String email, String address) {

        _name = name;
        _phone = phone;
        _email = email;
        _address = address;
    }

    //declares public methods to get the values of the private variables
    public String getName() {
        return _name;
    }

    public String getPhone() {
        return _phone;
    }

    public String getEmail() {
        return _email;
    }

    public String getAddress() {
        return _address;
    }
}
