package com.flashcampaigns.app.common.util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flashcampaigns.app.R;

/**
 * UI utilities class
 */
public final class UIUtils {

  /**
   * Empty constructor
   */
  private UIUtils() {
  }

  /**
   * Used to load images in a view with {@link Glide}.
   *
   * @param context the context.
   * @param view    the image view.
   * @param url     the url of the image.
   */
  public static void loadImageUrl(Context context, ImageView view, String url) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.color.bg_dark_grey)
        .crossFade()
        .into(view);
  }

  /**
   * Shows a {@link Toast} message.
   *
   * @param context the context.
   * @param message a string representing a message to be shown.
   */
  public static void showToastMessage(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }
}
