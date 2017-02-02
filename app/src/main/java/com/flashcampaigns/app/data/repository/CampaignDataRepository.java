package com.flashcampaigns.app.data.repository;

import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.data.entity.mapper.CampaignMapper;
import com.flashcampaigns.app.data.entity.response.CampaignResponse;
import com.flashcampaigns.app.data.repository.remote.APIService;
import com.flashcampaigns.app.domain.repository.CampaignRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * {@link CampaignDataRepository} for retrieving campaigns data.
 */
@Singleton
public class CampaignDataRepository implements CampaignRepository {

  private final APIService apiService;
  private final CampaignMapper campaignMapper;

  @Inject
  public CampaignDataRepository(APIService apiService, CampaignMapper campaignMapper) {
    this.apiService = apiService;
    this.campaignMapper = campaignMapper;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Observable<List<Campaign>> getCampaigns() {
    return apiService.getCampaigns().flatMap(new Func1<List<CampaignResponse>, Observable<List<Campaign>>>() {
      @Override
      public Observable<List<Campaign>> call(List<CampaignResponse> campaignResponses) {
        List<Campaign> campaigns = new ArrayList<>();

        // Transform campaigns
        for (CampaignResponse response : campaignResponses) {
          try {
            campaigns.add(campaignMapper.transform(response));
          } catch (ParseException exception) {
            return Observable.error(exception);
          }
        }

        return Observable.just(campaigns);
      }
    });
  }
}
