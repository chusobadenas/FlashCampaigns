package com.flashcampaigns.app.domain.interactor.campaign;

import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.domain.interactor.UseCase;
import com.flashcampaigns.app.domain.repository.CampaignRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for retrieving a collection of all
 * {@link Campaign}.
 */
public class GetCampaigns extends UseCase {

  private final CampaignRepository campaignRepository;

  @Inject
  public GetCampaigns(CampaignRepository campaignRepository, ThreadExecutor threadExecutor, PostExecutionThread
      postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.campaignRepository = campaignRepository;
  }

  @Override
  public Observable buildUseCaseObservable(Object... param) {
    return campaignRepository.getCampaigns();
  }
}
