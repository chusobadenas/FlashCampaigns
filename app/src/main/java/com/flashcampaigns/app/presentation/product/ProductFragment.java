package com.flashcampaigns.app.presentation.product;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.flashcampaigns.app.R;
import com.flashcampaigns.app.common.di.components.MainComponent;
import com.flashcampaigns.app.common.util.UIUtils;
import com.flashcampaigns.app.data.entity.Product;
import com.flashcampaigns.app.presentation.base.BaseFragment;

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
  GridView productGridView;
  @BindView(R.id.rl_progress)
  RelativeLayout progressView;
  @BindView(R.id.rl_retry)
  RelativeLayout retryView;

  private int campaignId;
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
    this.campaignId = getArguments().getInt("campaignId");
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
    productGridView.setVisibility(View.GONE);
    progressView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    progressView.setVisibility(View.GONE);
    productGridView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showRetry() {
    productGridView.setVisibility(View.GONE);
    retryView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideRetry() {
    retryView.setVisibility(View.GONE);
    productGridView.setVisibility(View.VISIBLE);
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
    productPresenter.loadProducts(campaignId);
  }

  @Override
  public void showProducts(List<Product> products) {
    // Populate the grid
    ProductAdapter adapter = new ProductAdapter(context(), products);
    productGridView.setAdapter(adapter);
  }

  @Override
  public Context context() {
    return getActivity();
  }
}
