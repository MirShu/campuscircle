package com.campuscircle.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.campuscircle.app.R;
import com.lxj.xpopup.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class PopuWindowShowPic {
    private ArrayList<String> mData;
    private String mCurrentImagePath;
    private View inflate;
    private Dialog dialog;
    private ViewPager vp;
    private ViewPagerAdapter pageAdapter;
    private Context mContext;
    private List<String> imagePath;

    public void showContentPopuWindow(Context context, String currentImagePath, ArrayList<String> imageArray) {
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.view_pic_dialog_layout, null);
        vp = inflate.findViewById(R.id.vp);
        mContext = context;
        imagePath = new ArrayList<>();
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框


        imagePath = new ArrayList<>();
        mData = imageArray;
        mCurrentImagePath = currentImagePath;
        imagePath.addAll(mData);
        int index = mData.indexOf(mCurrentImagePath);
        initMyPageAdapter();
        vp.setOffscreenPageLimit(1);
        vp.setCurrentItem(index);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    /***
     * 初始化viewpager适配器
     */
    private void initMyPageAdapter() {
        initPoint();
        if (pageAdapter == null) {
            pageAdapter = new ViewPagerAdapter();
            if (vp != null) {
                vp.setAdapter(pageAdapter);
            }
        } else {
            pageAdapter.notifyDataSetChanged();
        }
    }

    private void initPoint() {

    }


    private class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imagePath.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(mContext, R.layout.item_pic_show, null);
            PhotoView photoView = (PhotoView) view.findViewById(R.id.pic_pv);
            //单点事件
            photoView.setOnClickListener(view1 -> {
            });
            //长按事件
            photoView.setOnLongClickListener(view12 -> {
                return false;
            });

            Glide.with(photoView)
                    .load(imagePath.get(position))
                    .thumbnail(Glide.with(photoView).load(R.drawable.loading))
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                    .into(photoView);

            container.addView(view);

            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

    }

}
