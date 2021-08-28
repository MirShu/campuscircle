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
 * @Date 2021-8-27 11:00
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class ActivityLogin extends BaseActivity {

    @BindView(R.id.ed_phone_num)
    EditText edPhoneNum;

    @BindView(R.id.ed_code)
    EditText edCode;

    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    @BindView(R.id.checkbox_peri)
    CheckBox checkboxPeri;

    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    @BindView(R.id.tv_code)
    TextView tvCode;


    private Animation animation;

    private CountDownTimer timer;

    @Override
    public Integer getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        animation = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
    }

    @OnClick({R.id.tv_get_code, R.id.tv_register_login, R.id.tv_register})
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
                if (edPhoneNum.getText().toString().isEmpty()) {
                    edPhoneNum.startAnimation(animation);
                    return;
                } else if (edCode.getText().toString().isEmpty()) {
                    edCode.startAnimation(animation);
                    return;
                } else if (!checkboxPeri.isChecked()) {
                    llBottom.startAnimation(animation);
                    return;
                }
                login();
                break;
            case R.id.tv_register:
                finish();
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
                        UIHelper.toastMessage(ActivityLogin.this, messageResult.getMessage());
                        tvCode.setText(messageResult.getMessage());
                    } else if (messageResult.getCode() == 4002) {
                        UIHelper.toastMessage(ActivityLogin.this, messageResult.getMessage());
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
     * 登录
     */
    private void login() {
        RequestParams params = new RequestParams(Api.LOGIN);
        JSONObject json = new JSONObject();
        try {
            json.put("Phone", edPhoneNum.getText().toString());
            json.put("Code", edCode.getText().toString());
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
                        UIHelper.toastMessage(ActivityLogin.this, messageResult.getMessage());
                    } else if (messageResult.getCode() == 4007) {
                        UIHelper.toastMessage(ActivityLogin.this, messageResult.getMessage());
                    } else if (messageResult.getCode() == 4004) {
                        UIHelper.toastMessage(ActivityLogin.this, messageResult.getMessage());
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
