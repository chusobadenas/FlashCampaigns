package com.flashcampaigns.app.common.executor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class UIThreadTest {

  @Test
  public void testGetSchedulerNotNull() {
    UIThread uiThread = new UIThread();
    assertNotNull(uiThread.getScheduler());
  }
}
