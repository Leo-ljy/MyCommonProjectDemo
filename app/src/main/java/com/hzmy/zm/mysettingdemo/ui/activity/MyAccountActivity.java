package com.hzmy.zm.mysettingdemo.ui.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hzmy.zm.mysettingdemo.R;
import com.hzmy.zm.mysettingdemo.ui.utils.phone.PhotoUtils;
import com.hzmy.zm.mysettingdemo.ui.widget.BottomMenuDialog;
import com.hzmy.zm.mysettingdemo.ui.widget.LoadDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * Created by Administrator on 2015/3/2.
 */
public class MyAccountActivity extends BaseActionBarActivity implements View.OnClickListener {

    private static final int UPLOADPORTRAIT = 8;

    private RelativeLayout portraitItem, nameItem, passwordItem;



    private ImageView mImageView;

    private TextView mName , mPhone;

    private PhotoUtils photoUtils;
    private BottomMenuDialog dialog;


    private String imageUrl;

    private Uri selectUri;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            setImageView(selectUri);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.de_actionbar_back);
        getSupportActionBar().setTitle("个人账号");


        initView();


    }

    private void initView() {
        mPhone = (TextView) findViewById(R.id.tv_my_phone);
        portraitItem = (RelativeLayout) findViewById(R.id.rl_my_portrait);
        nameItem = (RelativeLayout) findViewById(R.id.rl_my_username);
        mImageView = (ImageView) findViewById(R.id.img_my_portrait);
        mName = (TextView) findViewById(R.id.tv_my_username);
        portraitItem.setOnClickListener(this);
        nameItem.setOnClickListener(this);

        String cacheName = "我是小安子";
        String cachePortrait = "头像地址";
        String cachePhone = "18768123456";
        if (!TextUtils.isEmpty(cachePhone)) {
            mPhone.setText("+86 " + cachePhone);
        }
        if (!TextUtils.isEmpty(cacheName)) {
            mName.setText(cacheName);
            if (TextUtils.isEmpty(cachePortrait)) {
//                ImageLoader.getInstance().displayImage(RongGenerate.generateDefaultAvatar(cacheName, sp.getString("loginid", "a")), mImageView, App.getOptions());
            } else {
//                ImageLoader.getInstance().displayImage(cachePortrait, mImageView, App.getOptions());
            }

        }
        setPortraitChangeListener();
    }

    private void setPortraitChangeListener() {
        photoUtils = new PhotoUtils(new PhotoUtils.OnPhotoResultListener() {
            @Override
            public void onPhotoResult(Uri uri) {
                if (uri != null && !TextUtils.isEmpty(uri.getPath())) {
                    selectUri = uri;
                    mHandler.sendEmptyMessage(1);

////                    uploadImage("测试", "测试", selectUri);
////                    LoadDialog.show(mContext);
                }
            }

            @Override
            public void onPhotoCancel() {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_my_portrait:
                showPhotoDialog();
                break;
            case R.id.rl_my_username:
                startActivity(new Intent(MyAccountActivity.this, UpdateNameActivity.class));
                break;
        }
    }



    /**
     * 弹出底部框
     */
    private void showPhotoDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        dialog = new BottomMenuDialog(mContext);
        dialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                photoUtils.takePicture(MyAccountActivity.this);
            }
        });
        dialog.setMiddleListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                photoUtils.selectPicture(MyAccountActivity.this);
            }
        });
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoUtils.INTENT_CROP:
            case PhotoUtils.INTENT_TAKE:
            case PhotoUtils.INTENT_SELECT:
                photoUtils.onActivityResult(MyAccountActivity.this, requestCode, resultCode, data);
                break;
        }
    }

    private void setImageView(Uri photoUri)
    {
        try
        {
            if (photoUri != null)
            {
                Bitmap bitmap = decodeUriAsBitmap(photoUri);
                mImageView.setImageBitmap(bitmap);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri)
    {
        System.out.println("路径：" + uri);
        System.out.println("路径image.getPath()：" + uri.getPath());
        Bitmap bitmap = null;
        try
        {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public void uploadImage(final String domain, String imageToken, Uri imagePath) {
        if (TextUtils.isEmpty(domain) && TextUtils.isEmpty(imageToken) && TextUtils.isEmpty(imagePath.toString())) {
            throw new RuntimeException("upload parameter is null!");
        }
//        File imageFile = new File(imagePath.getPath());

//        Log.d("","图片路径：" + imagePath.getPath());
//
//        if (this.uploadManager == null) {
//            this.uploadManager = new UploadManager();
//        }
//        this.uploadManager.put(imageFile, null, imageToken, new UpCompletionHandler() {
//
//            @Override
//            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
//                if (responseInfo.isOK()) {
//                    try {
//                        String key = (String) jsonObject.get("key");
//                        imageUrl = "http://" + domain + "/" + key;
//                        Log.e("uploadImage", imageUrl);
//                        if (!TextUtils.isEmpty(imageUrl)) {
//                            request(UPLOADPORTRAIT);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, null);
    }


}
