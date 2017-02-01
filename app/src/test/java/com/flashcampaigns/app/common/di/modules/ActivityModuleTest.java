package com.flashcampaigns.app.common.di.modules;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ActivityModuleTest {

  private ActivityModule mActivityModule;

  @Mock
  private Activity mMockActivity;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mActivityModule = new ActivityModule(mMockActivity);
  }

  @Test
  public void testGetActivitySuccess() {
    Activity activity = mActivityModule.activity();
    assertNotNull(activity);
    assertEquals(activity, mMockActivity);
  }
}
