package com.flashcampaigns.app.common.util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flashcampaigns.app.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

  /**
   * Creates a string representation of the date in a pretty format
   *
   * @param date the date
   * @return the string representation of the date
   */
  public static String showPrettyDate(Date date) {
    DateFormat formatter = new SimpleDateFormat("MMM dd yyyy", Locale.getDefault());
    return formatter.format(date);
  }
}
