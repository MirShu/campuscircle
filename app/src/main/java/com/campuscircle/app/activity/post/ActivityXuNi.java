package com.campuscircle.app.activity.post;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.campuscircle.app.R;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.base.BaseActivity;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.Global;
import com.campuscircle.app.utils.Post;
import com.campuscircle.app.utils.UIHelper;
import com.github.gzuliyujiang.wheelpicker.NumberPicker;
import com.github.gzuliyujiang.wheelpicker.contract.OnNumberPickedListener;
import com.github.gzuliyujiang.wheelview.contract.WheelFormatter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityXuNi extends BaseActivity implements OnNumberPickedListener {
    String radioChooseStata;
    @BindView(R.id.tv_day_num)
    TextView tvDayNum;
    @BindView(R.id.edit_short)
    EditText editShort;

    private int intShowDays = 1;

    @BindView(R.id.tv_show_news_setting_model)
    TextView tvShowNewsSettingModel;

    @Override
    public Integer getLayout() {
        return R.layout.activity_xuni;
    }

    @Override
    public void init() {
        Bundle bundle = this.getIntent().getExtras();
        radioChooseStata = bundle.getString("radioChooseStata");
        if (radioChooseStata.equals("1")) {
            tvTitle.setText("发布提供虚拟物品信息");
        } else {
            tvTitle.setText("发布想要虚拟物品信息");
        }
        tvDayNum.setText(Html.fromHtml(MessageFormat.format("有效期：<font color=\"#403b58\">{0}</font>天", 1)));
        tvShowNewsSettingModel.setText(Html.fromHtml(MessageFormat.format("需要消耗积分：<font color=\"#403b58\">{0}</font>", String.valueOf(appsDataSetting.read(Global.VirtualGiveRecords, "")))));
    }


    @OnClick({R.id.tv_ok, R.id.tv_add_day})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                post();
                break;

            case R.id.tv_add_day:
                onInteger();
                break;

        }
    }

    private void post() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
        if (TextUtils.isEmpty(editShort.getText().toString())) {
            editShort.startAnimation(shake);
            UIHelper.showImageToast(this, "请输入商品描述");
            return;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
        map.put("NewsCategoryId", 2);
        map.put("NewsType", 10);
        map.put("Short", editShort.getText().toString());
        map.put("ShowDays", intShowDays);
        list.add(map);
        Post.addBodyParameter(ActivityXuNi.this, Api.ADD_NEWS, list);
    }


    public void onInteger() {
        NumberPicker picker = new NumberPicker(this);
        picker.setOnNumberPickedListener(this);
        picker.setFormatter(new WheelFormatter() {
            @Override
            public String formatItem(@NonNull Object item) {
                return item.toString() + " 天";
            }
        });
        picker.setRange(1, 20, 1);
        picker.setDefaultValue(1);
        picker.getTitleView().setText("增加天数");
        picker.show();
    }

    @Override
    public void onNumberPicked(int position, Number item) {
        intShowDays = item.intValue();
        tvDayNum.setText(Html.fromHtml(MessageFormat.format("有效期：<font color=\"#403b58\">{0}</font>天", item)));
    }

}
