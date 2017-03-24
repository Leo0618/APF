package com.leo618.apf.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.leo618.apf.R;
import com.leo618.apf.ui.fragment.GuideFragment;
import com.leo618.apf.utils.CommUtil;
import com.leo618.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * function: App引导界面.
 *
 * <P></P>
 * Created by lzj on 2015/11/30.
 */
public class GuideActivity extends AppCompatActivity {

    private LinearLayout mPointContainer;

    private final List<Integer> mPicResIdList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(0, android.R.anim.fade_in);
        setContentView(R.layout.activity_guide);
        mPicResIdList.clear();
        mPicResIdList.add(R.drawable.bg_splash);
        mPicResIdList.add(R.drawable.bg_splash);
        mPicResIdList.add(R.drawable.bg_splash);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        mPointContainer = (LinearLayout) findViewById(R.id.layout_point_container);
        viewPager.addOnPageChangeListener(mViewPagerChangeListener);
        //动态添加ViewPager里的点
        for (int i = 0; i < mPicResIdList.size(); i++) {
            View v_point = new View(viewPager.getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) UIUtil.dip2px(7f), (int) UIUtil.dip2px(7));
            if (i == 0) {
                v_point.setBackgroundResource(R.drawable.shape_circle_point_selected);
            } else {
                lp.leftMargin = (int) UIUtil.dip2px(9);//设置点之间的间距
                v_point.setBackgroundResource(R.drawable.shape_circle_point_normal);
            }
            v_point.setLayoutParams(lp);
            mPointContainer.addView(v_point);
        }
        viewPager.setAdapter(new GuidePageFragmentAdapter(getSupportFragmentManager()));
        CommUtil.addShortcut(this);
    }

    private class GuidePageFragmentAdapter extends FragmentStatePagerAdapter {

        GuidePageFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return GuideFragment.newInstance(position, mPicResIdList.size(), mPicResIdList.get(position));
        }

        @Override
        public int getCount() {
            return mPicResIdList.size();
        }
    }

    private final ViewPager.OnPageChangeListener mViewPagerChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            //动态改变点的颜色
            for (int i = 0; i < mPointContainer.getChildCount(); i++) {
                if (i == position % 4) {
                    mPointContainer.getChildAt(i).setBackgroundResource(R.drawable.shape_circle_point_selected);
                } else {
                    mPointContainer.getChildAt(i).setBackgroundResource(R.drawable.shape_circle_point_normal);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0, android.R.anim.fade_out);
    }
}
