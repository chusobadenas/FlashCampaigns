package com.flashcampaigns.app.presentation.product;

import android.view.View;

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
  public void testGetCountSuccess() {
    assertEquals(adapter.getCount(), 1);
  }

  @Test
  public void testGetItemSuccess() {
    Product item = adapter.getItem(0);

    assertNotNull(item);
    assertEquals(item.name(), "My product");
    assertEquals(item.description(), "Awesome product");
    assertEquals(item.imageUrl(), "url");
    assertEquals(item.price(), Double.valueOf(5.0));
    assertEquals(item.listPrice(), Double.valueOf(5.0));
  }

  @Test
  public void testGetItemIdSuccess() {
    assertEquals(adapter.getItemId(0), 0);
  }

  @Test
  public void testGetViewFirstTime() {
    try {
      View view = adapter.getView(0, null, null);
      assertNotNull(view);
    } catch (Exception exception) {
      fail(exception.getMessage());
    }
  }
}
