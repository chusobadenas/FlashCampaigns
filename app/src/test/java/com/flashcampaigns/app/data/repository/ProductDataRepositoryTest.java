package com.flashcampaigns.app.data.repository;

import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.data.repository.remote.APIService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ProductDataRepositoryTest {

  private ProductDataRepository productDataRepository;

  @Mock
  private APIService apiService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    productDataRepository = new ProductDataRepository(apiService);
  }

  @Test
  public void testGetProductsSuccess() {
    // Create search
    Product product = Product.create(1, "My product", "Awesome product", "url", 5.0, 5.0);
    Observable<List<Product>> search = Observable.just(Collections.singletonList(product));

    when(apiService.getProducts(1)).thenReturn(search);

    Observable<List<Product>> observable = productDataRepository.getProducts(1);
    TestSubscriber<List<Product>> testSubscriber = new TestSubscriber<>();
    observable.subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    List<Product> products = testSubscriber.getOnNextEvents().get(0);

    assertEquals(products.size(), 1);
    assertEquals(products.get(0).id(), 1);
    assertEquals(products.get(0).name(), "My product");
    assertEquals(products.get(0).description(), "Awesome product");
    assertEquals(products.get(0).imageUrl(), "url");
    assertEquals(products.get(0).price(), Double.valueOf(5.0));
    assertEquals(products.get(0).listPrice(), Double.valueOf(5.0));
  }
}
