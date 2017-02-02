package com.flashcampaigns.app.presentation.main;

import com.flashcampaigns.app.domain.interactor.DefaultSubscriber;
import com.flashcampaigns.app.domain.interactor.campaign.GetCampaigns;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

  private MainPresenter mainPresenter;

  @Mock
  private GetCampaigns getCampaigns;
  @Mock
  private MainMvpView mainMvpView;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mainPresenter = new MainPresenter(getCampaigns);
    mainPresenter.attachView(mainMvpView);
  }

  @Test
  public void testAttachViewSuccess() {
    assertNotNull(mainPresenter.getMvpView());
  }

  @Test
  public void testDetachViewSuccess() {
    mainPresenter.detachView();
    assertNull(mainPresenter.getMvpView());
    verify(getCampaigns).unsubscribe();
  }

  @Test
  public void testInitializeSuccess() {
    mainPresenter.loadCampaigns();
    verify(getCampaigns).execute(any(DefaultSubscriber.class));
  }
}
