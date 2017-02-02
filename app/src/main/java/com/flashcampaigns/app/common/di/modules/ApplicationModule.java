package com.flashcampaigns.app.common.di.modules;

import android.content.Context;

import com.flashcampaigns.app.AndroidApplication;
import com.flashcampaigns.app.common.di.ApplicationContext;
import com.flashcampaigns.app.common.executor.JobExecutor;
import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.common.executor.UIThread;
import com.flashcampaigns.app.data.repository.CampaignDataRepository;
import com.flashcampaigns.app.data.repository.remote.APIService;
import com.flashcampaigns.app.domain.repository.CampaignRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

  private final AndroidApplication application;

  /**
   * Constructor
   *
   * @param application the application
   */
  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides
  @ApplicationContext
  @Singleton
  public Context provideApplicationContext() {
    return application;
  }

  @Provides
  @Singleton
  public ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides
  @Singleton
  public PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides
  @Singleton
  public APIService provideApiService() {
    return APIService.Creator.newAPIService();
  }

  @Provides
  @Singleton
  public CampaignRepository provideCampaignRepository(CampaignDataRepository campaignDataRepository) {
    return campaignDataRepository;
  }
}
