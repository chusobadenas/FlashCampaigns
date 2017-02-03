package com.flashcampaigns.app.domain.repository;

import com.flashcampaigns.app.data.entity.Product;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a repository for getting products related data.
 */
public interface ProductRepository {

  /**
   * Get an {@link Observable} which will emit the products of a campaign.
   *
   * @param campaignId the id of the campaign
   */
  Observable<List<Product>> getProducts(int campaignId);
}
