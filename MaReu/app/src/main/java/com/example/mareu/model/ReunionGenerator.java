package com.example.mareu.model;

import com.example.mareu.methods.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReunionGenerator {

    public static List<Reunion> DummyReunion = Arrays.asList(
            new Reunion("Réunion A", "14h00", "Peach", "maxime@lamzone.com, alex@lamzone.com, sylvia@lamzone.com, louis@lamzone.com"),
            new Reunion("Réunion B", "16h00", "Mario", "paul@lamzone.com, viviane@lamzone.com, tom@lamzone.com"),
            new Reunion("Réunion C", "10h00", "Luigi", "amandine@lamzone.com, luc@lamzone.com, sylvia@lamzone.com, louis@lamzone.com")
    );

    static List<Reunion> generateReunion(){return new ArrayList<>(DummyReunion);}

}
