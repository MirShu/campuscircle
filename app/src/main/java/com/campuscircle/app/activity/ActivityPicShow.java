package com.campuscircle.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.campuscircle.app.R;
import com.lxj.xpopup.photoview.PhotoView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityPicShow extends Activity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.vp)
    ViewPager vp;

    @BindView(R.id.tv_state)
    TextView tvState;

    private ViewPagerAdapter pageAdapter;
    private List<View> views = new ArrayList<>();
    private ArrayList<String> mData;
    private String mCurrentImagePath;
    List<String> imagePath;
    private int loadTag = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_show);
        ButterKnife.bind(this);
        init();
        vp.setOnPageChangeListener(this);
    }

    public void init() {
        imagePath = new ArrayList<>();
        mData = getIntent().getExtras().getStringArrayList("imageArray");
        mCurrentImagePath = getIntent().getExtras().getString("currentImagePath");
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

        tvState.setText(Html.fromHtml(MessageFormat.format("{0}{1}", vp.getCurrentItem(),mData.size())));

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
        views.clear();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
            View view = View.inflate(ActivityPicShow.this, R.layout.item_pic_show, null);
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

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (loadTag == 3) {
        } else {
        }
        super.onDestroy();
    }
}
