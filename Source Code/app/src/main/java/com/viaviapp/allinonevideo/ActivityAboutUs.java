package com.viaviapp.allinonevideo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.item.ItemAbout;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class ActivityAboutUs extends AppCompatActivity {

    TextView txtAppName, txtVersion, txtCompany, txtEmail, txtWebsite, txtContact;
    ImageView imgAppLogo;
    ArrayList<ItemAbout> mListItem;
    ScrollView mScrollView;
    ProgressBar mProgressBar;
    WebView webView;
    Toolbar toolbar;
    JsonUtils jsonUtils;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.menu_about));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        jsonUtils = new JsonUtils(this);
        jsonUtils.forceRTLIfSupported(getWindow());

        txtAppName = findViewById(R.id.text_app_name);
        txtVersion = findViewById(R.id.text_version);
        txtCompany = findViewById(R.id.text_company);
        txtEmail = findViewById(R.id.text_email);
        txtWebsite = findViewById(R.id.text_website);
        txtContact = findViewById(R.id.text_contact);
        imgAppLogo = findViewById(R.id.app_logo_about_us);
        webView = findViewById(R.id.webView);
        webView.setBackgroundColor(0);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDefaultFontSize(14);
        mScrollView = findViewById(R.id.scrollView);
        mProgressBar = findViewById(R.id.progressBar);

        mListItem = new ArrayList<>();

        if (JsonUtils.isNetworkAvailable(ActivityAboutUs.this)) {
            new getAboutUs().execute(Constant.ABOUT_US_URL);
        }

     }

    @SuppressLint("StaticFieldLeak")
    private class getAboutUs extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mScrollView.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mProgressBar.setVisibility(View.GONE);
            mScrollView.setVisibility(View.VISIBLE);
            if (null == result || result.length() == 0) {
                showToast(getString(R.string.no_data));
            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.LATEST_ARRAY_NAME);
                    JSONObject objJson;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);
                        ItemAbout itemAbout = new ItemAbout();
                        itemAbout.setAppName(objJson.getString(Constant.APP_NAME));
                        itemAbout.setAppLogo(objJson.getString(Constant.APP_IMAGE));
                        itemAbout.setAppVersion(objJson.getString(Constant.APP_VERSION));
                        itemAbout.setAppAuthor(objJson.getString(Constant.APP_AUTHOR));
                        itemAbout.setAppEmail(objJson.getString(Constant.APP_EMAIL));
                        itemAbout.setAppWebsite(objJson.getString(Constant.APP_WEBSITE));
                        itemAbout.setAppContact(objJson.getString(Constant.APP_CONTACT));
                        itemAbout.setAppDescription(objJson.getString(Constant.APP_DESC));
                        mListItem.add(itemAbout);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setResult();
            }
        }
    }

    private void setResult() {

        ItemAbout itemAbout = mListItem.get(0);
        txtAppName.setText(itemAbout.getAppName());
        txtVersion.setText(itemAbout.getAppVersion());
        txtCompany.setText(itemAbout.getAppAuthor());
        txtEmail.setText(itemAbout.getAppEmail());
        txtWebsite.setText(itemAbout.getAppWebsite());
        txtContact.setText(itemAbout.getAppContact());
        Picasso.get().load(Constant.IMAGE_PATH_URL + itemAbout.getAppLogo()).into(imgAppLogo);

        String mimeType = "text/html;charset=UTF-8";
        String encoding = "utf-8";
        String htmlText = itemAbout.getAppDescription();

        String text = "<html><head>"
                + "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/fonts/Poppins-Medium_0.ttf\")}body{font-family: MyFont;color: #8D8D8D;text-align:left}"
                + "</style></head>"
                + "<body>"
                + htmlText
                + "</body></html>";

        webView.loadDataWithBaseURL(null, text, mimeType, encoding, null);
    }

    public void showToast(String msg) {
        Toast.makeText(ActivityAboutUs.this, msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }
}

