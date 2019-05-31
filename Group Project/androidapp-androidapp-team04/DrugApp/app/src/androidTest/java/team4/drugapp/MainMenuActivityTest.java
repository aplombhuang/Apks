package team4.drugapp;


import android.content.ComponentName;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static team4.drugapp.R.id.ManageDrugs_btn;
import static team4.drugapp.R.id.activity_manage_drugs;

@RunWith(AndroidJUnit4.class)
public class MainMenuActivityTest {

    @Rule
    public ActivityTestRule<MainMenuActivity> mActivityTestRule = new ActivityTestRule<>(MainMenuActivity.class);

    @Test
    public void buttonsClickable() {
        // Check to make sure that all of the buttons on the UI are clickable
        onView(withId(R.id.ManageDrugs_btn)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.Alarms_Btn)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.DrugDictionary_Btn)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.UserSurvy_btn)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.DrugInteraction_Btn)).check(ViewAssertions.matches(isClickable()));
    }

    @Test
    public void ManageDrugsClickTest(){

        onView(withId(R.id.ManageDrugs_btn)).perform(click());
        onView(withId(R.id.addbtn)).perform(click());
        //intended(hasComponent(ManageDrugsActivity.class.getName()));
    }

//    ViewInteraction appCompatTextView = onView(
//            allOf(withId(R.id.textViewSignin), withText("Already Registered? Click here to sign-in"),
//                    withParent(allOf(withId(R.id.activity_main),
//                            withParent(withId(android.R.id.content)))),
//                    isDisplayed()));
//        appCompatTextView.perform(click());


    @Test
    public void AlarmsClickTest(){

        onView(withId(R.id.Alarms_Btn)).perform(click());
    }

    @Test
    public void DrugDictionaryClickTest(){

        onView(withId(R.id.DrugDictionary_Btn)).perform(click());
    }

    @Test
    public void SettingsClickTest(){

        onView(withId(R.id.Settings_Btn)).perform(click());
    }

    //pressBack();
}
