package com.campuscircle.app.activity.post;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.github.gzuliyujiang.wheelpicker.contract.OnNumberPickedListener;
import com.github.gzuliyujiang.wheelpicker.entity.DateEntity;
import com.github.gzuliyujiang.wheelpicker.widget.DateWheelLayout;
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
 * @ClassName ActivityQiTa
 * @Description TODO
 * @Author pc
 * @Date 2021-8-12 13:22
 * @Version 1.0
 */
public class ActivityQiTa extends BaseActivity implements OnNumberPickedListener {
    String radioChooseStata;
    @BindView(R.id.tv_day_num)
    TextView tvDayNum;

    @BindView(R.id.edit_title)
    EditText editTitle;

    @BindView(R.id.ed_short)
    EditText edShort;


    @BindView(R.id.tv_show_news_setting_model)
    TextView tvShowNewsSettingModel;

    private int intShowDays = 1;

    private String uriImage;

    private PictureBean pictureBean;

    @BindView(R.id.iv_add_image)
    ImageView ivAddImage;

    @Override
    public Integer getLayout() {
        return R.layout.activity_qita;
    }

    @Override
    public void init() {
        Bundle bundle = this.getIntent().getExtras();
        radioChooseStata = bundle.getString("radioChooseStata");
        if (radioChooseStata.equals("1")) {
            tvTitle.setText("发布其他消息");
        } else {
            tvTitle.setText("发布其他消息");
        }
        tvDayNum.setText(Html.fromHtml(MessageFormat.format("有效期：<font color=\"#403b58\">{0}</font>天", 1)));
        tvShowNewsSettingModel.setText(Html.fromHtml(MessageFormat.format("需要消耗积分：<font color=\"#403b58\">{0}</font>", String.valueOf(appsDataSetting.read(Global.InKindGiveRecords, "")))));

    }


    @OnClick({R.id.iv_add_image, R.id.tv_ok, R.id.tv_add_day})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_image:
                PictureSelector
                        .create(ActivityQiTa.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false);
                break;

            case R.id.tv_ok:
                post();
                break;

            case R.id.tv_add_day:
                onInteger();
                break;
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


    private void post() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
        if (TextUtils.isEmpty(editTitle.getText().toString())) {
            editTitle.startAnimation(shake);
            UIHelper.showImageToast(ActivityQiTa.this, "请输入需求说明");
            return;
        } else if (TextUtils.isEmpty(edShort.getText().toString())) {
            UIHelper.showImageToast(ActivityQiTa.this, "请输入描述");
            edShort.startAnimation(shake);
            return;
        }

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("File", new File(uriImage));
        map.put("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
        map.put("Title", editTitle.getText().toString());
        map.put("NewsCategoryId", 6);
        map.put("NewsType", 10);
        map.put("Short", edShort.getText().toString());
        map.put("ShowDays", intShowDays);
        list.add(map);
        Post.addBodyParameter(ActivityQiTa.this, Api.ADD_NEWS, list);
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
