package com.flashcampaigns.app.presentation.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flashcampaigns.app.R;
import com.flashcampaigns.app.common.util.UIUtils;
import com.flashcampaigns.app.data.entity.Campaign;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class defines the adapter of a {@link Campaign}
 */
public class CampaignAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int CAMPAIGN_STATUS_VIEW = 0;
  private static final int CAMPAIGN_VIEW = 1;

  private final Context context;
  private final List<Object> items;

  /**
   * Constructor
   *
   * @param context the context
   * @param items   the list of campaigns
   */
  public CampaignAdapter(Context context, List<Object> items) {
    this.context = context;
    this.items = items;
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  @Override
  public int getItemViewType(int position) {
    return items.get(position) instanceof Campaign ? CAMPAIGN_VIEW : CAMPAIGN_STATUS_VIEW;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    RecyclerView.ViewHolder holder = null;
    View view;

    switch (viewType) {
      case CAMPAIGN_STATUS_VIEW:
        view = LayoutInflater.from(context).inflate(R.layout.custom_campaign_status, parent, false);
        holder = new CampaignStatusHolder(view);
        break;
      case CAMPAIGN_VIEW:
        view = LayoutInflater.from(context).inflate(R.layout.custom_campaign, parent, false);
        holder = new CampaignHolder(view);
        break;
      default:
        break;
    }

    return holder;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    // Get item
    Object item = items.get(position);

    if (item instanceof String) {
      // Cast item
      String status = (String) item;
      // Set the status
      CampaignStatusHolder campaignStatusHolder = (CampaignStatusHolder) holder;
      campaignStatusHolder.statusView.setText(status);

    } else if (item instanceof Campaign) {
      // Cast item
      Campaign campaign = (Campaign) item;
      // Populate the row on the recycler view
      CampaignHolder campaignHolder = (CampaignHolder) holder;
      UIUtils.loadImageUrl(context, campaignHolder.imageView, campaign.imageUrl());
      campaignHolder.nameView.setText(campaign.name());
      campaignHolder.endDateView.setText(campaign.endDate().toString());
    }
  }

  /**
   * Campaign holder
   */
  class CampaignHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.campaign_image)
    ImageView imageView;
    @BindView(R.id.campaign_name)
    TextView nameView;
    @BindView(R.id.campaign_end_date)
    TextView endDateView;

    CampaignHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

  /**
   * Campaign status holder
   */
  class CampaignStatusHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.campaign_status)
    TextView statusView;

    CampaignStatusHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
