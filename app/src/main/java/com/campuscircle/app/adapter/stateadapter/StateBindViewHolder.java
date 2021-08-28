package com.campuscircle.app.adapter.stateadapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.campuscircle.app.model.ResultModel;

import java.util.List;

public class StateBindViewHolder {
    public void onBindViewHolder0(RecyclerView.ViewHolder holder, List<ResultModel.ResultBean> listBeans) {
    }

    public void onBindTYPE_MIDDLE_TWO(RecyclerView.ViewHolder holder, List<ResultModel.ResultBean> listBeans, Context context) {

    }


    //实物
    public void onBindViewHolder1(RecyclerView.ViewHolder holder, List<ResultModel.ResultBean> listBeans) {
        final int pos = getRealPosition(holder);
//        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
//        if (!TextUtils.isEmpty(listBeans.get(pos).getHeadline_img())) {
//            XImageOptions.imageData(((BaseViewHolder) holder).headlineImg, listBeans.get(pos).getHeadline_img());
//        } else {
//            XImageOptions.imageData(((BaseViewHolder) holder).headlineImg, "http://liveimg.miaobolive.com/pic/avator/201910/26/15/E31ED19F926F874063215984_640.png");
//        }
    }

    //虚拟
    public void onBindViewHolder2(RecyclerView.ViewHolder holder, List<ResultModel.ResultBean> listBeans) {
        final int pos = getRealPosition(holder);
//        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg, listBeans.get(pos).getHeadline_img());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg2, listBeans.get(pos).getHeadline_img());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg3, listBeans.get(pos).getHeadline_img());

    }

    //出租
    public void onBindViewHolder3(RecyclerView.ViewHolder holder, List<ResultModel.ResultBean> listBeans) {
        final int pos = getRealPosition(holder);
//        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
//        ((BaseViewHolder) holder).summary.setText(listBeans.get(pos).getSummary());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg, listBeans.get(pos).getHeadline_img());

    }

    //拼车
    public void onBindViewHolder4(RecyclerView.ViewHolder holder, List<ResultModel.ResultBean> listBeans) {
        final int pos = getRealPosition(holder);
//        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
//
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg, listBeans.get(pos).getHeadline_img());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg2, listBeans.get(pos).getHeadline_img());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg3, listBeans.get(pos).getHeadline_img());

    }

    //寄养
    public void onBindViewHolder5(RecyclerView.ViewHolder holder, List<ResultModel.ResultBean> listBeans) {
        final int pos = getRealPosition(holder);
//        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg, listBeans.get(pos).getHeadline_img());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg2, listBeans.get(pos).getHeadline_img());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg3, listBeans.get(pos).getHeadline_img());

    }

    //勤工俭学
    public void onBindViewHolder6(RecyclerView.ViewHolder holder, List<ResultModel.ResultBean> listBeans) {
        final int pos = getRealPosition(holder);
//        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
//        ((BaseViewHolder) holder).sourceTitle.setText(listBeans.get(pos).getSource_data().getTitle());
//        ((BaseViewHolder) holder).sourceSummary.setText(listBeans.get(pos).getSource_data().getSummary());
//        XImageOptions.imageData(((BaseViewHolder) holder).headlineImg, listBeans.get(pos).getHeadline_img());
//        XImageOptions.imageData(((BaseViewHolder) holder).sourceImage, listBeans.get(pos).getSource_data().getImage());

    }


    //其他
    public void onBindViewHolder7(RecyclerView.ViewHolder holder, List<ResultModel.ResultBean> listBeans) {
        final int pos = getRealPosition(holder);
//        ((BaseViewHolder) holder).title.setText(listBeans.get(pos).getTitle());
//        if (TextUtils.isEmpty(listBeans.get(pos).getHeadline_img())||listBeans.get(pos).getHeadline_img()==null) {
//            XImageOptions.imageData(((BaseViewHolder) holder).headlineImg, "http://2-im.guokr.com/BL-zs0S2cMtowORGmuKri_mYXjze4UY0ZkX-WGWn5Ug4BAAAvAEAAEpQ.jpg?imageView2/1/w/1080/h/444");
//        } else {
//            XImageOptions.imageData(((BaseViewHolder) holder).headlineImg, listBeans.get(pos).getHeadline_img());
//        }

    }


    // 获取条目的真实位置
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        if (position < 5) {
            return position - 1;
        }
        if (position > 5 && position < 10) {
            return position - 2;
        } else {
            return position - 3;
        }

    }
}
