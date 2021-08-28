package com.campuscircle.app.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.github.gzuliyujiang.wheelpicker.DatePicker;
import com.github.gzuliyujiang.wheelpicker.annotation.DateMode;
import com.github.gzuliyujiang.wheelpicker.contract.OnDatePickedListener;
import com.github.gzuliyujiang.wheelpicker.entity.DateEntity;
import com.github.gzuliyujiang.wheelpicker.widget.DateWheelLayout;


/**
 * @ClassName choseDatePicker
 * @Description TODO
 * @Author SeanLim
 * @Date 2021-8-23 9:55
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class ChoseDatePicker implements OnDatePickedListener {
    private String mMonth, mDay,todayTime;

    private TextView mTextview;

    private Context mContext;

    private int time1, mTimeState;

    public String choseDatePicker(Activity context, TextView textView, int timeState) {
        mContext = context;
        mTimeState = timeState;
        DatePicker picker = new DatePicker(context);
        mTextview = textView;
        picker.setOnDatePickedListener(this::onDatePicked);
        picker.setBodyWidth(240);
        DateWheelLayout wheelLayout = picker.getWheelLayout();
        wheelLayout.setDateMode(DateMode.YEAR_MONTH_DAY);
//                wheelLayout.setDateLabel("年", "月", "日");
        wheelLayout.setRange(DateEntity.today(), DateEntity.yearOnFuture(80), null);
        wheelLayout.setCurtainEnabled(true);
        wheelLayout.setCurtainColor(0xffffff);
        wheelLayout.setIndicatorEnabled(true);
        wheelLayout.setIndicatorColor(0xffffff);
//        wheelLayout.setIndicatorSize(view.getResources().getDisplayMetrics().density * 2);
        wheelLayout.setSelectedTextColor(0XFF0587e9);
        wheelLayout.setTextSize(50);
        picker.show();


//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        todayTime = dateFormat.format(date);
//        time1 = Integer.parseInt(todayTime);

        return null;
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
        if (mTimeState == 1) {
            time1 = Integer.parseInt(year + "" + mMonth + "" + mDay);
            mTextview.setText(year + "." + mMonth + "." + mDay);
        } else {
            int time2 = Integer.parseInt(year + "" + mMonth + "" + mDay);
            if (time2 < time1) {
                UIHelper.toastMessage(mContext, "出租结束时间有误");
            } else {
                mTextview.setText(year + "." + mMonth + "." + mDay);
            }
        }

    }
}
