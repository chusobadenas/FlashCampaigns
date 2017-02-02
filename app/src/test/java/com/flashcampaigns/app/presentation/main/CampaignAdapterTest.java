package com.flashcampaigns.app.presentation.main;

import com.flashcampaigns.app.AndroidApplicationTest;
import com.flashcampaigns.app.BuildConfig;
import com.flashcampaigns.app.data.entity.Campaign;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(application = AndroidApplicationTest.class, constants = BuildConfig.class, sdk = 21)
public class CampaignAdapterTest {

  private CampaignAdapter adapter;

  @Before
  public void setUp() {
    Campaign campaign = Campaign.create(1, "My Campaign", new Date(), new Date(), "url");
    List<Campaign> campaigns = Collections.singletonList(campaign);

    adapter = new CampaignAdapter(RuntimeEnvironment.application, campaigns);
  }

  @Test
  public void testGetItemCountSuccess() {
    assertEquals(adapter.getItemCount(), 1);
  }

  @Test
  public void testOnCreateViewHolderSuccess() {
    assertNotNull(adapter.onCreateViewHolder(null, 0));
  }
}
