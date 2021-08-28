package com.campuscircle.app.activity.me;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campuscircle.app.R;
import com.campuscircle.app.adapter.recycler.RecyclerAdapter;
import com.campuscircle.app.adapter.recycler.RecyclerViewHolder;
import com.campuscircle.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivitySc extends BaseActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<String> RollReycList;
    @Override
    public Integer getLayout() {
        return R.layout.activity_sc;
    }

    @Override
    public void init() {
        tvTitle.setText("我的收藏");
        recyclerView=findViewById(R.id.recyclerView_sc);
        initData();
    }

    private void initData() {

        RollReycList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RollReycList.add("" + i);
        }
        //1. 线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        this.recyclerAdapter = new RecyclerAdapter<String>(this, RollReycList,
                R.layout.item_need_type1) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
            }
        };

        recyclerView.setAdapter(recyclerAdapter);

    }

}
