package com.hzmy.zm.mysettingdemo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzmy.zm.mysettingdemo.R;

/**
 * Created by Administrator on 2015/3/6.
 */
public class MyFragment3 extends Fragment  {


    public static MyFragment3 instance = null;

    public static MyFragment3 getInstance() {
        if (instance == null) {
            instance = new MyFragment3();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        return view;
    }
}
