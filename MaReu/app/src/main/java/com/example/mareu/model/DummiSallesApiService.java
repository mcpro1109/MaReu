package com.example.mareu.model;

import com.example.mareu.methods.Room;

import java.util.List;

public class DummiSallesApiService implements SalleApiService {


    private final List<Room> salles = SalleReunion.generateSalle();
    private String salle;




    @Override
    public List<Room> getSalles() {
        return salles;
    }

    @Override
    public void deleteSalle(SalleReunion salleReunion) {

    }

    @Override
    public void createReunion(SalleReunion salleReunion) {

    }


}
