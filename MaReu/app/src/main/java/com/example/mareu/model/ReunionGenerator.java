package com.example.mareu.model;

import com.example.mareu.methods.Reunion;
import com.example.mareu.methods.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReunionGenerator {
private static Room peach=new Room("Peach", "F1ADF5");
private static Room mario=new Room("Mario", "E2360B");
private static Room luigi=new Room("Luigi", "1AB84F");
private static Room todd=new Room("Todd", "C03A16");
private static Room warrio=new Room("Warrio", "E2F030");
private static Room link=new Room("Link", "2F8E05");
private static Room zelda=new Room("Zelda", "28C7DA");
private static Room donkykong=new Room("DonkyKong", "805B34");
private static Room harmonie=new Room("Harmonie", "71F1EF");
private static Room pikachu=new Room("Pikachu", "DDF03D");
private static Room yochi=new Room("Yoshi", "74E719");
    public static List<Reunion> DummyReunion = Arrays.asList(
            new Reunion("Réunion A", "06/07/2022"," 14h00", peach, "maxime@lamzone.com, alex@lamzone.com, sylvia@lamzone.com, louis@lamzone.com"),
            new Reunion("Réunion B", "18/08/2022"," 16h00", mario, "paul@lamzone.com, viviane@lamzone.com, tom@lamzone.com"),
            new Reunion("Réunion C", "16/09/2022"," 10h00", luigi, "amandine@lamzone.com, luc@lamzone.com, sylvia@lamzone.com, louis@lamzone.com")
    );

    static List<Reunion> generateReunion(){return new ArrayList<>(DummyReunion);}

}
