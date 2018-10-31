package com.gridelayout.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gridelayout.R;
import com.gridelayout.utils.App;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActGrideLayout extends BaseActivity {

    String TAG = "ActGrideLayout";
    @BindView(R.id.gridLayout) GridLayout gridLayout;
    @BindView(R.id.rlMainWishLay) RelativeLayout rlMainWishLay;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ViewGroup.inflate(this, R.layout.act_gridelayout, ll_SubMainContainer);
            ButterKnife.bind(this);

            rl_baseToolbar.setVisibility(View.GONE);
            viewHeightWidth();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void viewHeightWidth() {
        try {

            ViewTreeObserver vto = gridLayout.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    gridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    int Screenwidth = 0, Screenheight = 0;
                    Screenwidth = gridLayout.getMeasuredWidth();
                    Screenheight = gridLayout.getMeasuredHeight();

                    App.showLog(TAG, "gridLayout width: " + Screenwidth); //layout-840 - 1080(Screen)
                    App.showLog(TAG, "gridLayout height: " + Screenheight); //layout-1437 - 1920(Screen)

                    addCustomView(gridLayout, Screenwidth, Screenheight);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-- https://gist.github.com/shivamsriva31093/3902facda6c7d21cc20b7c282ff05d07
    private void addCustomView(ViewGroup root, int Screenwidth, int Screenheight) {

        GridLayout gridLayout = new GridLayout(this);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.width = GridLayout.LayoutParams.MATCH_PARENT;
        gridLayout.setLayoutParams(layoutParams);


        int forLoopCount = 0;
        int rowLayHeight = 0, rowLayWidth = 0;
        int total = 30;
        int r = 0, c = 0;

        //-- Change outer main-layout height and width according to gride layout.
        rlMainWishLay.getLayoutParams().height = Screenheight;
        rlMainWishLay.getLayoutParams().width = Screenwidth;


        //-- Change total according to fit row and colum to screen
        if (total < 10)
        {
            //-- for total<20 otherwise to dynamic
            forLoopCount = total;

            gridLayout.setColumnCount(2);
            gridLayout.setRowCount(6);

            rowLayWidth = Screenwidth / 2;
            rowLayHeight = rowLayWidth;
            //rowLayHeight = Screenheight / 5;

        }
        else if (total >= 10 && total < 20)
        {
            total = 20;

            //-- for total<20 otherwise to dynamic
            forLoopCount = total;

            gridLayout.setColumnCount(4);
            gridLayout.setRowCount(5);

            rowLayWidth = Screenwidth/4;
            rowLayHeight = rowLayWidth;
            //rowLayHeight = Screenheight / 5;
        }
        else if (total >= 20 && total < 30)
        {
            total = 30;

            //-- for total<20 otherwise to dynamic
            forLoopCount = total;

            gridLayout.setColumnCount(5);
            gridLayout.setRowCount(6);

            rowLayWidth = Screenwidth /5;
            rowLayHeight = rowLayWidth;
            //rowLayHeight = Screenheight / 5;
        }
        else if (total >= 30)
        {
            if(total>100){
                total = 100;
            }


            //-- refere link to understand below code -- https://math.stackexchange.com/questions/2524483/dividing-rectangle-into-gridsquares-with-known-n-number-of-squares
            float overAllSquareSize = (Screenwidth*Screenheight)/total;  //-- single sqArea = total sq/total Number
            float exactSquareSize = (float) Math.sqrt(overAllSquareSize);  //-- as square having same width & height -> "root of (single sqArea) == width or height"
            int columCount = (int) (Screenwidth/exactSquareSize);  //-- total squares can feet on screen width
            int rowCount = (int) (Screenheight/exactSquareSize);  //-- total squares can feet on screen height

            //-- set total squares can feet on screen width & height
            gridLayout.setColumnCount(columCount);
            gridLayout.setRowCount(rowCount);


            //-- Loop Gride change from 20 to dynamic
            forLoopCount = (rowCount*columCount);

            if( (exactSquareSize*columCount)<Screenwidth && (exactSquareSize*rowCount)<Screenheight )
            {

                //-- Get original width and height && gride total count for width and height difference
                float widhDiff = Screenwidth -(exactSquareSize*columCount);
                float heightDiff = Screenheight -(exactSquareSize*rowCount);


                //-- get new square width and height according to original width and height
                float newExactSquareSize = 0;
                if( (widhDiff/columCount) <= (heightDiff/rowCount) )
                {
                    newExactSquareSize = exactSquareSize + (heightDiff/rowCount);
                    //example : 240 = 206 + (133/4) -> for total 35
                }

                if( (widhDiff/columCount) >= (heightDiff/rowCount) )
                {
                    newExactSquareSize = exactSquareSize + (widhDiff/columCount);
                }


                //-- change sqaureArea by single
                for(int i = (int) exactSquareSize; i<newExactSquareSize; i++)
                {
                    if( ((i*columCount)<Screenwidth) && ((i*rowCount)<Screenheight)  ) {
                        App.showLog(TAG, "new sqaure value according to width i: "+i);
                        exactSquareSize = i;
                    }
                    else {
                        App.showLog(TAG, "new sqaure value according to width i break");
                        break;
                    }
                }


                App.showLog(TAG, "exactSquareSize: "+exactSquareSize);

            }

            rowLayWidth = (int) exactSquareSize;
            rowLayHeight = (int) exactSquareSize;

            rlMainWishLay.getLayoutParams().height = (rowLayHeight*rowCount);
            rlMainWishLay.getLayoutParams().width = (rowLayWidth*columCount);

            App.showLog(TAG, "Screenwidth: "+Screenwidth);
            App.showLog(TAG, "Screenheight: "+Screenheight);
            App.showLog(TAG, "overAllSquareSize: "+overAllSquareSize);
            App.showLog(TAG, "columCount: "+columCount);
            App.showLog(TAG, "rowCount: "+rowCount);
        }

        App.showLog(TAG, "total: " + total);
        App.showLog(TAG, "rowLayHeight: " + rowLayHeight);
        App.showLog(TAG, "rowLayWidth: " + rowLayWidth);




        for (int j = 0; j < forLoopCount; j++) {

            try {

                final RelativeLayout relativeLayout = createChild();
                final ImageView imageView = new ImageView(this);

                imageView.setLayoutParams(new ViewGroup.LayoutParams(rowLayWidth, rowLayHeight));
                imageView.setImageResource(R.mipmap.ic_launcher);


                GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
                GridLayout.Spec colSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);

                GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(rowSpan, colSpan);
                relativeLayout.addView(imageView);

                //-- Animation to view coming from bottom
                setAnimation(relativeLayout, j);

                gridLayout.addView(relativeLayout, gridParam);


                r++;
                c++;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        root.addView(gridLayout);

    }

    int LastPosition_Notification = -1;
    private void setAnimation(final View viewToAnimate, final int position) {
        if (position > LastPosition_Notification) {

            Animation animation = AnimationUtils.loadAnimation(ActGrideLayout.this, R.anim.slide_in_up);
            animation.setDuration(position*50);
            viewToAnimate.startAnimation(animation);
            LastPosition_Notification = position;

        }
    }

    private RelativeLayout createChild() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams cvLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(cvLayoutParams);
        return relativeLayout;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        App.myFinishActivity(ActGrideLayout.this);
    }


}
