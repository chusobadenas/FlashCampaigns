package com.flashcampaigns.app.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.flashcampaigns.app.presentation.main.MainActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

  @Inject
  public Navigator() {
    // Empty constructor
  }

  /**
   * Goes to the main screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToMain(Context context) {
    if (context != null) {
      Intent intentToLaunch = MainActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }
}
