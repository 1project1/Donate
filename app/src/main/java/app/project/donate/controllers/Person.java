package app.project.donate.controllers;

/**
 * Created by ArupPc on 04-03-2017.
 */

public class Person {
    private String name;
    private String address;
    private String phone;

    public Person(){

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }
}
