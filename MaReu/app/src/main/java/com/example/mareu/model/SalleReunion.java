package com.example.mareu.model;

import java.util.Arrays;
import java.util.List;

public class SalleReunion {
    private String mSalleReunion;

    public static List<SalleReunion> DummySalleReunion = Arrays.asList(
            new SalleReunion("Peach"),
            new SalleReunion("Mario"),
            new SalleReunion("Luigi"),
            new SalleReunion("Todd"),
            new SalleReunion("Warrio"),
            new SalleReunion("Link"),
            new SalleReunion("Zelda"),
            new SalleReunion("Donkeykong"),
            new SalleReunion("Harmonie"),
            new SalleReunion("Pikachu"),
            new SalleReunion("Yoshi")
    );

    public SalleReunion(String salleReunion) {
        this.mSalleReunion=salleReunion;
    }
}
