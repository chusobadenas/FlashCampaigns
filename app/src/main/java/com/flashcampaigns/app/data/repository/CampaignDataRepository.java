package com.flashcampaigns.app.data.repository;

import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.data.repository.remote.APIService;
import com.flashcampaigns.app.domain.repository.CampaignRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link CampaignDataRepository} for retrieving campaigns data.
 */
@Singleton
public class CampaignDataRepository implements CampaignRepository {

  private final APIService apiService;

  @Inject
  public CampaignDataRepository(APIService apiService) {
    this.apiService = apiService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Observable<List<Campaign>> getCampaigns() {
    return apiService.getCampaigns();
  }
}
