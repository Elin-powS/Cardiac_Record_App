package com.example.cardiacrecorderapp;

public class User_info {

        public String name,email,birthday,phone_number,Username;
        public User_info()
        {

        }
        public User_info(String Fullname,String Email,String DOB,String Contact_number,String username,String address)
        {
            this.name = Fullname;
            this.email=Email;
            this.birthday=DOB;
            this.phone_number=Contact_number;
            this.Username=username;
        }
    }
