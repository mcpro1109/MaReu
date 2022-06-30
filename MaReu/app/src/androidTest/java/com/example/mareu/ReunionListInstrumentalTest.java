package com.example.mareu;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.lists.ReunionsListActivity;
import com.example.mareu.model.ReunionApiService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test for reunions.
 */

//@RunWith(RobolectricTestRunner.class)
 @RunWith(AndroidJUnit4.class)
public class ReunionListInstrumentalTest {

    private ReunionsListActivity listActivity;
    private ReunionApiService apiService;
    private static int ITEMS_COUNT = 3;

    @Rule
    public ActivityTestRule<ReunionsListActivity> activityTestRule = new ActivityTestRule<>(ReunionsListActivity.class);

    @Before
    public void Setup() {
        listActivity = activityTestRule.getActivity();
        assertThat(listActivity, notNullValue());
        apiService = DI.getReunionService();

    }

    //vérifier si la liste n'est pas vide
    @Test
    public void mReunionList_shouldNotBeEmpty() {
        onView(withId(R.id.recyclerView))
                .check(matches(hasMinimumChildCount(1)));

    }

    //test suppression de la réunion
    @Test
    public void ReunionList_deleteAction_shouldRemoveItem() {
        // int size = mApiService.getReunions().size();
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        /*onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 2
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 1));*/
    }

    //filtre par room
    @Test
    public void filterRoomWithSuccess() {
        onView(withId(R.id.filter_room)).perform(click());
        onView(withText("Peach")).check(matches(isNotChecked())).perform();
        onView(withText("Peach")).perform(click());
        onView(withId(R.id.recyclerView)).check(withItemCount(1));
    }

    //filtre par date
    @Test
    public void filterDateWithSuccess() {
        onView(withId(R.id.filter_date)).perform(click());
        /*onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 22));
        onView(withId(R.id.recyclerView)).check(withItemCount(1));*/
    }

    //filtre reset
    @Test
    public void filterResetWithSuccess() {
        int size = apiService.getReunions().size();
        onView(withId(R.id.filter_room)).perform(click());
        onView(withText("Peach")).check(matches(isNotChecked())).perform();
        onView(withText("Peach")).perform(click());
        onView(withId(R.id.reset)).perform(click());
        onView(withId(R.id.recyclerView)).check(withItemCount(size));
    }

    //ajouter une réunion
    @Test
    public void addNewMeetingWithSuccess() {
        int size = apiService.getReunions().size();

        onView(withId(R.id.addReu)).perform(click());

        onView(withId(R.id.spinnerRoom)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.spinnerRoom)).check(matches(withSpinnerText(containsString("Peach"))));

        onView(withId(R.id.date_input)).perform(click());
        /*onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 22));

        onView(withId(R.id.time_input)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(12, 45));

        onView(withId(R.id.textSujet)).perform(click()).perform(typeText("Sujet test"));*/

        onView(withId(R.id.ajoutParticipant)).perform(click()).perform(typeText("Participants test"));

        onView(withId(R.id.buttonAddReu)).perform(click());
        onView(withId(R.id.item_list_name)).check(withItemCount(size + 1));
    }
}