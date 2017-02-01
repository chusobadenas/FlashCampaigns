package com.flashcampaigns.app.presentation.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.flashcampaigns.app.common.di.HasComponent;

/**
 * Base {@link Fragment} class for each fragment in this application.
 */
public abstract class BaseFragment extends Fragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  /*
   * onAttach(Context) is not called on pre API 23 versions of Android and onAttach(Activity) is deprecated
   * Use onAttachToContext instead
   */
  @TargetApi(23)
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    onAttachToContext(context);
  }

  /*
   * Deprecated on API 23
   * Use onAttachToContext instead
   */
  @SuppressWarnings("deprecation")
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      onAttachToContext(activity);
    }
  }

  /*
   * Method to be overwritten by is child class if needed.
   *
   * @param context The context
   */
  protected void onAttachToContext(Context context) {
  }

  /**
   * Gets a component for dependency injection by its type.
   */
  @SuppressWarnings("unchecked")
  protected <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
  }
}
