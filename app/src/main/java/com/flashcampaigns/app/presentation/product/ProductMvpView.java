package com.flashcampaigns.app.presentation.product;

import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.presentation.base.MvpView;

import java.util.List;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
interface ProductMvpView extends MvpView {

  /**
   * Displays the products on the screen
   *
   * @param products the list of products
   */
  void showProducts(List<Product> products);
}
