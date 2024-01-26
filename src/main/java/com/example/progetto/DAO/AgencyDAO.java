package com.example.progetto.DAO;

import com.example.progetto.entity.Agency;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AgencyDAO implements GenericDAO <Agency> {
    private final Connectivity connection;
    public AgencyDAO(){
        connection =Connectivity.getSingletonInstance();

    }

    @Override
    public Agency execute(Object... params) throws SQLException {
        String username = (String) params[0];
        Agency utente=new Agency();
        connection.connected();
        CallableStatement cs = connection.conn.prepareCall("{call GetPasswordAgenzia(?,?)}");
        cs.setString(1,username);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.executeQuery();
        utente.setPassword(cs.getString(2));
        utente.setUser((String) params[0]);


        return utente;
    }
}