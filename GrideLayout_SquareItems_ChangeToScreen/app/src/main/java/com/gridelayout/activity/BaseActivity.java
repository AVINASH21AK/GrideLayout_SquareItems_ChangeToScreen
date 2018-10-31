package com.gridelayout.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gridelayout.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class BaseActivity extends AppCompatActivity {

    protected String TAG = "BaseActivity";

    protected RelativeLayout rl_baseToolbar;
    protected LinearLayout ll_SubMainContainer;
    protected TextView tvTitle;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_base);

        baseClickInitialize();

    }

    public void baseClickInitialize(){
        try{

            rl_baseToolbar = (RelativeLayout) findViewById(R.id.rl_baseToolbar);
            ll_SubMainContainer = (LinearLayout) findViewById(R.id.ll_SubMainContainer);
            tvTitle = (TextView) findViewById(R.id.tvTitle);


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
