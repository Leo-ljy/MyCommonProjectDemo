package com.hzmy.zm.mysettingdemo.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzmy.zm.mysettingdemo.R;
import com.hzmy.zm.mysettingdemo.ui.activity.AboutUsActivity;
import com.hzmy.zm.mysettingdemo.ui.activity.AccountSettingActivity;
import com.hzmy.zm.mysettingdemo.ui.activity.MyAccountActivity;


/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class MyFragment4 extends Fragment implements View.OnClickListener {
    public static MyFragment4 instance = null;

    public static MyFragment4 getInstance() {
        if (instance == null) {
            instance = new MyFragment4();
        }
        return instance;
    }

    private View mView;


    private ImageView imageView;

    private TextView mName;

    private LinearLayout mUserProfile, mMineStting, mMineService, mMineAbout;

    private ImageView mNewVersionView;

    private boolean isHasNewVersion;

    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_4, null);
        initViews(mView);
        initData();
        compareVersion();
        return mView;
    }

    /**
     * 判断是否更新
     */
    private void compareVersion() {

    }

    private void initData() {

    }

    private void initViews(View mView) {
        mNewVersionView = (ImageView) mView.findViewById(R.id.new_version_icon);
        imageView = (ImageView) mView.findViewById(R.id.mine_header);
        mName = (TextView) mView.findViewById(R.id.mine_name);
        mUserProfile = (LinearLayout) mView.findViewById(R.id.start_user_profile);
        mMineStting = (LinearLayout) mView.findViewById(R.id.mine_setting);
        mMineService = (LinearLayout) mView.findViewById(R.id.mine_service);
        mMineAbout = (LinearLayout) mView.findViewById(R.id.mine_about);
        mUserProfile.setOnClickListener(this);
        mMineStting.setOnClickListener(this);
        mMineService.setOnClickListener(this);
        mMineAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_user_profile:
                startActivity(new Intent(getActivity(), MyAccountActivity.class));
                break;
            case R.id.mine_setting:
                startActivity(new Intent(getActivity(), AccountSettingActivity.class));
                break;
            case R.id.mine_service:
                //客服
                // KEFU146001495753714 正式  KEFU145930951497220 测试
//                RongIM.getInstance().startCustomerServiceChat(getActivity(), "KEFU146001495753714", "在线客服", null);
                break;
            case R.id.mine_about:
                mNewVersionView.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                isHasNewVersion = true;
                intent.putExtra("isHasNewVersion", isHasNewVersion);
                url = "https://sm.wdjcdn.com/release/files/jupiter/5.19.1.12038/wandoujia-web_seo_baidu_homepage.apk";
                if (!TextUtils.isEmpty(url)) {
                    intent.putExtra("url", url);
                }
                startActivity(intent);
                break;
        }
    }

}
