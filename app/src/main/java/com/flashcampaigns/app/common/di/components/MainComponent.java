package com.flashcampaigns.app.common.di.components;

import com.flashcampaigns.app.common.di.PerActivity;
import com.flashcampaigns.app.common.di.modules.ActivityModule;
import com.flashcampaigns.app.common.di.modules.CampaignModule;
import com.flashcampaigns.app.common.di.modules.ProductModule;
import com.flashcampaigns.app.presentation.main.MainFragment;
import com.flashcampaigns.app.presentation.product.ProductFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CampaignModule.class, ProductModule
    .class})
public interface MainComponent extends ActivityComponent {

  void inject(MainFragment mainFragment);

  void inject(ProductFragment productFragment);
}
