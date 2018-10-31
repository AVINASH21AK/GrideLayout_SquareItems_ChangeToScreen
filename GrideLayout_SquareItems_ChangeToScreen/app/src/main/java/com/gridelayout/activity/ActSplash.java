package com.gridelayout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.gridelayout.R;
import com.gridelayout.utils.App;

import butterknife.ButterKnife;

public class ActSplash extends BaseActivity{

    String TAG = "ActSplash";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            ViewGroup.inflate(this, R.layout.act_splash, ll_SubMainContainer);
            ButterKnife.bind(this);

            initialize();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void initialize(){
        try{

            rl_baseToolbar.setVisibility(View.GONE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i1 = new Intent(ActSplash.this, ActGrideLayout.class);
                    App.myStartActivityClearTop(ActSplash.this, i1);

                }
            }, 3000);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
