package com.example.bookren.UserDetails;

public class Users
{
    private String Phone,email,name,password;
    public Users()
    {

    }

   // public Users(String phone, String email, String name, String password) {
     //   Phone = phone;
      //  this.email = email;
       // this.name = name;
       // this.password = password;
   // }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
