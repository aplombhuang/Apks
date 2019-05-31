package team4.drugapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DrugDictionaryTest {

    @Rule
    public ActivityTestRule<DrugDictionaryActivity> mActivityTestRule = new ActivityTestRule<>(DrugDictionaryActivity.class);

    @Test   // Verify that user can enter a drug name into the search bar.
    public void canEnterDrugTest() {

        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(typeText("Tigecycline"), closeSoftKeyboard());

        /* Future implementation will allow this selection to be found in the drug dictionary.  Testing
        will consist of 'pressing enter' to process the drug string.  With a specific drug selected
        the test will verify that the correct information is available to the user on the screen.
         */

    }

    @Test   // Verifies that drugs are listed under the correct alphabetical title
    public void alphaSublistTest() {

        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(typeText("Can you see the 'A' header?"), closeSoftKeyboard());

        onView(allOf(withId(R.id.exp_List_Header), withText("Drug begins with A"),
                        isDisplayed())).check(matches(withText("Drug begins with A")));

        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(typeText("Yes, I can see the 'A' header."), closeSoftKeyboard());


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(typeText("Can you see the 'Z' header?"), closeSoftKeyboard());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(typeText("No, I can not see the 'Z' header."), closeSoftKeyboard());

        onView(withId(R.id.fDrugs_List)).perform(swipeUp());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.fDrugs_List)).perform(swipeUp());

        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(typeText("Can you see the 'Z' header now?"), closeSoftKeyboard());


        onView(allOf(withId(R.id.exp_List_Header), withText("Drug begins with Z"),
                isDisplayed())).check(matches(withText("Drug begins with Z")));

        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.Dictionary_Search_Bar_TxtField)).perform(typeText("Yes, I can see the 'Z' header now."), closeSoftKeyboard());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*  In order to test the drug dictionary, it requires that the headers be clickable  which
            they are not.  This test does, however, verify that the drug dictionary list shows up
            and that all the headers listed on the screen can be seen, that it can be scrolled through,
            and that the items at the bottom of the screen are viewable once it is on the screen.
         */

    }

//    @Test   // Verify that user's correct search will result in a find
//            // NOT YET IMPLEMENTED
//    public void findDrugTest() {
//
//    }

    @Test   // Verify that a user can click to select a drug from the dictionary
            // User Story 18, Scenario 1
    public void clickDrugTest() {
        /* When the user is in the Drug Dictionary menu, all of the drugs listed will be clickable.
            The test will verify that at least one drug from A, Z, and some drug in the middle (say O)
            are clickable.
         */
    }


    @Test   // Verify a selected drug will show its details
            // User Story 18, Scenario 2, and 3
    public void showDrugDataTest() {
/*        When the functionality of this click is implemented, details of the drug will be presented
        to the user which also includes 1) Date of initial prescription 2) Next scheduled dosage
        3) Side effects icons

        The side effect icons will need to be tested for clikcability.  Then the button will be clicked
        and the correct results need to be verified on screen.  This will need to be done with at least
        2 buttons, preferably 3.  The test could be conducted on the same prescriptions pulled up
        for the above test (Starting with A, Z, and O)

        The 'edit' button will allow the user to add this drug to their current prescriptions (on
                the Manage Drug activity) and then schedule new dosage times.
*/
    }


}
