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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DrugInteractionTest {

    @Rule
    public ActivityTestRule<DrugInteractionActivity> mActivityTestRule = new ActivityTestRule<>(DrugInteractionActivity.class);

    @Test   // User Story 23 - Scenario 3
            // Verifiies an empty field with search pressed will return an error
    public void noInteractionTest() {

        onView(withId(R.id.interactionSearch_button)).perform(click());
        String drugtext = "No interactions in list. Drug was not found. Check spelling";
        onView(withId(R.id.interactionDescription)).check(ViewAssertions.matches(withText(drugtext)));
    }

    @Test   // User Story 23 - Scenario 2
    // Verifiies that if only drug1 is entered, it will return all of its interactions
    public void oneDrug1InteractionTest() {

        onView(withId(R.id.Drug1)).perform(ViewActions.typeText("Tigecycline"), closeSoftKeyboard());
        onView(withId(R.id.interactionSearch_button)).perform(click());

        String drugtext = "Name : tigecycline-rxcui : 384455\n1) The therapeutic efficacy of Picosulfuric acid can be decreased when used in combination with Tigecycline.\n2) The serum concentration of Warfarin can be increased when it is combined with Tigecycline.\n";

        onView(withId(R.id.interactionDescription)).check(matches(withText(drugtext)));

    }    @Test   // Verifies that if only drug2 is entered, there will be no interactions returned
    public void oneDrug2InteractionTest() {

        onView(withId(R.id.Drug2)).perform(ViewActions.typeText("Tigecycline"), closeSoftKeyboard());
        onView(withId(R.id.interactionSearch_button)).perform(click());

        String drugtext = "No interactions in list. Drug was not found. Check spelling";
        onView(withId(R.id.interactionDescription)).check(ViewAssertions.matches(withText(drugtext)));
    }

    @Test   // User Story 23 - Scenario 1
            // Verifiies two drugs input returns their interactions
            // Currently, Drug2 is case sensitive. Drug1 is not.
    public void twoDrugInteractionTest() {

        onView(withId(R.id.Drug1)).perform(ViewActions.typeText("advil"), closeSoftKeyboard());
        onView(withId(R.id.Drug2)).perform(ViewActions.typeText("Lepirudin"), closeSoftKeyboard());
        onView(withId(R.id.interactionSearch_button)).perform(click());

        String drugtext = "Name : Advil-rxcui : 153010\n1) Ibuprofen may increase the anticoagulant activities of Lepirudin.\n";

        onView(withId(R.id.interactionDescription)).check(matches(withText(drugtext)));
    }

}
