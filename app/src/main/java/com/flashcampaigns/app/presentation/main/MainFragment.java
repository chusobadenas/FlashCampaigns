package com.flashcampaigns.app.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flashcampaigns.app.R;
import com.flashcampaigns.app.common.di.components.MainComponent;
import com.flashcampaigns.app.common.util.UIUtils;
import com.flashcampaigns.app.presentation.base.BaseFragment;

import java.util.List;

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

  @BindView(R.id.content_main)
  LinearLayout mainView;
  @BindView(R.id.campaigns_list)
  RecyclerView campaignsListView;
  @BindView(R.id.rl_progress)
  RelativeLayout progressView;
  @BindView(R.id.rl_retry)
  RelativeLayout retryView;
  @BindView(R.id.rl_empty)
  RelativeLayout emptyView;

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
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      // Change title
      actionBar.setTitle(R.string.app_name);
      // Disable back button
      actionBar.setDisplayHomeAsUpEnabled(false);
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    loadCampaigns();
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
    mainView.setVisibility(View.GONE);
    progressView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    progressView.setVisibility(View.GONE);
    mainView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showRetry() {
    mainView.setVisibility(View.GONE);
    retryView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideRetry() {
    retryView.setVisibility(View.GONE);
    mainView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showEmpty() {
    mainView.setVisibility(View.GONE);
    emptyView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideEmpty() {
    emptyView.setVisibility(View.GONE);
    mainView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showError(String message) {
    UIUtils.showToastMessage(context(), message);
  }

  @OnClick(R.id.bt_retry)
  void onButtonRetryClick() {
    hideRetry();
    loadCampaigns();
  }

  /**
   * Loads the list of campaigns
   */
  private void loadCampaigns() {
    mainPresenter.loadCampaigns();
  }

  @Override
  public void showCampaigns(List<Object> items) {
    // Populate the list
    CampaignAdapter adapter = new CampaignAdapter(context(), items);
    campaignsListView.setAdapter(adapter);
    campaignsListView.setLayoutManager(new LinearLayoutManager(context()));
    campaignsListView.addItemDecoration(new DividerItemDecoration(context(), LinearLayout.VERTICAL));
  }

  @Override
  public Context context() {
    return getActivity();
  }
}
