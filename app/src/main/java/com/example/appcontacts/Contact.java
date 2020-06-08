package com.example.appcontacts;

public class Contact {
   private int id ;
   private String name ;
    private int phone ;
     Contact(String name,int phone){
        this.name=name;
        this.phone=phone ;

    }

    public Contact(int id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName(){
        return name ;

    }

    public int getId() {
        return id;
    }

    public int getPhone(){
        return phone ;

    }
}
