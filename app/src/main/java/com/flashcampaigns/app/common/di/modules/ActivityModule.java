package com.flashcampaigns.app.common.di.modules;

import android.app.Activity;

import com.flashcampaigns.app.common.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {

  private final Activity mActivity;

  /**
   * Constructor
   *
   * @param activity the activity
   */
  public ActivityModule(Activity activity) {
    this.mActivity = activity;
  }

  /**
   * Expose the activity to dependents in the graph.
   */
  @Provides
  @PerActivity
  Activity activity() {
    return mActivity;
  }
}
