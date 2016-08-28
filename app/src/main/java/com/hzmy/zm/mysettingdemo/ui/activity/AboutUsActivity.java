package com.hzmy.zm.mysettingdemo.ui.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzmy.zm.mysettingdemo.R;
import com.hzmy.zm.mysettingdemo.ui.utils.ToastUtils;
import com.hzmy.zm.mysettingdemo.ui.utils.update.UpdateService;
import com.hzmy.zm.mysettingdemo.ui.widget.DialogWithYesOrNoUtils;


/**
 * Created by Administrator on 2015/3/3.
 */
public class AboutUsActivity extends BaseActionBarActivity {

    private TextView mSDKVersion;

    private boolean isHasNewVersion;

    private ImageView mNewVersionView;

    private RelativeLayout mVersionItem;

    private String url;

    private RelativeLayout mCloseDebug;

    private RelativeLayout mstartDebug;

    long[] mHits = new long[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.de_actionbar_back);
        getSupportActionBar().setTitle(R.string.about_us);

        RelativeLayout mUpdateLog = (RelativeLayout) findViewById(R.id.rl_update_log);
        RelativeLayout mFunctionIntroduce = (RelativeLayout) findViewById(R.id.rl_function_introduce);
        RelativeLayout mRongCloudWeb = (RelativeLayout) findViewById(R.id.rl_rongcloud_web);
        mNewVersionView = (ImageView) findViewById(R.id.about_sealtalk_version);
        mVersionItem = (RelativeLayout) findViewById(R.id.rl_version);
        mSDKVersion = (TextView) findViewById(R.id.sdk_version_text);
        TextView version = (TextView) findViewById(R.id.sealtalk_version);
        mCloseDebug = (RelativeLayout) findViewById(R.id.close_debug);
        mstartDebug = (RelativeLayout) findViewById(R.id.start_debug);
        version.setText("1.0");
        mUpdateLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutUsActivity.this, UpdateLogActivity.class));
            }
        });
        mFunctionIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutUsActivity.this, FunctionIntroducedActivity.class));
            }
        });

        mRongCloudWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutUsActivity.this, FunctionIntroducedActivity.class));
            }
        });

        //判断新版本
        //新版本下载地址
        url = getIntent().getStringExtra("url");
        isHasNewVersion = getIntent().getBooleanExtra("isHasNewVersion", false);
        if (isHasNewVersion) {
            mNewVersionView.setVisibility(View.VISIBLE);
            mVersionItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNewVersionView.setVisibility(View.GONE);
                    final AlertDialog dlg = new AlertDialog.Builder(AboutUsActivity.this).create();
                    dlg.show();
                    Window window = dlg.getWindow();
                    window.setContentView(R.layout.dialog_download);
                    TextView text = (TextView) window.findViewById(R.id.friendship_content1);
                    TextView photo = (TextView) window.findViewById(R.id.friendship_content2);
                    text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(url);
                            intent.setData(content_url);
                            startActivity(intent);
                            dlg.cancel();
                        }
                    });
                    photo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.shortToast(mContext, getString(R.string.downloading_apk));
                            UpdateService.Builder.create(url)
                            .setStoreDir("update/flag")
                            .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                            .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                            .build(mContext);
                            dlg.cancel();
                        }
                    });
                    isHasNewVersion = false;
                }
            });
        }


        mstartDebug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] > SystemClock.uptimeMillis() - 10000) {
                    if (getSharedPreferences("config", MODE_PRIVATE).getBoolean("isDebug", false)) {
                        ToastUtils.shortToast(mContext, "debug 模式已开启");
                    } else {
                        DialogWithYesOrNoUtils.getInstance().showDialog(mContext, "是否开启 App Debug 模式(需要重新登录应用)?", new DialogWithYesOrNoUtils.DialogCallBack() {
                            @Override
                            public void exectEvent() {
//                                SharedPreferences.Editor editor = getSharedPreferences("config", MODE_PRIVATE).edit();
//                                editor.putBoolean("isDebug", true);
//                                editor.apply();
//                                BroadcastManager.getInstance(mContext).sendBroadcast(SealConst.EXIT);

                            }

                            @Override
                            public void exectEditEvent(String editText) {

                            }

                            @Override
                            public void updatePassword(String oldPassword, String newPassword) {

                            }
                        });
                    }
                }
            }
        });

        if (getSharedPreferences("config", MODE_PRIVATE).getBoolean("isDebug", false)) {
            mCloseDebug.setVisibility(View.VISIBLE);
            mCloseDebug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogWithYesOrNoUtils.getInstance().showDialog(mContext, "是否关闭 App Debug 模式(需要重新登录应用)?", new DialogWithYesOrNoUtils.DialogCallBack() {
                        @Override
                        public void exectEvent() {
//                            SharedPreferences.Editor editor = getSharedPreferences("config", MODE_PRIVATE).edit();
//                            editor.putBoolean("isDebug", false);
//                            editor.apply();
//                            BroadcastManager.getInstance(mContext).sendBroadcast(SealConst.EXIT);

                        }

                        @Override
                        public void exectEditEvent(String editText) {

                        }

                        @Override
                        public void updatePassword(String oldPassword, String newPassword) {

                        }
                    });
                }
            });
        }



        String[] versionInfo = getVersionInfo();
        mSDKVersion.setText(versionInfo[1]);
    }

    private String[] getVersionInfo() {
        String[] version = new String[2];

        PackageManager packageManager = getPackageManager();

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            version[0] = String.valueOf(packageInfo.versionCode);
            version[1] = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }
}
