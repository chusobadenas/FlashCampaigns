package com.flashcampaigns.app.presentation.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flashcampaigns.app.R;
import com.flashcampaigns.app.common.util.UIUtils;
import com.flashcampaigns.app.data.entity.Product;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class defines the adapter of a {@link Product}
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

  private final Context context;
  private final List<Product> products;

  public ProductAdapter(Context context, List<Product> products) {
    super();
    this.context = context;
    this.products = products;
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  @Override
  public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.custom_product, parent, false);
    return new ProductViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ProductViewHolder holder, int position) {
    // Get item
    Product product = products.get(position);

    // Display elements
    UIUtils.loadImageUrl(context, holder.imageView, product.imageUrl());
    Double price = product.price();
    String priceAsStr = price == null ? "- €" :
        String.format(Locale.getDefault(), "%d €", price.intValue());
    holder.priceView.setText(priceAsStr);
    holder.nameView.setText(product.name());
    holder.descriptionView.setText(product.description());
  }

  /**
   * Product holder
   */
  class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.product_image)
    ImageView imageView;
    @BindView(R.id.product_price)
    TextView priceView;
    @BindView(R.id.product_name)
    TextView nameView;
    @BindView(R.id.product_description)
    TextView descriptionView;

    ProductViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
