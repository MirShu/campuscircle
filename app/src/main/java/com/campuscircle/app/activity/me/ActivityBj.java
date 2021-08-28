package com.campuscircle.app.activity.me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.campuscircle.app.R;
import com.campuscircle.app.activity.post.ActivityChuZu;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.base.BaseActivity;
import com.campuscircle.app.model.DataModel;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.Global;
import com.campuscircle.app.utils.UIHelper;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityBj extends BaseActivity {
    @BindView(R.id.iv_user_head)
    RoundedImageView ivUserHead;
    @BindView(R.id.ed_nick)
    EditText edNick;
    private PictureBean pictureBean;

    @Override
    public Integer getLayout() {
        return R.layout.activity_bj;
    }

    @Override
    public void init() {
        tvTitle.setText("编辑用户信息");
        tvRight.setText("保存");
        tvRight.setVisibility(View.VISIBLE);
        edNick.setHint(appsDataSetting.read(Global.NICKNAME, ""));

    }


    @OnClick({R.id.iv_user_head, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_head:
                userHead();
                break;

            case R.id.tv_right:
                if (TextUtils.isEmpty(edNick.getText().toString())) {
                    UIHelper.toastMessage(this, "用户名不能为空");
                } else {
                    editCustomerInfo();
                }
                break;
        }
    }

    private void userHead() {
        PictureSelector
                .create(ActivityBj.this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(false);

//                FilePickerBuilder.getInstance()
//                        .setMaxCount(5) //optional
//                        .setSelectedFiles(filePaths) //optional
//                        .setActivityTheme(R.style.LibAppTheme) //optional
//                        .pickPhoto(this);

    }


    /**
     * 用户信息  AccessToken	是	string	用户凭据
     * Nickname	否	string	昵称
     * file	是	file	无
     */
    private void editCustomerInfo() {
        RequestParams params = new RequestParams(Api.EDITCUSTOMERINFO);
        params.addBodyParameter("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
        params.addBodyParameter("Nickname", edNick.getText().toString());
        params.addBodyParameter("file", new File(pictureBean.getPath()));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    MessageResult messageResult = MessageResult.parse(result);
                    if (messageResult.getCode() == 200) {
                        UIHelper.toastMessage(ActivityBj.this, messageResult.getMessage());
                        finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                if (pictureBean.isCut()) {
                    ivUserHead.setImageBitmap(BitmapFactory.decodeFile(pictureBean.getPath()));
                } else {
                    ivUserHead.setImageURI(pictureBean.getUri());
                }
            }
        }
    }

}
