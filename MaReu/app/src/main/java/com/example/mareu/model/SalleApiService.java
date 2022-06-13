package com.example.mareu.model;

import com.example.mareu.methods.Room;

import java.util.List;

public interface SalleApiService {


    //accés à la salle
    List<Room> getSalles();

    //suppression de la salle
    void deleteSalle(SalleReunion salleReunion);

    //création de la salle
    void createReunion(SalleReunion salleReunion);


}
