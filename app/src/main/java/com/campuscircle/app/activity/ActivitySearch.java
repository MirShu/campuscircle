package com.campuscircle.app.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.campuscircle.app.R;
import com.campuscircle.app.adapter.homeadapter.Adapter;
import com.campuscircle.app.adapter.recycler.RecyclerAdapter;
import com.campuscircle.app.adapter.recycler.RecyclerViewHolder;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.base.BaseActivity;
import com.campuscircle.app.model.ListModel;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.PopuWindowContent;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivitySearch extends BaseActivity {
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.iv_search_back)
    ImageView ivSearchBack;
    @BindView(R.id.tv_search_nodata)
    TextView tvSearchNodata;


    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<String> RollReycList;


    @Override
    public Integer getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void init() {
        tvTitle.setText("搜索");
        tvRight.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recyclerView_search);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ivSearchBack.setVisibility(View.VISIBLE);
                tvSearchNodata.setVisibility(View.GONE);
                initData();
            }
        });

    }


    private void initData() {

        RollReycList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RollReycList.add("" + i);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        this.recyclerAdapter = new RecyclerAdapter<String>(this, RollReycList,
                R.layout.item_search) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
            }
        };

        recyclerView.setAdapter(recyclerAdapter);
























        RequestParams params = new RequestParams(Api.GET_HOME_NEWS_LIST);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("NewsTypeId", "20");
        jsonObject.put("PageIndex", "");
        jsonObject.put("PageSize", "20");
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toString());//设置正文内容
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    MessageResult messageResult = MessageResult.parse(result);
                    if (messageResult.getCode() == 200) {
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
