package com.hzmy.zm.mysettingdemo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzmy.zm.mysettingdemo.R;

/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class MyFragment4 extends Fragment {
    public static MyFragment4 instance = null;
    public static MyFragment4 getInstance() {
        if (instance == null) {
            instance = new MyFragment4();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        return view;
    }
}
