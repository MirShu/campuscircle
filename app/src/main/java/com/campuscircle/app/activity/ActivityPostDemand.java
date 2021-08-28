package com.campuscircle.app.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.campuscircle.app.R;
import com.campuscircle.app.activity.post.ActivityBaoChe;
import com.campuscircle.app.activity.post.ActivityChuZu;
import com.campuscircle.app.activity.post.ActivityQGJX;
import com.campuscircle.app.activity.post.ActivityQiTa;
import com.campuscircle.app.activity.post.ActivityShiWu;
import com.campuscircle.app.activity.post.ActivityXuNi;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.base.BaseActivity;
import com.campuscircle.app.model.DataModel;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.Global;
import com.campuscircle.app.utils.Post;
import com.campuscircle.app.utils.UIHelper;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityPostDemand extends BaseActivity {
    @BindView(R.id.tv_shiwu)
    TextView tvShiWu;
    @BindView(R.id.tv_xuni)
    TextView tvXuNi;
    @BindView(R.id.tv_chuzu)
    TextView tvChuZu;
    @BindView(R.id.tv_baoche)
    TextView tvBaoChe;
    @BindView(R.id.tv_qgjx)
    TextView tvQgjx;
    @BindView(R.id.tv_qita)
    TextView tvQita;

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.radio_button1)
    RadioButton radioButton1;

    @BindView(R.id.radio_button2)
    RadioButton radioButton2;
    private int postStata = 0;
    private int radioChooseStata = 1;


    @Override
    public Integer getLayout() {
        return R.layout.activity_post_demand;
    }

    @Override
    public void init() {
        tvTitle.setText("发布需求消息");
        radioButton1.setChecked(true);
        radioChooseNo(radioButton2);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radio_button1:
                    radioChoose(radioButton1);
                    radioChooseNo(radioButton2);
                    radioChooseStata = 1;
                    break;
                case R.id.radio_button2:
                    radioChoose(radioButton2);
                    radioChooseNo(radioButton1);
                    radioChooseStata = 2;
                    break;
            }
        });
        /**
         * 获取咨询配置项
         */
        Post.showNewsSettingModel();
    }

    private void radioChooseNo(RadioButton radioButton) {
        Resources resources = getBaseContext().getResources();
        ColorStateList colorStateList1 = resources.getColorStateList(R.color.color_403b58);
        radioButton.setTextColor(colorStateList1);
    }

    private void radioChoose(RadioButton radioButton) {
        Resources resource = getBaseContext().getResources();
        ColorStateList colorStateList = resource.getColorStateList(R.color.color_ffffff);
        radioButton.setTextColor(colorStateList);

    }


    @OnClick({R.id.tv_shiwu, R.id.tv_xuni, R.id.tv_chuzu, R.id.tv_baoche, R.id.tv_qgjx, R.id.tv_qita, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shiwu:
                showClickChose(1);
                postStata = 1;
                break;
            case R.id.tv_xuni:
                showClickChose(2);
                postStata = 2;
                break;

            case R.id.tv_chuzu:
                showClickChose(3);
                postStata = 3;
                break;

            case R.id.tv_baoche:
                showClickChose(4);
                postStata = 4;
                break;

            case R.id.tv_qgjx:
                showClickChose(5);
                postStata = 5;
                break;

            case R.id.tv_qita:
                showClickChose(6);
                postStata = 6;
                break;

            case R.id.tv_next:
                if (radioChooseStata == 1) {
                    if (postStata == 0) {
                        UIHelper.showImageToast(ActivityPostDemand.this, "请选择需求类别");
                        animationshow();
                    } else {
                        postNext(radioChooseStata);

                    }
                } else {
                    if (postStata == 0) {
                        UIHelper.showImageToast(ActivityPostDemand.this, "请选择想要需求类别");
                        animationshow();
                    } else {
                        postNext(radioChooseStata);
                    }
                }

                break;

        }
    }

    private void postNext(int radioChooseStata) {
        Bundle bundle = new Bundle();
        bundle.putString("radioChooseStata", String.valueOf(radioChooseStata));
        if (postStata == 1) {
            UIHelper.startActivity(this, ActivityShiWu.class, bundle);
        } else if (postStata == 2) {
            UIHelper.startActivity(this, ActivityXuNi.class, bundle);
        } else if (postStata == 3) {
            UIHelper.startActivity(this, ActivityChuZu.class, bundle);
        } else if (postStata == 4) {
            UIHelper.startActivity(this, ActivityBaoChe.class, bundle);
        } else if (postStata == 5) {
            UIHelper.startActivity(this, ActivityQGJX.class, bundle);
        } else if (postStata == 6) {
            UIHelper.startActivity(this, ActivityQiTa.class, bundle);
        }
    }

    private void animationshow() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
        tvShiWu.startAnimation(shake);
        tvXuNi.startAnimation(shake);
        tvChuZu.startAnimation(shake);
        tvBaoChe.startAnimation(shake);
        tvQgjx.startAnimation(shake);
        tvQita.startAnimation(shake);

    }

    private void showClickChose(int state) {
        if (state == 1) {
            chose(tvShiWu);
            choseno(tvXuNi);
            choseno(tvChuZu);
            choseno(tvBaoChe);
            choseno(tvQgjx);
            choseno(tvQita);
        } else if (state == 2) {
            choseno(tvShiWu);
            chose(tvXuNi);
            choseno(tvChuZu);
            choseno(tvBaoChe);
            choseno(tvQgjx);
            choseno(tvQita);
        } else if (state == 3) {
            choseno(tvShiWu);
            choseno(tvXuNi);
            chose(tvChuZu);
            choseno(tvBaoChe);
            choseno(tvQgjx);
            choseno(tvQita);
        } else if (state == 4) {
            choseno(tvShiWu);
            choseno(tvXuNi);
            choseno(tvChuZu);
            chose(tvBaoChe);
            choseno(tvQgjx);
            choseno(tvQita);
        } else if (state == 5) {
            choseno(tvShiWu);
            choseno(tvXuNi);
            choseno(tvChuZu);
            choseno(tvBaoChe);
            chose(tvQgjx);
            choseno(tvQita);
        } else if (state == 6) {
            choseno(tvShiWu);
            choseno(tvXuNi);
            choseno(tvChuZu);
            choseno(tvBaoChe);
            choseno(tvQgjx);
            chose(tvQita);
        }


    }


    private void choseno(TextView textView) {
        textView.setBackgroundResource(R.drawable.shape_post_tv_chose_bg);
        Resources resource2 = getBaseContext().getResources();
        ColorStateList colorStateList2 = resource2.getColorStateList(R.color.color_403b58);
        textView.setTextColor(colorStateList2);
    }


    private void chose(TextView textView) {
        textView.setBackgroundResource(R.drawable.shape_post_next);
        Resources resource = getBaseContext().getResources();
        ColorStateList colorStateList = resource.getColorStateList(R.color.color_ffffff);
        textView.setTextColor(colorStateList);

    }

}
