package com.hzmy.zm.mysettingdemo.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzmy.zm.mysettingdemo.R;
import com.hzmy.zm.mysettingdemo.ui.adapter.FragmentAdapter;
import com.hzmy.zm.mysettingdemo.ui.fragment.MyFragment1;
import com.hzmy.zm.mysettingdemo.ui.fragment.MyFragment2;
import com.hzmy.zm.mysettingdemo.ui.fragment.MyFragment3;
import com.hzmy.zm.mysettingdemo.ui.fragment.MyFragment4;
import com.hzmy.zm.mysettingdemo.ui.widget.DragPointView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, DragPointView.OnDragListencer
{
    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    private FragmentPagerAdapter mFragmentPagerAdapter; //将 tab  页面持久在内存中

    private ViewPager mViewPager;

    private List<Fragment> mFragment = new ArrayList<>();

    private RelativeLayout chatRLayout, contactRLayout, foundRLayout, mineRLayout;

    private ImageView moreImage, mImageChats, mImageContact, mImageFind, mImageMe, mMineRed;

    private TextView mTextChats, mTextContact, mTextFind, mTextMe;

    private DragPointView mUnreadNumView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initViews();
        initMianViewPager();
        changeTextViewColor();
        changeSelectedTabState(0);
    }


    private void initViews()
    {
        chatRLayout = (RelativeLayout) findViewById(R.id.seal_chat);
        contactRLayout = (RelativeLayout) findViewById(R.id.seal_contact_list);
        foundRLayout = (RelativeLayout) findViewById(R.id.seal_find);
        mineRLayout = (RelativeLayout) findViewById(R.id.seal_me);
        mImageChats = (ImageView) findViewById(R.id.tab_img_chats);
        mImageContact = (ImageView) findViewById(R.id.tab_img_contact);
        mImageFind = (ImageView) findViewById(R.id.tab_img_find);
        mImageMe = (ImageView) findViewById(R.id.tab_img_me);
        mTextChats = (TextView) findViewById(R.id.tab_text_chats);
        mTextContact = (TextView) findViewById(R.id.tab_text_contact);
        mTextFind = (TextView) findViewById(R.id.tab_text_find);
        mTextMe = (TextView) findViewById(R.id.tab_text_me);
        mMineRed = (ImageView) findViewById(R.id.mine_red);
        moreImage = (ImageView) findViewById(R.id.seal_more);

        chatRLayout.setOnClickListener(this);
        contactRLayout.setOnClickListener(this);
        foundRLayout.setOnClickListener(this);
        mineRLayout.setOnClickListener(this);
        moreImage.setOnClickListener(this);
    }


    private void initMianViewPager()
    {
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);

        mUnreadNumView = (DragPointView) findViewById(R.id.seal_num);
        mUnreadNumView.setOnClickListener(this);
        mUnreadNumView.setDragListencer(this);

        mFragment.add(MyFragment2.getInstance());
        mFragment.add(MyFragment1.getInstance());
        mFragment.add(MyFragment3.getInstance());
        mFragment.add(MyFragment4.getInstance());

        mFragmentPagerAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragment);
        mViewPager.setAdapter(mFragmentPagerAdapter);

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(this);
        initData();
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        changeTextViewColor();
        changeSelectedTabState(position);
    }

    private void changeTextViewColor()
    {
    }

    private void changeSelectedTabState(int position)
    {
        setSelected();
        switch (position)
        {
            case PAGE_ONE:
                mTextChats.setSelected(true);
                mImageChats.setSelected(true);
                break;
            case PAGE_TWO:
                mTextContact.setSelected(true);
                mImageContact.setSelected(true);
                break;
            case PAGE_THREE:
                mTextFind.setSelected(true);
                mImageFind.setSelected(true);
                break;
            case PAGE_FOUR:
                mTextMe.setSelected(true);
                mImageMe.setSelected(true);
                break;
        }
    }

    //重置所有文本的选中状态
    private void setSelected() {
        mTextChats.setSelected(false);
        mTextContact.setSelected(false);
        mTextFind.setSelected(false);
        mTextMe.setSelected(false);

        mImageChats.setSelected(false);
        mImageContact.setSelected(false);
        mImageFind.setSelected(false);
        mImageMe.setSelected(false);
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.seal_chat:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.seal_contact_list:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.seal_find:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.seal_me:
                mViewPager.setCurrentItem(3, false);
                mMineRed.setVisibility(View.GONE);
                break;
            case R.id.seal_more:

                break;
        }
    }


    protected void initData()
    {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDragOut()
    {
        mUnreadNumView.setVisibility(View.GONE);
    }



}
