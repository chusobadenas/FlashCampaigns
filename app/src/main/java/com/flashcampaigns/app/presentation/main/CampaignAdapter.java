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
public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.CampaignHolder> {

  private final Context context;
  private final List<Campaign> campaigns;

  /**
   * Constructor
   *
   * @param context   the context
   * @param campaigns the list of campaigns
   */
  public CampaignAdapter(Context context, List<Campaign> campaigns) {
    this.context = context;
    this.campaigns = campaigns;
  }

  @Override
  public int getItemCount() {
    return campaigns.size();
  }

  @Override
  public CampaignHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // Inflate the layout and initialize the holder
    View view = LayoutInflater.from(context).inflate(R.layout.custom_campaign, parent, false);
    return new CampaignHolder(view);
  }

  @Override
  public void onBindViewHolder(CampaignHolder holder, int position) {
    // Get item
    Campaign campaign = campaigns.get(position);

    // Populate the row on the recycler view
    UIUtils.loadImageUrl(context, holder.imageView, campaign.imageUrl());
    holder.nameView.setText(campaign.name());
    holder.endDateView.setText(campaign.endDate().toString());
  }

  /**
   * View holder
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
}
