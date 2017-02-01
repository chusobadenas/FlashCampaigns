package com.flashcampaigns.app.data.repository.remote;

import com.flashcampaigns.app.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * APIService for retrieving data from the network using Retrofit.
 */
public interface APIService {

  String API_BASE_URL = "PUT_YOUR_API_URL_HERE";

  /********
   * Helper class that sets up a new services
   *******/
  class Creator {

    private static OkHttpClient createHttpClient() {
      OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

      // Enable logging
      if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        clientBuilder.addInterceptor(interceptor);
      }

      return clientBuilder
          .readTimeout(10000, TimeUnit.MILLISECONDS)
          .connectTimeout(15000, TimeUnit.MILLISECONDS)
          .build();
    }

    private static Retrofit createRetrofit(String baseUrl) {
      Gson gson = new GsonBuilder()
          .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
          .registerTypeAdapterFactory(GsonAdapterFactory.create())
          .create();

      return new Retrofit.Builder()
          .baseUrl(baseUrl)
          .client(createHttpClient())
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .build();
    }

    public static APIService newAPIService() {
      return createRetrofit(API_BASE_URL).create(APIService.class);
    }
  }
}
