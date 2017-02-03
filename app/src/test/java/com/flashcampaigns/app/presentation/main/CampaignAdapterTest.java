package com.flashcampaigns.app.presentation.main;

import android.support.v7.widget.RecyclerView;

import com.flashcampaigns.app.AndroidApplicationTest;
import com.flashcampaigns.app.BuildConfig;
import com.flashcampaigns.app.data.entity.Campaign;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
@Config(application = AndroidApplicationTest.class, constants = BuildConfig.class, sdk = 21)
public class CampaignAdapterTest {

  private static final int CAMPAIGN_STATUS_VIEW = 0;
  private static final int CAMPAIGN_VIEW = 1;

  private CampaignAdapter adapter;

  @Before
  public void setUp() {
    List<Object> items = new ArrayList<>();
    Campaign campaign = Campaign.create(1, "My Campaign", new Date(), new Date(), "url");
    String status = "Active";

    items.add(status);
    items.add(campaign);

    adapter = new CampaignAdapter(RuntimeEnvironment.application, items);
  }

  @Test
  public void testGetItemCountSuccess() {
    assertEquals(adapter.getItemCount(), 2);
  }

  @Test
  public void testGetItemViewType() {
    assertEquals(adapter.getItemViewType(0), CAMPAIGN_STATUS_VIEW);
    assertEquals(adapter.getItemViewType(1), CAMPAIGN_VIEW);
  }

  @Test
  public void testOnCreateStatusViewHolderSuccess() {
    RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(null, 0);
    assertNotNull(holder);
    assertTrue(holder instanceof CampaignAdapter.CampaignStatusHolder);
  }

  @Test
  public void testOnCreateCampaignViewHolderSuccess() {
    RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(null, 1);
    assertNotNull(holder);
    assertTrue(holder instanceof CampaignAdapter.CampaignHolder);
  }

  @Test
  public void testOnBindStatusViewHolder() {
    RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(null, 0);

    try {
      adapter.onBindViewHolder(holder, 0);
    } catch (Exception exception) {
      fail(exception.getMessage());
    }
  }

  @Test
  public void testOnBindCampaignViewHolder() {
    RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(null, 1);

    try {
      adapter.onBindViewHolder(holder, 1);
    } catch (Exception exception) {
      fail(exception.getMessage());
    }
  }
}
