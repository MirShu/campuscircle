package com.campuscircle.app.activity.me;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.campuscircle.app.R;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.base.BaseActivity;
import com.campuscircle.app.model.DataModel;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.Global;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;


public class ActivityLx extends BaseActivity {
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    @Override
    public Integer getLayout() {
        return R.layout.activity_lx;
    }

    @Override
    public void init() {
        tvTitle.setText("管理联系方式");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("保存");
        getContactInfo();
    }

    private void getContactInfo() {
        RequestParams params = new RequestParams(Api.GETCONTACTINFO);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    MessageResult messageResult = MessageResult.parse(result);
                    if (messageResult.getCode() == 200) {
                        DataModel dataModel = JSONObject.parseObject(messageResult.getData(), DataModel.class);
                        tvPhone.setText(dataModel.getPhone());
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


}
