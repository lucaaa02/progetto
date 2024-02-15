package com.example.progetto.controller_graf;
import com.example.progetto.Applicazione;
import com.example.progetto.bean.UserBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class UserHomeController {
    @FXML
    private Button user;
    private Applicazione main;
    private UserBean currentUser;

    public void setUser(UserBean utente){

        currentUser=utente;
    }
    public void setButtonText() {

        user.setText(currentUser.getUsername());
    }


    public void setMain(Applicazione main){

        this.main = main;
    }

    @FXML
    private void vaiAHome(){

        main.vaiAHome();
    }

    @FXML
    private void viewTrip() throws IOException, SQLException {
        PageTripController page=new PageTripController();
        page.viewTrip(main, currentUser);

    }
    @FXML
    private void myTrip(){}
    MyTripController myTrips=new MyTripController();
    myTrips.setMain(main);

}
