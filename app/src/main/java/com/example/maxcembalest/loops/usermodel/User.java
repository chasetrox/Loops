package com.example.maxcembalest.loops.usermodel;

import com.orm.SugarRecord;

/**
 * Created by maxcembalest on 11/17/16.
 */

public class User extends SugarRecord{

    private String email;
    private String username;
    private String password;

    public User(){

    }
    public User(String email, String username, String password){
        this.email=email;
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
