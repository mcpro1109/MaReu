package com.example.mareu.methods;

import java.io.Serializable;

public class Reunion implements Serializable {
    private  String mNomReunion;
    private String mHeure;
    private String mSalle;
    private String mParticipants;

    public Reunion(String nomReunion, String heure, String salle, String participants) {
        this.mNomReunion =nomReunion;
        this.mHeure =heure;
        this.mSalle =salle;
        this.mParticipants =participants;


    }

    public  String getNomReunion() {
        return mNomReunion;
    }

    public void setNomReunion(String nomReunion) {
        this.mNomReunion = nomReunion;
    }

    public String getHeure() {
        return mHeure;
    }

    public void setHeure(String heure) {
        this.mHeure = heure;
    }

    public String getSalle() {
        return mSalle;
    }

    public void setSalle(String salle) {
        this.mSalle = salle;
    }

    public String getParticipants() {
        return mParticipants;
    }

    public void setParticipants(String participants) {
        this.mParticipants = participants;
    }
}
