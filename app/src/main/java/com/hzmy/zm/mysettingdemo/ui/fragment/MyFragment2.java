package com.hzmy.zm.mysettingdemo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hzmy.zm.mysettingdemo.R;


/**
 * tab 4 客服的 Fragment
 * Created by Administrator on 2015/3/6.
 */
public class MyFragment2 extends Fragment  {

    /**
     * 客服聊天的按钮
     */
    private TextView mCustomerChat;
    public static MyFragment2 instance = null;

    public static MyFragment2 getInstance() {
        if (instance == null) {
            instance = new MyFragment2();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        return view;
    }

}
