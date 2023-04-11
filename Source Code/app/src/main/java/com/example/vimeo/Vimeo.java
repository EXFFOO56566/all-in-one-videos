package com.example.vimeo;


import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.Rational;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings.PluginState;

import com.viaviapp.allinonevideo.R;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Vimeo extends Activity {
	private final String TAG = "MainActivity";
	private HTML5WebView mWebView;
	String Id;
	private final PictureInPictureParams.Builder pictureInPictureParamsBuilder =
			new PictureInPictureParams.Builder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWebView = new HTML5WebView(this);
		mWebView.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				switch (view.getId()) {

					case R.id.pip:
						pictureInPictureMode();
						break;
				}

			}
		});
		
//		Intent i=getIntent();
//	    Id=i.getStringExtra("id");
		setVideoView(getIntent());
		//Auto playing vimeo videos in Android webview
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setAllowFileAccess(true);
		mWebView.getSettings().setAppCacheEnabled(true);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setPluginState(PluginState.OFF);
		mWebView.getSettings().setAllowFileAccess(true);
		

		setContentView(mWebView.getLayout());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vimeo, menu);
		return true;
	}
	private void setVideoView(Intent i) {
		Id = i.getStringExtra("id");
		mWebView.loadUrl("http://player.vimeo.com/video/"+Id+"?player_id=player&autoplay=1&title=0&byline=0&portrait=0&api=1&maxheight=480&maxwidth=800");

	}
	private void pictureInPictureMode(){
		Rational aspectRatio = new Rational(mWebView.getWidth(), mWebView.getHeight());
		pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
		enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
	}
	@Override
	public void onUserLeaveHint(){
		if(!isInPictureInPictureMode()){
			Rational aspectRatio = new Rational(mWebView.getWidth(), mWebView.getHeight());
			pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
			enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
		}
	}
	@Override
	public void onPictureInPictureModeChanged (boolean isInPictureInPictureMode,
											   Configuration newConfig) {
		if (isInPictureInPictureMode) {
			mWebView.button.setVisibility(View.GONE);
			//tb.setVisibility(View.GONE);
		} else {
			mWebView.button.setVisibility(View.VISIBLE);
			//tb.setVisibility(View.VISIBLE);
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
//		if( mWebView.isPlaying()){
//			vv.stopPlayback();
//		}
		mWebView.handleBack(this);
		super.onStop();
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item.getItemId() == R.id.reload){
			mWebView.destroyDrawingCache();
			Log.d(TAG,"Reloading..");
			mWebView.reload();
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		mWebView.handleBack(this);
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Checks the orientation of the screen
		int orientation = getResources().getConfiguration().orientation;
		if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// In landscape
			mWebView.button.setVisibility(View.GONE);
		} else {
			// In portrait
			mWebView.button.setVisibility(View.VISIBLE);
		}

	}
}
