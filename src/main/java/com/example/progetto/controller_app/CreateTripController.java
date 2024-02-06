package com.example.progetto.controller_app;

import com.example.progetto.dao.TripDAO;
import com.example.progetto.bean.TripBean;
import com.example.progetto.entity.Trip;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class CreateTripController {

    private final TripBean tripbean;
    private final Trip new_trip = new Trip();

    public CreateTripController(TripBean trip){
        this.tripbean=trip;

        new_trip.setCity(tripbean.getCity());
        new_trip.setAvailable(trip.getAvailable());
        new_trip.setData_and(tripbean.getData_and());
        new_trip.setData_rit(tripbean.getData_rit());
        new_trip.setPrice(trip.getPrice());
        new_trip.setImage(trip.getImage());
    }

    public void upload_trip(){
        TripDAO dao = new TripDAO();

        try {
            dao.add_trip(new_trip.getCity(),new_trip.getAvailable(),new_trip.getData_and(),new_trip.getData_rit(),new_trip.getPrice(), new_trip.getImage());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creazione fallita");
            alert.setHeaderText(null);
            alert.setContentText("Impossibile aggiungere il nuovo itinerario al DB");
            alert.showAndWait();
        }
        System.out.println("Si spera funzioni");

    }
}
