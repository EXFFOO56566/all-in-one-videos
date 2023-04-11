-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 05, 2018 at 11:28 AM
-- Server version: 5.5.61-38.13-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `viavio7b_all_in_one_videos_app_demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_admin`
--

INSERT INTO `tbl_admin` (`id`, `username`, `password`, `email`, `image`) VALUES
(1, 'admin', 'admin', 'viaviwebtech@gmail.com', 'profile.png');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_category`
--

CREATE TABLE `tbl_category` (
  `cid` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `category_image` varchar(255) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_category`
--

INSERT INTO `tbl_category` (`cid`, `category_name`, `category_image`, `status`) VALUES
(1, 'Animation', '63095_Animation.jpg', 1),
(2, 'Comedy', '91287_Comedy1.jpg', 1),
(3, 'Fashion', '11419_fashion-images.jpg', 1),
(4, 'Music', '10543_music.jpg', 1),
(5, 'Sports', '26013_Sports.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_comments`
--

CREATE TABLE `tbl_comments` (
  `id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `comment_text` text NOT NULL,
  `dt_rate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_home_banner`
--

CREATE TABLE `tbl_home_banner` (
  `id` int(11) NOT NULL,
  `banner_name` varchar(255) NOT NULL,
  `banner_image` varchar(255) NOT NULL,
  `banner_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_home_banner`
--

INSERT INTO `tbl_home_banner` (`id`, `banner_name`, `banner_image`, `banner_url`) VALUES
(1, 'Android Drawing', '97286_android_drawing.jpg', 'https://codecanyon.net/item/android-drawing/8193028'),
(2, 'Daily Motion', '9680_Daily_Motion.jpg', 'https://codecanyon.net/item/daily-motion/8239582'),
(3, 'Alphabet', '40905_alphabet.jpg', 'https://codecanyon.net/item/alphabet/8108766');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rating`
--

CREATE TABLE `tbl_rating` (
  `id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `rate` int(11) NOT NULL,
  `dt_rate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_settings`
--

CREATE TABLE `tbl_settings` (
  `id` int(11) NOT NULL,
  `email_from` varchar(255) NOT NULL,
  `onesignal_app_id` text NOT NULL,
  `onesignal_rest_key` text NOT NULL,
  `app_name` varchar(255) NOT NULL,
  `app_logo` varchar(255) NOT NULL,
  `app_email` varchar(255) NOT NULL,
  `app_version` varchar(255) NOT NULL,
  `app_author` varchar(255) NOT NULL,
  `app_contact` varchar(255) NOT NULL,
  `app_website` varchar(255) NOT NULL,
  `app_description` text NOT NULL,
  `app_developed_by` varchar(255) NOT NULL,
  `app_privacy_policy` text NOT NULL,
  `api_all_order_by` varchar(255) NOT NULL,
  `api_latest_limit` int(3) NOT NULL,
  `api_cat_order_by` varchar(255) NOT NULL,
  `api_cat_post_order_by` varchar(255) NOT NULL,
  `publisher_id` text NOT NULL,
  `interstital_ad` text NOT NULL,
  `interstital_ad_id` text NOT NULL,
  `interstital_ad_click` varchar(255) NOT NULL,
  `banner_ad` text NOT NULL,
  `banner_ad_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_settings`
--

INSERT INTO `tbl_settings` (`id`, `email_from`, `onesignal_app_id`, `onesignal_rest_key`, `app_name`, `app_logo`, `app_email`, `app_version`, `app_author`, `app_contact`, `app_website`, `app_description`, `app_developed_by`, `app_privacy_policy`, `api_all_order_by`, `api_latest_limit`, `api_cat_order_by`, `api_cat_post_order_by`, `publisher_id`, `interstital_ad`, `interstital_ad_id`, `interstital_ad_click`, `banner_ad`, `banner_ad_id`) VALUES
(1, 'info@viaviweb.in', '52b8c16f-3c86-4f44-907d-48756981c113', 'ZTIxOWE3YWItZDBhMC00OGNiLWJjYzUtNTDmPsNiM2QxMzNh', 'All In One Videos App', 'icon256.png', 'info@viaviweb.in', '1.0.0', 'viaviwebtech', '+91 922 7777 522', 'http://www.viaviweb.com/', '<p>This Application is best application for Video, User can play their favourite videos through applications.</p>\r\n\r\n<ul>\r\n	<li>Easy to play video</li>\r\n	<li>Great UI</li>\r\n	<li>You can set video to favourite list</li>\r\n	<li>Userfriendly</li>\r\n</ul>\r\n\r\n<p>AllInOneVideo Application is designed and developed by Viavi Webtech (INDIA), for more Applications contact viaviwebtech@gmail.com</p>\r\n\r\n<p>Website: www.viaviweb.com</p>\r\n\r\n<p>We also develop custom applications, if you need any kind of custom application contact us on given Email or Contact No.</p>\r\n\r\n<p><strong>Email:</strong> viaviwebtech@gmail.com<br />\r\n<strong>WhatsApp:</strong> +919227777522<br />\r\n<strong>Website:</strong> www.viaviweb.com</p>\r\n', 'Viavi Webtech', '<p><strong>We are committed to protecting your privacy</strong></p>\n\n<p>We collect the minimum amount of information about you that is commensurate with providing you with a satisfactory service. This policy indicates the type of processes that may result in data being collected about you. Your use of this website gives us the right to collect that information.&nbsp;</p>\n\n<p><strong>Information Collected</strong></p>\n\n<p>We may collect any or all of the information that you give us depending on the type of transaction you enter into, including your name, address, telephone number, and email address, together with data about your use of the website. Other information that may be needed from time to time to process a request may also be collected as indicated on the website.</p>\n\n<p><strong>Information Use</strong></p>\n\n<p>We use the information collected primarily to process the task for which you visited the website. Data collected in the UK is held in accordance with the Data Protection Act. All reasonable precautions are taken to prevent unauthorised access to this information. This safeguard may require you to provide additional forms of identity should you wish to obtain information about your account details.</p>\n\n<p><strong>Cookies</strong></p>\n\n<p>Your Internet browser has the in-built facility for storing small files - &quot;cookies&quot; - that hold information which allows a website to recognise your account. Our website takes advantage of this facility to enhance your experience. You have the ability to prevent your computer from accepting cookies but, if you do, certain functionality on the website may be impaired.</p>\n\n<p><strong>Disclosing Information</strong></p>\n\n<p>We do not disclose any personal information obtained about you from this website to third parties unless you permit us to do so by ticking the relevant boxes in registration or competition forms. We may also use the information to keep in contact with you and inform you of developments associated with us. You will be given the opportunity to remove yourself from any mailing list or similar device. If at any time in the future we should wish to disclose information collected on this website to any third party, it would only be with your knowledge and consent.&nbsp;</p>\n\n<p>We may from time to time provide information of a general nature to third parties - for example, the number of individuals visiting our website or completing a registration form, but we will not use any information that could identify those individuals.&nbsp;</p>\n\n<p>In addition Dummy may work with third parties for the purpose of delivering targeted behavioural advertising to the Dummy website. Through the use of cookies, anonymous information about your use of our websites and other websites will be used to provide more relevant adverts about goods and services of interest to you. For more information on online behavioural advertising and about how to turn this feature off, please visit youronlinechoices.com/opt-out.</p>\n\n<p><strong>Changes to this Policy</strong></p>\n\n<p>Any changes to our Privacy Policy will be placed here and will supersede this version of our policy. We will take reasonable steps to draw your attention to any changes in our policy. However, to be on the safe side, we suggest that you read this document each time you use the website to ensure that it still meets with your approval.</p>\n\n<p><strong>Contacting Us</strong></p>\n\n<p>If you have any questions about our Privacy Policy, or if you want to know what information we have collected about you, please email us at hd@dummy.com. You can also correct any factual errors in that information or require us to remove your details form any list under our control.</p>\n', 'ASC', 15, 'category_name', 'DESC', 'pub-9456493320432553', 'true', 'ca-app-pub-3940256099942544/1033173712', '5', 'true', 'ca-app-pub-3940256099942544/6300978111');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL,
  `user_type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `confirm_code` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`id`, `user_type`, `name`, `email`, `password`, `phone`, `confirm_code`, `status`) VALUES
(198, 'Normal', 'test app new', 'testapp@gmail.com', 'testapp', '1234567890', NULL, '1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_video`
--

CREATE TABLE `tbl_video` (
  `id` int(11) NOT NULL,
  `cat_id` int(11) NOT NULL,
  `video_type` varchar(255) NOT NULL,
  `video_title` varchar(255) NOT NULL,
  `video_url` text NOT NULL,
  `video_id` varchar(255) NOT NULL,
  `video_thumbnail` text NOT NULL,
  `video_duration` varchar(255) NOT NULL,
  `video_description` text NOT NULL,
  `total_rate` int(11) NOT NULL DEFAULT '0',
  `rate_avg` float(11,1) NOT NULL DEFAULT '0.0',
  `totel_viewer` int(11) NOT NULL DEFAULT '0',
  `featured` int(1) NOT NULL DEFAULT '0',
  `status` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_video`
--

INSERT INTO `tbl_video` (`id`, `cat_id`, `video_type`, `video_title`, `video_url`, `video_id`, `video_thumbnail`, `video_duration`, `video_description`, `total_rate`, `rate_avg`, `totel_viewer`, `featured`, `status`) VALUES
(1, 2, 'youtube', 'Chup chup ke movie comedy scenes | Rajpal yadav chup chupke', 'https://www.youtube.com/watch?v=xSYjspui1Ps', 'xSYjspui1Ps', '', '07:19', '<p>Chup chup ke movie comedy scenes | Rajpal yadav chup chupkeChup chup ke movie comedy scenes | Rajpal yadav chup chupkeChup chup ke movie comedy scenes | Rajpal yadav chup chupkeChup chup ke movie comedy scenes | Rajpal yadav chup chupke</p>\r\n', 7, 4.0, 24, 0, 1),
(3, 4, 'dailymotion', 'Zaalima RAEES | HD Video Song', 'http://www.dailymotion.com/video/x57gffr_zaalima-raees-hd-video-song-shahrukh-khan-mahira-khan-arijit-singh-latest-bollywood-songs-2017_music', 'x57gffr', '', '02:52', '<p>Zaalima RAEES | HD Video Song | ShahRukh Khan-Mahira Khan | Arijit Singh | Latest Bollywood Songs 2017</p>\r\n', 2, 3.0, 16, 0, 1),
(10, 1, 'local', 'Mere Rashke Qamar', 'http://www.viaviweb.in/envato/cc/all_in_one_videos_app_demo_new/uploads/48335_Radha.mp4', '', '23977_mererashkekamar.jpg', '2:01', '<p>&quot;Mere Rashke Qamar&quot; Video &quot;Song&quot; with Lyrics | Baadshaho | Nusrat Fateh Ali Khan, Rahat Fateh Ali Khan | New Song 2017 Presenting the first song &quot;Mere Rashke Qamar&quot; with Lyrics from the upcoming movie Baadshaho, Gulshan Kumar presents a T-Series production in association with Vertex Motion Pictures Pvt Ltd. Produced by Bhushan Kumar Krishan Kumar and Milan LuthriaIndian action thriller film written by Rajat Arora, directed by Milan Luthria. It features Ajay Devgn, Emraan Hashmi, Esha Gupta, Ileana D&#39;Cruz Vidyut Jammwal and Sanjay Mishra in the lead roles</p>\r\n', 4, 4.0, 40, 0, 1),
(11, 1, 'vimeo', 'How To Be Confidentt', 'https://vimeo.com/46835688', '46835688', 'http://i.vimeocdn.com/video/326290204_640.jpg', '02:03', '<p>Within this School of Life animation, philosopher and author Alain de Botton imparts some handy wisdom that may help you to become more confident.</p>\r\n', 4, 4.0, 16, 0, 1),
(12, 2, 'youtube', 'FAST AND FURIOUS 8', 'https://www.youtube.com/watch?v=9PcmKECf82g', '9PcmKECf82g', '', '7:12', '<p>Fast and Furious 8 / The Fate of the Furious Trailer 2017 | Watch the official trailer &amp; clip compilation for &quot;The Fate of the Furious&quot;, an action movie starring Dwayne Johnson, Scott Eastwood &amp; Jason Statham, arriving April 14, 2017 !</p>\r\n', 7, 4.0, 32, 0, 1),
(13, 4, 'youtube', 'Half Girlfriend Official Trailer', 'https://www.youtube.com/watch?v=KmlBnmyelHI', 'KmlBnmyelHI', '', '02:42', '<p>Balaji Motion Pictures presents &lsquo;Half Girlfriend &ndash; Dost se Zyada, Girlfriend se kam&rsquo;, an adaptation of Chetan Bhagat&#39;s best selling novel &lsquo;Half Girlfriend&rsquo;. Directed by Mohit Suri, the intense love story sets out to explore the &lsquo;grey area&rsquo; in relationships today; the in-between space, between two people.<br />\r\n<br />\r\nStarring Arjun Kapoor, as Madhav Jha &amp; Shraddha Kapoor as Riya Somani, the film is set against the backdrop of three distinct worlds of Delhi, Patna &amp; New York.<br />\r\nMadhav wanted a relationship. Riya didn&#39;t. So, she decided to be his &lsquo;Half Girlfriend&rsquo;.<br />\r\nMore than a friend, les than a girlfriend,</p>\r\n', 2, 5.0, 11, 0, 1),
(14, 5, 'vimeo', 'BOTHER', 'https://vimeo.com/99751349', '99751349', 'http://i.vimeocdn.com/video/483330670_640.jpg', '02:42', '<p>Video by Harry Israelson &amp; Harry Schleiff</p>\r\n\r\n<p>Music by Chaz Bundick<br />\r\nColor by Mikey Rossiter @ The Mill</p>\r\n\r\n<p>Asst. Producer: Sidney Schleiff<br />\r\nAsst. Producer John Belanger</p>\r\n\r\n<p>Special Thanks:<br />\r\nCooper Rogers<br />\r\nHeath Raymond</p>\r\n', 5, 4.0, 10, 0, 1),
(15, 4, 'youtube', 'The Flash - First Look', 'https://www.youtube.com/watch?v=mVDGoP4_VYE', 'mVDGoP4_VYE', '', '01:00', '<p>The Fastest Man Alive is coming to TV this fall, Tuesdays on The CW!<br />\r\n<br />\r\nFor more on The Flash:</p>\r\n', 2, 4.0, 16, 0, 1),
(16, 5, 'youtube', 'Top 5 Luxury Cars 2017', 'https://www.youtube.com/watch?v=yH46mtKn32M', 'yH46mtKn32M', '', '12:25', '<p>Top 5 Luxury Cars 2017</p>\r\n', 8, 4.0, 9, 0, 1),
(17, 5, 'vimeo', 'Dean Tennant', 'https://vimeo.com/212125324', '212125324', 'http://i.vimeocdn.com/video/628040142_640.jpg', '02:03', '<p>Dean riding on Vancouver Island.</p>\r\n', 5, 4.0, 8, 0, 1),
(20, 5, 'youtube', 'IPL 2018 : CSK crush hyderabad and qualifier for Final', 'https://www.youtube.com/watch?v=VZJXHfM7XY4', 'VZJXHfM7XY4', '', '6:23', '<p>That background music is so annoying yaar, please stop the background music was only for the world cup which was amazing but now its it has become annoying and you bloody news people if you people don&#39;t have any news we just start showing cricket Dhoom Dhadaka and all﻿</p>\r\n', 18, 4.0, 14, 0, 1),
(21, 5, 'youtube', 'Royal Challengers Bangalore vs Sunrisers ', 'https://www.youtube.com/watch?v=QzRYysYPNv8', 'QzRYysYPNv8', '', '13:28', '<p>Watch Sunrisers Hyderabad win their maiden IPL trophy thanks to an all-round show to beat Royal Challengers Bangalore.</p>\r\n', 21, 4.0, 36, 1, 1),
(22, 2, 'server_url', 'Android Kitkat', 'http://www.viaviweb.in/envato/cc/all_in_one_videos_demo/uploads/70010_kitkat.mp4', '', '26096_android_kitkat.jpg', '02:30', '<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>\r\n', 0, 0.0, 89, 0, 1),
(23, 3, 'youtube', 'X-men: Dark Phoenix', 'https://www.youtube.com/watch?v=BG9p34PlHfE', 'BG9p34PlHfE', '', '02:30', '<p>In DARK PHOENIX, the X-MEN face their most formidable and powerful foe: one of their own, Jean Grey. During a rescue mission in space, Jean is nearly killed when she is hit by a mysterious cosmic force. Once she returns home, this force not only makes her infinitely more powerful, but far more unstable. Wrestling with this entity inside her, Jean unleashes her powers in ways she can neither comprehend nor contain.&nbsp;</p>\r\n\r\n<p>Coming soon to a theater near you.</p>\r\n', 2, 5.0, 70, 0, 1),
(24, 1, 'dailymotion', 'Urvashi Offical Music Video', 'https://www.dailymotion.com/video/x6udgzx', 'x6udgzx', '', '4:25', '<p>Gulshan Kumar presents Bhushan Kumar&#39;s proudly bring to you URVASHI official music video starring stunning Shahid Kapoor and the very beautiful Kiara Advani, This new Hindi song in the voice of sensational &quot;Yo Yo Honey Singh &quot;, The song is also composed by Yo Yo Honey Singh, penned by Yo Yo Honey Singh &amp; Singhsta.</p>\r\n\r\n<p>The video is directed by DirectorGifty. Hit &#39;LIKE&#39; if you &hearts; this song</p>\r\n', 2, 1.0, 23, 0, 1),
(26, 3, 'youtube', 'Manikarnika Official Teaser', 'https://www.youtube.com/watch?v=eBw8SPPvGXQ', 'eBw8SPPvGXQ', '', '02:30', '<p>Manikarnika releasing on 25th January, 2019.</p>\r\n\r\n<p>Directed by Radha Krishna Jagarlamud.</p>\r\n\r\n<p>Starring KanganaRanaut, Jishu Sengupta, Suresh Oberoi, Danny Denzongappa, Atul Kulkarni, Ankita Lokhande, Misti, Unnati Davera, Zeeshan Ayub, Rajeev kacharo, Nihar Pandya, Kulbhushan Kharbanda, Manish Wadhwa</p>\r\n', 0, 0.0, 4, 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_category`
--
ALTER TABLE `tbl_category`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `tbl_comments`
--
ALTER TABLE `tbl_comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_home_banner`
--
ALTER TABLE `tbl_home_banner`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_rating`
--
ALTER TABLE `tbl_rating`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_settings`
--
ALTER TABLE `tbl_settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_video`
--
ALTER TABLE `tbl_video`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_category`
--
ALTER TABLE `tbl_category`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_comments`
--
ALTER TABLE `tbl_comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_home_banner`
--
ALTER TABLE `tbl_home_banner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_rating`
--
ALTER TABLE `tbl_rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_settings`
--
ALTER TABLE `tbl_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=212;

--
-- AUTO_INCREMENT for table `tbl_video`
--
ALTER TABLE `tbl_video`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
