package com.viaviapp.allinonevideo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.CommentAdapter;
import com.example.item.ItemComment;
import com.example.util.Constant;
import com.example.util.ItemOffsetDecoration;
import com.example.util.JsonUtils;
import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivityComment extends AppCompatActivity {

    ProgressBar progressBar;
    ArrayList<ItemComment> mCommentList;
    CommentAdapter commentAdapter;
    RecyclerView recyclerViewCommentVideo;
    TextView txt_comment_no;
    JsonUtils jsonUtils;
    MyApplication myApplication;
    EditText edt_comment;
    ImageView image_sent;
    ProgressDialog pDialog;
    String strMessage;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
       // setDragEdge(SwipeBackLayout.DragEdge.TOP);

        mCommentList = new ArrayList<>();
        myApplication = MyApplication.getInstance();
        pDialog = new ProgressDialog(this);
        jsonUtils = new JsonUtils(this);
        jsonUtils.forceRTLIfSupported(getWindow());

        progressBar = findViewById(R.id.progressBar);
        txt_comment_no = findViewById(R.id.txt_comment_no);
        recyclerViewCommentVideo = findViewById(R.id.rv_comment_video);
        recyclerViewCommentVideo.setHasFixedSize(false);
        recyclerViewCommentVideo.setNestedScrollingEnabled(false);
        recyclerViewCommentVideo.setLayoutManager(new LinearLayoutManager(ActivityComment.this, LinearLayoutManager.VERTICAL, false));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(ActivityComment.this, R.dimen.item_offset);
        recyclerViewCommentVideo.addItemDecoration(itemDecoration);
        edt_comment = findViewById(R.id.edt_comment);
        image_sent = findViewById(R.id.image_sent);

        if (getResources().getString(R.string.isRTL).equals("true")) {
            image_sent.setImageResource(R.drawable.send_right);
        }
        else {
            image_sent.setImageResource(R.drawable.send);
        }

        if (JsonUtils.isNetworkAvailable(ActivityComment.this)) {
            new getVideoDetail().execute(Constant.SINGLE_VIDEO_URL + Constant.LATEST_CMT_IDD);
        }

        image_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myApplication.getIsLogin()) {
                    if (edt_comment.length() == 0) {
                        Toast.makeText(ActivityComment.this, getString(R.string.comment_require), Toast.LENGTH_SHORT).show();
                    } else {
                        if (JsonUtils.isNetworkAvailable(ActivityComment.this)) {
                            new MyTaskComment().execute(Constant.COMMENT_URL + edt_comment.getText().toString() + "&user_name=" + myApplication.getUserName() + "&post_id=" + Constant.LATEST_CMT_IDD);
                        }
                    }

                } else {
                    Toast.makeText(ActivityComment.this, getString(R.string.login_require), Toast.LENGTH_SHORT).show();
                    Intent intent_login = new Intent(ActivityComment.this, ActivitySignIn.class);
                    intent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_login.putExtra("isfromdetail", true);
                    intent_login.putExtra("isvideoid", Constant.LATEST_CMT_IDD);
                    startActivity(intent_login);
                }

            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class getVideoDetail extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            recyclerViewCommentVideo.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            recyclerViewCommentVideo.setVisibility(View.VISIBLE);
            if (null == result || result.length() == 0) {
                showToast(getString(R.string.no_data));
            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.LATEST_ARRAY_NAME);
                    JSONObject objJson;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);

                        JSONArray jsonArrayCmt = objJson.getJSONArray(Constant.COMMENT_ARRAY);
                        JSONObject objComment;
                        for (int j = 0; j < jsonArrayCmt.length(); j++) {
                            objComment = jsonArrayCmt.getJSONObject(j);
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

        if (mCommentList.size() == 0) {
            txt_comment_no.setVisibility(View.VISIBLE);
        }
        commentAdapter = new CommentAdapter(ActivityComment.this, mCommentList);
        recyclerViewCommentVideo.setAdapter(commentAdapter);

    }

    public void showToast(String msg) {
        Toast.makeText(ActivityComment.this, msg, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("StaticFieldLeak")
    private class MyTaskComment extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dismissProgressDialog();

            if (null == result || result.length() == 0) {
                showToast(getString(R.string.no_data));

            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.LATEST_ARRAY_NAME);
                    JSONObject objJson;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);
                        strMessage = objJson.getString(Constant.MSG);
                        Constant.GET_SUCCESS_MSG = objJson.getInt(Constant.SUCCESS);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setResult();
            }
        }
    }

    public void setResult() {

        if (Constant.GET_SUCCESS_MSG == 0) {
            showToast(getString(R.string.error_title) + "\n" + strMessage);
        } else {
            onBackPressed();
        }
    }

    private void skipActivity(Class<?> classOf) {
        Intent intent = new Intent(getApplicationContext(), classOf);
        startActivity(intent);
    }

    public void showProgressDialog() {
        pDialog.setMessage(getString(R.string.loading));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public void dismissProgressDialog() {
        pDialog.dismiss();
    }
}
