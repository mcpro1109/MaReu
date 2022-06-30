package com.example.mareu;

import com.example.mareu.model.DummiReunionApiService;
import com.example.mareu.model.DummyRoomApiService;
import com.example.mareu.model.ReunionApiService;
import com.example.mareu.model.RoomApiService;

public class DI {

    private static final ReunionApiService service = new DummiReunionApiService();

    public static ReunionApiService getReunionService() {
        return service;
    }

    private static final RoomApiService sDummyRoomApiService = new DummyRoomApiService();

    public static RoomApiService getRoomService() {
        return sDummyRoomApiService;
    }
}
