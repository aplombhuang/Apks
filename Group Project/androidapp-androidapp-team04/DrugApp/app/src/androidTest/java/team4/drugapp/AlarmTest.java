package team4.drugapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.R.attr.x;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringStartsWith.startsWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AlarmTest {

    @Rule
    public ActivityTestRule<AlarmsActivity> mActivityTestRule = new ActivityTestRule<>(AlarmsActivity.class);

    @Test   // Verify that the buttons in the Alarms Activity can be pressed.
    public void alarmButtonsClickable() {
        onView(withId(R.id.AddAlarmBtn)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.SettingsInAlarm_button)).check(ViewAssertions.matches(isClickable()));
    }

    @Test   // Test the add alarm button to make sure it can add an alarm
    public void addAlarmTest(){
        onView(withId(R.id.AddAlarmBtn)).perform(click());
        // Can only click on the SetAlarmAccpet_button if the activitiy_add_alarm_pop_up is viewable
        onView(withId(R.id.SetAlarmAccept_button)).check(ViewAssertions.matches(isClickable()));
    }

    @Test   // Create an alarm and verify that it has been created
            // User story 19 - Scenarios 1 and 2
    public void alarmCreatedTest(){
        onView(withId(R.id.AddAlarmBtn)).perform(click());
        /*  After clicking on the AddAlarm button, the add alarm pop up should be viewable
            Change the time in the AlarmTimePicker to verify that it changes as it should
            Click in the AlarmDescriptionTxt box and enter the name "Test Alarm" then click DONE on the keyboard
            Click accept
            Verify that the new alarm shows up in the Alarms listview.
         */
    }

    /* User Story 19 - Scenario 3, User Story 22 - Scenarios 1 and 2
        Create a test to verify that the alarm goes off at the proper time in the same way as above.
        Set alarm with vibrate to verify functionality
        Set alarm with only tone to verify functionality
        Set alarm with a reminder for each value 10, 20, or 30 minutes prior to verify each functions properly

        Verify that when the alarm sounds it can be stopped by clicking "Taking Medication"
        Verify that when the alarm sounds it can be stopped by clicking "Not Taking Medication"
            - This will also store that the medication has/has not been taken in the
                "History" activity (not implemented)
     */

    /* Alarms activity should be able to access the Current Drugs activity/list in order to identify
    which medications are needing to be taken at the specific alarm time.
     */

    /* Add 20 alarms to verify that the app can handle that many
        Each alarm needs to sound and display the proper medication/dosage to be taken at that time
        Each alarm should have a different medication or multiple medications in order to test that functionality
      */

    /*  User Story 22 - Scenario 3
    The calendar activity has not been implemented.  This would allow for setting the dates
    that prescriptions need to be refilled.  When the app can keep track of dates rather than only times
    the functionality of refill reminders can be implemented and tested
     */

    @Test   // Test first snooze alarm
            // NOT YET IMPLEMENTED
            // User Story 21, Scenario 123
    public void AlarmSnooze1Test() {
        /*
        Scenario
        a.Given the alarm is on, displaying information and producing sound and/or vibration
        b.When the user clicks on the remind me again button.
        c.Then The alarm will reset to the amount of time the user has selected in their remind me later option.

        The test will set an alarm for 1 minute beyond the current time.
        Wait 60 seconds
        Verify that the alarm is sounding and options are given to select that drug has been taken or
            to remind me again.
        Click "Remind me again" button and select 1 minute"
        Verify that the alarm is no longer sounding
        Wait 60 seconds.
        Verify that the alarm is sounding and there is an option to select that drug has been taken or
            to "remind me again".
        Click "Taken"
        Wait 60 seconds.
        Verify that the alarm does not sound again.
        */

    }
//
//    @Test   // Test 2nd snooze on alarm
//            // NOT YET IMPLEMENTED
//            // User Story 21, Scenario 2
//    public void AlarmSnooze2Test() {
///*
//
//        Scenario
//        a.Given the alarm sounded again
//        b.When i swipe to remind me later again
//        c.Then the alarm will display a popup message saying i should take the medication soon
//
//        The test will set an alarm for 1 minute beyond the current time.
//        Wait 60 seconds
//        Verify that the alarm is sounding and options are given to select that drug has been taken or
//            to remind me again.
//        Click "Remind me again" button and select 1 minute"
//        Verify that the alarm is no longer sounding
//        Wait 60 seconds.
//        Verify that the alarm sounds after the 1st snooze and there is an option to select that drug has been taken or
//            to "remind me again".
//        Click "Remind me again" button and select 1 minute"
//        Verify that the alarm is no longer sounding
//        Wait 60 seconds.
//        Verify that the alarm is sounding after the 2nd snooze and there is an option to select that drug has been taken or
//            to "remind me again".
//        Click "Taken"
//        Wait 60 seconds.
//        Verify that the alarm does not sound again.
//*/
//
//    }
//
//
//    @Test   // Test 3rd snooze on alarm
//    // NOT YET IMPLEMENTED
//    // User Story 21, Scenario 3
//    public void AlarmSnooze3Test() {
///*
//        Scenario
//        a.Given the alarm sounded for the third time
//        b.When i swipe to remind me later again
//        c.Then the alarm will display a popup message saying if i miss this dose, do not take twice the dose next time.
//
//
//        The test will set an alarm for 1 minute beyond the current time.
//        Wait 60 seconds
//        Verify that the alarm is sounding and options are given to select that drug has been taken or
//            to remind me again.
//        Click "Remind me again" button and select 1 minute"
//        Verify that the alarm is no longer sounding
//        Wait 60 seconds.
//        Verify that the alarm sounds after the 1st snooze and there is an option to select that drug has been taken or
//            to "remind me again".
//        Click "Remind me again" button and select 1 minute"
//        Verify that the alarm is no longer sounding
//        Wait 60 seconds.
//        Verify that the alarm is sounding after the 2nd snooze and there is an option to select that drug has been taken or
//            to "remind me again".
//                Click "Remind me again" button and select 1 minute"
//        Verify that the alarm is no longer sounding
//        Wait 60 seconds.
//        Verify that the alarm is sounding after the 3nd snooze and there is an option to select that drug has been taken.
//            There should NOT be a "remind me again" button, but a popup should display that says "Take dosage now.
//            Taking doses to close together may be hazardous to your health."
//        Click the "Confirm" button on the popup message.
//        Click "Taken"
//        Wait 60 seconds.
//        Verify that the alarm does not sound again.
//
//*/  }

    // Tried to commit/push this file but it didn't go.  Adding this line to re-commit file.
}
