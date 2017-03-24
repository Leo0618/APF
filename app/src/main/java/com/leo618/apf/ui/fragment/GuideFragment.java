package com.leo618.apf.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.leo618.apf.MyApp;
import com.leo618.apf.R;
import com.leo618.apf.base.BaseFragment;
import com.leo618.apf.common.Const;
import com.leo618.apf.ui.activity.GuideActivity;
import com.leo618.apf.ui.activity.MainActivity;
import com.leo618.apf.utils.IntentLauncher;
import com.leo618.utils.PackageManagerUtil;
import com.leo618.utils.SPUtil;

import butterknife.Bind;

/**
 * function:引导页 单独页
 *
 * <p></p>
 * Created by lzj on 2017/3/24.
 */

public class GuideFragment extends BaseFragment {
    private static final String ARG_position = "ARG_position";
    private static final String ARG_size = "ARG_size";
    private static final String ARG_picResID = "ARG_picResID";

    private int mCurrPosition;
    private int mCurrSize;
    private int mCurrPicResId;

    public GuideFragment() {
    }

    public static GuideFragment newInstance(int position, int size, int picResID) {
        GuideFragment fragment = new GuideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_position, position);
        args.putInt(ARG_size, size);
        args.putInt(ARG_picResID, picResID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            throw new IllegalArgumentException("error : you need pass args(position,size,picResID)");
        }
        mCurrPosition = getArguments().getInt(ARG_position);
        mCurrSize = getArguments().getInt(ARG_size);
        mCurrPicResId = getArguments().getInt(ARG_picResID);
    }

    @Bind(R.id.img_guide)
    ImageView mImgUI;
    @Bind(R.id.btn_enter)
    View mBtnEnter;

    @Override
    protected int setContentViewResId() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void doBusiness() {
        mImgUI.setImageResource(mCurrPicResId);
        mBtnEnter.setVisibility(mCurrPosition == mCurrSize - 1 ? View.VISIBLE : View.GONE);
        mBtnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtil.putInt(Const.KEY_LAST_VERSION_CODE, PackageManagerUtil.getVersionCode());
                IntentLauncher.with(getActivity()).launch(MainActivity.class);
                MyApp.getApplication().finishActivity(GuideActivity.class);
            }
        });
    }
}

