package com.flashcampaigns.app;

import android.app.Application;

import com.flashcampaigns.app.common.di.HasComponent;
import com.flashcampaigns.app.common.di.components.ApplicationComponent;
import com.flashcampaigns.app.common.di.components.DaggerApplicationComponent;
import com.flashcampaigns.app.common.di.modules.ApplicationModule;

import timber.log.Timber;

public class AndroidApplication extends Application implements HasComponent<ApplicationComponent> {

  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    initializeInjector();
    initializeTimber();
  }

  @Override
  public ApplicationComponent getComponent() {
    return applicationComponent;
  }

  private void initializeInjector() {
    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  private void initializeTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
