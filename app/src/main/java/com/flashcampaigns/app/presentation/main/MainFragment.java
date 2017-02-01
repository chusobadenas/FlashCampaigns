package com.flashcampaigns.app.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.flashcampaigns.app.R;
import com.flashcampaigns.app.common.di.components.MainComponent;
import com.flashcampaigns.app.common.util.UIUtils;
import com.flashcampaigns.app.presentation.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Main fragment
 */
public class MainFragment extends BaseFragment implements MainMvpView {

  @Inject
  MainPresenter mainPresenter;

  @BindView(R.id.rl_progress)
  RelativeLayout progressView;
  @BindView(R.id.rl_retry)
  RelativeLayout retryView;

  private Unbinder unbinder;

  /**
   * Creates a new instance of {@link MainFragment}.
   */
  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getComponent(MainComponent.class).inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
    unbinder = ButterKnife.bind(this, fragmentView);
    mainPresenter.attachView(this);
    return fragmentView;
  }

  @Override
  public void onStart() {
    super.onStart();
    initialize();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mainPresenter.detachView();
  }

  @Override
  public void showLoading() {
    progressView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    progressView.setVisibility(View.GONE);
  }

  @Override
  public void showRetry() {
    retryView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideRetry() {
    retryView.setVisibility(View.GONE);
  }

  @Override
  public void showError(String message) {
    UIUtils.showToastMessage(context(), message);
  }

  @OnClick(R.id.bt_retry)
  void onButtonRetryClick() {
    hideRetry();
    initialize();
  }

  private void initialize() {
    mainPresenter.initialize();
  }

  @Override
  public Context context() {
    return getActivity();
  }
}
