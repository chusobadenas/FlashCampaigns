package com.flashcampaigns.app.common.di.modules;

import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.data.repository.ProductDataRepository;
import com.flashcampaigns.app.domain.interactor.UseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class ProductModuleTest {

  private ProductModule productModule;

  @Mock
  private ProductDataRepository productDataRepository;
  @Mock
  private ThreadExecutor threadExecutor;
  @Mock
  private PostExecutionThread postExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    productModule = new ProductModule();
  }

  @Test
  public void testProvideGetProductsSuccess() {
    UseCase getProducts = productModule.provideGetProducts(productDataRepository, threadExecutor, postExecutionThread);
    assertNotNull(getProducts);
  }
}
