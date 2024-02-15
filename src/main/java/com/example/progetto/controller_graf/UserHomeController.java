package com.example.progetto.controller_graf;
import com.example.progetto.Applicazione;
import com.example.progetto.bean.UserBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        ViewTripController page=new ViewTripController();
        page.viewTrip(main, currentUser);

    }
    @FXML
    private void myTrip() throws SQLException, IOException {
        FXMLLoader myTripLoader = new FXMLLoader(Applicazione.class.getResource("myTrip.fxml"));
        Parent mytriproot = myTripLoader.load();
        Scene myTripScene = new Scene(mytriproot);
        MyTripController myTripController = myTripLoader.getController();
        myTripController.setMain(main);
        myTripController.setUser(currentUser);
        myTripController.charge();
        Stage stage = main.getStage();
        stage.setScene(myTripScene);
        stage.setTitle("I miei viaggi");
        
    }
}
