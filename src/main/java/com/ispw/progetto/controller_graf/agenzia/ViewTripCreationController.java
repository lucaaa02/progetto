package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.Applicazione;
import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.CreateTripController;
import com.ispw.progetto.exception.DateNotValidException;
import com.ispw.progetto.exception.EmptystatementException;
import com.ispw.progetto.exception.SQLStatementException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewTripCreationController {


    private Applicazione main;
    private String imagePath;
    private byte[] imageBytes;
    private AgencyBean currentUser;
    @FXML
    private Button agency;
    @FXML
    private ImageView imageViewer;
    @FXML
    private TextField nomecitta;
    @FXML
    private TextField disponibili;
    @FXML
    private DatePicker dataAnd;
    @FXML
    private DatePicker dataRit;
    @FXML
    private TextField prezzo;


    public void setMain(Applicazione main) {

        this.main = main;
    }

    public void setUser(AgencyBean utente){

        currentUser=utente;
    }
    public void setButtonText() {

        agency.setText(currentUser.getUsername());
    }

    @FXML
    public void vaiAHome(){

        main.vaiAHome();
    }

    @FXML
    private void vaiAAgencyHome() throws IOException {
        FXMLLoader agencyhomeloader = new FXMLLoader(Applicazione.class.getResource("view1/agenzia/agency_home.fxml"));
        Parent agencyhomeroot = agencyhomeloader.load();
        Scene tripcreationscene = new Scene(agencyhomeroot);
        AgencyHomeController agencyhome = agencyhomeloader.getController();
        agencyhome.setMain(main);
        agencyhome.setUser(currentUser);
        Stage stage = main.getStage();
        stage.setScene(tripcreationscene);
        agencyhome.setButtonText();
        stage.setTitle("Home Agenzia");
    }

    @FXML
    private void insertImage() throws IOException {
        FileChooser imageUploader = new FileChooser();
        imageUploader.setTitle("Seleziona l'immagine");
        imageUploader.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("File immagine","*.png","*.jpg","*.jpeg","*.gif"));
        File selectedFile = imageUploader.showOpenDialog(new Stage());
        if(selectedFile!=null){

            imagePath = selectedFile.getAbsolutePath();
            Image image = new Image(selectedFile.toURI().toString());
            imageBytes = imageToBytes(image);
            Image recreatedImage = bytesToImage(imageBytes);
            imageViewer.setImage(recreatedImage);
        }

    }

    public byte[] imageToBytes(Image image) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image,null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"png",outputStream);

        return outputStream.toByteArray();
    }

    private Image bytesToImage(byte[] bytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return new Image(inputStream);
    }

    @FXML
    private void submit() throws SQLStatementException {
        try{
            String city = nomecitta.getText().trim();
            if (city.isEmpty() || imagePath.isEmpty()){
                throw new EmptystatementException("Riempire tutti i campi");
            }
            int available = Integer.parseInt(disponibili.getText());

            LocalDate checkAndata = dataAnd.getValue();
            LocalDate checkRitorno = dataRit.getValue();

            if (checkAndata.isBefore(LocalDate.now())){
                dataAnd.setValue(null);
                throw new DateNotValidException("Valore di andata non valido");
            }
            else if (checkRitorno.isBefore(checkAndata)){
                dataRit.setValue(null);
                throw new DateNotValidException("Valore di ritorno non valido");
            }
            Date andata = Date.valueOf(checkAndata);
            Date ritorno = Date.valueOf(checkRitorno);
            float price = Float.parseFloat(prezzo.getText());
            TripBean trip = new TripBean(city,available,andata,ritorno,price,imageBytes);
            CreateTripController creation = new CreateTripController(trip);
            creation.uploadTrip();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creazione riuscita");
            alert.setHeaderText(null);
            alert.setContentText("Viaggio creato con successo");
            alert.showAndWait();
        } catch (DateNotValidException|EmptystatementException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creazione riuscita");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (SQLException | IOException e) {
            throw new SQLStatementException("Statement non eseguito");
        }


    }
}
