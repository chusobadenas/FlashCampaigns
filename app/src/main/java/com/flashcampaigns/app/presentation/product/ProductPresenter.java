package com.flashcampaigns.app.presentation.product;

import com.flashcampaigns.app.R;
import com.flashcampaigns.app.common.di.PerActivity;
import com.flashcampaigns.app.common.exception.DefaultErrorBundle;
import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.domain.interactor.DefaultSubscriber;
import com.flashcampaigns.app.domain.interactor.UseCase;
import com.flashcampaigns.app.presentation.base.BasePresenter;
import com.flashcampaigns.app.presentation.base.Presenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import timber.log.Timber;

/**
 * {@link Presenter} that controls communication between views and models of the presentation layer.
 */
@PerActivity
public class ProductPresenter extends BasePresenter<ProductMvpView> {

  private final UseCase getProductsUseCase;

  @Inject
  public ProductPresenter(@Named("products") UseCase getProductsUseCase) {
    this.getProductsUseCase = getProductsUseCase;
  }

  @Override
  public void detachView() {
    super.detachView();
    getProductsUseCase.unsubscribe();
  }

  /**
   * Loads the list of products
   *
   * @param campaignId the id of the campaign
   */
  void loadProducts(int campaignId) {
    checkViewAttached();
    getMvpView().showLoading();
    getProducts(campaignId);
  }

  private void getProducts(int campaignId) {
    getProductsUseCase.execute(new ProductsSubscriber(), campaignId);
  }

  private final class ProductsSubscriber extends DefaultSubscriber<List<Product>> {

    @Override
    public void onError(Throwable throwable) {
      // Create error
      ProductMvpView mvpView = getMvpView();
      DefaultErrorBundle errorBundle = new DefaultErrorBundle(mvpView.context(), throwable, R.string.error_loading_products);

      // Show error
      Timber.e(errorBundle.getException(), "There was an error loading the products");
      mvpView.hideLoading();
      mvpView.showRetry();
      showErrorMessage(errorBundle);
    }

    @Override
    public void onNext(List<Product> products) {
      ProductMvpView mvpView = getMvpView();
      mvpView.hideLoading();
      mvpView.showProducts(products);
    }
  }
}
