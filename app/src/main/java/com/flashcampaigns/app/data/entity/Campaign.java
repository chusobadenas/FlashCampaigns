package com.flashcampaigns.app.data.entity;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a campaign.
 */
@AutoValue
public abstract class Campaign {

  @SerializedName("id")
  public abstract int id();

  @SerializedName("name")
  public abstract String name();

  @SerializedName("start_date")
  public abstract String startDate();

  @SerializedName("end_date")
  public abstract String endDate();

  @SerializedName("image")
  public abstract String imageUrl();

  public static Campaign create(int id, String name, String startDate, String endDate, String imageUrl) {
    return new AutoValue_Campaign(id, name, startDate, endDate, imageUrl);
  }

  public static TypeAdapter<Campaign> typeAdapter(Gson gson) {
    return new AutoValue_Campaign.GsonTypeAdapter(gson);
  }
}
