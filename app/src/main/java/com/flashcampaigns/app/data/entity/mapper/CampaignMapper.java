package com.flashcampaigns.app.data.entity.mapper;

import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.data.entity.response.CampaignResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform a {@link CampaignResponse} into a {@link Campaign}.
 */
@Singleton
public class CampaignMapper {

  @Inject
  public CampaignMapper() {
    // Empty constructor
  }

  /**
   * Transform a {@link CampaignResponse} into a {@link Campaign}.
   *
   * @param campaignResponse object to be transformed.
   * @return {@link Campaign} if valid {@link CampaignResponse}, otherwise null.
   * @throws ParseException when a date is not valid
   */
  public Campaign transform(CampaignResponse campaignResponse) throws ParseException {
    Campaign campaign = null;

    if (campaignResponse != null) {
      int id = campaignResponse.id();
      String name = campaignResponse.name();
      String imageUrl = campaignResponse.imageUrl();

      DateFormat formatter = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault());
      Date startDate = formatter.parse(campaignResponse.startDate());
      Date endDate = formatter.parse(campaignResponse.endDate());

      campaign = Campaign.create(id, name, startDate, endDate, imageUrl);
    }

    return campaign;
  }
}
