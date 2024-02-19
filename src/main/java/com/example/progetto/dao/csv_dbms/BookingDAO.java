package com.example.progetto.dao.csv_dbms;

import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.CSVInteractionException;
import com.example.progetto.model.UserTrip;

import java.io.IOException;
import java.sql.SQLException;

public interface BookingDAO {
    public void setTripBook(UserTrip book) throws SQLException, AlreadyPrenotedException, IOException;
    public void alreadyExist(UserTrip booking) throws SQLException, AlreadyPrenotedException, IOException, CSVInteractionException;
}