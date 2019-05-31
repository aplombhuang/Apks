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

import team4.drugapp.MainActivity;

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
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

//    @Test  // User must enter a valid email
//    public void verifyValidEmail{
//
//    }
//
//    @Test // Password must be within proper parameters
//    public void verifyValidPassword{
//
//    }
//
//    @Test // The email entered cannot be previously registered
//    public void userAlreadyRegistered{
//
//    }
//
//    @Test // Verify click on register user and user is created
//    public void newUserRegisterProperly{
//
//    }
//
//    @Test // Verify the click to sign in button works properly
//    public void clickAlreadyRegistered{
//
//    }

}
