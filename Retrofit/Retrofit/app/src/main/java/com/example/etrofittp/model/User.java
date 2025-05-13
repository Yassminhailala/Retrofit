package com.example.etrofittp.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Address address;

    public int getId() {return id; }
    public String getName() {return name; }
    public String getEmail() {return email; }
    public String getPhone() {return phone; }


    public String getCity() {
        return (address != null) ? address.getCity() : "";
    }


    public static class Address {
        private String city;

        public String getCity() {return city; }
    }
}
