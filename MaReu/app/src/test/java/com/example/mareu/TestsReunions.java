package com.example.mareu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.mareu.DI.DI;
import com.example.mareu.methods.Reunion;
import com.example.mareu.model.ReunionApiService;
import com.example.mareu.model.ReunionGenerator;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test of reunion service
 *
  */

@RunWith(JUnit4.class)
public class TestsReunions {

    private ReunionApiService mApiService;
    Reunion reunion;

    @Before
    public void setup(){
        mApiService= DI.getNewInstanceApiService();
    }

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions= mApiService.getReunions();
        List<Reunion> expectedReunions= ReunionGenerator.DummyReunion;
        assertThat(reunions, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedReunions.toArray()));

    }

    @Test
    public void deleteReunionWithSuccess(){
        Reunion reunionDelete=mApiService.getReunions().get(0);
        mApiService.deleteReunion(reunionDelete);
        assertFalse(mApiService.getReunions().contains(reunionDelete));
    }

    @Test
    public void createReunionWithSuccess(){
        List<Reunion> reunions= mApiService.getReunions();
        mApiService.createReunion(reunion);
        assertTrue(reunions.contains(reunion));
        ;
    }
}