package com.example.mareu.model;

import com.example.mareu.methods.Room;

import java.util.List;

public class DummyRoomApiService implements RoomApiService {


    private final List<Room> mRooms = RoomsGenerator.rooms;


    @Override
    public List<Room> getRooms() {
        return mRooms;
    }

    @Override
    public Room getRoomByName(String roomName) {
       //retourner la room
        for (Room room:
                mRooms
             ) {
            if (room.getName().equals(roomName)){
                return  room;
            }
        }

        return null;
    }

}
