package com.campuscircle.app.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.campuscircle.app.R;
import com.campuscircle.app.activity.me.ActivityBj;
import com.campuscircle.app.activity.me.ActivityGy;
import com.campuscircle.app.activity.me.ActivityHhr;
import com.campuscircle.app.activity.me.ActivityLx;
import com.campuscircle.app.activity.me.ActivitySc;
import com.campuscircle.app.activity.me.ActivitySm;
import com.campuscircle.app.activity.me.ActivityYj;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.model.DataModel;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.AppUtils;
import com.campuscircle.app.utils.AppsDataSetting;
import com.campuscircle.app.utils.Global;
import com.campuscircle.app.utils.UIHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by ShuLin on 2017/4/24.
 * Email linlin.1016@qq.com
 * Company Shhanghai Quantpower Information Technology Co.,Ltd.
 */

public class FragmentHomeMe extends Fragment {
    private View mRootView;

    @BindView(R.id.rl_hhr)
    RelativeLayout rlHhr;

    @BindView(R.id.tv_versionname)
    TextView tvVersionname;

    @BindView(R.id.tv_nick)
    TextView tvNick;

    @BindView(R.id.tv_reward_point)
    TextView tvRewardPoint;

    @BindView(R.id.tv_customer_sign)
    TextView tvCustomerSign;

    @BindView(R.id.iv_user_head)
    RoundedImageView ivUserHead;
    protected AppsDataSetting appsDataSetting;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_me, null);
        unbinder = ButterKnife.bind(this, mRootView);
        appsDataSetting = AppsDataSetting.getInstance();
        ButterKnife.bind(getActivity());
        ivUserHead = mRootView.findViewById(R.id.iv_user_head);
        return mRootView;
    }

    //判断是否正在显示
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            showView();
        }
    }



    /**
     * 显示数据
     */
    private void showView() {
        tvNick.setText(appsDataSetting.read(Global.NICKNAME, ""));
        tvRewardPoint.setText(appsDataSetting.read(Global.REWARDPOINT, ""));
        if (appsDataSetting.read(Global.ISSIGN, true)) {
            tvCustomerSign.setText("已签到");
        } else {
            tvCustomerSign.setText("每日签到");
        }
        tvVersionname.setText("version  " + AppUtils.getAppVersionName(getContext()));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @OnClick({R.id.tv_bj, R.id.rl_hhr, R.id.tv_lx, R.id.tv_sc, R.id.tv_sm, R.id.tv_yj, R.id.tv_gy, R.id.tv_customer_sign})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bj:
                UIHelper.startActivity(getActivity(), ActivityBj.class);
                break;
            case R.id.rl_hhr:
                UIHelper.startActivity(getActivity(), ActivityHhr.class);
                break;
            case R.id.tv_lx:
                UIHelper.startActivity(getActivity(), ActivityLx.class);
                break;
            case R.id.tv_sc:
                UIHelper.startActivity(getActivity(), ActivitySc.class);
                break;
            case R.id.tv_sm:
                UIHelper.startActivity(getActivity(), ActivitySm.class);
                break;
            case R.id.tv_yj:
                UIHelper.startActivity(getActivity(), ActivityYj.class);
                break;
            case R.id.tv_gy:
                UIHelper.startActivity(getActivity(), ActivityGy.class);
                break;

            case R.id.tv_customer_sign:
                if (appsDataSetting.read(Global.ISSIGN, true)) {
                    UIHelper.toastMessage(getContext(), "今日已签到");
                } else {
                    customerSign();
                }
                break;
        }
    }


    /**
     * 签到
     */
    private void customerSign() {
        RequestParams params = new RequestParams(Api.CUSTOMERSIGN);
        JSONObject json = new JSONObject();
        try {
            json.put("AccessToken", appsDataSetting.read(Global.ACCESSTOKEN, ""));
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
                        appsDataSetting.write(Global.ISSIGN, dataModel.isIssign());
                        tvCustomerSign.setText("已签到");
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
