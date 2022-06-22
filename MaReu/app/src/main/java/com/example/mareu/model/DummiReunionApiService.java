package com.example.mareu.model;

import static java.util.Calendar.DAY_OF_YEAR;

import android.util.Log;

import com.example.mareu.methods.Reunion;
import com.example.mareu.methods.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DummiReunionApiService implements ReunionApiService {


    private final List<Reunion> reunions = ReunionGenerator.generateReunion();
    private Room mRoom;

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

    //filtre par date
    @Override
    public List<Reunion> getReunionsFilteredByTime(Date date) {
        List<Reunion> reunionByDate = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
//        Log.d("date2", "date=" + cal1);
        for (int i = 0; i < reunions.size(); i++) {
            Calendar cal2 = Calendar.getInstance();
           //cal2.setTime(reunions.get(i).getDate());
            String date2=reunions.get(i).getDate();
            cal2.setTimeZone(TimeZone.getTimeZone(date2));
          //  Log.d("date3", "date=" + cal2);
            boolean sameDay = cal1.get(DAY_OF_YEAR) == cal2.get(DAY_OF_YEAR) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
            if (sameDay) reunionByDate.add(reunions.get(i));
          //  Log.d("date4", "date=" + sameDay);
        }

        return reunionByDate;

    }


    //filtre par salle
    @Override
    public List<Reunion> getReunionsFilterByPlace(String name) {
        List<Reunion> reunionByPlace = new ArrayList<>();
        for (Reunion reunion : reunions) {
            if (reunion.getRoom().equals(mRoom)) {
                Log.d("resultat", String.valueOf(mRoom));
                reunionByPlace.add(reunion);
            }
        }
       // Log.d("ajoutfiltre", String.valueOf(reunionByPlace));
        return reunionByPlace;


      /*  List<Reunion> reunionByPlace = new ArrayList<>();
        for (Reunion reunion : reunions) {
            Log.d("resultat", String.valueOf(reunion));
            if (reunion.getRoom().equals(name)) {
               // Log.d("resultat", String.valueOf(reunions));
                reunionByPlace.add(reunion);
            }
        }
        Log.d("ajoutfiltre", String.valueOf(reunionByPlace));
        return reunionByPlace;*/

    }

    @Override
    public Object getReunionByDate() {
        return null;
    }

    @Override
    public Object getReunionByDate(String date) {
      /*  for (Reunion reunion:
                reunions
        ) {
            if (reunion.getDate().equals(date)){
                return  reunion;
            }
        }*/

        return null;
    }


}
