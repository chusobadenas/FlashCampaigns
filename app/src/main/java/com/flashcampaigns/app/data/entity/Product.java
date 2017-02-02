package com.flashcampaigns.app.data.entity;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a product.
 */
@AutoValue
public abstract class Product {

  @SerializedName("id")
  public abstract int id();

  @SerializedName("name")
  public abstract String name();

  @SerializedName("description")
  public abstract String description();

  @SerializedName("image")
  public abstract String imageUrl();

  @Nullable
  @SerializedName("price")
  public abstract Double price();

  @Nullable
  @SerializedName("list_price")
  public abstract Double listPrice();

  public static Product create(int id, String name, String description, String imageUrl, Double
      price, Double listPrice) {
    return new AutoValue_Product(id, name, description, imageUrl, price, listPrice);
  }

  public static TypeAdapter<Product> typeAdapter(Gson gson) {
    return new AutoValue_Product.GsonTypeAdapter(gson);
  }
}
