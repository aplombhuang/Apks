# androidapp-androidapp-team04
androidapp-androidapp-team04 created by GitHub Classroom

1st Sprint:<br>
-Added all of the main menus so we could have a skeleton of the app for further development<br>
-Created a "registration activity" that takes from the user an email and a password to be used to track that particular person's interactions with the app<br>
-Also created a login activity so if a user has already registered an account then they will just need to log in rather than registering all over again<br>
-Created intent to move from login activity to profile activity<br>
-Created profile activity to take from the user their name, address, weight, age, and gender if they so choose to give that information, a button was created on this screen so that the user can skip this part of the process and go to the main menu<br>
-Created the main activity that allows user to navigate to the important aspects of the application such as the Manage Drugs, Alarms, and Drug Dictionary activities as well as the Settings activity which will, in the future, allow the user to change the overall settings of the app<br>
-Added an option for the user to go back and complete the survey of name, address, weight, age,and gender if they did not originally complete it<br>
-Created Drug Dictionary activity in which user will be able to lookup other drugs to see if they have any particular interactions with any of the medications they are currently taking<br>

2nd Sprint:<br>
-In the Manage Mrugs Activity an "Add New Drug" button was created that brings up an auto complete text view that allows the user to type in a drug, have a list pop up of possible drugs matching what is typed, and can add that drug to the list of medications the user is currently taking.<br>
-In the Alarms Activity the user is now able to add alarms.<br>
-When an alarm goes off another screen pops up that allows the user to select whether or not they took the medication that said alarm is sounding for, the user either selects "Took medicine" or "Not taking medicine".<br>
-In the drug interactions activity the user has two editText fields that they can enter a drug name into, when a drug is entered into the first field and presses the search button a list of the possible interactions with that drug is displayed below. When the user types two different drug names in each of the editText fields a list of interactions between the two drugs is shown. If there are no interactions between the drugs entered then it displays that fact below.<br>
-In the Drug Dictionary activity a list was created so that the user will be able to select a particular drug and view any information about it.<br>
-Tests were created for the Alarm Activity to ensure that buttons are working correctly as well as the fact that an alarm was created successfully.<br>
-Tests were created for the Drug Dictionary Activity to ensure the buttons are working correctly as well as the fact that the list is working properly to select the differents sections according to the letter of the alphabet.<br>
-Tests were created for the Drug Interactions Activity to test the different scenarios of what is entered into the two editText fields to ensure that all possible interactions are displayed correctly.<br>

3rd Sprint:
-In the Manage Drugs Activity the user is looking at the list of current drugs they are able to click on one of them and a new activity is started that shows the user their overall history with taking that drug
-Then, the user is able to see a list of possible side effects while looking at that particular drug.
-User is then able to select a certain drug and edit certain information about it.
-In the Alarms Activity when an alarm goes off, the app displays the alarm, and displays icons of the drug taking conditions for that particular drug.
-When the list of conditions is revealed the user can click on any of the icons displayed and a more detailed view of the conditon will be displayed.
-After the conditions are displayed the user can select that they have taken the drug.
-In the Alarms Activity when an alarm is going off the user has the option to remind them again later.
-When the alarm is sounded again for the same drug and the user chooses to remind again a message is popped up saying they should take the drug soon
-When the user selects to be reminded a 3rd time the app gives information on what to do since they missed the dose
-In the Alarms Activity the user can hold down on a particular alarm and they will then have the option of deleting the alarm
-When a user clicks on an alarm they will have the option to edit the alarm and upon being edited the user can click accept to accept the changes they have made to the alarm.
-Tests where written to ensure that the when the user is navigating through the drug information in the Manage Drugs Activity that every thing is clickable and the user is able to continously move through the activities.
-Tests were written to ensure that the information edited in the Alarm Acitity was properly updated when the user did anything with an alarm.
