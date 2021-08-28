package com.campuscircle.app.widget;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.campuscircle.app.MainActivity;
import com.campuscircle.app.fragment.home.FragmentHomeMe;
import com.campuscircle.app.fragment.home.FragmentHomeNeed;
import com.campuscircle.app.fragment.home.FragmentHomeProvide;
import com.campuscircle.app.fragment.home.FragmentHomeState;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private FragmentHomeProvide myFragment1;
    private FragmentHomeNeed myFragment2;
    private FragmentHomeState myFragment3;
    private FragmentHomeMe myFragment4;


    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new FragmentHomeProvide();
        myFragment2 = new FragmentHomeNeed();
        myFragment3 = new FragmentHomeState();
        myFragment4 = new FragmentHomeMe();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }


}

