package com.example.mareu.model;

import com.example.mareu.methods.Reunion;

import java.util.ArrayList;
import java.util.Calendar;
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
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        for (int i = 0; i < reunions.size(); i++) {
            Calendar cal2 = Calendar.getInstance();
           // Log.d("cal1", "cal1: " + cal1);
            Date date2= new Date(reunions.get(i).getDateReunion());
           // date du jour cal2.setTimeZone(TimeZone.getTimeZone(date2));
            cal2.setTime(date2);
         //   Log.d("cal2", "cal2: " + cal2);
           /* boolean sameDay = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
            if (sameDay) reunionByDate.add(reunions.get(i));
           Log.d("boll", "getReunionsFilteredByTime: " + sameDay);*/
            if (cal1.before(cal2)){
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
