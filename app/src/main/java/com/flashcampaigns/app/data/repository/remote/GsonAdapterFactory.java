package com.flashcampaigns.app.data.repository.remote;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
public abstract class GsonAdapterFactory implements TypeAdapterFactory {

  public static GsonAdapterFactory create() {
    return new AutoValueGson_GsonAdapterFactory();
  }
}
