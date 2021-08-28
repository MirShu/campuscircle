package com.campuscircle.app.fragment.need;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.campuscircle.app.R;
import com.campuscircle.app.activity.post.ActivityShiWu;
import com.campuscircle.app.adapter.banner.ImageNetAdapter;
import com.campuscircle.app.adapter.homeadapter.Adapter;
import com.campuscircle.app.base.Api;
import com.campuscircle.app.base.BaseAplication;
import com.campuscircle.app.model.BannerModel;
import com.campuscircle.app.model.ListModel;
import com.campuscircle.app.model.MessageResult;
import com.campuscircle.app.utils.Global;
import com.campuscircle.app.utils.PopuWindowContent;
import com.campuscircle.app.utils.Post;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.util.BannerUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * @ClassName NoScrollViewPager
 * @Description 首页提供需求全部列表    底部tab  第二个
 * @Author SeanLim
 * @Date 2021-8-13 16:52
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class NeedAllList extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final String ARG_POSITION = "position";
    private RecyclerView mRvView;
    private View mRootView;
    private Adapter mAdapter;
    private View inflate;
    private Dialog dialog;
    private Banner banner;
    private List<ListModel.NewsInfoModelsDTO> list = new ArrayList<>();
    private BGARefreshLayout bgaRefreshLayout;
    private boolean isLoadingMore = false;
    private int pageIndex = 1;

    public static NeedAllList newInstance(int position) {
        NeedAllList fragmentHomeList = new NeedAllList();
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


    /*  NewsTypeId	是	int	咨询类型，0：全部；10：供应；20：求购*/
    private void getData() {
        RequestParams params = new RequestParams(Api.GET_HOME_NEWS_LIST);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("NewsTypeId", "20");
        jsonObject.put("PageIndex", pageIndex);
        jsonObject.put("PageSize", "20");
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toString());
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
                        mAdapter.setItemClickListener((view, position) -> {
                            PopuWindowContent popuWindowContent = new PopuWindowContent();
                            popuWindowContent.showContentPopuWindow(getContext(), 1);
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

    private void showShareDialog() {
        dialog = new Dialog(getContext(), R.style.ActionSheetDialogStyle);
        inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_details_dialog_layout, null);

        banner = inflate.findViewById(R.id.banner);

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框


        banner.setAdapter(new ImageNetAdapter(BannerModel.getTestData3()));
        banner.setIndicator(new RectangleIndicator(getActivity()));
        banner.setIndicatorSpace((int) BannerUtils.dp2px(4));
        banner.setIndicatorRadius(0);


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
