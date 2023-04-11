package com.example.serverlocal;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Rational;
import android.view.View;
import android.widget.Button;

import com.halilibo.bettervideoplayer.BetterVideoPlayer;
import com.viaviapp.allinonevideo.R;

@RequiresApi(api = Build.VERSION_CODES.O)

public class PipServerActivity extends AppCompatActivity {

    private Button pip;
    private BetterVideoPlayer mBetterVideoPlayer;
    private final PictureInPictureParams.Builder pictureInPictureParamsBuilder =
            new PictureInPictureParams.Builder();

    String id, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_video);

        mBetterVideoPlayer = findViewById(R.id.bvp);
        setVideoView(getIntent());

        pip = findViewById(R.id.pip);
        pip.setOnClickListener(onClickListener);


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
        id = i.getStringExtra("id");
        title = i.getStringExtra("title");

        mBetterVideoPlayer.setSource(Uri.parse(id));
        // mBetterVideoPlayer.showControls();
        //mBetterVideoPlayer.setAutoPlay(true);
    }

    private void pictureInPictureMode() {
        Rational aspectRatio = new Rational(mBetterVideoPlayer.getWidth(), mBetterVideoPlayer.getHeight());
        pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
        enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
    }

    @Override
    public void onUserLeaveHint() {
        if (!isInPictureInPictureMode()) {
            Rational aspectRatio = new Rational(mBetterVideoPlayer.getWidth(), mBetterVideoPlayer.getHeight());
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
        if (mBetterVideoPlayer.isPlaying()) {
            mBetterVideoPlayer.stop();
        }
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
}