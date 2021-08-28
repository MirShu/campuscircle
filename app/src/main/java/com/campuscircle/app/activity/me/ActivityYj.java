package com.campuscircle.app.activity.me;

import com.campuscircle.app.R;
import com.campuscircle.app.base.BaseActivity;

public class ActivityYj extends BaseActivity {
    @Override
    public Integer getLayout() {
        return R.layout.activity_yj;
    }

    @Override
    public void init() {
        tvTitle.setText("意见反馈");
    }

}
