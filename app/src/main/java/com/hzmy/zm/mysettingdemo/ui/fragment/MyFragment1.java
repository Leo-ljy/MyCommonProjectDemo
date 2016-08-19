package com.hzmy.zm.mysettingdemo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzmy.zm.mysettingdemo.R;

/**
 * tab 3 通讯录的 Fragment
 * Created by Bob on 2015/1/25.
 */
public class MyFragment1 extends Fragment  {


    public static MyFragment1 instance = null;

    public static MyFragment1 getInstance() {
        if (instance == null) {
            instance = new MyFragment1();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        return view;
    }


}
