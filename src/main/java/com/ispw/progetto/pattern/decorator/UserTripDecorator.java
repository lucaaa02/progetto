package com.ispw.progetto.pattern.decorator;

public abstract class UserTripDecorator implements  UserTripInterface{

    protected UserTripInterface userTrip;
    protected UserTripDecorator(UserTripInterface userTrip){
        this.userTrip=userTrip;
    }
    @Override
    public void setUsername(String username){
        userTrip.setUsername(username);
    }
    @Override
    public String getUsername(){
        return userTrip.getUsername();
    }
}
