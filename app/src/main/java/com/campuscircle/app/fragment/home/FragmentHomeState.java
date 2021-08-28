package com.campuscircle.app.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.campuscircle.app.R;
import com.campuscircle.app.adapter.homeadapter.Adapter;
import com.campuscircle.app.adapter.recycler.RecyclerAdapter;
import com.campuscircle.app.adapter.recycler.RecyclerViewHolder;
import com.campuscircle.app.adapter.stateadapter.StateAdapter;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.model.ListModel;
import com.campuscircle.app.model.NewsModel;
import com.campuscircle.app.model.ResultModel;
import com.campuscircle.app.utils.AppUtils;
import com.campuscircle.app.utils.UIHelper;
import com.campuscircle.app.widget.RecyclerViewSpacesItemDecoration;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by ShuLin on 2017/4/24.
 * Email linlin.1016@qq.com
 * Company Shhanghai Quantpower Information Technology Co.,Ltd.
 */

public class FragmentHomeState extends Fragment {
    private View mRootView;
    private CheckBox checkBox;


    private RecyclerView listRecyclerView, popupRecyclerView;
    private RecyclerAdapter popupAdapter;
    private List<String> popupList;
//    private List<ListModel.NewsInfoModelsDTO> mList = new ArrayList<>();

    private   List<ListModel.NewsInfoModelsDTO> list = new ArrayList<>();

    private StateAdapter mAdapter;


    private RelativeLayout rlPopupWindow;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_state, container, false);
        listRecyclerView = mRootView.findViewById(R.id.recyclerView_state);
        checkBox = mRootView.findViewById(R.id.checkbox_more);
        rlPopupWindow = mRootView.findViewById(R.id.rl_view_popup_window_state_more);
        popupRecyclerView = mRootView.findViewById(R.id.recyclerView_popu);
        initview();
        return mRootView;

    }


    //添加头部
    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_head, view, false);
        mAdapter.setHeaderView(header);
    }

    //在RecycleView 中间添加布局
    private void setMiddle(RecyclerView view) {
        View middle = LayoutInflater.from(getActivity()).inflate(R.layout.item_middle, view, false);
        mAdapter.setMiddleView(middle);
    }

    //在RecycleView的任意位置插入布局
    private void setMiddle2(RecyclerView view) {
        View middle2 = LayoutInflater.from(getActivity()).inflate(R.layout.item_middle2, view, false);
        mAdapter.setMiddleView2(middle2);
    }


    String result;
    private void initview() {
//        listList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            listList.add("" + i);
//        }
//        //1. 线性布局
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        listRecyclerView.setLayoutManager(linearLayoutManager);
//
//
//        this.listAdapter = new RecyclerAdapter<String>(getContext(), listList,
//                R.layout.item_home_state) {
//            @Override
//            public void convert(RecyclerViewHolder helper, String item, int position) {
//                TextView tv01=helper.getView(R.id.tv_01);
//                TextView tv02=helper.getView(R.id.tv_02);
//                TextView tv03=helper.getView(R.id.tv_03);
//                TextView tv04=helper.getView(R.id.tv_04);
//                TextView tv05=helper.getView(R.id.tv_05);
//            }
//
//        };
//        listRecyclerView.setAdapter(listAdapter);


        RequestParams params = new RequestParams(Api.TOUTIAO);
        params.addBodyParameter("key=", Api.USERKEY);
        params.addBodyParameter("type=", "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                NewsModel newsModel = gson.fromJson(result, NewsModel.class);
                Log.i("ResultModel", result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i("ResultModel", cex.toString());
            }

            @Override
            public void onFinished() {
            }
        });


        try {
            mAdapter = new StateAdapter("publish", list, getActivity());
            listRecyclerView.setAdapter(mAdapter);
            listRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

            setHeader(listRecyclerView);
            setMiddle(listRecyclerView);
            setMiddle2(listRecyclerView);
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {

        }


        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rlPopupWindow.setVisibility(View.VISIBLE);
            } else {
                rlPopupWindow.setVisibility(View.GONE);
            }

        });

        rlPopupWindow.setOnClickListener(v -> {
            rlPopupWindow.setVisibility(View.GONE);
            checkBox.setChecked(false);
        });
        showPopup();

    }

    private void showPopup() {
        popupList = new ArrayList<>();
        popupList.add("全部");
        popupList.add("等待审核");
        popupList.add("被拒绝");
        popupList.add("已发布");
        popupList.add("已过期");
        popupList.add("暂停展示");
        popupList.add("已完成");
        popupList.add("被拒绝");
        popupList.add("被删除");
        popupRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false));
        this.popupAdapter = new RecyclerAdapter<String>(getContext(), popupList,
                R.layout.item_popup) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
                TextView tvPop = helper.getView(R.id.tv_pop_item);
                tvPop.setText(item);
            }
        };
        popupRecyclerView.setAdapter(popupAdapter);


        popupAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                UIHelper.toastMessage(getContext(), popupList.get(position));
            }
        });
    }
}
