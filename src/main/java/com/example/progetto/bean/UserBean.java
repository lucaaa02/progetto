package com.example.progetto.bean;
public class UserBean {
    private final String username;
    private final String password;
    private boolean token;

    public UserBean(String username, String password){
        this.username=username;
        this.password=password;
        token=false;
    }
    public String getUsername(){

        return username;
    }
    public String getPassword(){

        return password;
    }
    public void setToken(){
        token=true;
    }
    public boolean getToken(){
        return token;
    }


}

