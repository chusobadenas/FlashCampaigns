package com.flashcampaigns.app.data.entity.mapper;

import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.data.entity.response.CampaignResponse;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CampaignMapperTest {

  @Test
  public void testTransformCampaignSuccess() {
    DateFormat formatter = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault());
    CampaignMapper mapper = new CampaignMapper();

    try {
      CampaignResponse campaignResponse = CampaignResponse.create(1, "My campaign", "2017-01-01",
          "2017-31-12", "url");
      Campaign campaign = mapper.transform(campaignResponse);

      assertNotNull(campaign);
      assertEquals(campaign.id(), 1);
      assertEquals(campaign.name(), "My campaign");
      assertEquals(campaign.startDate().getTime(), formatter.parse("2017-01-01").getTime());
      assertEquals(campaign.endDate().getTime(), formatter.parse("2017-31-12").getTime());
      assertEquals(campaign.imageUrl(), "url");

    } catch (ParseException exception) {
      fail(exception.getMessage());
    }
  }
}
