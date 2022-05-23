package com.example.mareu.model;

import com.example.mareu.methods.Reunion;

import java.util.List;

public class DummiReunionApiService implements ReunionApiService {

    private final List<Reunion> reunions = ReunionGenerator.generateReunion();

    //accés à la réunion
    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }


    //suppression de la réunion
    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);

    }

    //création de la réunion
    @Override
    public void createReunion(Reunion reunion) {
        reunions.add(reunion);
    }
}
