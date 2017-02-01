package com.flashcampaigns.app.presentation.splash;

import android.content.Intent;

import com.flashcampaigns.app.AndroidApplicationTest;
import com.flashcampaigns.app.BuildConfig;
import com.flashcampaigns.app.presentation.main.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(application = AndroidApplicationTest.class, constants = BuildConfig.class, sdk = 21)
public class SplashActivityTest {

  @Test
  public void testOnCreateSuccess() {
    SplashActivity activity = Robolectric.setupActivity(SplashActivity.class);
    Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
    assertEquals(intent.getComponent().getClassName(), MainActivity.class.getCanonicalName());
    assertTrue(Shadows.shadowOf(activity).isFinishing());
  }
}
