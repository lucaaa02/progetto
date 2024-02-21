package com.example.progetto.pattern.factory;

import com.example.progetto.model.Agency;
import com.example.progetto.model.Buono;
import com.example.progetto.model.User;


public class Factory implements  FactoryInterface{


    @Override
    public EntityFactory CreateEntity(int type) {
        return switch (type) {
            case 1 -> new User();
            case 2 -> new Agency();
            default -> {
                try {
                    throw new Exception("Invalid type : " + type);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @Override
    public Buono CreateBuono(String codice, int valore) {
        return new Buono(codice,valore);
    }

}
