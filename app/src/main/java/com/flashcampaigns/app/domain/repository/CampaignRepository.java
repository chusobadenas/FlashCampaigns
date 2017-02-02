package com.flashcampaigns.app.domain.repository;

import com.flashcampaigns.app.data.entity.Campaign;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a repository for getting campaigns related data.
 */
public interface CampaignRepository {

  /**
   * Get an {@link Observable} which will emit a list of campaigns.
   */
  Observable<List<Campaign>> getCampaigns();
}
