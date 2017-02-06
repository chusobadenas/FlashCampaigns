package com.flashcampaigns.app.presentation.product;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flashcampaigns.app.R;
import com.flashcampaigns.app.common.di.components.MainComponent;
import com.flashcampaigns.app.common.util.UIUtils;
import com.flashcampaigns.app.data.entity.Campaign;
import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.presentation.base.BaseFragment;
import com.flashcampaigns.app.presentation.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Product fragment
 */
public class ProductFragment extends BaseFragment implements ProductMvpView {

  @Inject
  ProductPresenter productPresenter;

  @BindView(R.id.content_product)
  LinearLayout productView;
  @BindView(R.id.products_list)
  RecyclerView productListView;
  @BindView(R.id.products_image_campaign)
  ImageView productCampaignImageView;
  @BindView(R.id.rl_progress)
  RelativeLayout progressView;
  @BindView(R.id.rl_retry)
  RelativeLayout retryView;

  private Campaign campaign;
  private Unbinder unbinder;

  /**
   * Creates a new instance of {@link ProductFragment}.
   */
  public static ProductFragment newInstance() {
    return new ProductFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getComponent(MainComponent.class).inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View fragmentView = inflater.inflate(R.layout.fragment_product, container, false);
    unbinder = ButterKnife.bind(this, fragmentView);
    productPresenter.attachView(this);
    return fragmentView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    // Get campaign
    this.campaign = (Campaign) getArguments().getSerializable("campaign");

    ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      // Change title
      actionBar.setTitle(campaign.name());
      // Enable back button
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    loadProducts();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    productPresenter.detachView();
  }

  @Override
  public void showLoading() {
    productView.setVisibility(View.GONE);
    progressView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    progressView.setVisibility(View.GONE);
    productView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showRetry() {
    productView.setVisibility(View.GONE);
    retryView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideRetry() {
    retryView.setVisibility(View.GONE);
    productView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showError(String message) {
    UIUtils.showToastMessage(context(), message);
  }

  @OnClick(R.id.bt_retry)
  void onButtonRetryClick() {
    hideRetry();
    loadProducts();
  }

  /**
   * Loads the list of products
   */
  private void loadProducts() {
    productPresenter.loadProducts(campaign.id());
  }

  @Override
  public void showProducts(List<Product> products) {
    // First display the image of the campaign
    UIUtils.loadImageUrl(context(), productCampaignImageView, campaign.imageUrl());
    // Populate the list
    ProductAdapter adapter = new ProductAdapter(context(), products);
    productListView.setAdapter(adapter);
    productListView.setLayoutManager(new LinearLayoutManager(context()));
    productListView.addItemDecoration(new DividerItemDecoration(context(), LinearLayout.VERTICAL));
  }

  @Override
  public Context context() {
    return getActivity();
  }
}
