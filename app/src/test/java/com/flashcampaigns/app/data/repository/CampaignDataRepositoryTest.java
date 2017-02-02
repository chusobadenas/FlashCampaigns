package com.flashcampaigns.app.data.repository;

import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.data.entity.mapper.CampaignMapper;
import com.flashcampaigns.app.data.entity.response.CampaignResponse;
import com.flashcampaigns.app.data.repository.remote.APIService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CampaignDataRepositoryTest {

  private CampaignDataRepository campaignDataRepository;

  @Mock
  private APIService apiService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    campaignDataRepository = new CampaignDataRepository(apiService, new CampaignMapper());
  }

  @Test
  public void testGetCampaignsSuccess() {
    // Create search
    CampaignResponse campaign = CampaignResponse.create(1, "My campaign", "01-01-2017", "01-01-2018", "url");
    Observable<List<CampaignResponse>> search = Observable.just(Collections.singletonList(campaign));

    when(apiService.getCampaigns()).thenReturn(search);

    Observable<List<Campaign>> observable = campaignDataRepository.getCampaigns();
    TestSubscriber<List<Campaign>> testSubscriber = new TestSubscriber<>();
    observable.subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    List<Campaign> campaigns = testSubscriber.getOnNextEvents().get(0);

    assertEquals(campaigns.size(), 1);
    assertEquals(campaigns.get(0).id(), 1);
    assertEquals(campaigns.get(0).name(), "My campaign");
  }
}
