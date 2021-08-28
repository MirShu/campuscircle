package com.campuscircle.app.adapter.homeadapter;


import android.text.Html;

import androidx.recyclerview.widget.RecyclerView;

import com.campuscircle.app.model.ListModel;
import com.campuscircle.app.utils.showImageOptions;

import java.text.MessageFormat;
import java.util.List;

public class BaseBindViewHolder {

    public void onBindViewHolder0(RecyclerView.ViewHolder holder, List<ListModel.NewsInfoModelsDTO> listBeans) {
    }


    //实物
    public void onBindViewHolder1(RecyclerView.ViewHolder holder, List<ListModel.NewsInfoModelsDTO> listBeans) {
        final int pos = getRealPosition(holder);
        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
        ((BaseViewHolder) holder).tvUserName.setText(listBeans.get(pos).getNickname());
        ((BaseViewHolder) holder).tvTime.setText(listBeans.get(pos).getCreateDateTime());
        showImageOptions.imageGlideData(((BaseViewHolder) holder).ivUserHead, listBeans.get(pos).getAvatarUrl());
    }

    //虚拟
    public void onBindViewHolder2(RecyclerView.ViewHolder holder, List<ListModel.NewsInfoModelsDTO> listBeans) {
        final int pos = getRealPosition(holder);
        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
        ((BaseViewHolder) holder).tvUserName.setText(listBeans.get(pos).getNickname());
        ((BaseViewHolder) holder).tvTime.setText(listBeans.get(pos).getCreateDateTime());
        showImageOptions.imageGlideData(((BaseViewHolder) holder).ivUserHead, listBeans.get(pos).getAvatarUrl());

    }

    //出租
    public void onBindViewHolder3(RecyclerView.ViewHolder holder, List<ListModel.NewsInfoModelsDTO> listBeans) {
        final int pos = getRealPosition(holder);
        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
        ((BaseViewHolder) holder).tvUserName.setText(listBeans.get(pos).getNickname());
        ((BaseViewHolder) holder).tvTime.setText(listBeans.get(pos).getCreateDateTime());
        showImageOptions.imageGlideData(((BaseViewHolder) holder).ivUserHead, listBeans.get(pos).getAvatarUrl());
        ((BaseViewHolder) holder).tvWorkStartTime.setText(Html.fromHtml(MessageFormat.format("{0}~{1}", listBeans.get(pos).getWorkStartTime(), listBeans.get(pos).getWorkEndTime())));
        ((BaseViewHolder) holder).tvPrice.setText(String.valueOf(listBeans.get(pos).getPrice()));
        ((BaseViewHolder) holder).tvShort.setText(String.valueOf(listBeans.get(pos).getShortX()));

    }

    //拼车
    public void onBindViewHolder4(RecyclerView.ViewHolder holder, List<ListModel.NewsInfoModelsDTO> listBeans) {
        final int pos = getRealPosition(holder);
        ((BaseViewHolder) holder).tvUserName.setText(listBeans.get(pos).getNickname());
        ((BaseViewHolder) holder).tvTime.setText(listBeans.get(pos).getCreateDateTime());
        ((BaseViewHolder) holder).tvLastsite.setText(String.valueOf(listBeans.get(pos).getLastSite()));
        ((BaseViewHolder) holder).tvShort.setText(String.valueOf(listBeans.get(pos).getShortX()));
        ((BaseViewHolder) holder).tvWorkStartTime.setText(String.valueOf(listBeans.get(pos).getWorkStartTime()));
        showImageOptions.imageGlideData(((BaseViewHolder) holder).ivUserHead, listBeans.get(pos).getAvatarUrl());
        ((BaseViewHolder) holder).tvRoute.setText(Html.fromHtml(MessageFormat.format("{0}->{1}", listBeans.get(pos).getRouteStart(), listBeans.get(pos).getRouteEnd())));
    }

    //勤工俭学
    public void onBindViewHolder5(RecyclerView.ViewHolder holder, List<ListModel.NewsInfoModelsDTO> listBeans) {
        final int pos = getRealPosition(holder);
        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
        ((BaseViewHolder) holder).tvUserName.setText(listBeans.get(pos).getNickname());
        ((BaseViewHolder) holder).tvTime.setText(listBeans.get(pos).getCreateDateTime());
        showImageOptions.imageGlideData(((BaseViewHolder) holder).ivUserHead, listBeans.get(pos).getAvatarUrl());

    }

    //其他
    public void onBindViewHolder6(RecyclerView.ViewHolder holder, List<ListModel.NewsInfoModelsDTO> listBeans) {
        final int pos = getRealPosition(holder);
        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
        ((BaseViewHolder) holder).tvUserName.setText(listBeans.get(pos).getNickname());
        ((BaseViewHolder) holder).tvTime.setText(listBeans.get(pos).getCreateDateTime());
        showImageOptions.imageGlideData(((BaseViewHolder) holder).ivUserHead, listBeans.get(pos).getAvatarUrl());
        String s=listBeans.get(pos).getTitle();
    }


    // 获取条目的真实位置
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return position;
    }
}
