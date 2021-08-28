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
import com.github.gzuliyujiang.wheelpicker.DatePicker;
import com.github.gzuliyujiang.wheelpicker.NumberPicker;
import com.github.gzuliyujiang.wheelpicker.annotation.DateMode;
import com.github.gzuliyujiang.wheelpicker.contract.OnDatePickedListener;
import com.github.gzuliyujiang.wheelpicker.contract.OnNumberPickedListener;
import com.github.gzuliyujiang.wheelpicker.entity.DateEntity;
import com.github.gzuliyujiang.wheelpicker.widget.DateWheelLayout;
import com.github.gzuliyujiang.wheelview.contract.WheelFormatter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName ActivityBaoChe
 * @Description TODO
 * @Author SeanLim
 * @Date 2021-8-12 13:27
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class ActivityBaoChe extends BaseActivity implements OnDatePickedListener, OnNumberPickedListener {
    String radioChooseStata;

    @BindView(R.id.tv_go_time)
    TextView tvGoTime;

    @BindView(R.id.tv_seat_num)
    TextView tvSeatNum;

    @BindView(R.id.tv_day_num)
    TextView tvDayNum;

    @BindView(R.id.ed_route_start)
    EditText edRouteStart;

    @BindView(R.id.edit_route_end)
    EditText editRouteEnd;

    @BindView(R.id.edit_short)
    EditText editShort;

    private int intShowDays = 1;
    private String mMonth, mDay;


    private String stringDatePicked, stringSeatNum;

    private int timeType;

    @Override
    public Integer getLayout() {
        return R.layout.activity_baoche;
    }

    @Override
    public void init() {
        Bundle bundle = this.getIntent().getExtras();
        radioChooseStata = bundle.getString("radioChooseStata");
        if (radioChooseStata.equals("1")) {
            tvTitle.setText("发布提供拼车、包车需求");
        } else {
            tvTitle.setText("发布想要拼/包车信息");
        }

        Date dateChose = new Date();
        SimpleDateFormat dateChoseFormat = new SimpleDateFormat("yyyy.MM.dd");
        tvGoTime.setText(dateChoseFormat.format(dateChose));
        stringDatePicked = dateChoseFormat.format(dateChose);
        stringSeatNum = String.valueOf(1);
        tvDayNum.setText(Html.fromHtml(MessageFormat.format("有效期：<font color=\"#403b58\">{0}</font>天", 1)));
    }


    @OnClick({R.id.tv_go_time, R.id.tv_seat_num, R.id.tv_ok, R.id.tv_add_day})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_go_time:
                choseDatePicker();
                break;

            case R.id.tv_seat_num:
                timeType = 2;
                choseSeatNum();
                break;

            case R.id.tv_ok:
                post();
                break;
            case R.id.tv_add_day:
                timeType = 1;
                onInteger();
                break;


        }
    }

    private void post() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
        if (TextUtils.isEmpty(edRouteStart.getText().toString())) {
            editShort.startAnimation(shake);
            UIHelper.showImageToast(this, "输入出发地");
            return;
        } else if (TextUtils.isEmpty(editRouteEnd.getText().toString())) {
            editShort.startAnimation(shake);
            UIHelper.showImageToast(this, "输入目的地");
            return;
        }
        if (TextUtils.isEmpty(editShort.getText().toString())) {
            editShort.startAnimation(shake);
            UIHelper.showImageToast(this, "请输入拼车备注");
            return;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
        map.put("NewsCategoryId", 4);
        map.put("NewsType", 10);
        map.put("Short", editShort.getText().toString());
        map.put("ShowDays", intShowDays);
        map.put("RouteStart", edRouteStart.getText().toString());
        map.put("RouteEnd", editRouteEnd.getText().toString());
        map.put("LastSite", stringSeatNum);
        map.put("WorkStartTime", stringDatePicked);
        list.add(map);
        Post.addBodyParameter(ActivityBaoChe.this, Api.ADD_NEWS, list);
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


    private void choseSeatNum() {
        NumberPicker picker = new NumberPicker(this);
        picker.setOnNumberPickedListener(this);
        picker.setFormatter(item -> item.toString() + "");
        picker.setRange(1, 3, 1);
        picker.setDefaultValue(1);
        picker.getTitleView().setText("剩余座位");
        picker.show();
    }


    private void choseDatePicker() {
        DatePicker picker = new DatePicker(this);
        picker.setOnDatePickedListener(this);
        picker.setBodyWidth(240);
        DateWheelLayout wheelLayout = picker.getWheelLayout();
        wheelLayout.setDateMode(DateMode.YEAR_MONTH_DAY);
        wheelLayout.setRange(DateEntity.today(), DateEntity.yearOnFuture(80), null);
        wheelLayout.setCurtainEnabled(true);
        wheelLayout.setCurtainColor(0xffffff);
        wheelLayout.setIndicatorEnabled(true);
        wheelLayout.setIndicatorColor(0xffffff);
        wheelLayout.setSelectedTextColor(0XFF0587e9);
        wheelLayout.setTextSize(50);
        picker.show();

    }


    @Override
    public void onDatePicked(int year, int month, int day) {
        if (month < 10) {
            mMonth = "0" + month;
        } else {
            mMonth = String.valueOf(month);
        }
        if (day < 10) {
            mDay = "0" + day;
        } else {
            mDay = String.valueOf(day);
        }
        tvGoTime.setText(year + "." + mMonth + "." + mDay);
        stringDatePicked = year + mMonth + mDay;
    }

    @Override
    public void onNumberPicked(int position, Number item) {
        if (timeType == 1) {
            intShowDays = item.intValue();
            tvDayNum.setText(Html.fromHtml(MessageFormat.format("有效期：<font color=\"#403b58\">{0}</font>天", item)));
        } else {
            tvSeatNum.setText(String.valueOf(item));
            stringSeatNum = String.valueOf(item);
        }
    }
}
