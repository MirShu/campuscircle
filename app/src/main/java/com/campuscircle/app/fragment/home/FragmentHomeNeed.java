package com.campuscircle.app.fragment.home;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.campuscircle.app.R;
import com.campuscircle.app.fragment.need.NeedAllList;
import com.campuscircle.app.fragment.need.NeedOtherList;
import com.campuscircle.app.utils.HorizontalScrollViewEx;

public class FragmentHomeNeed extends Fragment {
    private View mRootView;
    private HorizontalScrollViewEx scrollViewEx;
    private DisplayMetrics dm;
    private ViewPager viewPager;
    public static final String ARGS = "args";


    private NeedAllList needAllList;

    public static NeedAllList newInstance(String s) {
        NeedAllList fragmentHomePublish = new NeedAllList();
        Bundle bundle = new Bundle();
        bundle.putString(ARGS, s);
        fragmentHomePublish.setArguments(bundle);
        return fragmentHomePublish;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_need, null);
        dm = getResources().getDisplayMetrics();
        viewPager = mRootView.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(6);
        scrollViewEx = mRootView.findViewById(R.id.tabs);
        viewPager.setAdapter(new HomeNeedAdapter(getChildFragmentManager()));
        scrollViewEx.setViewPager(viewPager);
        setTabsValue();
        return mRootView;
    }


    /**
     * 对HorizontalScrollViewEx的各项属性进行赋值。
     */
    private void setTabsValue() {
        scrollViewEx.setShouldExpand(false);
        scrollViewEx.setDividerColor(Color.TRANSPARENT);
        scrollViewEx.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, dm));
        scrollViewEx.setSelectedTextColor(Color.parseColor("#007aff"));
        scrollViewEx.setTabBackground(0);
    }


    public class HomeNeedAdapter extends FragmentPagerAdapter {
        public HomeNeedAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = {"全部", "实物", "虚拟", "出租", "拼车", "其他"};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                if (needAllList == null) {
                    needAllList = new NeedAllList();
                }
                return needAllList;
            }

            return NeedOtherList.newInstance(position);
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
