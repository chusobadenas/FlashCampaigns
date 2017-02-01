package com.flashcampaigns.app.common.di.modules;

import android.content.Context;

import com.flashcampaigns.app.AndroidApplication;
import com.flashcampaigns.app.common.di.ApplicationContext;
import com.flashcampaigns.app.common.executor.JobExecutor;
import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.common.executor.UIThread;
import com.flashcampaigns.app.data.repository.remote.APIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

  private final AndroidApplication mApplication;

  /**
   * Constructor
   *
   * @param application the application
   */
  public ApplicationModule(AndroidApplication application) {
    this.mApplication = application;
  }

  @Provides
  @ApplicationContext
  @Singleton
  Context provideApplicationContext() {
    return mApplication;
  }

  @Provides
  @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides
  @Singleton
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides
  @Singleton
  APIService provideApiService() {
    return APIService.Creator.newAPIService();
  }
}
