package com.flashcampaigns.app.presentation.main;

import com.flashcampaigns.app.data.entity.Campaign;

import java.util.Date;

/**
 * Campaign utilities class
 */
public final class CampaignUtils {

  /**
   * Private constructor
   */
  private CampaignUtils() {
  }

  /**
   * An active campaign will be any campaign that today is between start and end time.
   *
   * @param campaign the campaign
   * @return true if the campaign is active, false otherwise
   */
  public static boolean isActive(Campaign campaign) {
    Date today = new Date();
    Date start = campaign.startDate();
    Date end = campaign.endDate();

    return start.before(today) && end.after(today);
  }
}
