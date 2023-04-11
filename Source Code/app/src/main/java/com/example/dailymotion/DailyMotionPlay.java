package com.example.dailymotion;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Rational;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.viaviapp.allinonevideo.R;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DailyMotionPlay extends Activity {

    private DMWebVideoView mVideoView;
    String Id;
    private Button pip;
    private final PictureInPictureParams.Builder pictureInPictureParamsBuilder =
            new PictureInPictureParams.Builder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.dailymotion);

        mVideoView = ((DMWebVideoView) findViewById(R.id.dmWebVideoView));
        setVideoView(getIntent());

        pip = findViewById(R.id.pip);
        pip.setOnClickListener(onClickListener);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    private final View.OnClickListener onClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.pip:
                            pictureInPictureMode();
                            break;
                    }
                }
            };

    private void setVideoView(Intent i) {
        Id = i.getStringExtra("id");
        mVideoView.setVideoId(Id, true);

    }

    private void pictureInPictureMode() {
        Rational aspectRatio = new Rational(mVideoView.getWidth(), mVideoView.getHeight());
        pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
        enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
    }

    @Override
    public void onUserLeaveHint() {
        if (!isInPictureInPictureMode()) {
            Rational aspectRatio = new Rational(mVideoView.getWidth(), mVideoView.getHeight());
            pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
            enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode,
                                              Configuration newConfig) {
        if (isInPictureInPictureMode) {
            pip.setVisibility(View.GONE);
        } else {
            pip.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNewIntent(Intent i) {
        setVideoView(i);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();

    }

    @Override
    public void onStop() {
        mVideoView.handleBackPress(this);
        super.onStop();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            pip.setVisibility(View.GONE);
        } else {
            // In portrait
            pip.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        mVideoView.handleBackPress(this);
    }

}
