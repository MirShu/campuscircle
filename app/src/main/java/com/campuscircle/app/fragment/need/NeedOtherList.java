package com.campuscircle.app.fragment.need;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campuscircle.app.R;
import com.campuscircle.app.adapter.homeadapter.Adapter;
import com.campuscircle.app.adapter.recycler.RecyclerAdapter;
import com.campuscircle.app.adapter.recycler.RecyclerViewHolder;
import com.campuscircle.app.utils.PopuWindowContent;

import java.util.ArrayList;
import java.util.List;

public class NeedOtherList extends Fragment {
    private View mRootView;
    private static final String ARG_POSITION = "position";
    private RecyclerView mRvView;
    private RecyclerAdapter recyclerAdapter;
    private List<String> RollReycList;
    private int mFragmentPosition;
    private int needState;

    public static NeedOtherList newInstance(int position) {
        NeedOtherList fragmentHomeList = new NeedOtherList();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        fragmentHomeList.setArguments(bundle);
        return fragmentHomeList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_other_list, container, false);
        mRvView = mRootView.findViewById(R.id.recyclerView_other_list);
        mFragmentPosition = getArguments().getInt(ARG_POSITION);
        initData();
        return mRootView;
    }


    private void initData() {




















        RollReycList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RollReycList.add("" + i);
        }

        if (mFragmentPosition == 1) {
            this.recyclerAdapter = new RecyclerAdapter<String>(getContext(), RollReycList,
                    R.layout.item_need_type1) {
                @Override
                public void convert(RecyclerViewHolder helper, String item, int position) {
                }
            };
        } else if (mFragmentPosition == 2) {
            this.recyclerAdapter = new RecyclerAdapter<String>(getContext(), RollReycList,
                    R.layout.item_need_type2) {
                @Override
                public void convert(RecyclerViewHolder helper, String item, int position) {
                }
            };
        } else if (mFragmentPosition == 3) {
            this.recyclerAdapter = new RecyclerAdapter<String>(getContext(), RollReycList,
                    R.layout.item_need_type3) {
                @Override
                public void convert(RecyclerViewHolder helper, String item, int position) {
                }
            };
        } else if (mFragmentPosition == 4) {
            this.recyclerAdapter = new RecyclerAdapter<String>(getContext(), RollReycList,
                    R.layout.item_need_type4) {
                @Override
                public void convert(RecyclerViewHolder helper, String item, int position) {
                }
            };
        } else if (mFragmentPosition == 5) {
            this.recyclerAdapter = new RecyclerAdapter<String>(getContext(), RollReycList,
                    R.layout.item_need_type5) {
                @Override
                public void convert(RecyclerViewHolder helper, String item, int position) {
                }
            };
        }

        this.mRvView.setLayoutManager(new GridLayoutManager(getContext(), 2,
                LinearLayoutManager.VERTICAL, false));
        mRvView.setAdapter(recyclerAdapter);


        recyclerAdapter.setOnItemClickListener((parent, position) -> {
            PopuWindowContent popuWindowContent = new PopuWindowContent();
            if (mFragmentPosition == 5) {
                needState = 6;
            } else {
                needState = mFragmentPosition;
            }
            popuWindowContent.showContentPopuWindow(getContext(), needState);
        });


    }
}
