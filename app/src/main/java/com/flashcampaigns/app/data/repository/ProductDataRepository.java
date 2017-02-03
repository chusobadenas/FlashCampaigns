package com.flashcampaigns.app.data.repository;

import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.data.repository.remote.APIService;
import com.flashcampaigns.app.domain.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link ProductDataRepository} for retrieving products data.
 */
@Singleton
public class ProductDataRepository implements ProductRepository {

  private final APIService apiService;

  @Inject
  public ProductDataRepository(APIService apiService) {
    this.apiService = apiService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Observable<List<Product>> getProducts(int campaignId) {
    return apiService.getProducts(campaignId);
  }
}
