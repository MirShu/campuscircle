package com.campuscircle.app.activity.me;

import com.campuscircle.app.R;
import com.campuscircle.app.base.BaseActivity;

public class ActivityGy extends BaseActivity {
    @Override
    public Integer getLayout() {
        return R.layout.activity_gy;
    }

    @Override
    public void init() {
        tvTitle.setText("关于我们");
    }

}
