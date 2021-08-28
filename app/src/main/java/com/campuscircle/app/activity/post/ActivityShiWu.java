package com.campuscircle.app.activity.post;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

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


/**
 * @ClassName ActivityShiWu
 * @Description TODO
 * @Author SeanLim
 * @Date 2021-8-12 13:27
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class ActivityShiWu extends BaseActivity implements OnNumberPickedListener {
    String radioChooseStata;
    @BindView(R.id.ed_goods_name)
    EditText edGoodsName;
    @BindView(R.id.edit_price)
    EditText editPrice;
    @BindView(R.id.edit_sum)
    EditText editSum;
    @BindView(R.id.edit_link)
    EditText editLink;

    @BindView(R.id.ll_post)
    LinearLayout llPost;

    @BindView(R.id.iv_add_image)
    ImageView ivAddImage;

    private String uriImage;

    @BindView(R.id.tv_day_num)
    TextView tvDayNum;

    @BindView(R.id.tv_show_news_setting_model)
    TextView tvShowNewsSettingModel;


    private String name;
    private String price;
    private String sum;
    private int intShowDays = 1;

    @Override
    public Integer getLayout() {
        return R.layout.activity_shiwu;
    }

    @Override
    public void init() {
        Bundle bundle = this.getIntent().getExtras();
        radioChooseStata = bundle.getString("radioChooseStata");
        if (radioChooseStata.equals("1")) {
            tvTitle.setText("发布卖实物信息");
            llPost.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setText("发布买实物信息");
            llPost.setVisibility(View.GONE);
        }


//        map.put("File", uriImage);
//        map.put("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
//        map.put("Title", edGoodsName.getText().toString());
//        map.put("NewsCategoryId", 1);
//        map.put("NewsType", 10);
//        map.put("Short", editSum.getText().toString());
//        map.put("ShowDays", intShowDays);
//        map.put("OutSiteLink", editLink.getText().toString());
//        map.put("Price", editPrice.getText().toString());
//

        tvDayNum.setText(Html.fromHtml(MessageFormat.format("有效期：<font color=\"#403b58\">{0}</font>天", 1)));
        tvShowNewsSettingModel.setText(Html.fromHtml(MessageFormat.format("需要消耗积分：<font color=\"#403b58\">{0}</font>", String.valueOf(appsDataSetting.read(Global.InKindGiveRecords, "")))));

    }


    @OnClick({R.id.iv_add_image, R.id.tv_ok, R.id.tv_add_day})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_image:
                addImage();
                break;

            case R.id.tv_ok:
                post();
                break;

            case R.id.tv_add_day:
                onInteger();
                break;

        }
    }

    private void post() {
        name = edGoodsName.getText().toString();
        price = editPrice.getText().toString();
        sum = editSum.getText().toString();
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
        if (TextUtils.isEmpty(name)) {
            edGoodsName.startAnimation(shake);
            UIHelper.showImageToast(ActivityShiWu.this, "请输入商品名称");
            return;
        } else if (TextUtils.isEmpty(price)) {
            UIHelper.showImageToast(ActivityShiWu.this, "请输入商品价格");
            editPrice.startAnimation(shake);
            return;
        } else if (TextUtils.isEmpty(sum)) {
            UIHelper.showImageToast(ActivityShiWu.this, "请输入描述信息");
            editSum.startAnimation(shake);
            return;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("File", new File(uriImage));
        map.put("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
        map.put("Title", edGoodsName.getText().toString());
        map.put("NewsCategoryId", 1);
        map.put("NewsType", 10);
        map.put("Short", editSum.getText().toString());
        map.put("ShowDays", intShowDays);
        map.put("OutSiteLink", editLink.getText().toString());
        map.put("Price", editPrice.getText().toString());
        list.add(map);
        Post.addBodyParameter(ActivityShiWu.this, Api.ADD_NEWS, list);
    }


    private void addImage() {
        PictureSelector
                .create(ActivityShiWu.this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(false);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                String s = pictureBean.getPath();
                if (pictureBean.isCut()) {
                    ivAddImage.setImageBitmap(BitmapFactory.decodeFile(pictureBean.getPath()));
                } else {
                    ivAddImage.setImageURI(pictureBean.getUri());
                }
                uriImage = pictureBean.getPath();
            }
        }
    }


    public void onInteger() {
        NumberPicker picker = new NumberPicker(this);
        picker.setOnNumberPickedListener(ActivityShiWu.this);
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
