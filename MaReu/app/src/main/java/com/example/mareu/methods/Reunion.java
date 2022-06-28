package com.example.mareu.methods;

import java.io.Serializable;

public class Reunion implements Serializable {
    private String nomReunion;
    private String dateReunion;
    private String heure;
    private Room room;
    private String participants;


    public Reunion(String nomReunion, String date, String heure, Room room, String participants) {
        this.nomReunion = nomReunion;
        this.dateReunion = date;
        this.heure = heure;
        this.room = room;
        this.participants = participants;
    }

    public String getNomReunion() {
        return nomReunion;
    }

    public void setNomReunion(String nomReunion) {
        this.nomReunion = nomReunion;
    }

    public String getDateReunion() {
        return dateReunion;
    }

        public void setDateReunion(String dateReunion) {
        this.dateReunion = dateReunion;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}
