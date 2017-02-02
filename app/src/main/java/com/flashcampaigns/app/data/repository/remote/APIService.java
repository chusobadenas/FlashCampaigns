package com.flashcampaigns.app.data.repository.remote;

import com.flashcampaigns.app.BuildConfig;
import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.data.entity.response.CampaignResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * APIService for retrieving data from the network using Retrofit.
 */
public interface APIService {

  String API_BASE_URL = "https://dashboard.eelp.com/api/";
  String VERSION = "v1";

  @GET(VERSION + "/flash_campaigns")
  Observable<List<CampaignResponse>> getCampaigns();

  @GET(VERSION + "/flash_campaigns/{id}/products")
  Observable<List<Product>> getProducts(@Path("id") int campaignId);

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
