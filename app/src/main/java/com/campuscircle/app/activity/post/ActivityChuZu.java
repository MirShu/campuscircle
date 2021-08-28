package com.campuscircle.app.activity.post;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.PrecomputedText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.core.text.PrecomputedTextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campuscircle.app.R;
import com.campuscircle.app.adapter.recycler.RecyclerAdapter;
import com.campuscircle.app.adapter.recycler.RecyclerViewHolder;
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
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
 * @ClassName ActivityChuZu
 * @Description TODO
 * @Author SeanLim
 * @Date 2021-8-12 13:27
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class ActivityChuZu extends BaseActivity implements OnDatePickedListener, OnNumberPickedListener {
    String radioChooseStata;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    private String mMonth, mDay, todayTime;
    private int workStartTime, workEndTime, timeState;
    @BindView(R.id.iv_add_image)
    ImageView ivAddImage;

    @BindView(R.id.recyclerView_image)
    RecyclerView recyclerViewImage;

    @BindView(R.id.ed_goods_name)
    EditText edGoodsName;

    @BindView(R.id.edit_price)
    EditText editPrice;

    @BindView(R.id.edit_sum)
    EditText editSum;

    private String name;
    private String price;
    private String sum;

    private String uriImage;

    private PictureBean pictureBean;

    @BindView(R.id.tv_day_num)
    TextView tvDayNum;

    @BindView(R.id.tv_show_news_setting_model)
    TextView tvShowNewsSettingModel;

    private int intShowDays = 1;


    @Override
    public Integer getLayout() {
        return R.layout.activity_chuzu;
    }

    @Override
    public void init() {
        Bundle bundle = this.getIntent().getExtras();
        radioChooseStata = bundle.getString("radioChooseStata");
        if (radioChooseStata.equals("1")) {
            tvTitle.setText("发布提供出租物品信息");
        } else {
            tvTitle.setText("发布求租信息");
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        todayTime = dateFormat.format(date);
        workStartTime = Integer.parseInt(todayTime);
        Date dateChose = new Date();
        SimpleDateFormat dateChoseFormat = new SimpleDateFormat("yyyy.MM.dd");
        tvStartTime.setText(dateChoseFormat.format(dateChose));
        tvEndTime.setText(dateChoseFormat.format(dateChose));
        tvDayNum.setText(Html.fromHtml(MessageFormat.format("有效期：<font color=\"#403b58\">{0}</font>天", 1)));
        tvShowNewsSettingModel.setText(Html.fromHtml(MessageFormat.format("需要消耗积分：<font color=\"#403b58\">{0}</font>", String.valueOf(appsDataSetting.read(Global.InKindGiveRecords, "")))));

    }

    @OnClick({R.id.tv_start_time, R.id.tv_end_time, R.id.iv_add_image, R.id.tv_ok, R.id.tv_add_day})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_time:
                timeState = 1;
                choseDatePicker();
                break;

            case R.id.tv_end_time:
                timeState = 2;
                choseDatePicker();
                break;


            case R.id.iv_add_image:
                PictureSelector
                        .create(ActivityChuZu.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false);

//                FilePickerBuilder.getInstance()
//                        .setMaxCount(5) //optional
//                        .setSelectedFiles(filePaths) //optional
//                        .setActivityTheme(R.style.LibAppTheme) //optional
//                        .pickPhoto(this);

                break;


            case R.id.tv_ok:
                post();
                break;

            case R.id.tv_add_day:
                onInteger();
                break;

        }
    }

    private void choseDatePicker() {
        DatePicker picker = new DatePicker(this);
        picker.setOnDatePickedListener(this);
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

        if (timeState == 1) {
            workStartTime = Integer.parseInt(year + "" + mMonth + "" + mDay);
            tvStartTime.setText(year + "." + mMonth + "." + mDay);
        } else {
            workEndTime = Integer.parseInt(year + "" + mMonth + "" + mDay);
            if (workEndTime < workStartTime) {
                UIHelper.toastMessage(this, "出租结束时间有误");
            } else {
                tvEndTime.setText(year + "." + mMonth + "." + mDay);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/

        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                if (pictureBean.isCut()) {
                    ivAddImage.setImageBitmap(BitmapFactory.decodeFile(pictureBean.getPath()));
                } else {
                    ivAddImage.setImageURI(pictureBean.getUri());
                }
                uriImage = pictureBean.getPath();
            }
        }
    }


    private RecyclerAdapter recyclerAdapter;
    private List<String> RollReycList;

    private void showImage() {
        RollReycList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RollReycList.add("" + i);
        }
        recyclerViewImage.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false));
        this.recyclerAdapter = new RecyclerAdapter<String>(this, RollReycList,
                R.layout.item_add_image) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
                ImageView imDelet = helper.getView(R.id.im_delet);
                imDelet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.toastMessage(ActivityChuZu.this, "" + position);
                    }
                });
            }
        };

        recyclerViewImage.setAdapter(recyclerAdapter);
    }

    private void post() {
        name = edGoodsName.getText().toString();
        price = editPrice.getText().toString();
        sum = editSum.getText().toString();
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
        if (TextUtils.isEmpty(name)) {
            edGoodsName.startAnimation(shake);
            UIHelper.showImageToast(ActivityChuZu.this, "请输入商品名称");
            return;
        } else if (TextUtils.isEmpty(price)) {
            UIHelper.showImageToast(ActivityChuZu.this, "请输入商品价格");
            editPrice.startAnimation(shake);
            return;
        } else if (TextUtils.isEmpty(sum)) {
            UIHelper.showImageToast(ActivityChuZu.this, "请输入描述信息");
            editSum.startAnimation(shake);
            return;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("File", new File(uriImage));
        map.put("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
        map.put("Title", edGoodsName.getText().toString());
        map.put("WorkStartTime", workStartTime);
        map.put("WorkEndTime", workEndTime);
        map.put("NewsCategoryId", 3);
        map.put("NewsType", 10);
        map.put("Short", editSum.getText().toString());
        map.put("ShowDays", intShowDays);
        map.put("Price", editPrice.getText().toString());
        list.add(map);
        Post.addBodyParameter(ActivityChuZu.this, Api.ADD_NEWS, list);
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
    protected void onDestroy() {

        super.onDestroy();
    }
}
