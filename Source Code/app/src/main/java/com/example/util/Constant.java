package com.example.util;


import com.viaviapp.allinonevideo.BuildConfig;

import java.io.Serializable;
import java.util.ArrayList;

public class Constant implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//server url
	public static final String SERVER_URL= BuildConfig.server_url;

	public static final String IMAGE_PATH_URL =SERVER_URL+"images/";

	public static final String CATEGORY_URL = SERVER_URL+"api.php?cat_list";

	public static final String CATEGORY_ITEM_URL = SERVER_URL+"api.php?cat_id=";

	public static final String LATEST_URL = SERVER_URL+"api.php?latest_video";

	public static final String HOME_URL = SERVER_URL+"api.php?home_videos";

	public static final String SLIDER_URL = SERVER_URL+"api.php?home_banner";

	public static final String ALL_URL = SERVER_URL+"api.php?All_videos";

    public static final String SINGLE_VIDEO_URL = SERVER_URL+"api.php?video_id=";

	public static final String SEARCH_URL = SERVER_URL+"api.php?search_text=";

	public static final String ABOUT_US_URL = SERVER_URL+"api.php?settings";

	public static final String LOGIN_URL = SERVER_URL+"api.php?users_login&email=";

	public static final String LOGIN_STATUS_URL = SERVER_URL+"api.php?user_status&user_id=";

	public static final String REGISTER_URL = SERVER_URL+"api.php?user_register&name=";

	public static final String PROFILE_URL = SERVER_URL+"api.php?user_profile&id=";

	public static final String UPDATE_PROFILE_URL = SERVER_URL+"api.php?user_profile_update&user_id=";

	public static final String COMMENT_URL = SERVER_URL+"api.php?video_comment&comment_text=";

	public static final String FORGOT_URL = SERVER_URL+"api.php?forgot_pass&email=";

	public static final String YOUTUBE_IMAGE_FRONT="http://img.youtube.com/vi/";
	public static final String YOUTUBE_SMALL_IMAGE_BACK="/hqdefault.jpg";

	public static final String DAILYMOTION_IMAGE_PATH="http://www.dailymotion.com/thumbnail/video/";

	public static final String LATEST_ARRAY_NAME="ALL_IN_ONE_VIDEO";
	public static final String RELATED_ARRAY="related";
	public static final String COMMENT_ARRAY="user_comments";

	public static final String HOME_BANNER_ID="id";
	public static final String HOME_BANNER_NAME="banner_name";
	public static final String HOME_BANNER_IMAGE="banner_image";
	public static final String HOME_BANNER_LINK="banner_url";

	public static final String LATEST_ID="id";
	public static final String LATEST_CATID="cat_id";
	public static final String LATEST_CAT_NAME="category_name";
	public static final String LATEST_VIDEO_URL="video_url";
	public static final String LATEST_VIDEO_ID="video_id";
	public static final String LATEST_VIDEO_DURATION="video_duration";
	public static final String LATEST_VIDEO_NAME="video_title";
	public static final String LATEST_VIDEO_DESCRIPTION="video_description";
	public static final String LATEST_IMAGE_URL="video_thumbnail_b";
 	public static final String LATEST_TYPE="video_type";
	public static final String LATEST_VIEW="totel_viewer";
	public static final String LATEST_RATE="rate_avg";

 	public static final String CATEGORY_NAME="category_name";
	public static final String CATEGORY_CID="cid";
	public static final String CATEGORY_IMAGE="category_image";

	public static final String COMMENT_ID="video_id";
	public static final String COMMENT_NAME="user_name";
	public static final String COMMENT_MSG="comment_text";

	//for title display in CategoryItemF
	public static String CATEGORY_TITLEE;
	public static String CATEGORY_IDD;
	public static  String LATEST_IDD;
	public static  String LATEST_CMT_IDD;
	public static  int POS_ID;

	public static int GET_SUCCESS_MSG;
	public static final String MSG = "msg";
	public static final String SUCCESS = "success";
	public static final String USER_NAME = "name";
	public static final String USER_ID = "user_id";
	public static final String USER_EMAIL = "email";
	public static final String USER_PHONE = "phone";


  	public static final String APP_NAME="app_name";
	public static final String APP_IMAGE="app_logo";
	public static final String APP_VERSION="app_version";
	public static final String APP_AUTHOR="app_author";
	public static final String APP_CONTACT="app_contact";
	public static final String APP_EMAIL="app_email";
	public static final String APP_WEBSITE="app_website";
	public static final String APP_DESC="app_description";
	public static final String APP_PRIVACY="app_privacy_policy";
	public static final String APP_DEVELOP="app_developed_by";

	//public static ArrayList<ItemMostView> mList = new ArrayList<>();

	public static final String ADS_BANNER_ID="banner_ad_id";
	public static final String ADS_FULL_ID="interstital_ad_id";
	public static final String ADS_BANNER_ON_OFF="banner_ad";
	public static final String ADS_FULL_ON_OFF="interstital_ad";
	public static final String ADS_PUB_ID="publisher_id";
	public static final String ADS_CLICK="interstital_ad_click";
	public static String SAVE_ADS_BANNER_ID,SAVE_ADS_FULL_ID,SAVE_ADS_BANNER_ON_OFF,SAVE_ADS_FULL_ON_OFF,SAVE_ADS_PUB_ID,SAVE_ADS_CLICK;

}
