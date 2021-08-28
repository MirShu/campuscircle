package com.campuscircle.app.utils;

import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.campuscircle.app.R;
import com.campuscircle.app.adapter.homeadapter.BaseViewHolder;

import org.xutils.common.util.DensityUtil;
import org.xutils.x;

public class showImageOptions {
    public static void  imageData(ImageView imageView,String url){
        org.xutils.image.ImageOptions imageOptions = new org.xutils.image.ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
                .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
//                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher_round)//加载中默认显示图片
                .setFailureDrawableId(R.mipmap.ic_launcher_round)//加载失败后默认显示图片
                .build();
        x.image().bind(imageView, url, imageOptions);
    }





    public  static void imageGlideData(ImageView imageView,String url){
        Glide.with(imageView)
                .load(url)
                .thumbnail(Glide.with(imageView).load(R.mipmap.ic_launcher_round))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(imageView);

    }





}
