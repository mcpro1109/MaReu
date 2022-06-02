package com.example.mareu;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.mareu.lists.ReunionsListActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test for reunions.
 *
 */
@RunWith(AndroidJUnit4.class)
public class ReunionListTest {

    private ReunionsListActivity mListActivity;


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }
}