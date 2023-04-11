package com.example.dailymotion;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.WindowManager;

import com.viaviapp.allinonevideo.R;

public class DailyMotionPlayNoPip extends Activity {

    private DMWebVideoView mVideoView;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.dailymotion_no_pip);
        Intent i=getIntent();
        Id=i.getStringExtra("id");
   
        mVideoView = ((DMWebVideoView) findViewById(R.id.dmWebVideoView));
        mVideoView.setVideoId(Id, true);
        
        }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        mVideoView.handleBackPress(this);
    }

}
