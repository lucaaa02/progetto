package com.example.progetto.dao;

import com.example.progetto.entity.Agency;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AgencyDAO implements GenericDAO <Agency> {
    private final Connectivity connection;
    public AgencyDAO() throws SQLException, IOException {
        connection = Connectivity.getSingletonInstance();

    }

    @Override
    public Agency execute(Object... params) throws SQLException {
        String username = (String) params[0];
        Agency utente = new Agency();
        try {
            try (CallableStatement cs = connection.conn.prepareCall("{call GetPasswordAgenzia(?,?)}")) {
                cs.setString(1, username);
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.executeQuery();
                utente.setPassword(cs.getString(2));
                utente.setUser((String) params[0]);
            }
        } finally {
            // Chiudi la connessione
            if (connection.conn != null) {
                    connection.conn.close();

            }
        }
        return utente;
    }

}
