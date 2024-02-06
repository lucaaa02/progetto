package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.UserBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.progetto.controller_app.LogiinController;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameUtente;

    @FXML
    private PasswordField passwordUtente;

    @FXML
    public TextField usernameAgenzia;

    @FXML
    public PasswordField passwordAgenzia;

    private Applicazione main;


    @FXML
    private void vai_a_Home(){

        main.vaiAHome();
    }

    public void setMain(Applicazione main){

        this.main = main;

    }

    @FXML
    private void HandlerLoginUtente() throws IOException, SQLException {
        String user_utente=usernameUtente.getText();
        String pass_utente=passwordUtente.getText();
        UserBean user = new UserBean(user_utente,pass_utente);
        LogiinController login=new LogiinController(user);
        login.loginUtente();
        if(user.getToken()){
            FXMLLoader UserHomeLoader = new FXMLLoader(Applicazione.class.getResource("home_login.fxml"));
            Parent UserHomeRoot = UserHomeLoader.load();
            Scene homeloginScene = new Scene(UserHomeRoot);
            UserHomeController userhome = UserHomeLoader.getController();
            userhome.setMain(main);
            userhome.setUser(user);
            Stage stage = main.getStage();
            stage.setScene(homeloginScene);
            userhome.setButtonText();
            stage.setTitle("Accedi");
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login fallito");
            alert.setHeaderText(null);
            alert.setContentText("Username o password errati");
            alert.showAndWait();
        }


    }

    @FXML
    private void HandlerLoginAgenzia() throws IOException, SQLException {

        String user_Agenzia = usernameAgenzia.getText();
        String pass_Agenzia = passwordAgenzia.getText();
        AgencyBean agency = new AgencyBean(user_Agenzia,pass_Agenzia);
        LogiinController login = new LogiinController(agency);
        login.loginAgenzia();
        if(agency.getToken()){
            FXMLLoader AgencyHomeLoader = new FXMLLoader(Applicazione.class.getResource("agency_home.fxml"));
            Parent AgencyHomeRoot = AgencyHomeLoader.load();
            Scene homeloginScene = new Scene(AgencyHomeRoot);
            AgencyHomeController agencyhome = AgencyHomeLoader.getController();
            agencyhome.setMain(main);
            agencyhome.setUser(agency);
            Stage stage = main.getStage();
            stage.setScene(homeloginScene);
            agencyhome.setButtonText();
            stage.setTitle("Home Agenzia");
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login fallito");
            alert.setHeaderText(null);
            alert.setContentText("Username o password errati");
            alert.showAndWait();
        }

    }
}
