package com.example.mareu.model;

import com.example.mareu.methods.Room;

import java.util.List;

public interface RoomApiService {


    //accés à la salle
    List<Room> getRooms();


    Room getRoomByName(String roomName);

}
