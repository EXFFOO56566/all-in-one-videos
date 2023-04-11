package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.HomeAllAdapter;
import com.example.adapter.HomeCatAdapter;
import com.example.adapter.HomeLatestAdapter;
import com.example.item.ItemCategory;
import com.example.item.ItemLatest;
import com.example.util.Constant;
import com.example.util.EnchantedViewPager;
import com.example.util.ItemOffsetDecoration;
import com.example.util.JsonUtils;
import com.example.util.RecyclerTouchListener;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;
import com.viaviapp.allinonevideo.MainActivity;
import com.viaviapp.allinonevideo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {

    RecyclerView recyclerViewLatestVideo, recyclerViewAllVideo, recyclerViewCatVideo;
    EnchantedViewPager mViewPager;
    CustomViewPagerAdapter mAdapter;
    ScrollView mScrollView;
    ProgressBar mProgressBar;
    ArrayList<ItemLatest> mSliderList;
    CircleIndicator circleIndicator;
    Button btnAll, btnLatest, btnCategory;
    int currentCount = 0;
    ArrayList<ItemLatest> mLatestList, mAllList;
    ArrayList<ItemCategory> mCatList;
    HomeCatAdapter homeCatAdapter;
    HomeLatestAdapter homeLatestAdapter;
    HomeAllAdapter homeAllAdapter;
    private FragmentManager fragmentManager;
    private InterstitialAd mInterstitial;
    private int AD_COUNT = 0;
    ItemCategory itemCategory;
    TextView txt_latest_video_no, txt_all_video_no, txt_cat_video_no;
    LinearLayout lay_1, lay_2, lay_3;
    private ProgressDialog pDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mSliderList = new ArrayList<>();
        mAllList = new ArrayList<>();
        mLatestList = new ArrayList<>();
        mCatList = new ArrayList<>();

        fragmentManager = requireActivity().getSupportFragmentManager();
        mProgressBar = rootView.findViewById(R.id.progressBar);
        mScrollView = rootView.findViewById(R.id.scrollView);
        mViewPager = rootView.findViewById(R.id.viewPager);
        circleIndicator = rootView.findViewById(R.id.indicator_unselected_background);

        recyclerViewLatestVideo = rootView.findViewById(R.id.rv_latest_video);
        recyclerViewAllVideo = rootView.findViewById(R.id.rv_all_video);
        recyclerViewCatVideo = rootView.findViewById(R.id.rv_cat_video);

        lay_1 = rootView.findViewById(R.id.lay_1);
        lay_2 = rootView.findViewById(R.id.lay_2);
        lay_3 = rootView.findViewById(R.id.lay_3);

        btnLatest = rootView.findViewById(R.id.btn_latest_video);
        btnAll = rootView.findViewById(R.id.btn_all_video);
        btnCategory = rootView.findViewById(R.id.btn_cat_video);

        txt_latest_video_no = rootView.findViewById(R.id.txt_latest_video_no);
        txt_all_video_no = rootView.findViewById(R.id.txt_all_video_no);
        txt_cat_video_no = rootView.findViewById(R.id.txt_cat_video_no);

        recyclerViewLatestVideo.setHasFixedSize(false);
        recyclerViewLatestVideo.setNestedScrollingEnabled(false);
        recyclerViewLatestVideo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(requireActivity(), R.dimen.item_offset);
        recyclerViewLatestVideo.addItemDecoration(itemDecoration);

        recyclerViewAllVideo.setHasFixedSize(false);
        recyclerViewAllVideo.setNestedScrollingEnabled(false);
        recyclerViewAllVideo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAllVideo.addItemDecoration(itemDecoration);

        recyclerViewCatVideo.setHasFixedSize(false);
        recyclerViewCatVideo.setNestedScrollingEnabled(false);
        recyclerViewCatVideo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCatVideo.addItemDecoration(itemDecoration);

        if (getResources().getString(R.string.isRTL).equals("true")) {
            lay_1.setBackgroundResource(R.drawable.home_title_gradient_right);
            lay_2.setBackgroundResource(R.drawable.home_title_gradient_right);
            lay_3.setBackgroundResource(R.drawable.home_title_gradient_right);
        } else {
            lay_1.setBackgroundResource(R.drawable.home_title_gradient);
            lay_2.setBackgroundResource(R.drawable.home_title_gradient);
            lay_3.setBackgroundResource(R.drawable.home_title_gradient);
        }

        btnLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity) requireActivity()).highLightNavigation(1, getString(R.string.menu_latest));
                LatestVideoFragment latestVideoFragment = new LatestVideoFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(HomeFragment.this);
                fragmentTransaction.add(R.id.Container, latestVideoFragment, getString(R.string.menu_latest));
                fragmentTransaction.addToBackStack(getString(R.string.menu_latest));
                fragmentTransaction.commit();
                ((MainActivity) requireActivity()).setToolbarTitle(getString(R.string.menu_latest));

            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity) requireActivity()).highLightNavigationBottom(2);
                AllVideoFragment allVideoFragment = new AllVideoFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(HomeFragment.this);
                fragmentTransaction.add(R.id.Container, allVideoFragment, getString(R.string.menu_video));
                fragmentTransaction.addToBackStack(getString(R.string.menu_video));
                fragmentTransaction.commit();
                ((MainActivity) requireActivity()).setToolbarTitle(getString(R.string.menu_video));

            }
        });


        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity) requireActivity()).highLightNavigationBottom(1);
                CategoryFragment categoryFragment = new CategoryFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(HomeFragment.this);
                fragmentTransaction.add(R.id.Container, categoryFragment, getString(R.string.menu_category));
                fragmentTransaction.addToBackStack(getString(R.string.menu_category));
                fragmentTransaction.commit();
                ((MainActivity) requireActivity()).setToolbarTitle(getString(R.string.menu_category));

            }
        });

        if (JsonUtils.isNetworkAvailable(requireActivity())) {
            new HomeSlider().execute(Constant.SLIDER_URL);
        }
        mViewPager.useScale();
        mViewPager.removeAlpha();
        return rootView;
    }

    private class CustomViewPagerAdapter extends PagerAdapter {
        private LayoutInflater inflater;

        private CustomViewPagerAdapter() {
            // TODO Auto-generated constructor stub
            inflater = requireActivity().getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mSliderList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View imageLayout = inflater.inflate(R.layout.row_slider_item, container, false);
            assert imageLayout != null;

            ImageView image = imageLayout.findViewById(R.id.image);
            TextView text_title = imageLayout.findViewById(R.id.text);
            LinearLayout lytParent = imageLayout.findViewById(R.id.rootLayout);

            final ItemLatest[] itemSlider = {mSliderList.get(position)};

            text_title.setText(itemSlider[0].getLatestVideoName());

            Picasso.get().load(itemSlider[0].getLatestVideoImgBig()).into(image);

            imageLayout.setTag(EnchantedViewPager.ENCHANTED_VIEWPAGER_POSITION + position);
            lytParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSlider[0] = mSliderList.get(position);
                    String id = itemSlider[0].getLatestVideoUrl();
                    if (id.isEmpty()) {
                        Toast.makeText(getActivity(), getString(R.string.no_url), Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(id)));
                    }

                }
            });
            container.addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((View) object);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class HomeSlider extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

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

                        objItem.setLatestId(objJson.getString(Constant.HOME_BANNER_ID));
                        objItem.setLatestVideoName(objJson.getString(Constant.HOME_BANNER_NAME));
                        objItem.setLatestVideoImgBig(objJson.getString(Constant.HOME_BANNER_IMAGE));
                        objItem.setLatestVideoUrl(objJson.getString(Constant.HOME_BANNER_LINK));

                        mSliderList.add(objItem);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setResultSlider();
            }
        }
    }

    private void setResultSlider() {

        if (!mSliderList.isEmpty()) {
            mAdapter = new CustomViewPagerAdapter();
            mViewPager.setAdapter(mAdapter);
            circleIndicator.setViewPager(mViewPager);
            autoPlay(mViewPager);
        }

        if (JsonUtils.isNetworkAvailable(requireActivity())) {
            new HomeVideo().execute(Constant.HOME_URL);
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    private void autoPlay(final ViewPager viewPager) {

        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mAdapter != null && viewPager.getAdapter().getCount() > 0) {
                        int position = currentCount % mAdapter.getCount();
                        currentCount++;
                        viewPager.setCurrentItem(position);
                        autoPlay(viewPager);
                    }
                } catch (Exception e) {
                    Log.e("TAG", "auto scroll pager error.", e);
                }
            }
        }, 2500);
    }

    @SuppressLint("StaticFieldLeak")
    private class HomeVideo extends AsyncTask<String, Void, String> {

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
                    JSONObject mainJsonob = mainJson.getJSONObject(Constant.LATEST_ARRAY_NAME);
                    JSONArray jsonArray = mainJsonob.getJSONArray("latest_video");
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

                        mLatestList.add(objItem);
                    }

                    JSONArray jsonArraymost = mainJsonob.getJSONArray("all_video");
                    JSONObject objJsonmost;
                    for (int i = 0; i < jsonArraymost.length(); i++) {
                        objJsonmost = jsonArraymost.getJSONObject(i);

                        ItemLatest objItem = new ItemLatest();

                        objItem.setLatestId(objJsonmost.getString(Constant.LATEST_ID));
                        objItem.setLatestCategoryName(objJsonmost.getString(Constant.LATEST_CAT_NAME));
                        objItem.setLatestCategoryId(objJsonmost.getString(Constant.LATEST_CATID));
                        objItem.setLatestVideoUrl(objJsonmost.getString(Constant.LATEST_VIDEO_URL));
                        objItem.setLatestVideoPlayId(objJsonmost.getString(Constant.LATEST_VIDEO_ID));
                        objItem.setLatestVideoName(objJsonmost.getString(Constant.LATEST_VIDEO_NAME));
                        objItem.setLatestDuration(objJsonmost.getString(Constant.LATEST_VIDEO_DURATION));
                        objItem.setLatestDescription(objJsonmost.getString(Constant.LATEST_VIDEO_DESCRIPTION));
                        objItem.setLatestVideoImgBig(objJsonmost.getString(Constant.LATEST_IMAGE_URL));
                        objItem.setLatestVideoType(objJsonmost.getString(Constant.LATEST_TYPE));
                        objItem.setLatestVideoRate(objJsonmost.getString(Constant.LATEST_RATE));
                        objItem.setLatestVideoView(objJsonmost.getString(Constant.LATEST_VIEW));

                        mAllList.add(objItem);
                    }
                    JSONArray jsonArray2 = mainJsonob.getJSONArray("category");
                    JSONObject objJson2 = null;
                    for (int i = 0; i < jsonArray2.length(); i++) {
                        objJson2 = jsonArray2.getJSONObject(i);

                        ItemCategory objItem = new ItemCategory();

                        objItem.setCategoryId(objJson2.getString(Constant.CATEGORY_CID));
                        objItem.setCategoryImageUrl(objJson2.getString(Constant.CATEGORY_IMAGE));
                        objItem.setCategoryName(objJson2.getString(Constant.CATEGORY_NAME));

                        mCatList.add(objItem);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setHomeVideo();
            }
        }
    }

    private void setHomeVideo() {

        txt_latest_video_no.setText(String.valueOf(mLatestList.size()) + "\u0020" + getResources().getString(R.string.total_video));
        txt_all_video_no.setText(String.valueOf(mAllList.size()) + "\u0020" + getResources().getString(R.string.total_video));
        txt_cat_video_no.setText(String.valueOf(mCatList.size()) + "\u0020" + getResources().getString(R.string.total_category));

        if (getActivity() != null) {
            homeLatestAdapter = new HomeLatestAdapter(getActivity(), mLatestList);
            recyclerViewLatestVideo.setAdapter(homeLatestAdapter);
        }
        if (getActivity() != null) {
            homeAllAdapter = new HomeAllAdapter(getActivity(), mAllList);
            recyclerViewAllVideo.setAdapter(homeAllAdapter);
        }
        if (getActivity() != null) {
            homeCatAdapter = new HomeCatAdapter(getActivity(), mCatList);
            recyclerViewCatVideo.setAdapter(homeCatAdapter);
        }

        recyclerViewCatVideo.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerViewCatVideo, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                itemCategory = mCatList.get(position);
                Constant.CATEGORY_IDD = itemCategory.getCategoryId();
                Constant.CATEGORY_TITLEE = itemCategory.getCategoryName();

                if (Constant.SAVE_ADS_FULL_ON_OFF.equals("true")) {
                    AD_COUNT++;
                    if (AD_COUNT == Integer.parseInt(Constant.SAVE_ADS_CLICK)) {
                        AD_COUNT = 0;
                        mInterstitial = new InterstitialAd(requireActivity());
                        mInterstitial.setAdUnitId(Constant.SAVE_ADS_FULL_ID);
                        AdRequest adRequest;
                        if (JsonUtils.personalization_ad) {
                            adRequest = new AdRequest.Builder()
                                    .build();
                        } else {
                            Bundle extras = new Bundle();
                            extras.putString("npa", "1");
                            adRequest = new AdRequest.Builder()
                                    .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                                    .build();
                        }
                        mInterstitial.loadAd(adRequest);
                        mInterstitial.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                // TODO Auto-generated method stub
                                super.onAdLoaded();
                                if (mInterstitial.isLoaded()) {
                                    mInterstitial.show();
                                }
                            }

                            public void onAdClosed() {
                                CategoryListFragment categoryListFragment = new CategoryListFragment();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.hide(HomeFragment.this);
                                fragmentTransaction.add(R.id.Container, categoryListFragment, Constant.CATEGORY_TITLEE);
                                fragmentTransaction.addToBackStack(Constant.CATEGORY_TITLEE);
                                fragmentTransaction.commit();
                                ((MainActivity) requireActivity()).setToolbarTitle(Constant.CATEGORY_TITLEE);
                            }

                            @Override
                            public void onAdFailedToLoad(int errorCode) {
                                CategoryListFragment categoryListFragment = new CategoryListFragment();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.hide(HomeFragment.this);
                                fragmentTransaction.add(R.id.Container, categoryListFragment, Constant.CATEGORY_TITLEE);
                                fragmentTransaction.addToBackStack(Constant.CATEGORY_TITLEE);
                                fragmentTransaction.commit();
                                ((MainActivity) requireActivity()).setToolbarTitle(Constant.CATEGORY_TITLEE);
                            }
                        });
                    } else {
                        CategoryListFragment categoryListFragment = new CategoryListFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.hide(HomeFragment.this);
                        fragmentTransaction.add(R.id.Container, categoryListFragment, Constant.CATEGORY_TITLEE);
                        fragmentTransaction.addToBackStack(Constant.CATEGORY_TITLEE);
                        fragmentTransaction.commit();
                        ((MainActivity) requireActivity()).setToolbarTitle(Constant.CATEGORY_TITLEE);
                    }
                } else {
                    CategoryListFragment categoryListFragment = new CategoryListFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.hide(HomeFragment.this);
                    fragmentTransaction.add(R.id.Container, categoryListFragment, Constant.CATEGORY_TITLEE);
                    fragmentTransaction.addToBackStack(Constant.CATEGORY_TITLEE);
                    fragmentTransaction.commit();
                    ((MainActivity) requireActivity()).setToolbarTitle(Constant.CATEGORY_TITLEE);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
}