package com.flashcampaigns.app.presentation.product;

import com.flashcampaigns.app.domain.interactor.DefaultSubscriber;
import com.flashcampaigns.app.domain.interactor.product.GetProducts;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class ProductPresenterTest {

  private ProductPresenter productPresenter;

  @Mock
  private GetProducts getProducts;
  @Mock
  private ProductMvpView productMvpView;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    productPresenter = new ProductPresenter(getProducts);
    productPresenter.attachView(productMvpView);
  }

  @Test
  public void testAttachViewSuccess() {
    assertNotNull(productPresenter.getMvpView());
  }

  @Test
  public void testDetachViewSuccess() {
    productPresenter.detachView();
    assertNull(productPresenter.getMvpView());
    verify(getProducts).unsubscribe();
  }

  @Test
  public void testLoadProductsSuccess() {
    productPresenter.loadProducts(1);
    verify(getProducts).execute(any(DefaultSubscriber.class), eq(1));
  }
}
