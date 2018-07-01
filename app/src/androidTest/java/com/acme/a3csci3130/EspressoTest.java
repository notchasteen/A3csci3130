package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.clearText;
import static org.junit.Assert.*;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static org.hamcrest.CoreMatchers.anything;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     *
     * @throws Exception
     */
    /*@Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }*/

    /**
     * Test to create a business
     *
     */
    @Test
    public void CreateTest() {
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.businessNum)).perform(typeText("123456789"));
        onView(withId(R.id.businessName)).perform(typeText("Redondo's"));
        onView(withId(R.id.primaryBusiness)).perform(typeText("Distributor"));
        onView(withId(R.id.businessAddress)).perform(typeText("101 Candor Street"));
        onView(withId(R.id.province)).perform(typeText("QC"));
        onView(withId(R.id.submitButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));
    }

    /**
     * Checks if the firebase read the data,
     * checks if the data on firebase matches the user entry
     */
    @Test
   public void ReadTest() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.businessNum)).check(matches(withText("123456789")));
        onView(withId(R.id.businessName)).check(matches(withText("Redondo's")));
        onView(withId(R.id.primaryBusiness)).check(matches(withText("Distributor")));
        onView(withId(R.id.businessAddress)).check(matches(withText("101 Candor Street")));
        onView(withId(R.id.province)).check(matches(withText("QC")));
    }
    /**
     * For testing the update functionality
     */
    @Test
    public void UpdateTest() {

        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.businessNum)).perform(typeText("987654321"));
        onView(withId(R.id.businessName)).perform(typeText("Leigh's"));
        onView(withId(R.id.primaryBusiness)).perform(typeText("Fisher"));
        onView(withId(R.id.businessAddress)).perform(typeText("203 Barrington Street"));
        onView(withId(R.id.province)).perform(typeText("NS"));

        onView(withId(R.id.submitButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.businessNum)).perform(clearText(), typeText("012345678"));
        onView(withId(R.id.businessName)).perform(clearText(),typeText("Fermin's"));
        onView(withId(R.id.primaryBusiness)).perform(clearText(), typeText("Processor"));
        onView(withId(R.id.businessAddress)).perform(clearText(), typeText("105 Compton Street"));
        onView(withId(R.id.province)).perform(clearText(), typeText("NB"));
        onView(withId(R.id.updateButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.businessNum)).check(matches(withText("012345678")));
        onView(withId(R.id.businessName)).check(matches(withText("Fermin's")));
        onView(withId(R.id.primaryBusiness)).check(matches(withText("Processor")));
        onView(withId(R.id.businessAddress)).check(matches(withText("105 Compton Street")));
        onView(withId(R.id.province)).check(matches(withText("NB")));
    }
    /**
     * For testing the delete functionality
     */
    @Test
    public void DeleteTest() {

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.deleteButton)).perform(click());
    }
}
