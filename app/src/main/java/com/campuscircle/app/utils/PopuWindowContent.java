package com.campuscircle.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.campuscircle.app.R;
import com.campuscircle.app.activity.ActivityPicShow;
import com.campuscircle.app.adapter.banner.ImageNetAdapter;
import com.campuscircle.app.model.BannerModel;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.util.BannerUtils;

import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * @ClassName PopuWindowContent
 * @Description TODO
 * @Author SeanLim
 * @Date 2021-8-13 13:56
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class PopuWindowContent {
    private View inflate;
    private Dialog dialog;
    private ImageView ivBack;
    private Banner banner;
    private TextView tvSum, tvPrice, tvTime, tvGoodsSum, tvGoodsName, tvGoodsShoujia, tvChuzuSm, tvShuoMing;
    private RelativeLayout rlChuzuSum, rlIv;
    private LinearLayout llChuzu, llPincheSw, lljob, llPrice;
    private Context mContext;

    public void showContentPopuWindow(Context context, int state) {
        mContext = context;
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        inflate = LayoutInflater.from(context).inflate(R.layout.view_details_dialog_layout, null);
        banner = inflate.findViewById(R.id.banner);
        ivBack = inflate.findViewById(R.id.iv_back);
        tvSum = inflate.findViewById(R.id.tv_sum);
        tvTime = inflate.findViewById(R.id.tv_user_name_time);
        tvGoodsSum = inflate.findViewById(R.id.tv_goods_sum);
        tvGoodsName = inflate.findViewById(R.id.tv_goods_name);

        llChuzu = inflate.findViewById(R.id.rl_chuzu);
        rlChuzuSum = inflate.findViewById(R.id.rl_chuzu_sm);

        tvGoodsShoujia = inflate.findViewById(R.id.tv_goods_shoujia);

        llPincheSw = inflate.findViewById(R.id.ll_pinche_zw);

        tvPrice = inflate.findViewById(R.id.tv_price);

        tvChuzuSm = inflate.findViewById(R.id.tv_chuzu_sm);

        tvShuoMing = inflate.findViewById(R.id.tv_shuo_ming);

        lljob = inflate.findViewById(R.id.lljob);
        llPrice = inflate.findViewById(R.id.ll_price);
        rlIv = inflate.findViewById(R.id.rl_iv);

//        tvSum.setText(Html.fromHtml(MessageFormat.format("{0}",context.getResources().getString(R.string.string_popu_sum1))));
        tvTime.setText(Html.fromHtml(MessageFormat.format("2021.08.10????????? ??????????????????<font color=\"#e6230f\">{0}</font>???", 1)));
        if (state == 1) {
            tvGoodsSum.setVisibility(View.VISIBLE);
            banner.setVisibility(View.VISIBLE);
            llChuzu.setVisibility(View.GONE);
            rlChuzuSum.setVisibility(View.GONE);
            llPincheSw.setVisibility(View.GONE);
            tvGoodsSum.setVisibility(View.VISIBLE);
            tvGoodsName.setVisibility(View.VISIBLE);

        } else if (state == 2) {
            tvGoodsSum.setVisibility(View.GONE);
            banner.setVisibility(View.GONE);
            llChuzu.setVisibility(View.GONE);
            rlChuzuSum.setVisibility(View.GONE);
            llPincheSw.setVisibility(View.GONE);
        } else if (state == 3) {
            tvGoodsSum.setVisibility(View.VISIBLE);
            banner.setVisibility(View.VISIBLE);
            llChuzu.setVisibility(View.VISIBLE);
            rlChuzuSum.setVisibility(View.VISIBLE);
            tvGoodsShoujia.setText("????????????");
            llPincheSw.setVisibility(View.GONE);
        } else if (state == 4) {
            llPincheSw.setVisibility(View.GONE);
            tvGoodsSum.setVisibility(View.GONE);
            tvGoodsName.setVisibility(View.GONE);
            tvPrice.setText(Html.fromHtml(MessageFormat.format("<font color=\"#3e3959\">{0}</font>", "?????? ->??????")));
            tvGoodsShoujia.setText("????????????");
            tvChuzuSm.setText("????????????");
            tvShuoMing.setText("?????????????????????????????????????????????");
            banner.setVisibility(View.GONE);
            llPincheSw.setVisibility(View.VISIBLE);
        } else if (state == 5) {
            tvGoodsSum.setVisibility(View.GONE);
            tvGoodsName.setVisibility(View.GONE);
            banner.setVisibility(View.GONE);
            llPrice.setVisibility(View.GONE);
            lljob.setVisibility(View.VISIBLE);
            tvChuzuSm.setText("????????????");
            tvShuoMing.setText("1???18-30????????????????????????\n2?????????????????????????????????" +
                    "\n3???????????????????????????????????????????????????????????????\n4?????????????????????????????????????????????????????????????????????????????????????????????" +
                    "\n5?????????????????????????????????????????????????????????????????????");
        } else if (state == 6) {
            banner.setVisibility(View.GONE);
            tvGoodsSum.setVisibility(View.GONE);
            tvGoodsName.setVisibility(View.GONE);
            llChuzu.setVisibility(View.GONE);
            llPincheSw.setVisibility(View.GONE);
            tvGoodsShoujia.setText("??????");
            tvChuzuSm.setText("??????????????????");
            tvShuoMing.setText("?????????????????????????????????");
            rlIv.setVisibility(View.VISIBLE);
        }

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;//???????????????????????????
        dialogWindow.setAttributes(lp);
        dialog.show();//???????????????

        banner.setAdapter(new ImageNetAdapter(BannerModel.getTestData3()));
        banner.setIndicator(new RectangleIndicator(context));
        banner.setIndicatorSpace(BannerUtils.dp2px(4));
        banner.setIndicatorRadius(0);


        banner.setOnBannerListener((data, position) -> {

            ArrayList<String> list = new ArrayList<>();
            list.add("https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg");
            list.add("https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg");
            list.add("https://img.zcool.cn/community/01270156fb62fd6ac72579485aa893.jpg");
            list.add("https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg");
            list.add("https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg");

            Bundle b = new Bundle();
            b.putString("currentImagePath", "https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg");
            b.putStringArrayList("imageArray", list);
            goIntent(ActivityPicShow.class, b);


//            String currentImagePath = "https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg";
//            PopuWindowShowPic popuWindowShowPic = new PopuWindowShowPic();
//            popuWindowShowPic.showContentPopuWindow(context, currentImagePath,list);


        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }


    public void goIntent(Class<?> toClsActivity, Bundle toBundle) {
        Intent intent = new Intent(mContext, toClsActivity);
        if (toBundle != null) {
            intent.putExtras(toBundle);
        }
        mContext.startActivity(intent);
    }

}
