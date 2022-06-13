package com.example.mareu.methods;

import java.io.Serializable;

public class Room implements Serializable {

    private  String name;
    private String colorHex;

    public Room(String name, String colorHex) {

        this.name=name;
        this.colorHex=colorHex;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }
}
