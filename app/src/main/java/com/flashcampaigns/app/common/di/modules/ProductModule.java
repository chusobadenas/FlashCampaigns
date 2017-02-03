package com.flashcampaigns.app.common.di.modules;

import com.flashcampaigns.app.common.di.PerActivity;
import com.flashcampaigns.app.common.executor.PostExecutionThread;
import com.flashcampaigns.app.common.executor.ThreadExecutor;
import com.flashcampaigns.app.data.repository.ProductDataRepository;
import com.flashcampaigns.app.domain.interactor.UseCase;
import com.flashcampaigns.app.domain.interactor.product.GetProducts;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module that provides products.
 */
@Module
public class ProductModule {

  @Provides
  @PerActivity
  @Named("products")
  public UseCase provideGetProducts(ProductDataRepository productDataRepository, ThreadExecutor threadExecutor,
                                    PostExecutionThread postExecutionThread) {
    return new GetProducts(productDataRepository, threadExecutor, postExecutionThread);
  }
}
