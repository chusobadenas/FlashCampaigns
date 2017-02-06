package com.flashcampaigns.app.presentation.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.flashcampaigns.app.AndroidApplication;
import com.flashcampaigns.app.common.di.components.ApplicationComponent;
import com.flashcampaigns.app.common.di.modules.ActivityModule;
import com.flashcampaigns.app.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Base {@link AppCompatActivity} class for each activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Inject
  protected Navigator navigator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
  }

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment        The fragment to be added.
   */
  public void addFragment(int containerViewId, Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .add(containerViewId, fragment)
        .commit();
  }

  /**
   * Replace a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where replace the fragment.
   * @param fragment        The fragment to be added.
   */
  public void replaceFragment(int containerViewId, Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .replace(containerViewId, fragment)
        .addToBackStack(null)
        .commit();
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link ApplicationComponent}
   */
  public ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getApplication()).getComponent();
  }

  /**
   * Get an Activity module for dependency injection.
   *
   * @return {@link ActivityModule}
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean result = super.onOptionsItemSelected(item);

    switch (item.getItemId()) {
      case android.R.id.home:
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
          fragmentManager.popBackStack();
        } else {
          finish();
        }
        result = true;
        break;
      default:
        break;
    }

    return result;
  }

  @Override
  public void onBackPressed() {
    FragmentManager fragmentManager = getSupportFragmentManager();

    if (fragmentManager.getBackStackEntryCount() > 0) {
      fragmentManager.popBackStack();
    } else {
      super.onBackPressed();
    }
  }
}
