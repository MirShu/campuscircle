package com.campuscircle.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.campuscircle.app.activity.ActivityRegister;
import com.campuscircle.app.activity.ActivityPostDemand;
import com.campuscircle.app.activity.ActivitySearch;
import com.campuscircle.app.utils.AppsDataSetting;
import com.campuscircle.app.utils.Global;
import com.campuscircle.app.utils.StatusBarUtils;
import com.campuscircle.app.utils.UIHelper;
import com.campuscircle.app.widget.HomeFragmentPagerAdapter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    //UI Objects
    private RadioGroup rgTabBar;
    private RadioButton rgTabHomePage;
    private RadioButton rbTabbaractivity;
    private RadioButton rbTabbarOrder;
    private RadioButton rbTabbarMy;
    private ViewPager vpager;
    private Unbinder unbinder;
    private TextView tvHomeTopSum;
    private LinearLayout llHomeHead;
    private HomeFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    long secondTime = 0;

    protected AppsDataSetting appsDataSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.color_app_main);
        StatusBarUtils.setStatusBarLightMode(this);
        appsDataSetting = AppsDataSetting.getInstance();
        unbinder = ButterKnife.bind(this);
        llHomeHead = findViewById(R.id.ll_home_head);
        tvHomeTopSum = findViewById(R.id.tv_home_top_sum);
        mAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rgTabHomePage.setChecked(true);
    }


    private void bindViews() {
        rgTabBar = findViewById(R.id.rg_tab_bar);
        rgTabHomePage = findViewById(R.id.rb_home_page);
        rbTabbaractivity = findViewById(R.id.rb_tabbar_activity);
        rbTabbarOrder = findViewById(R.id.rb_tabbar_order);
        rbTabbarMy = findViewById(R.id.rb_tabbar_my);
        rgTabBar.setOnCheckedChangeListener(this);

        vpager = findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.setOffscreenPageLimit(4);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home_page:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_tabbar_activity:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_tabbar_order:
                if (TextUtils.isEmpty(appsDataSetting.read(Global.ACCESSTOKEN, ""))) {
                    UIHelper.startActivity(this, ActivityRegister.class);
                } else {
                    vpager.setCurrentItem(PAGE_THREE);
                }
                break;
            case R.id.rb_tabbar_my:
                if (TextUtils.isEmpty(appsDataSetting.read(Global.ACCESSTOKEN, ""))) {
                    UIHelper.startActivity(this, ActivityRegister.class);
                } else {
                    vpager.setCurrentItem(PAGE_FOUR);
                }
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }


    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    tvHomeTopSum.setText(R.string.string_home_top_sum1);
                    rgTabHomePage.setChecked(true);
                    llHomeHead.setVisibility(View.VISIBLE);
                    break;
                case PAGE_TWO:
                    rbTabbaractivity.setChecked(true);
                    tvHomeTopSum.setText(R.string.string_home_top_sum2);
                    llHomeHead.setVisibility(View.VISIBLE);
                    break;
                case PAGE_THREE:
                    if (!TextUtils.isEmpty(appsDataSetting.read(Global.ACCESSTOKEN, ""))) {
                        rbTabbarOrder.setChecked(true);
                        llHomeHead.setVisibility(View.GONE);
                    }
                    break;
                case PAGE_FOUR:
                    if (!TextUtils.isEmpty(appsDataSetting.read(Global.ACCESSTOKEN, ""))) {
                        rbTabbarMy.setChecked(true);
                        llHomeHead.setVisibility(View.GONE);
                    }
                    break;
            }
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


    @OnClick({R.id.iv_post_demand, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_post_demand:
                if (TextUtils.isEmpty(appsDataSetting.read(Global.ACCESSTOKEN, ""))) {
                    UIHelper.startActivity(this, ActivityRegister.class);
                } else {
                    UIHelper.startActivity(this, ActivityPostDemand.class);
                }

                break;

            case R.id.tv_search:
                UIHelper.startActivity(this, ActivitySearch.class);
                break;

        }
    }


    @Override
    public void onBackPressed() {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - secondTime > 2000) {
            secondTime = currentClickTime;
            UIHelper.toastMessage(this, getString(R.string.exit));
        } else {
            finish();
        }

    }

}