package com.flashcampaigns.app.domain.interactor.product;

import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.domain.interactor.UseCase;
import com.flashcampaigns.app.domain.repository.ProductRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for retrieving a collection of all the
 * {@link Product} of a {@link Campaign}.
 */
public class GetProducts extends UseCase {

  private final ProductRepository productRepository;

  @Inject
  public GetProducts(ProductRepository productRepository, ThreadExecutor threadExecutor, PostExecutionThread
      postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.productRepository = productRepository;
  }

  @Override
  public Observable buildUseCaseObservable(Object... param) {
    int campaignId = (int) param[0];
    return productRepository.getProducts(campaignId);
  }
}
