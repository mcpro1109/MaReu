package com.example.mareu;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.lists.ReunionsListActivity;
import com.example.mareu.model.ReunionApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
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

    //vérifier si la liste n'est pas vide ok
    @Test
    public void ReunionList_shouldNotBeEmpty() {
        onView(withId(R.id.recyclerView))
                .check(matches(hasMinimumChildCount(1)));

    }

    //test suppression de la réunion ok
    @Test
    public void ReunionList_deleteAction_shouldRemoveItem() {
        // int size = mApiService.getReunions().size();
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(2));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 2
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(2));


    }

    //filtre par room ok
    @Test
    public void filterRoomWithSuccess() {
        ViewInteraction overflowMenuButton3 = onView(allOf(withContentDescription("More options"),
                childAtPosition(childAtPosition(withId(R.id.toolBar), 1), 0), isDisplayed()));
        overflowMenuButton3.perform(click());
        ViewInteraction materialTextView3 = onView(
                allOf(withId(androidx.appcompat.R.id.title), withText("filtrer par salle"),
                        childAtPosition(childAtPosition(withId(androidx.appcompat.R.id.content), 0), 0), isDisplayed()));
        materialTextView3.perform(click());
        ViewInteraction recyclerView = onView(allOf(withId(R.id.filtreRoom),
                childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")), 0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

    //filtre par date ok
    @Test
    public void filterDateWithSuccess() {
        ViewInteraction overflowMenuButton = onView(
                Matchers.allOf(withContentDescription("More options"),
                        childAtPosition(childAtPosition(withId(R.id.toolBar), 1), 0), isDisplayed()));
        overflowMenuButton.perform(click());
        ViewInteraction materialTextView = onView(Matchers.allOf(withId(androidx.appcompat.R.id.title), withText("filtrer par date"),
                childAtPosition(childAtPosition(withId(androidx.appcompat.R.id.content), 0), 0), isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction appCompatImageButton = onView(
                Matchers.allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Next month"),
                        childAtPosition(
                                Matchers.allOf(withClassName(is("android.widget.DayPickerView")),
                                        childAtPosition(
                                                withClassName(is("com.android.internal.widget.DialogViewAnimator")), 0)), 2)));
        appCompatImageButton.perform(scrollTo(), click());
        ViewInteraction materialButton = onView(Matchers.allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3)));
        materialButton.perform(scrollTo(), click());
    }

    //filtre reset ok
    @Test
    public void filterResetWithSuccess() {
        ViewInteraction overflowMenuButton2 = onView(allOf(withContentDescription("More options"),
                childAtPosition(childAtPosition(withId(R.id.toolBar), 1), 0), isDisplayed()));
        overflowMenuButton2.perform(click());
        ViewInteraction materialTextView2 = onView(
                allOf(withId(androidx.appcompat.R.id.title), withText("reset"),
                        childAtPosition(childAtPosition(withId(androidx.appcompat.R.id.content), 0), 0), isDisplayed()));
        materialTextView2.perform(click());

    }

    //ajouter une réunion
    @Test
    public void addNewMeetingWithSuccess() {
        ViewInteraction floatingActionButton = onView(allOf(withId(R.id.addReu),
                childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
        floatingActionButton.perform(click());
        ViewInteraction appCompatSpinner = onView(allOf(withId(R.id.spinnerRoom),
                childAtPosition(childAtPosition(withId(R.id.scroll), 0), 2),
                isDisplayed()));
        appCompatSpinner.perform(click());
        DataInteraction appCompatCheckedTextView = onData(Matchers.anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());
        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.date_input),
                childAtPosition(childAtPosition(withId(R.id.scroll), 0), 4), isDisplayed()));
        appCompatEditText.perform(click());
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Next month"),
                        childAtPosition(allOf(withClassName(is("android.widget.DayPickerView")),
                                        childAtPosition(withClassName(is("com.android.internal.widget.DialogViewAnimator")),0)), 2)));
       // appCompatImageButton2.perform(click());
        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.time_input),
                childAtPosition(childAtPosition(withId(R.id.scroll), 0), 5), isDisplayed()));
      //  appCompatEditText2.perform(click());
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.textSujet), childAtPosition(
                        childAtPosition(withId(R.id.scroll), 0), 7), isDisplayed()));
        textInputEditText.perform(replaceText("sujet"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(allOf(withId(R.id.ajoutParticipant),
                childAtPosition(childAtPosition(withId(R.id.scroll), 0), 9), isDisplayed()));
        textInputEditText2.perform(replaceText("participants"), closeSoftKeyboard());

        onView((withId(R.id.buttonAddReu))).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(3));
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
