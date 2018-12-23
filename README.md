
---

# Use A Password Unlock Screen When the User Returns to Your Android Application After Being In the Background.


---

After a user exits an app that contains personal data, you may want to validate that when the user relaunches or returns to it that the user is authorized to view that data. In this article, I will demonstrate how to show a passcode screen every time a user re-opens the app, ensuring the privacy of the currently logged in user.


---

# Step 1: Store values in SharedPreferences
We need to use two booleans stored in SharedPreferences in order to track if the application is in the background. These two values represent when the application is locked and when it is in the foreground. In my example, I called them APP_IS_LOCKED and APP_IN_FOREGROUND.
private val SharedPrefs (val sharedPref: SharedPreferences) {

    companion object {

        private var INSTANCE: SharedPrefs? = null

        @JvmStatic fun getInstance(
               sp: SharedPreferences
        ): SharedPrefs =
               INSTANCE ?: SharedPrefs(sp).apply { INSTANCE = this }
    }
var appInForeground: Boolean
        get() = sharedPref.getBoolean(
                    Constants.APP_IN_FOREGROUND,
                    true
                )
        set(value) = sharedPref.edit()
                         .putBoolean(
                             Constants.IN_APP_STATUS,
                             value
                         ).apply()
    var appIsLock: Boolean
        get() = sharedPref.getBoolean(Constants.APP_IS_LOCK, true)
        set(value) = sharedPref.edit()
                         .putBoolean(
                             Constants.APP_IS_LOCK,
                             value
                         ).apply()
}
Tip: Here I have used an external Constants to store my immutable key values. 


---

# Step 2: Set up appInForeground inside all activities that require a passcode.
The APP_IN_FOREGROUND value represents when the application is in the foreground or the background.
The way it works is that each time we start a new activity we set IN_APP_STATUS to true and when it goes onStop we set IN_APP_STATUS to false, in this way we are making sure that when on stop is called in these activities we always know if the app is going to background or is just starting a new activity.
We are going to handle the onBackPressed as well, here we are going to set APP_IS_LOCK to false.
val sharedPref = SharedPrefs(
                 context.getSharedPreferences(
                     Constants.SHARE_PREFS_NAME,     
                     Context.MODE_PRIVATE
                 )
             )
override fun onBackPressed() {
       sharedPref.appIsLock = false
       super.onBackPressed()
   }
   override fun onStop() {
       sharedPref.inAppStatus = false
       super.onStop()
   }
   private fun startNewActivity() {
       sharedPref.inAppStatus = true
       val intent = Intent(context, NextActivity::class.java)
       startActivity(intent)
   }


---

# Step 3: Create a LoggedInBaseActivity
Next step is to create a parent class for all these activities, in order to decide what to do each time onStop and onStart are called.
OnStop:
Let's start with onStop first, each time any of these activities stop we are going to check the value of our boolean IN_APP_STATUS, this one will tell us if the activity is simply starting another activity (app still in foreground) or it just went onStop without starting any other activity (app is in background).
Here in this if statementwe are going to set our APP_IS_LOCK value, as you can imagine we will set it to true if the app is in the background (IN_APP_STATUS == false) or false if the app is in the foreground (IN_APP_STATUS == true).
override fun onStop() {
      sharedPref.appIsLock = !sharedPref.inAppStatus
    super.onStop()
}
onStart:
Here onStart we are checking our APP_IS_LOCK value.
If the app is locked (APP_IS_LOCK == true) we are going to start our unlockAppActivity.
override fun onStart() {
    super.onStart()

    if (sharedPref.appIsLock) {
       startUnlockWithPasscodeActivity()
    }
}
Also check, we could check here if the current User (usually stored in the local database) is not null.
This will make sure that if other activities that don't need to lock the app (for example a login activity or register), don't show the unlockAppActivity even if they implement loggedInBaseActivity (This can happen if other developers are working in your project and don't understand your logic properly).
override fun onStart() {
    super.onStart()

    if (getLockStatus() == true && currentUser!= null) {
       startUnlockWithPasscodeActivity()
    }
}
And this is it!


---

# Conclusion:
There are different ways of doing this, I personally find this solution clean and easy to understand.
