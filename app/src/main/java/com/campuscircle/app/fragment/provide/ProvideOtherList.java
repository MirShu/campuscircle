package com.campuscircle.app.fragment.provide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.campuscircle.app.base.BaseAplication;
import com.campuscircle.app.model.ListModel;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.PopuWindowContent;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class ProvideOtherList extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private View mRootView;
    private static final String ARG_POSITION = "position";
    private RecyclerView mRvView;
    private RecyclerAdapter mAdapter;
    private List<ListModel.NewsInfoModelsDTO> mList = new ArrayList<>();
    private int mFragmentPosition;
    private BGARefreshLayout bgaRefreshLayout;
    private boolean isLoadingMore = false;
    private int pageIndex = 1;


    public static ProvideOtherList newInstance(int position) {
        ProvideOtherList fragmentHomeList = new ProvideOtherList();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        fragmentHomeList.setArguments(bundle);
        return fragmentHomeList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_all_list, container, false);
        mRvView = mRootView.findViewById(R.id.home_news_rview);
        mFragmentPosition = getArguments().getInt(ARG_POSITION);
        bgaRefreshLayout = mRootView.findViewById(R.id.bga_refresh_layout);
        this.bgaRefreshLayout.setDelegate(this);
        this.bgaRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(BaseAplication.getContext(), true));
        getData();
        return mRootView;
    }



  /*NewsCategoryId	是	string	咨询分类   0、全部   1、实物  2、虚拟   3、出租   4、拼车  5、勤工俭学  6、其他
    NewsTypeId	    是	string	咨询类型， 10：供应；20：求购
    NewsStatus	    是	string	咨询状态
    PageIndex	    是	string	当前页码
    PageSize	    是	string	每页展示数量每页展示数量每页展示
    Keywords	    是	string	关键词搜索
*/

    private void getData() {
        RequestParams params = new RequestParams(Api.GET_NEWS_LIST);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("NewsCategoryId", "1");
        jsonObject.put("NewsTypeId", "0");
        jsonObject.put("NewsStatus", "0");
        jsonObject.put("PageIndex", pageIndex);
        jsonObject.put("PageSize", "20");
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toJSONString());//设置正文内容
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    MessageResult messageResult = MessageResult.parse(result);
                    if (messageResult.getCode() == 200) {
                        if (!isLoadingMore) {
                            mList.clear();
                        }
                        ListModel modelList = JSON.parseObject(messageResult.getData(), ListModel.class);
                        mList.addAll(modelList.getNewsInfoModels());
//                        mAdapter = new Adapter("publish", list, getActivity());

//                        if (!isLoadingMore) {
//                            mRvView.setAdapter(mAdapter);
//                            mRvView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
//                        } else {
//                            mAdapter.notifyDataSetChanged();
//                        }


                        showReclerData();

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

    private void showReclerData() {
        if (mFragmentPosition == 1) {
            this.mAdapter = new RecyclerAdapter<ListModel.NewsInfoModelsDTO>(getContext(), mList,
                    R.layout.item_type1) {
                @Override
                public void convert(RecyclerViewHolder helper, ListModel.NewsInfoModelsDTO item, int position) {
                }
            };
        } else if (mFragmentPosition == 2) {
            this.mAdapter = new RecyclerAdapter<ListModel.NewsInfoModelsDTO>(getContext(), mList,
                    R.layout.item_type2) {
                @Override
                public void convert(RecyclerViewHolder helper, ListModel.NewsInfoModelsDTO item, int position) {
                }
            };
        } else if (mFragmentPosition == 3) {
            this.mAdapter = new RecyclerAdapter<ListModel.NewsInfoModelsDTO>(getContext(), mList,
                    R.layout.item_type3) {
                @Override
                public void convert(RecyclerViewHolder helper, ListModel.NewsInfoModelsDTO item, int position) {
                }
            };
        } else if (mFragmentPosition == 4) {
            this.mAdapter = new RecyclerAdapter<ListModel.NewsInfoModelsDTO>(getContext(), mList,
                    R.layout.item_type4) {
                @Override
                public void convert(RecyclerViewHolder helper, ListModel.NewsInfoModelsDTO item, int position) {
                }
            };
        } else if (mFragmentPosition == 5) {
            this.mAdapter = new RecyclerAdapter<ListModel.NewsInfoModelsDTO>(getContext(), mList,
                    R.layout.item_type5) {
                @Override
                public void convert(RecyclerViewHolder helper, ListModel.NewsInfoModelsDTO item, int position) {
                }
            };
        } else if (mFragmentPosition == 6) {
            this.mAdapter = new RecyclerAdapter<ListModel.NewsInfoModelsDTO>(getContext(), mList,
                    R.layout.item_type6) {
                @Override
                public void convert(RecyclerViewHolder helper, ListModel.NewsInfoModelsDTO item, int position) {

                }
            };
        }

        if (mFragmentPosition == 2 || mFragmentPosition == 4 || mFragmentPosition == 6) {
            this.mRvView.setLayoutManager(new GridLayoutManager(getContext(), 2,
                    LinearLayoutManager.VERTICAL, false));
        } else {
            this.mRvView.setLayoutManager(new GridLayoutManager(getContext(), 1,
                    LinearLayoutManager.VERTICAL, false));
        }

        mRvView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener((parent, position) -> {
            PopuWindowContent popuWindowContent = new PopuWindowContent();
            popuWindowContent.showContentPopuWindow(getContext(), mFragmentPosition);
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
