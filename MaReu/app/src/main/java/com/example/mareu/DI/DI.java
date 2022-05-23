package com.example.mareu.DI;

import com.example.mareu.model.DummiReunionApiService;
import com.example.mareu.model.ReunionApiService;

public class DI {

    private static final ReunionApiService service = new DummiReunionApiService();

    public static ReunionApiService getService() {
        return service;
    }

    public static ReunionApiService getNewInstanceApiService() {
        return new DummiReunionApiService();
    }
}
