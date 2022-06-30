package com.example.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.mareu.methods.Reunion;
import com.example.mareu.methods.Room;
import com.example.mareu.model.ReunionApiService;
import com.example.mareu.model.ReunionGenerator;
import com.example.mareu.model.RoomApiService;
import com.example.mareu.model.RoomsGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Unit test of reunion service
 */

@RunWith(JUnit4.class)
public class TestsReunions {

    private ReunionApiService mApiService = DI.getReunionService();
    private RoomApiService mRoomApiService = DI.getRoomService();
    Reunion reunion;
    private String roomName;

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions = mApiService.getReunions();
        List<Reunion> expectedReunions = ReunionGenerator.DummyReunion;
        assertTrue(expectedReunions.containsAll(reunions));
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionDelete = mApiService.getReunions().get(0);
        mApiService.deleteReunion(reunionDelete);
        assertFalse(mApiService.getReunions().contains(reunionDelete));
    }

    @Test
    public void createReunionWithSuccess() {
        List<Reunion> reunions = mApiService.getReunions();
        mApiService.createReunion(reunion);
        assertFalse(reunions.contains(reunion));
    }

    @Test
    public void getRoomsWithSuccess() {
        List<Room> rooms = mRoomApiService.getRooms();
        List<Room> expectedRooms = RoomsGenerator.rooms;
        assertTrue(expectedRooms.containsAll(rooms));
    }

    @Test
    public void filterReunionByRoomWithSuccess() {
        List<Reunion> reunions = mApiService.getReunionsFilteredByRoom("Peach");
        for (Reunion reunion : reunions) {
            assertEquals(reunion.getRoom().getName(), "Peach");
        }
    }

    @Test
    public void filterReunionByDateWithSuccess() {
        int year = 2022;
        int month = 8;
        int dayOfMonth = 2;
        Integer date = 12 / 07 / 2022;

        List<Reunion> reunionList = mApiService.getReunionsFilteredByTime(new Date(date));
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        calendar2.set(year, month, dayOfMonth+1);
        Date date1 = calendar.getTime();
        Date date2 = calendar2.getTime();
        for (Reunion reunion : reunionList) {
            assertTrue(date1.before(date2));

        }
    }

}