package com.sihuan.ui.activity;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.sihuan.R;
import com.sihuan.ui.base.widget.PagerSlidingTabStrip;
import com.sihuan.ui.fragment.SuperAwesomeCardFragment;

/**
 * Created by admin on 2016/1/9.
 */
public class MissionActivity extends FragmentActivity {

    private final Handler handler = new Handler();

    /**
     * |____|_____|_____|
     */
    private PagerSlidingTabStrip tabs;

    //+---------------------+
    // [1]   [2]   [3]
    private ViewPager pager;

    private MissonPagerAdapter mpAdapter;

    public void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_mmm);

       //tab
       tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager)findViewById(R.id.pager);


        mpAdapter = new MissonPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(mpAdapter);

        tabs.setViewPager(pager);
    }



    protected  class MissonPagerAdapter extends FragmentPagerAdapter
    {

        private final String[] TITLES = { "任务#1", "任务#2", "任务#3", "任务#4", "任务#5"};
        public MissonPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            return SuperAwesomeCardFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    };
}
