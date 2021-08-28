package com.campuscircle.app.utils;

import android.app.Activity;
import android.util.Log;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.campuscircle.app.adapter.homeadapter.Adapter;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.base.BaseAplication;
import com.campuscircle.app.model.DataModel;
import com.campuscircle.app.model.ListModel;
import com.campuscircle.app.model.MessageResult;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;
import java.util.Map;

public class Post {
    public static void showNewsSettingModel() {
        RequestParams params = new RequestParams(Api.SHOWNEWSSETTINGMODEL);
        JSONObject json = new JSONObject();
        try {
            json.put("AccessToken", BaseAplication.appsDataSetting().read(Global.ACCESSTOKEN, ""));
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
                        BaseAplication.appsDataSetting().write(Global.InKindWantRecords, String.valueOf(dataModel.getInKindWantRecords()));
                        BaseAplication.appsDataSetting().write(Global.InKindGiveRecords, String.valueOf(dataModel.getInKindGiveRecords()));
                        BaseAplication.appsDataSetting().write(Global.VirtualWantRecords, String.valueOf(dataModel.getVirtualWantRecords()));
                        BaseAplication.appsDataSetting().write(Global.VirtualGiveRecords, String.valueOf(dataModel.getVirtualGiveRecords()));
                        BaseAplication.appsDataSetting().write(Global.RentWantRecords, String.valueOf(dataModel.getRentWantRecords()));
                        BaseAplication.appsDataSetting().write(Global.RentGiveRecords, String.valueOf(dataModel.getRentGiveRecords()));
                        BaseAplication.appsDataSetting().write(Global.CarpoolWantRecords, String.valueOf(dataModel.getCarpoolWantRecords()));
                        BaseAplication.appsDataSetting().write(Global.CarpoolGiveRecords, String.valueOf(dataModel.getCarpoolGiveRecords()));
                        BaseAplication.appsDataSetting().write(Global.FosterCareWantRecords, String.valueOf(dataModel.getFosterCareWantRecords()));
                        BaseAplication.appsDataSetting().write(Global.FosterCareGiveRecords, String.valueOf(dataModel.getFosterCareGiveRecords()));
                        BaseAplication.appsDataSetting().write(Global.OtherWantRecords, String.valueOf(dataModel.getOtherWantRecords()));
                        BaseAplication.appsDataSetting().write(Global.OtherGiveRecords, String.valueOf(dataModel.getOtherGiveRecords()));
                        BaseAplication.appsDataSetting().write(Global.WorkStudyGiveRecords, String.valueOf(dataModel.getWorkStudyGiveRecords()));
                        BaseAplication.appsDataSetting().write(Global.ReportDealCount, String.valueOf(dataModel.getReportDealCount()));

                    } else if (messageResult.getCode() == 4002) {
                        UIHelper.toastMessage(BaseAplication.getContext(), messageResult.getMessage());
                    } else if (messageResult.getCode() == 4007) {
                        UIHelper.toastMessage(BaseAplication.getContext(), messageResult.getMessage());
                    } else if (messageResult.getCode() == 4004) {
                        UIHelper.toastMessage(BaseAplication.getContext(), messageResult.getMessage());
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
     * 发布各类信息
     */
    private static String data;

    public static String addBodyParameter(Activity mActivity, String url, List<Map<String, Object>> mList) {
        RequestParams params = new RequestParams(url);
        for (Map<String, Object> map : mList) {
            for (Map.Entry<String, Object> m : map.entrySet()) {
                params.addBodyParameter(m.getKey(), m.getValue());
            }
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    MessageResult messageResult = MessageResult.parse(result);
                    if (messageResult.getCode() == 200) {
                        data = messageResult.getData();
                        UIHelper.toastMessage(mActivity, messageResult.getMessage());
                        mActivity.finish();
                    } else if (messageResult.getCode() == 4002) {
                        UIHelper.toastMessage(mActivity, messageResult.getMessage());
                    } else if (messageResult.getCode() == 4015) {
                        UIHelper.toastMessage(mActivity, messageResult.getMessage());
                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });


        return data;
    }

      String stringBodyData;
    public  String bodyContentParameter(Activity mActivity, String url, List<Map<String, Object>> mList) {
        RequestParams params = new RequestParams(url);
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        for (Map<String, Object> map : mList) {
            for (Map.Entry<String, Object> m : map.entrySet()) {
                jsonObject.put(m.getKey(), m.getValue());
            }
        }
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                stringBodyData = result;
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


        return stringBodyData;
    }


}
