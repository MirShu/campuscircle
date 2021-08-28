package com.campuscircle.app.activity.me;

import com.campuscircle.app.R;
import com.campuscircle.app.base.BaseActivity;

public class ActivitySm extends BaseActivity {
    @Override
    public Integer getLayout() {
        return R.layout.activity_sm;
    }

    @Override
    public void init() {
        tvTitle.setText("免责声明");
    }

}
