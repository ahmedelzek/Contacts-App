package com.example.contacts;

public class Contact {
    public String name, phone, description;

    public Contact( String contactName, String phone, String description) {


        this.name = contactName;
        this.phone = phone;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
