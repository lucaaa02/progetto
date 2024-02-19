package com.example.progetto.bean;

import com.example.progetto.pattern.Factory.BeanFactory;

public class AgencyBean implements BeanFactory {
    private String username;
    private String password;
    @Override
    public BeanFactory createLoginBean() {
        return this;
    }
    @Override
    public String getUsername(){

        return username;
    }
    @Override
    public String getPassword(){

        return password;
    }
    @Override

    public void setUsername(String username) {
        this.username = username;
    }
    @Override

    public void setPassword(String password) {
        this.password = password;
    }
}
