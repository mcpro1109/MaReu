package com.example.mareu.methods;

import java.io.Serializable;

public class Reunion implements Serializable {
    private String mNomReunion;
    private String mDate;
    private String mHeure;
    private Room mRoom;
    private String mParticipants;


    public Reunion(String nomReunion, String date, String heure, Room room, String participants) {
        this.mNomReunion = nomReunion;
        this.mDate = date;
        this.mHeure = heure;
        this.mRoom = room;
        this.mParticipants = participants;
    }

    public String getNomReunion() {
        return mNomReunion;
    }

    public void setNomReunion(String nomReunion) {
        this.mNomReunion = nomReunion;
    }

    public String getDate() {
        return mDate;
    }

        public void setDate(String date) {
        mDate = date;
    }

    public String getHeure() {
        return mHeure;
    }

    public void setHeure(String heure) {
        this.mHeure = heure;
    }

    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room room) {
        this.mRoom = room;
    }

    public String getParticipants() {
        return mParticipants;
    }

    public void setParticipants(String participants) {
        this.mParticipants = participants;
    }
}
