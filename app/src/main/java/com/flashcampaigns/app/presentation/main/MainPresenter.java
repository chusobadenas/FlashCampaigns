package com.flashcampaigns.app.presentation.main;

import com.flashcampaigns.app.common.di.PerActivity;
import com.flashcampaigns.app.presentation.base.BasePresenter;
import com.flashcampaigns.app.presentation.base.Presenter;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation layer.
 */
@PerActivity
public class MainPresenter extends BasePresenter<MainMvpView> {

  @Inject
  public MainPresenter() {
    // Empty constructor
  }

  /**
   * Initializes the presenter
   */
  void initialize() {
    checkViewAttached();
  }
}
