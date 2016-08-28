package com.hzmy.zm.mysettingdemo.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.hzmy.zm.mysettingdemo.R;
import com.hzmy.zm.mysettingdemo.ui.widget.ClearWriteEditText;
import com.hzmy.zm.mysettingdemo.ui.widget.LoadDialog;


/**
 * Created by AMing on 16/6/23.
 * Company RongCloud
 */
public class UpdateNameActivity extends BaseActivity {

    private ClearWriteEditText mNameEditText;

    private String newName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.de_actionbar_back);
        getSupportActionBar().setTitle("昵称");
        mNameEditText = (ClearWriteEditText) findViewById(R.id.update_name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.de_select_ok, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ok) {
            newName = mNameEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(newName)) {
                LoadDialog.show(mContext);
            } else {
                Toast.makeText(mContext, "昵称不能为空",Toast.LENGTH_SHORT).show();
                mNameEditText.setShakeAnimation();
            }
        } else {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
