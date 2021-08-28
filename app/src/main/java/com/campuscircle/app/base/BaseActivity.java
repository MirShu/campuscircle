package com.campuscircle.app.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.campuscircle.app.R;
import com.campuscircle.app.utils.AppsDataSetting;
import com.campuscircle.app.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.tv_back)
    public TextView tvBack;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_right)
    public TextView tvRight;
    @BindView(R.id.rl_head)
    public RelativeLayout rlHead;
    private Unbinder unbinder;
    public Bundle InstanceState;
    protected AppsDataSetting appsDataSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baselayout);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.color_app_main);
        StatusBarUtils.setStatusBarLightMode(this);
        appsDataSetting = AppsDataSetting.getInstance();
        initView();
        unbinder = ButterKnife.bind(this);
        init();
        InstanceState = savedInstanceState;
    }

    private void initView() {
        View view = LayoutInflater.from(this).inflate(getLayout(), null);
        FrameLayout layout = findViewById(R.id.baseLayout);
        layout.addView(view);
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
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public abstract Integer getLayout();

    public abstract void init();

    @OnClick(R.id.tv_back)
    public void onClick() {
        finish();
    }
}
