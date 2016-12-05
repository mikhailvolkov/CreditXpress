package com.mikhail.creditexpress.activities.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.creditexpress.PromotionInfo;
import com.mikhail.creditexpress.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class PromotionRecyclerAdapter extends RecyclerView.Adapter<PromotionRecyclerAdapter.ViewHolder>{
    private List<PromotionInfo> data;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sloganHeader;
        public TextView sloganBody;
        public ImageView promotionImage;
        public ImageView organizationImage;
        public Button additionalInfoBut;

        public ViewHolder(View v) {
            super(v);
            sloganHeader = (TextView) v.findViewById(R.id.slogan_header);
            sloganBody = (TextView) v.findViewById(R.id.slogan_body);
            promotionImage = (ImageView) v.findViewById(R.id.promotion_image);
            organizationImage = (ImageView) v.findViewById(R.id.organization_image);
            additionalInfoBut = (Button) v.findViewById(R.id.additional_info_button);
         }
    }

    public PromotionRecyclerAdapter(List<PromotionInfo> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public PromotionRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promotion_recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.sloganHeader.setText(data.get(position).getSloganHeader());
        holder.sloganBody.setText(data.get(position).getSloganBody());
        Picasso.with(context)
                .load(data.get(position).getPromotionImageLink())
                .into(holder.promotionImage);
        Picasso.with(context)
                .load(data.get(position).getOrganizationImageLink())
                .into(holder.organizationImage);
        holder.additionalInfoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.get(position).getAdditionalInfoLink()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
