package com.example.dariusm.egzaminas.Model;

/**
 * Created by DariusM on 19/06/2018.
 */

public class Klientas {
    private int id;
    private String vardas;
    private int metai;
    private int telefon;
    private String tipas;

    public Klientas(int id, String vardas, int metai, int telefon, String tipas) {
        this.id = id;
        this.vardas = vardas;
        this.metai = metai;
        this.telefon = telefon;
        this.tipas = tipas;


    }

    public Klientas() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public int getMetai() {
        return metai;
    }

    public void setMetai(int metai) {
        this.metai = metai;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getTipas() {
        return tipas;
    }

    public void setTipas(String tipas) {
        this.tipas = tipas;
    }

    @Override
    public String toString() {
        return "Klientas{" +
                "id=" + id +
                ", vardas='" + vardas + '\'' +
                ", metai=" + metai +
                ", telefon=" + telefon +
                ", tipas='" + tipas + '\'' +
                '}';
    }
}
