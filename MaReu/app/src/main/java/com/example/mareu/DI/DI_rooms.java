package com.example.mareu.DI;

import com.example.mareu.model.DummiSallesApiService;
import com.example.mareu.model.SalleApiService;

public class DI_rooms {

    private static final SalleApiService service = new DummiSallesApiService();

    public static SalleApiService getService() {
        return service;
    }


}
