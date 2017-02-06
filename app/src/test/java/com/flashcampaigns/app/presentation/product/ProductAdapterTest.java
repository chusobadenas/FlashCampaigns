package com.flashcampaigns.app.presentation.product;

import com.flashcampaigns.app.AndroidApplicationTest;
import com.flashcampaigns.app.BuildConfig;
import com.flashcampaigns.app.data.entity.Product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
@Config(application = AndroidApplicationTest.class, constants = BuildConfig.class, sdk = 21)
public class ProductAdapterTest {

  private ProductAdapter adapter;

  @Before
  public void setUp() {
    Product product = Product.create(1, "My product", "Awesome product", "url", 5.0, 5.0);
    adapter = new ProductAdapter(RuntimeEnvironment.application, Collections.singletonList(product));
  }

  @Test
  public void testGetItemCountSuccess() {
    assertEquals(adapter.getItemCount(), 1);
  }

  @Test
  public void testOnCreateProductViewHolderSuccess() {
    ProductAdapter.ProductViewHolder holder = adapter.onCreateViewHolder(null, 0);
    assertNotNull(holder);
  }

  @Test
  public void testOnBindProductViewHolder() {
    ProductAdapter.ProductViewHolder holder = adapter.onCreateViewHolder(null, 0);

    try {
      adapter.onBindViewHolder(holder, 0);
    } catch (Exception exception) {
      fail(exception.getMessage());
    }
  }
}
