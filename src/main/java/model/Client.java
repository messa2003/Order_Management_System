package model;

import businessLayer.validators.PhoneNumberValidator;

public class Client extends PhoneNumberValidator {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone_nr;

    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getAddress()
    {
        return address;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhone_nr()
    {
        return phone_nr;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public void setId(String clientID)
    {
        this.id = clientID;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPhone_nr(String phoneNumber)
    {
        this.phone_nr = phoneNumber;
    }
    @Override
    public String toString() {
        return "Client [clientID = " + id + ", name = " + name + ", address = " + address + ", email = " + email + " , phone number = " + phone_nr + "]";
    }
}