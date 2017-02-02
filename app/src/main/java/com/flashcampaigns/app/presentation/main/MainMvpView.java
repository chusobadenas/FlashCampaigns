package com.flashcampaigns.app.presentation.main;

import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.presentation.base.MvpView;

import java.util.List;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
interface MainMvpView extends MvpView {

  /**
   * Displays the campaigns on the screen
   *
   * @param campaigns the list of campaigns
   */
  void showCampaigns(List<Campaign> campaigns);

  /**
   * Shows a view indicating there is no campaigns to show.
   */
  void showEmpty();

  /**
   * Hides the empty view.
   */
  void hideEmpty();
}
