package com.flashcampaigns.app.domain.interactor.campaign;

import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.domain.repository.CampaignRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class GetCampaignsTest {

  private GetCampaigns getCampaigns;

  @Mock
  private CampaignRepository campaignRepository;
  @Mock
  private ThreadExecutor threadExecutor;
  @Mock
  private PostExecutionThread postExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getCampaigns = new GetCampaigns(campaignRepository, threadExecutor, postExecutionThread);
  }

  @Test
  public void testGetCampaignsSuccess() {
    Product product = Product.create(1, "My Product", "Awesome product", "url", null, null);
    Campaign campaign = Campaign.create(1, "My Campaign", "01-01-2017", "01-01-2018", "url", Collections.singletonList(product));
    List<Campaign> campaigns = Collections.singletonList(campaign);
    Observable<List<Campaign>> observable = Observable.just(campaigns);
    when(campaignRepository.getCampaigns()).thenReturn(observable);

    assertNotNull(getCampaigns.buildUseCaseObservable());

    verify(campaignRepository).getCampaigns();
    verifyNoMoreInteractions(campaignRepository);
    verifyZeroInteractions(threadExecutor);
    verifyZeroInteractions(postExecutionThread);
  }
}
