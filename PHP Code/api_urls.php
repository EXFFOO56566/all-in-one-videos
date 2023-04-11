<?php include("includes/header.php");

$file_path = 'http://'.$_SERVER['SERVER_NAME'] . dirname($_SERVER['REQUEST_URI']).'/';
?>
<div class="row">
      <div class="col-sm-12 col-xs-12">
     	 	<div class="card">
		        <div class="card-header">
		          Example API urls
		        </div>
       			    <div class="card-body no-padding">
         		
         			 <pre><code class="html"><b>Home Banner</b><br><?php echo $file_path."api.php?home_banner"?><b><br><br>Home Videos
</b><?php echo $file_path."api.php?home_videos"?><br><br><b>All videos</b><br><?php echo $file_path."api.php?All_videos"?><br>
<b>Latest Videos</b><br><?php echo $file_path."api.php?latest_video"?><b><br><br>Category List</b><br><?php echo $file_path."api.php?cat_list"?><br> 
<b>Videos list by Cat ID</b><br><?php echo $file_path."api.php?cat_id=2"?><b><br><br>Single Video</b><br><?php echo $file_path."api.php?video_id=8"?><br><b><br>Search Video</b><br><?php echo $file_path."api.php?search_text=the"?><br>
<b>User Register</b><br><?php echo $file_path."api.php?user_register&name=admina&email=admincx@gmail.com&password=admin1233&phone=1256098765"?><br>
<b>User Login</b><br><?php echo $file_path."api.php?users_login&email=adminfg@gmail.com&password=admin1233"?><br><br><b>User Profile
</b><?php echo $file_path."api.php?user_profile&id=2"?><br><br><b>User Profile Update
</b><?php echo $file_path."api.php?user_profile_update&user_id=37&name=John&email=john@gmail.com&password=123456&phone=1234567891"?><br><br><b>User Status</b><br><?php echo $file_path."api.php?user_status&user_id=2"?><br>
<b>Forgot Password</b><br><?php echo $file_path."api.php?forgot_pass&email=john@gmail.com"?><br><br>Rating
</b><?php echo $file_path."api.php?video_rate&device_id=123&post_id=8&user_id=1&rate=5"?><br><br><b>User Comment
</b><?php echo $file_path."api.php?video_comment&comment_text=nice video&user_name=Ashok&post_id=19"?><br><br><b>App Details
</b><?php echo $file_path."api.php?settings"?>
       				</div>
          	</div>
        </div>
</div>
    <br/>
    <div class="clearfix"></div>
        
<?php include("includes/footer.php");?>       
