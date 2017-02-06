package com.flashcampaigns.app.presentation.main;

import com.flashcampaigns.app.data.entity.Campaign;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CampaignUtilsTest {

  private static final long ONE_DAY_IN_MS = 86400000L;

  @Test
  public void testIsCampaignActiveTrue() {
    Date today = new Date();
    Date start = new Date(0);
    Date end = new Date(today.getTime() + ONE_DAY_IN_MS);
    Campaign campaign = Campaign.create(1, "My Campaign", start, end, "url");

    assertTrue(CampaignUtils.isActive(campaign));
  }

  @Test
  public void testIsCampaignActiveFalse() {
    Date start = new Date(0);
    Date end = new Date(1);
    Campaign campaign = Campaign.create(1, "My Campaign", start, end, "url");

    assertFalse(CampaignUtils.isActive(campaign));
  }
}
