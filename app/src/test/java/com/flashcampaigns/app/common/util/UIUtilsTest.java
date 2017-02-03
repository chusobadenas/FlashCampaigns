package com.flashcampaigns.app.common.util;

import android.content.Context;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Toast.class)
public class UIUtilsTest {

  @Mock
  private Context context;
  @Mock
  private Toast toast;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testShowToastMessageSuccess() {
    PowerMockito.mockStatic(Toast.class);
    PowerMockito.when(Toast.makeText(context, "Hello!", Toast.LENGTH_SHORT)).thenReturn(toast);

    UIUtils.showToastMessage(context, "Hello!");

    PowerMockito.verifyStatic();
    Toast.makeText(context, "Hello!", Toast.LENGTH_SHORT);
    Mockito.verify(toast).show();
  }

  @Test
  public void testShowPrettyDateSuccess() {
    Date date = new Date(0);
    assertTrue(UIUtils.showPrettyDate(date).contains("01 1970"));
  }
}
