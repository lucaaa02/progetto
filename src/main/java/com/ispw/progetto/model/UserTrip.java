package com.ispw.progetto.model;

import com.ispw.progetto.pattern.decorator.UserTripDecorator;
import com.ispw.progetto.pattern.decorator.UserTripInterface;

public class UserTrip extends UserTripDecorator {
    private int idTrip;

    public UserTrip(UserTripInterface userTrip,int id) {
        super(userTrip);
        this.idTrip=id;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;

    }

    public int getIdTrip() {
        return idTrip;
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }
}