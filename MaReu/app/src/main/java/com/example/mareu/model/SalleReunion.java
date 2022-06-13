package com.example.mareu.model;

import com.example.mareu.methods.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalleReunion {

    private String mSalleReunion;

    public static List<Room> DummySalleReunion = Arrays.asList(
            new Room("Peach", "F1ADF5"),
            new Room("Mario", "E2360B"),
            new Room("Luigi", "1AB84F"),
            new Room("Todd", "C03A16"),
            new Room("Warrio", "E2F030"),
            new Room("Link", "2F8E05"),
            new Room("Zelda", "28C7DA"),
            new Room("DonkyKong", "805B34"),
            new Room("Harmonie", "71F1EF"),
            new Room("Pikachu", "DDF03D"),
            new Room("Yoshi", "74E719")
    );

  /*  public SalleReunion(String salleReunion, String ffffff) {
        this.mSalleReunion=salleReunion;
    }*/

    static List<Room> generateSalle() {
        return new ArrayList<>(DummySalleReunion);
    }
}
