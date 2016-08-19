package com.hzmy.zm.mysettingdemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.hzmy.zm.mysettingdemo.ui.activity.MainActivity;
import com.hzmy.zm.mysettingdemo.ui.fragment.MyFragment1;
import com.hzmy.zm.mysettingdemo.ui.fragment.MyFragment2;
import com.hzmy.zm.mysettingdemo.ui.fragment.MyFragment3;
import com.hzmy.zm.mysettingdemo.ui.fragment.MyFragment4;

import java.util.List;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/8/19 10:49                               <br>
 */
public class FragmentAdapter extends FragmentPagerAdapter
{

    List<Fragment> mFragment;


    public FragmentAdapter(FragmentManager fm, List<Fragment> mFragment)
    {
        super(fm);
        this.mFragment = mFragment;
    }


    @Override
    public int getCount()
    {
        return mFragment.size();
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position)
    {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragment.get(position);
    }

}
