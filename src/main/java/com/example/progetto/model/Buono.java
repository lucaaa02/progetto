package com.example.progetto.model;

public class Buono {
    String codice;
    int cifra;
    public Buono(String codice, int cifra){
        this.cifra=cifra;
        this.codice=codice;
    }

    public String getCodice() {
        return codice;
    }

    public int getCifra() {
        return cifra;
    }
}
