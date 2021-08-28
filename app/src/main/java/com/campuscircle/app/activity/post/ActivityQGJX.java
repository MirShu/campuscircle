package com.campuscircle.app.activity.post;

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
import com.github.gzuliyujiang.wheelpicker.TimePicker;
import com.github.gzuliyujiang.wheelpicker.annotation.TimeMode;
import com.github.gzuliyujiang.wheelpicker.contract.OnNumberPickedListener;
import com.github.gzuliyujiang.wheelpicker.contract.OnTimePickedListener;
import com.github.gzuliyujiang.wheelpicker.entity.TimeEntity;
import com.github.gzuliyujiang.wheelpicker.impl.UnitTimeFormatter;
import com.github.gzuliyujiang.wheelpicker.widget.TimeWheelLayout;
import com.github.gzuliyujiang.wheelview.contract.WheelFormatter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName ActivityQGJX
 * @Description TODO
 * @Author SeanLim
 * @Date 2021-8-12 13:27
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class ActivityQGJX extends BaseActivity implements OnTimePickedListener, OnNumberPickedListener {
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;

    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    private String mHour, mMinute;
    private String strStartTime, strEndTime;

    private String stringTime;

    private int timeState;


    @BindView(R.id.tv_day_num)
    TextView tvDayNum;

    @BindView(R.id.tv_show_news_setting_model)
    TextView tvShowNewsSettingModel;

    @BindView(R.id.ed_job_info)
    EditText edJobInfo;
    @BindView(R.id.edit_salary)
    EditText editSalary;
    @BindView(R.id.ed_short)
    EditText edShort;
    @BindView(R.id.edit_basicrequirements)
    EditText editBasicrequirements;
    private int intShowDays = 1;

    @Override
    public Integer getLayout() {
        return R.layout.activity_qgjx;
    }

    @Override
    public void init() {
        tvTitle.setText("发布勤工俭学");
        Calendar calendar = Calendar.getInstance();
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));   //修改时区
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");  //HH:24小时制  hh:12小时制
        String date = dateFormat.format(new Date());
        tvStartTime.setText(date);
        tvEndTime.setText(date);
        tvDayNum.setText(Html.fromHtml(MessageFormat.format("有效期：<font color=\"#403b58\">{0}</font>天", 1)));
        tvShowNewsSettingModel.setText(Html.fromHtml(MessageFormat.format("需要消耗积分：<font color=\"#403b58\">{0}</font>", String.valueOf(appsDataSetting.read(Global.InKindGiveRecords, "")))));

    }

    @OnClick({R.id.tv_start_time, R.id.tv_end_time, R.id.tv_ok, R.id.tv_add_day})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_time:
                timeState = 1;
                showWorkTime();
                break;
            case R.id.tv_end_time:
                timeState = 2;
                showWorkTime();
                break;
            case R.id.tv_ok:
                post();
                break;

            case R.id.tv_add_day:
                onInteger();
                break;
        }
    }

    private void showWorkTime() {
        TimePicker picker = new TimePicker(this);
        picker.setOnTimePickedListener(this);
        picker.getWheelLayout().setTimeMode(TimeMode.HOUR_24_NO_SECOND);
        picker.getWheelLayout().setTimeFormatter(new UnitTimeFormatter());
        picker.getWheelLayout().setDefaultValue(TimeEntity.now());
        picker.show();
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


    @Override
    public void onTimePicked(int hour, int minute, int second) {

        if (hour < 10) {
            mHour = "0" + hour;
        } else {
            mHour = "" + hour;
        }

        if (minute < 10) {
            mMinute = "0" + minute;
        } else {
            mMinute = "" + minute;
        }
        stringTime = mHour + ":" + mMinute;
        if (timeState == 1) {
            tvStartTime.setText(stringTime);
            strStartTime = stringTime;
        } else {
            tvEndTime.setText(stringTime);
            strEndTime = stringTime;
        }
    }
    private void post() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
        if (TextUtils.isEmpty(edJobInfo.getText().toString())) {
            edJobInfo.startAnimation(shake);
            UIHelper.showImageToast(ActivityQGJX.this, "请输入岗位名称");
            return;
        } else if (TextUtils.isEmpty(editSalary.getText().toString())) {
            UIHelper.showImageToast(ActivityQGJX.this, "请输入薪资待遇");
            editSalary.startAnimation(shake);
            return;
        } else if (TextUtils.isEmpty(editBasicrequirements.getText().toString())) {
            UIHelper.showImageToast(ActivityQGJX.this, "输入岗位要求");
            editBasicrequirements.startAnimation(shake);
            return;
        }

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
        map.put("WorkStartTime", strStartTime);
        map.put("WorkEndTime", strEndTime);
        map.put("NewsCategoryId", 5);
        map.put("NewsType", 10);
        map.put("JobInfo", edJobInfo.getText().toString());
        map.put("ShowDays", intShowDays);
        map.put("Salary", editSalary.getText().toString());
        map.put("BasicRequirements", editBasicrequirements.getText().toString());
        map.put("Short", edShort.getText().toString());
        list.add(map);
        Post.addBodyParameter(ActivityQGJX.this, Api.ADD_NEWS, list);
    }
}
