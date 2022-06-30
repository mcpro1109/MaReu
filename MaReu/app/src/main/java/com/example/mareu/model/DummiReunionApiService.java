package com.example.mareu.model;

import com.example.mareu.methods.Reunion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummiReunionApiService implements ReunionApiService {

    private final List<Reunion> reunions = ReunionGenerator.generateReunion();

    //accés à la réunion
    @Override
    public List<Reunion> getReunions() {
        return new ArrayList<>(reunions);
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

    //filtre par date
    @Override
    public List<Reunion> getReunionsFilteredByTime(Date date) {
        List<Reunion> reunionByDate = new ArrayList<>();
        for (int i = 0; i < reunions.size(); i++) {
            Date date2 = null;
            try {
                date2 = new SimpleDateFormat("dd/MM/yyyy").parse(reunions.get(i).getDateReunion());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date.before(date2)) {
                reunionByDate.add(reunions.get(i));
            }
        }
        return reunionByDate;
    }

    //filtre par salle
    @Override
    public List<Reunion> getReunionsFilteredByRoom(String name) {
        List<Reunion> reunionByRoom = new ArrayList<>();
        for (Reunion reunion : reunions) {
            if (reunion.getRoom().getName().equalsIgnoreCase(name)) {
                reunionByRoom.add(reunion);
            }
        }
        return reunionByRoom;
    }
}
