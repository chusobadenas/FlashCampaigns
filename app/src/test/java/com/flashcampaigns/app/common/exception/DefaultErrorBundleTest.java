package com.flashcampaigns.app.common.exception;

import android.content.Context;

import com.flashcampaigns.app.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class DefaultErrorBundleTest {

  @Mock
  private Context context;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    when(context.getString(R.string.exception_message_generic))
        .thenReturn("There was an application error");
    when(context.getString(R.string.btn_text_retry))
        .thenReturn("Retry");
  }

  @Test
  public void testCreationNullMessage() {
    DefaultErrorBundle defaultErrorBundle = new DefaultErrorBundle(context, null, null);

    assertNull(defaultErrorBundle.getException());
    assertEquals(defaultErrorBundle.getErrorMessage(), "There was an application error");
  }

  @Test
  public void testCreationCustomMessage() {
    Exception exception = new Exception("Error");
    DefaultErrorBundle defaultErrorBundle = new DefaultErrorBundle(context, exception, R.string
        .btn_text_retry);

    assertEquals(defaultErrorBundle.getException(), exception);
    assertEquals(defaultErrorBundle.getErrorMessage(), "Retry");
  }
}
