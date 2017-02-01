package com.flashcampaigns.app.common.exception;

import android.content.Context;

import com.flashcampaigns.app.R;

/**
 * Wrapper around Exceptions used to manage default errors.
 */
public class DefaultErrorBundle implements ErrorBundle {

  private final Context context;
  private Exception exception;
  private final Integer errorMsgId;

  /**
   * Constructor
   *
   * @param context    application context
   * @param throwable  the exception
   * @param errorMsgId resource id of the error message
   */
  public DefaultErrorBundle(Context context, Throwable throwable, Integer errorMsgId) {
    this.context = context;
    this.errorMsgId = errorMsgId;
    if (throwable instanceof Exception) {
      this.exception = (Exception) throwable;
    }
  }

  @Override
  public Exception getException() {
    return exception;
  }

  @Override
  public String getErrorMessage() {
    String message;

    if (errorMsgId == null) {
      message = context.getString(R.string.exception_message_generic);
    } else {
      message = context.getString(errorMsgId);
    }

    return message;
  }
}
