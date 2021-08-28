package com.campuscircle.app.fragment.provide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.campuscircle.app.R;
import com.campuscircle.app.adapter.homeadapter.Adapter;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.base.BaseAplication;
import com.campuscircle.app.model.ListModel;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.PopuWindowContent;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * @ClassName NoScrollViewPager
 * @Description 首页需求全部列表    底部tab  第一个
 * @Author SeanLim
 * @Date 2021-8-13 16:52
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */

public class ProvideAllList extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final String ARG_POSITION = "position";
    private RecyclerView mRvView;
    private View mRootView;
    private Adapter mAdapter;
    private List<ListModel.NewsInfoModelsDTO> list = new ArrayList<>();
    private BGARefreshLayout bgaRefreshLayout;
    private boolean isLoadingMore = false;
    private int pageIndex = 1;

    public static ProvideAllList newInstance(int position) {
        ProvideAllList fragmentHomeList = new ProvideAllList();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        fragmentHomeList.setArguments(bundle);
        return fragmentHomeList;
    }


    //判断是否正在显示
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_all_list, container, false);
        mRvView = mRootView.findViewById(R.id.home_news_rview);
        bgaRefreshLayout = mRootView.findViewById(R.id.bga_refresh_layout);
        getData();
        this.bgaRefreshLayout.setDelegate(this);
        this.bgaRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(BaseAplication.getContext(), true));
        return mRootView;
    }

    private void getData() {
        RequestParams params = new RequestParams(Api.GET_HOME_NEWS_LIST);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("NewsTypeId", "0");
            jsonObject.put("PageIndex", pageIndex);
            jsonObject.put("PageSize", "20");
        } catch (Exception e) {

        }
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toString());//设置正文内容
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    MessageResult messageResult = MessageResult.parse(result);
                    if (messageResult.getCode() == 200) {
                        if (!isLoadingMore) {
                            list.clear();
                        }
                        ListModel modelList = JSON.parseObject(messageResult.getData(), ListModel.class);
                        list.addAll(modelList.getNewsInfoModels());
                        mAdapter = new Adapter("publish", list, getActivity());

                        if (!isLoadingMore) {
                            mRvView.setAdapter(mAdapter);
                            mRvView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        } else {
                            mAdapter.notifyDataSetChanged();
                        }

                        mAdapter.setItemClickListener(new Adapter.OnClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                PopuWindowContent popuWindowContent = new PopuWindowContent();
                                popuWindowContent.showContentPopuWindow(getContext(), 1);
                            }
                        });
                    }
                } catch (Exception e) {

                }
                bgaRefreshLayout.endRefreshing();
                bgaRefreshLayout.endLoadingMore();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                bgaRefreshLayout.endRefreshing();
                bgaRefreshLayout.endLoadingMore();
            }
        });

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isLoadingMore = false;
        pageIndex = 1;
        getData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isLoadingMore = true;
        pageIndex += 1;
        getData();
        return true;
    }
}
