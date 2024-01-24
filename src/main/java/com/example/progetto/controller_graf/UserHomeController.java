package com.example.progetto.controller_graf;
import com.example.progetto.Applicazione;
import com.example.progetto.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class UserHomeController {
    @FXML
    private Button user;
    private Applicazione main;
    private User currentUser;

    public void setUser(User utente){
        currentUser=utente;
    }
    public void setButtonText() {
        user.setText(currentUser.getUsername());
    }


    public void setMain(Applicazione main){
        this.main = main;
    }

}