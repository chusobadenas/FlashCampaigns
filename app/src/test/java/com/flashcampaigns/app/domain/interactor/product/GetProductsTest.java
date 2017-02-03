package com.flashcampaigns.app.domain.interactor.product;

import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.domain.repository.ProductRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class GetProductsTest {

  private GetProducts getProducts;

  @Mock
  private ProductRepository productRepository;
  @Mock
  private ThreadExecutor threadExecutor;
  @Mock
  private PostExecutionThread postExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getProducts = new GetProducts(productRepository, threadExecutor, postExecutionThread);
  }

  @Test
  public void testGetProductsSuccess() {
    Product product = Product.create(1, "My product", "Awesome product", "url", null, null);
    List<Product> products = Collections.singletonList(product);
    Observable<List<Product>> observable = Observable.just(products);
    when(productRepository.getProducts(1)).thenReturn(observable);

    assertNotNull(getProducts.buildUseCaseObservable(1));

    verify(productRepository).getProducts(1);
    verifyNoMoreInteractions(productRepository);
    verifyZeroInteractions(threadExecutor);
    verifyZeroInteractions(postExecutionThread);
  }
}
