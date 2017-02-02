package com.flashcampaigns.app.common.di.modules;

import com.flashcampaigns.app.common.di.PerActivity;
import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.data.repository.CampaignDataRepository;
import com.flashcampaigns.app.domain.interactor.UseCase;
import com.flashcampaigns.app.domain.interactor.campaign.GetCampaigns;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module that provides campaigns.
 */
@Module
public class CampaignModule {

  @Provides
  @PerActivity
  @Named("campaigns")
  public UseCase provideGetCampaigns(CampaignDataRepository campaignDataRepository, ThreadExecutor threadExecutor,
                                     PostExecutionThread postExecutionThread) {
    return new GetCampaigns(campaignDataRepository, threadExecutor, postExecutionThread);
  }
}
