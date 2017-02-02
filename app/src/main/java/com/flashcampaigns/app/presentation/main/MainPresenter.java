package com.flashcampaigns.app.presentation.main;

import com.flashcampaigns.app.R;
import com.flashcampaigns.app.common.di.PerActivity;
import com.flashcampaigns.app.common.exception.DefaultErrorBundle;
import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.domain.interactor.DefaultSubscriber;
import com.flashcampaigns.app.domain.interactor.UseCase;
import com.flashcampaigns.app.presentation.base.BasePresenter;
import com.flashcampaigns.app.presentation.base.Presenter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import timber.log.Timber;

/**
 * {@link Presenter} that controls communication between views and models of the presentation layer.
 */
@PerActivity
public class MainPresenter extends BasePresenter<MainMvpView> {

  private final UseCase getCampaignsUseCase;

  @Inject
  public MainPresenter(@Named("campaigns") UseCase getCampaignsUseCase) {
    this.getCampaignsUseCase = getCampaignsUseCase;
  }

  @Override
  public void detachView() {
    super.detachView();
    getCampaignsUseCase.unsubscribe();
  }

  /**
   * Loads the list of campaigns
   */
  void loadCampaigns() {
    checkViewAttached();
    getMvpView().showLoading();
    getCampaigns();
  }

  private void getCampaigns() {
    getCampaignsUseCase.execute(new CampaignsSubscriber());
  }

  private final class CampaignsSubscriber extends DefaultSubscriber<List<Campaign>> {

    /**
     * Compare campaigns by end date
     */
    private final Comparator<Campaign> dateComparator = new Comparator<Campaign>() {

      @Override
      public int compare(Campaign campaign, Campaign anotherCampaign) {
        return anotherCampaign.endDate().compareTo(campaign.endDate());
      }
    };

    @Override
    public void onError(Throwable throwable) {
      // Create error
      MainMvpView mvpView = getMvpView();
      DefaultErrorBundle errorBundle = new DefaultErrorBundle(mvpView.context(), throwable,
          R.string.error_loading_campaigns);

      // Show error
      Timber.e(errorBundle.getException(), "There was an error loading the campaigns");
      mvpView.hideLoading();
      showErrorMessage(errorBundle);
    }

    @Override
    public void onNext(List<Campaign> campaigns) {
      MainMvpView mvpView = getMvpView();
      mvpView.hideLoading();

      // Sort campaigns by end date
      Collections.sort(campaigns, dateComparator);

      // Set values in the view
      mvpView.showCampaigns(campaigns);
    }
  }
}
