package com.example.mareu.model;

import com.example.mareu.methods.Reunion;

import java.util.List;

public interface ReunionApiService {


    //accés à la réunion
    List<Reunion> getReunions();

    //suppression de la réunion
    void deleteReunion(Reunion reunion);

    //création de la réunion
    void createReunion(Reunion reunion);

}
