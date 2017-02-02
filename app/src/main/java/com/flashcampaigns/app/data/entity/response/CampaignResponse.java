package com.flashcampaigns.app.data.entity.response;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a campaign response.
 */
@AutoValue
public abstract class CampaignResponse {

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

  public static CampaignResponse create(int id, String name, String startDate, String endDate, String imageUrl) {
    return new AutoValue_CampaignResponse(id, name, startDate, endDate, imageUrl);
  }

  public static TypeAdapter<CampaignResponse> typeAdapter(Gson gson) {
    return new AutoValue_CampaignResponse.GsonTypeAdapter(gson);
  }
}
