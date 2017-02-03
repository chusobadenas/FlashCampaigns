package com.flashcampaigns.app.presentation.product;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.flashcampaigns.app.R;
import com.flashcampaigns.app.common.util.UIUtils;
import com.flashcampaigns.app.data.entity.Product;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends BaseAdapter {

  private final Context context;
  private final List<Product> products;

  public ProductAdapter(Context context, List<Product> products) {
    super();
    this.context = context;
    this.products = products;
  }

  @Override
  public int getCount() {
    return products.size();
  }

  @Override
  public Product getItem(int position) {
    return products.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // Reuse view
    ProductViewHolder holder;
    CardView currentView = (CardView) convertView;

    if (currentView == null) {
      currentView = (CardView) LayoutInflater.from(context).inflate(R.layout.custom_product, null);
      holder = new ProductViewHolder(currentView);
      currentView.setTag(holder);
    } else {
      holder = (ProductViewHolder) currentView.getTag();
    }

    // Get item
    Product product = getItem(position);

    // Display elements
    UIUtils.loadImageUrl(context, holder.imageView, product.imageUrl());
    Double price = product.price();
    String priceAsStr = price == null ? "- €" : String.format(Locale.getDefault(), "%d €", price.intValue());
    holder.priceView.setText(priceAsStr);
    holder.nameView.setText(product.name());
    holder.descriptionView.setText(product.description());

    return currentView;
  }

  class ProductViewHolder {

    @BindView(R.id.product_image)
    ImageView imageView;
    @BindView(R.id.product_price)
    TextView priceView;
    @BindView(R.id.product_name)
    TextView nameView;
    @BindView(R.id.product_description)
    TextView descriptionView;

    ProductViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
