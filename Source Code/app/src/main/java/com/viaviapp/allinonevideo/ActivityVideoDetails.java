package com.viaviapp.allinonevideo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.CommentAdapter;
import com.example.adapter.RelatedAdapter;
import com.example.dailymotion.DailyMotionPlay;
import com.example.dailymotion.DailyMotionPlayNoPip;
import com.example.favorite.DatabaseHelper;
import com.example.item.ItemComment;
import com.example.item.ItemLatest;
import com.example.serverlocal.NoPipServerActivity;
import com.example.serverlocal.PipServerActivity;
import com.example.util.Constant;
import com.example.util.ItemOffsetDecoration;
import com.example.util.JsonUtils;
import com.example.vimeo.Vimeo;
import com.example.vimeo.VimeoNoPip;
import com.example.youtube.YoutubePlay;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivityVideoDetails extends AppCompatActivity {

    Toolbar toolbar;
    WebView webViewDesc;
    TextView textViewTitle, text_time, text_view_all, txt_comment_no;
    ImageView imageViewVideo, imageViewPlay;
    LinearLayout linearLayoutMain;
    ProgressBar progressBar;
    ArrayList<ItemLatest> mVideoList, mVideoListRelated;
    ItemLatest itemVideo;
    Menu menu;
    LinearLayout adLayout;
    boolean isWhichScreenNotification;
    JsonUtils jsonUtils;
    DatabaseHelper databaseHelper;
    RelatedAdapter relatedAdapter;
    ArrayList<ItemComment> mCommentList;
    CommentAdapter commentAdapter;
    RecyclerView recyclerViewRelatedVideo, recyclerViewCommentVideo;
    MyApplication myApplication;
    LinearLayout lay_detail;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        jsonUtils = new JsonUtils(this);
        jsonUtils.forceRTLIfSupported(getWindow());

        mVideoList = new ArrayList<>();
        mVideoListRelated = new ArrayList<>();
        mCommentList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(ActivityVideoDetails.this);
        myApplication = MyApplication.getInstance();

        webViewDesc = findViewById(R.id.web_desc);
        textViewTitle = findViewById(R.id.text);
        imageViewPlay = findViewById(R.id.image_play);
        imageViewVideo = findViewById(R.id.image);
        linearLayoutMain = findViewById(R.id.lay_main);
        text_time = findViewById(R.id.text_time);
        progressBar = findViewById(R.id.progressBar);
        adLayout = findViewById(R.id.ad_view);
        recyclerViewRelatedVideo = findViewById(R.id.rv_most_video);
        recyclerViewRelatedVideo.setHasFixedSize(false);
        recyclerViewRelatedVideo.setNestedScrollingEnabled(false);
        recyclerViewRelatedVideo.setLayoutManager(new LinearLayoutManager(ActivityVideoDetails.this, LinearLayoutManager.HORIZONTAL, false));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(ActivityVideoDetails.this, R.dimen.item_offset);
        recyclerViewRelatedVideo.addItemDecoration(itemDecoration);
        text_view_all = findViewById(R.id.txt_comment_all);
        txt_comment_no = findViewById(R.id.txt_comment_no);
        lay_detail = findViewById(R.id.lay_detail);

        recyclerViewCommentVideo = findViewById(R.id.rv_comment_video);
        recyclerViewCommentVideo.setHasFixedSize(false);
        recyclerViewCommentVideo.setNestedScrollingEnabled(false);
        recyclerViewCommentVideo.setLayoutManager(new LinearLayoutManager(ActivityVideoDetails.this, LinearLayoutManager.VERTICAL, false));
        recyclerViewCommentVideo.addItemDecoration(itemDecoration);

        if (getResources().getString(R.string.isRTL).equals("true")) {
            lay_detail.setBackgroundResource(R.drawable.home_title_gradient_right);
        } else {
            lay_detail.setBackgroundResource(R.drawable.home_title_gradient);
        }

        Intent intent = getIntent();
        isWhichScreenNotification = intent.getBooleanExtra("isNotification", false);
        if (!isWhichScreenNotification) {
            if (JsonUtils.personalization_ad) {
                JsonUtils.showPersonalizedAds(adLayout, ActivityVideoDetails.this);
            } else {
                JsonUtils.showNonPersonalizedAds(adLayout, ActivityVideoDetails.this);
            }

        }


        if (JsonUtils.isNetworkAvailable(ActivityVideoDetails.this)) {
            new getVideoDetail().execute(Constant.SINGLE_VIDEO_URL + Constant.LATEST_IDD);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class getVideoDetail extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            linearLayoutMain.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            linearLayoutMain.setVisibility(View.VISIBLE);
            if (null == result || result.length() == 0) {
                showToast(getString(R.string.no_data));
            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.LATEST_ARRAY_NAME);
                    JSONObject objJson;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);
                        ItemLatest objItem = new ItemLatest();

                        objItem.setLatestId(objJson.getString(Constant.LATEST_ID));
                        objItem.setLatestCategoryName(objJson.getString(Constant.LATEST_CAT_NAME));
                        objItem.setLatestCategoryId(objJson.getString(Constant.LATEST_CATID));
                        objItem.setLatestVideoUrl(objJson.getString(Constant.LATEST_VIDEO_URL));
                        objItem.setLatestVideoPlayId(objJson.getString(Constant.LATEST_VIDEO_ID));
                        objItem.setLatestVideoName(objJson.getString(Constant.LATEST_VIDEO_NAME));
                        objItem.setLatestDuration(objJson.getString(Constant.LATEST_VIDEO_DURATION));
                        objItem.setLatestDescription(objJson.getString(Constant.LATEST_VIDEO_DESCRIPTION));
                        objItem.setLatestVideoImgBig(objJson.getString(Constant.LATEST_IMAGE_URL));
                        objItem.setLatestVideoType(objJson.getString(Constant.LATEST_TYPE));
                        objItem.setLatestVideoRate(objJson.getString(Constant.LATEST_RATE));
                        objItem.setLatestVideoView(objJson.getString(Constant.LATEST_VIEW));

                        mVideoList.add(objItem);

                        JSONArray jsonArrayChild = objJson.getJSONArray(Constant.RELATED_ARRAY);

                        for (int j = 0; j < jsonArrayChild.length(); j++) {
                            JSONObject objChild = jsonArrayChild.getJSONObject(j);
                            ItemLatest objItem2 = new ItemLatest();

                            objItem2.setLatestId(objChild.getString(Constant.LATEST_ID));
                            objItem2.setLatestCategoryName(objChild.getString(Constant.LATEST_CAT_NAME));
                            objItem2.setLatestCategoryId(objChild.getString(Constant.LATEST_CATID));
                            objItem2.setLatestVideoUrl(objChild.getString(Constant.LATEST_VIDEO_URL));
                            objItem2.setLatestVideoPlayId(objChild.getString(Constant.LATEST_VIDEO_ID));
                            objItem2.setLatestVideoName(objChild.getString(Constant.LATEST_VIDEO_NAME));
                            objItem2.setLatestDuration(objChild.getString(Constant.LATEST_VIDEO_DURATION));
                            objItem2.setLatestDescription(objChild.getString(Constant.LATEST_VIDEO_DESCRIPTION));
                            objItem2.setLatestVideoImgBig(objChild.getString(Constant.LATEST_IMAGE_URL));
                            objItem2.setLatestVideoType(objChild.getString(Constant.LATEST_TYPE));
                            objItem2.setLatestVideoRate(objChild.getString(Constant.LATEST_RATE));
                            objItem2.setLatestVideoView(objChild.getString(Constant.LATEST_VIEW));

                            mVideoListRelated.add(objItem2);
                        }

                        JSONArray jsonArrayCmt = objJson.getJSONArray(Constant.COMMENT_ARRAY);

                        int k = jsonArrayCmt.length() >= 3 ? 3 : jsonArrayCmt.length();
                        for (int j = 0; j < k; j++) {
                            JSONObject objComment = jsonArrayCmt.getJSONObject(j);
                            ItemComment itemComment = new ItemComment();

                            itemComment.setCommentId(objComment.getString(Constant.COMMENT_ID));
                            itemComment.setCommentName(objComment.getString(Constant.COMMENT_NAME));
                            itemComment.setCommentMsg(objComment.getString(Constant.COMMENT_MSG));

                            mCommentList.add(itemComment);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setResultSlider();
            }
        }
    }

    private void setResultSlider() {

        itemVideo = mVideoList.get(0);

        textViewTitle.setText(itemVideo.getLatestVideoName());
        webViewDesc.setBackgroundColor(0);
        webViewDesc.setFocusableInTouchMode(false);
        webViewDesc.setFocusable(false);
        WebSettings webSettings = webViewDesc.getSettings();
        webSettings.setDefaultFontSize(14);
        webViewDesc.getSettings().setDefaultTextEncodingName("UTF-8");

        String mimeType = "text/html;charset=UTF-8";
        String encoding = "utf-8";
        String htmlText = itemVideo.getLatestDescription();

        String text = "<html><head>"
                + "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/fonts/Poppins-Medium_0.ttf\")}body{font-family: MyFont;color: #666666;line-height:1.4}"
                + "</style></head>"
                + "<body>"
                + htmlText
                + "</body></html>";

        webViewDesc.loadDataWithBaseURL(null, text, mimeType, encoding, null);
        text_time.setText(itemVideo.getLatestDuration());

        switch (itemVideo.getLatestVideoType()) {
            case "local":
                Picasso.get().load(itemVideo.getLatestVideoImgBig()).into(imageViewVideo);
                break;
            case "server_url":
                Picasso.get().load(itemVideo.getLatestVideoImgBig()).into(imageViewVideo);
                break;
            case "youtube":
                Picasso.get().load(Constant.YOUTUBE_IMAGE_FRONT + itemVideo.getLatestVideoPlayId() + Constant.YOUTUBE_SMALL_IMAGE_BACK).into(imageViewVideo);
                break;
            case "dailymotion":
                Picasso.get().load(Constant.DAILYMOTION_IMAGE_PATH + itemVideo.getLatestVideoPlayId()).into(imageViewVideo);
                break;
            case "vimeo":
                Picasso.get().load(itemVideo.getLatestVideoImgBig()).into(imageViewVideo);
                break;
        }

        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (itemVideo.getLatestVideoType()) {
                    case "local": {

                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            // Do something for lollipop and above versions
                            Intent i = new Intent();
                            i.setClass(ActivityVideoDetails.this, PipServerActivity.class);
                            i.putExtra("id", itemVideo.getLatestVideoUrl());
                            i.putExtra("title", itemVideo.getLatestVideoName());
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        } else {
                            // do something for phones running an SDK before lollipop
                            Toast.makeText(ActivityVideoDetails.this, getString(R.string.pip_not_support), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            i.setClass(ActivityVideoDetails.this, NoPipServerActivity.class);
                            i.putExtra("id", itemVideo.getLatestVideoUrl());
                            i.putExtra("title", itemVideo.getLatestVideoName());
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }

                        break;
                    }
                    case "server_url": {
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            // Do something for lollipop and above versions
                            Intent i = new Intent();
                            i.setClass(ActivityVideoDetails.this, PipServerActivity.class);
                            i.putExtra("id", itemVideo.getLatestVideoUrl());
                            i.putExtra("title", itemVideo.getLatestVideoName());
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        } else {
                            // do something for phones running an SDK before lollipop
                            Toast.makeText(ActivityVideoDetails.this, getString(R.string.pip_not_support), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            i.setClass(ActivityVideoDetails.this, NoPipServerActivity.class);
                            i.putExtra("id", itemVideo.getLatestVideoUrl());
                            i.putExtra("title", itemVideo.getLatestVideoName());
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                        break;
                    }
                    case "youtube": {
                        Intent i = new Intent(ActivityVideoDetails.this, YoutubePlay.class);
                        i.putExtra("id", itemVideo.getLatestVideoPlayId());
                        startActivity(i);
                        break;
                    }
                    case "dailymotion": {
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            // Do something for lollipop and above versions
                            Intent i = new Intent(ActivityVideoDetails.this, DailyMotionPlay.class);
                            i.putExtra("id", itemVideo.getLatestVideoPlayId());
                            startActivity(i);
                        } else {
                            // do something for phones running an SDK before lollipop
                            Toast.makeText(ActivityVideoDetails.this, getString(R.string.pip_not_support), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ActivityVideoDetails.this, DailyMotionPlayNoPip.class);
                            i.putExtra("id", itemVideo.getLatestVideoPlayId());
                            startActivity(i);
                        }
                        break;
                    }
                    case "vimeo": {
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            // Do something for lollipop and above versions
                            Intent i = new Intent(ActivityVideoDetails.this, Vimeo.class);
                            i.putExtra("id", itemVideo.getLatestVideoPlayId());
                            startActivity(i);
                        } else {
                            // do something for phones running an SDK before lollipop
                            Toast.makeText(ActivityVideoDetails.this, getString(R.string.pip_not_support), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ActivityVideoDetails.this, VimeoNoPip.class);
                            i.putExtra("id", itemVideo.getLatestVideoPlayId());
                            startActivity(i);
                        }
                        break;
                    }
                }
            }
        });


        relatedAdapter = new RelatedAdapter(ActivityVideoDetails.this, mVideoListRelated);
        recyclerViewRelatedVideo.setAdapter(relatedAdapter);

        if (mCommentList.size() == 0) {
            txt_comment_no.setVisibility(View.VISIBLE);
        }
        commentAdapter = new CommentAdapter(ActivityVideoDetails.this, mCommentList);
        recyclerViewCommentVideo.setAdapter(commentAdapter);

        text_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.LATEST_CMT_IDD = itemVideo.getLatestId();
                skipActivity(ActivityComment.class);
            }
        });

    }

    private void skipActivity(Class<?> classOf) {
        Intent intent = new Intent(getApplicationContext(), classOf);
        startActivity(intent);
    }

    public void showToast(String msg) {
        Toast.makeText(ActivityVideoDetails.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        isFavourite();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_fav:
                ContentValues fav = new ContentValues();
                if (databaseHelper.getFavouriteById(Constant.LATEST_IDD)) {
                    databaseHelper.removeFavouriteById(Constant.LATEST_IDD);
                    menu.getItem(0).setIcon(R.drawable.ic_fav);
                    Toast.makeText(ActivityVideoDetails.this, getString(R.string.favourite_remove), Toast.LENGTH_SHORT).show();
                } else {
                    fav.put(DatabaseHelper.KEY_ID, Constant.LATEST_IDD);
                    fav.put(DatabaseHelper.KEY_TITLE, itemVideo.getLatestVideoName());
                    fav.put(DatabaseHelper.KEY_IMAGE, itemVideo.getLatestVideoImgBig());
                    fav.put(DatabaseHelper.KEY_VIEW, itemVideo.getLatestVideoView());
                    fav.put(DatabaseHelper.KEY_TYPE, itemVideo.getLatestVideoType());
                    fav.put(DatabaseHelper.KEY_PID, itemVideo.getLatestVideoPlayId());
                    fav.put(DatabaseHelper.KEY_TIME, itemVideo.getLatestDuration());
                    fav.put(DatabaseHelper.KEY_CNAME, itemVideo.getLatestCategoryName());
                    databaseHelper.addFavourite(DatabaseHelper.TABLE_FAVOURITE_NAME, fav, null);
                    menu.getItem(0).setIcon(R.drawable.ic_fav_hov);
                    Toast.makeText(ActivityVideoDetails.this, getString(R.string.favourite_add), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_share:

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, itemVideo.getLatestVideoName() + "\n" + itemVideo.getLatestVideoUrl() + "\n" + getResources().getString(R.string.share_msg) + getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    private void isFavourite() {
        if (databaseHelper.getFavouriteById(Constant.LATEST_IDD)) {
            menu.getItem(0).setIcon(R.drawable.ic_fav_hov);
        } else {
            menu.getItem(0).setIcon(R.drawable.ic_fav);
        }
    }

    @Override
    public void onBackPressed() {
        if (!isWhichScreenNotification) {
            super.onBackPressed();

        } else {
            Intent intent = new Intent(ActivityVideoDetails.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }


}
