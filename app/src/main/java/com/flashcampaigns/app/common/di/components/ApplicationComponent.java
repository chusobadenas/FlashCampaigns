package com.flashcampaigns.app.common.di.components;

import android.content.Context;

import com.flashcampaigns.app.common.di.ApplicationContext;
import com.flashcampaigns.app.common.di.modules.ApplicationModule;
import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.data.repository.CampaignDataRepository;
import com.flashcampaigns.app.data.repository.ProductDataRepository;
import com.flashcampaigns.app.data.repository.remote.APIService;
import com.flashcampaigns.app.presentation.base.BaseActivity;
import com.flashcampaigns.app.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  void inject(BaseActivity baseActivity);

  // Exposed to sub-graphs
  @ApplicationContext
  Context context();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  APIService apiService();

  Navigator navigator();

  CampaignDataRepository campaignDataRepository();

  ProductDataRepository productDataRepository();
}
