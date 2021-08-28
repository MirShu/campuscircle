package com.campuscircle.app.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.campuscircle.app.R;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.base.BaseActivity;
import com.campuscircle.app.model.DataModel;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.Global;
import com.campuscircle.app.utils.UIHelper;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName ActivityLogin
 * @Description TODO
 * @Author SeanLim
 * @Date 2021-8-25 14:36
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class ActivityRegister extends BaseActivity {
    @BindView(R.id.ed_university_name)
    EditText edUniversityName;

    @BindView(R.id.ed_phone_num)
    EditText edPhoneNum;

    @BindView(R.id.ed_code)
    EditText edCode;

    @BindView(R.id.ed_recommendcode)
    EditText edRecommendCode;

    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    @BindView(R.id.checkbox_peri)
    CheckBox checkboxPeri;

    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;


    @BindView(R.id.tv_code)
    TextView tv_code;


    private Animation animation;

    private CountDownTimer timer;

    @Override
    public Integer getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void init() {
        animation = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
    }


    @OnClick({R.id.tv_get_code, R.id.tv_register_login, R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                if (edPhoneNum.getText().toString().isEmpty()) {
                    edPhoneNum.startAnimation(animation);
                    return;
                } else {
                    startTimer();
                    getCode();
                }
                break;
            case R.id.tv_register_login:
                if (edUniversityName.getText().toString().isEmpty()) {
                    edUniversityName.startAnimation(animation);
                    return;
                } else if (edPhoneNum.getText().toString().isEmpty()) {
                    edPhoneNum.startAnimation(animation);
                    return;
                } else if (edCode.getText().toString().isEmpty()) {
                    edCode.startAnimation(animation);
                    return;
                } else if (!checkboxPeri.isChecked()) {
                    llBottom.startAnimation(animation);
                    return;
                }
                registerLogin();
                break;
            case R.id.tv_login:
                UIHelper.startActivity(this, ActivityLogin.class);
                break;
        }
    }

    private void getCode() {
        RequestParams params = new RequestParams(Api.GET_CODE);
        JSONObject json = new JSONObject();
        try {
            json.put("Phone", edPhoneNum.getText().toString());
        } catch (Exception e) {

        }
        params.setAsJsonContent(true);
        params.setBodyContent(json.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    MessageResult messageResult = MessageResult.parse(result);
                    if (messageResult.getCode() == 200) {
                        UIHelper.toastMessage(ActivityRegister.this, messageResult.getMessage());
                        tv_code.setText(messageResult.getMessage());
                    } else if (messageResult.getCode() == 4002) {
                        UIHelper.toastMessage(ActivityRegister.this, messageResult.getMessage());
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 注册登录最后一步
     */
    private void registerLogin() {
        RequestParams params = new RequestParams(Api.REGISTER);
        JSONObject json = new JSONObject();
        try {
            json.put("Phone", edPhoneNum.getText().toString());
            json.put("Code", edCode.getText().toString());
            json.put("RecommendCode", edRecommendCode.getText().toString());
            json.put("UniversityId", edUniversityName.getText().toString());
        } catch (Exception e) {

        }
        params.setAsJsonContent(true);
        params.setBodyContent(json.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    MessageResult messageResult = MessageResult.parse(result);
                    if (messageResult.getCode() == 200) {
                        DataModel dataModel = JSON.parseObject(messageResult.getData(), DataModel.class);
                        appsDataSetting.write(Global.ACCESSTOKEN, dataModel.getAccessToken());
                        appsDataSetting.write(Global.REWARDPOINT, String.valueOf(dataModel.getRewardPoint()));
                        appsDataSetting.write(Global.NICKNAME, dataModel.getNickname());
                        appsDataSetting.write(Global.AVATARURL, dataModel.getAvatarUrl());
                        appsDataSetting.write(Global.AVATARPICTUREID, String.valueOf(dataModel.getAvatarPictureId()));
                        appsDataSetting.write(Global.AUDITSTATUS, dataModel.getAuditStatus());
                        appsDataSetting.write(Global.REMARK, dataModel.getRemark());
                        finish();
                        timer.cancel();
                    } else if (messageResult.getCode() == 4002) {
                        UIHelper.toastMessage(ActivityRegister.this, messageResult.getMessage());
                    } else if (messageResult.getCode() == 4007) {
                        UIHelper.toastMessage(ActivityRegister.this, messageResult.getMessage());
                    } else if (messageResult.getCode() == 4004) {
                        UIHelper.toastMessage(ActivityRegister.this, messageResult.getMessage());
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void startTimer() {
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimerUI(millisUntilFinished);
                tvGetCode.setClickable(false);
            }

            @Override
            public void onFinish() {
                tvGetCode.setClickable(true);
                tvGetCode.setEnabled(true);
                tvGetCode.setText("发送验证码");
            }
        };
        timer.start();
    }

    private void updateTimerUI(final long millisUntilFinished) {
        tvGetCode.setText(String.format("%s秒后再次发送", millisUntilFinished / 1000));
    }


    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }
}
