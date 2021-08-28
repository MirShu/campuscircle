package com.campuscircle.app.adapter.homeadapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.campuscircle.app.R;
import com.makeramen.roundedimageview.RoundedImageView;


public class BaseViewHolder extends RecyclerView.ViewHolder {
    public TextView title,tvUserName,tvTime,tvRoute,tvLastsite,tvShort,tvWorkStartTime,tvPrice;
    public RoundedImageView ivUserHead;


    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_goods_name);
        tvUserName = itemView.findViewById(R.id.tv_user_name);
        ivUserHead = itemView.findViewById(R.id.iv_user_head);
        tvTime = itemView.findViewById(R.id.tv_user_name_time);
        tvRoute = itemView.findViewById(R.id.tv_route);

        tvLastsite = itemView.findViewById(R.id.tv_lastsite);
        tvShort = itemView.findViewById(R.id.tv_short);
        tvWorkStartTime = itemView.findViewById(R.id.tv_work_start_time);
        tvPrice = itemView.findViewById(R.id.tv_price);

    }
}
