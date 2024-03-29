package com.ispw.progetto.dao;

import com.ispw.progetto.exception.NotValidCouponException;
import com.ispw.progetto.model.Buono;
import com.ispw.progetto.pattern.factory.Factory;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
public class BuonoDAO implements GenericDAO <Buono> {
    private final Connectivity connection;
    public BuonoDAO() throws SQLException, IOException {
        connection =Connectivity.getSingletonInstance();

    }

    @Override
    public Buono execute(Object... params) throws SQLException, NotValidCouponException {
        String codice = (String) params[0];
        Factory factory=new Factory();
        Buono buono;
        try (CallableStatement cs = connection.conn.prepareCall("{call GetBuono(?,?)}")){

            cs.setString(1, codice);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeQuery();
            int n = cs.getInt(2);
            buono=factory.createBuono(codice,n);
            if (n == 0) {
                throw new NotValidCouponException("buono non trovato");
            }
        }
        catch (SQLException e){
            throw new SQLException("errore recupero buono"+e.getMessage());
        }

        return buono;
    }
}

