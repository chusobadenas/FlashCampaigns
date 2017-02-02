package com.flashcampaigns.app.common.di.modules;

import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.data.repository.CampaignDataRepository;
import com.flashcampaigns.app.domain.interactor.UseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class CampaignModuleTest {

  private CampaignModule campaignModule;

  @Mock
  private CampaignDataRepository campaignDataRepository;
  @Mock
  private ThreadExecutor threadExecutor;
  @Mock
  private PostExecutionThread postExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    campaignModule = new CampaignModule();
  }

  @Test
  public void testProvideGetCampaignsSuccess() {
    UseCase getCampaigns = campaignModule.provideGetCampaigns(campaignDataRepository, threadExecutor, postExecutionThread);
    assertNotNull(getCampaigns);
  }
}
