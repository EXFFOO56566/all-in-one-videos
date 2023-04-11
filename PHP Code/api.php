<?php include("includes/connection.php");
 	  include("includes/function.php"); 	
	
	$file_path = 'http://'.$_SERVER['SERVER_NAME'] . dirname($_SERVER['REQUEST_URI']).'/';
	
 if(isset($_GET['home_banner']))
	{
		
		$jsonObj= array();
		
		 
		$query="SELECT id,banner_name,banner_image,banner_url FROM tbl_home_banner ORDER BY tbl_home_banner.id";
		$sql = mysqli_query($mysqli,$query)or die(mysql_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			 

			$row['id'] = $data['id'];
			$row['banner_name'] = $data['banner_name'];
			$row['banner_image'] = $file_path.'images/'.$data['banner_image'];
			$row['banner_image_thumb'] = $file_path.'images/thumbs/'.$data['banner_image'];
			$row['banner_url'] = $data['banner_url'];
 
			array_push($jsonObj,$row);
		
		}

		$set['ALL_IN_ONE_VIDEO'] = $jsonObj;	
			

		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();

	}
	else if(isset($_GET['home_videos']))
	{
 		
 		$jsonObj_2= array();	

		$query_all="SELECT * FROM tbl_video
		LEFT JOIN tbl_category ON tbl_video.cat_id= tbl_category.cid 
		WHERE tbl_video.status='1' ORDER BY id DESC LIMIT 3";

		$sql_all = mysqli_query($mysqli,$query_all)or die(mysqli_error());

		while($data_all = mysqli_fetch_assoc($sql_all))
		{
			$row2['id'] = $data_all['id'];
			$row2['cat_id'] = $data_all['cat_id'];
			$row2['video_type'] = $data_all['video_type'];
			$row2['video_title'] = $data_all['video_title'];
			$row2['video_url'] = $data_all['video_url'];
			$row2['video_id'] = $data_all['video_id'];
			
			if($data_all['video_type']=='server_url' or $data_all['video_type']=='local')
			{
				$row2['video_thumbnail_b'] = $file_path.'images/'.$data_all['video_thumbnail'];
				$row2['video_thumbnail_s'] = $file_path.'images/thumbs/'.$data_all['video_thumbnail'];
			}
			else
			{
				$row2['video_thumbnail_b'] = $data_all['video_thumbnail'];
				$row2['video_thumbnail_s'] = $data_all['video_thumbnail'];
			}

			$row2['video_duration'] = $data_all['video_duration'];
			$row2['video_description'] = $data_all['video_description'];
			$row2['rate_avg'] = $data_all['rate_avg'];
			$row2['totel_viewer'] = $data_all['totel_viewer'];
 			 

			$row2['cid'] = $data_all['cid'];
			$row2['category_name'] = $data_all['category_name'];
			$row2['category_image'] = $file_path.'images/'.$data_all['category_image'];
			$row2['category_image_thumb'] = $file_path.'images/thumbs/'.$data_all['category_image'];
			
			

			array_push($jsonObj_2,$row2);
		
		}
		$row['featured_video']=$jsonObj_2;
		
		$jsonObj= array();	
 
		$query="SELECT * FROM tbl_video
		LEFT JOIN tbl_category ON tbl_video.cat_id= tbl_category.cid 
		WHERE tbl_video.status='1' ORDER BY tbl_video.id DESC LIMIT 3";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row1['id'] = $data['id'];
			$row1['cat_id'] = $data['cat_id'];
			$row1['video_type'] = $data['video_type'];
			$row1['video_title'] = $data['video_title'];
			$row1['video_url'] = $data['video_url'];
			$row1['video_id'] = $data['video_id'];
			 
			if($data['video_type']=='server_url' or $data['video_type']=='local')
			{
				$row1['video_thumbnail_b'] = $file_path.'images/'.$data['video_thumbnail'];
				$row1['video_thumbnail_s'] = $file_path.'images/thumbs/'.$data['video_thumbnail'];
			}
			else
			{
				$row1['video_thumbnail_b'] = $data['video_thumbnail'];
				$row1['video_thumbnail_s'] = $data['video_thumbnail'];
			}

			$row1['video_duration'] = $data['video_duration'];
			$row1['video_description'] = $data['video_description'];
			$row1['video_description'] = $data['video_description'];
			$row1['rate_avg'] = $data['rate_avg'];
			$row1['totel_viewer'] = $data['totel_viewer'];
 			 

			$row1['cid'] = $data['cid'];
			$row1['category_name'] = $data['category_name'];
			$row1['category_image'] = $file_path.'images/'.$data['category_image'];
			$row1['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 
			 
			array_push($jsonObj,$row1);
		
		}
		
		
		$row['latest_video']=$jsonObj;
 

		$jsonObj_3= array();

		$query_3="SELECT * FROM tbl_video
				LEFT JOIN tbl_category ON tbl_video.cat_id= tbl_category.cid
				WHERE tbl_video.status=1 ORDER BY rand() DESC LIMIT 3";

		$sql_3 = mysqli_query($mysqli,$query_3)or die(mysqli_error());

		while($data_3 = mysqli_fetch_assoc($sql_3))
		{
			$row_3['id'] = $data_3['id'];
			$row_3['cat_id'] = $data_3['cat_id'];
			$row_3['video_type'] = $data_3['video_type'];
			$row_3['video_title'] = $data_3['video_title'];
			$row_3['video_url'] = $data_3['video_url'];
			$row_3['video_id'] = $data_3['video_id'];
			
			if($data_3['video_type']=='server_url' or $data_3['video_type']=='local')
			{
				$row_3['video_thumbnail_b'] = $file_path.'images/'.$data_3['video_thumbnail'];
				$row_3['video_thumbnail_s'] = $file_path.'images/thumbs/'.$data_3['video_thumbnail'];
			}
			else
			{
				$row_3['video_thumbnail_b'] = $data_3['video_thumbnail'];
				$row_3['video_thumbnail_s'] = $data_3['video_thumbnail'];
			}

			$row_3['video_duration'] = $data_3['video_duration'];
			$row_3['video_description'] = $data_3['video_description'];
			$row_3['rate_avg'] = $data_3['rate_avg'];
 			$row_3['totel_viewer'] = $data_3['totel_viewer']; 

			$row_3['cid'] = $data_3['cid'];
			$row_3['category_name'] = $data_3['category_name'];
			$row_3['category_image'] = $file_path.'images/'.$data_3['category_image'];
			$row_3['category_image_thumb'] = $file_path.'images/thumbs/'.$data_3['category_image'];
			
			array_push($jsonObj_3,$row_3);
			
		}

	    $row['all_video']=$jsonObj_3; 


		$jsonObj_4= array();

	     $query_4="SELECT* FROM tbl_category  ORDER BY tbl_category.cid Limit 3";


		$sql_4 = mysqli_query($mysqli,$query_4)or die(mysqli_error());

		while($data_4 = mysqli_fetch_assoc($sql_4))
		{
			$row_4['cid'] = $data_4['cid'];
			$row_4['category_name'] = $data_4['category_name'];
			$row_4['category_image'] = $file_path.'images/'.$data_4['category_image'];
			$row_4['category_image_thumb'] = $file_path.'images/thumbs/'.$data_4['category_image'];
 
			
			array_push($jsonObj_4,$row_4);
			
		}
		$row['category']=$jsonObj_4;

		$set['ALL_IN_ONE_VIDEO'] = $row;	

		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();

	}
 else if(isset($_GET['All_videos']))
 	{
 		$video_order_by=API_ALL_VIDEO_ORDER_BY;

		$jsonObj= array();	
 
		$query="SELECT * FROM tbl_video
		LEFT JOIN tbl_category ON tbl_video.cat_id= tbl_category.cid 
		WHERE tbl_video.status='1' ORDER BY tbl_video.id $video_order_by";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row['id'] = $data['id'];
			$row['cat_id'] = $data['cat_id'];
			$row['video_type'] = $data['video_type'];
			$row['video_title'] = $data['video_title'];
			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];
			
			if($data['video_type']=='server_url' or $data['video_type']=='local')
			{
				$row['video_thumbnail_b'] = $file_path.'images/'.$data['video_thumbnail'];
				$row['video_thumbnail_s'] = $file_path.'images/thumbs/'.$data['video_thumbnail'];
			}
			else
			{
				$row['video_thumbnail_b'] = $data['video_thumbnail'];
				$row['video_thumbnail_s'] = $data['video_thumbnail'];
			}

			$row['video_duration'] = $data['video_duration'];
			$row['video_description'] = $data['video_description'];
			$row['rate_avg'] = $data['rate_avg'];
 			$row['totel_viewer'] = $data['totel_viewer'];  

			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 

			array_push($jsonObj,$row);
		
		}

		$set['ALL_IN_ONE_VIDEO'] = $jsonObj;	
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
 	}
		
	else if(isset($_GET['latest_video']))
	{
		 
		$limit=API_LATEST_LIMIT;

		$jsonObj= array();	
 
		$query="SELECT * FROM tbl_video
		LEFT JOIN tbl_category ON tbl_video.cat_id= tbl_category.cid 
		WHERE tbl_video.status='1' ORDER BY tbl_video.id DESC LIMIT $limit";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row['id'] = $data['id'];
			$row['cat_id'] = $data['cat_id'];
			$row['video_type'] = $data['video_type'];
			$row['video_title'] = $data['video_title'];
			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];

			if($data['video_type']=='server_url' or $data['video_type']=='local')
			{
				$row['video_thumbnail_b'] = $file_path.'images/'.$data['video_thumbnail'];
				$row['video_thumbnail_s'] = $file_path.'images/thumbs/'.$data['video_thumbnail'];
			}
			else
			{
				$row['video_thumbnail_b'] = $data['video_thumbnail'];
				$row['video_thumbnail_s'] = $data['video_thumbnail'];
			}

			$row['video_duration'] = $data['video_duration'];
			$row['video_description'] = $data['video_description'];
			$row['rate_avg'] = $data['rate_avg'];
 			$row['totel_viewer'] = $data['totel_viewer'];   

			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 

			array_push($jsonObj,$row);
		
		}
		
		$set['ALL_IN_ONE_VIDEO'] = $jsonObj;	
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
	
	}
	
	else if(isset($_GET['cat_list']))
 	{
	$jsonObj= array();
		
		$cat_order=API_CAT_ORDER_BY;


		$query="SELECT cid,category_name,category_image FROM tbl_category ORDER BY tbl_category.".$cat_order."";
		$sql = mysqli_query($mysqli,$query)or die(mysql_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			 

			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
 
			array_push($jsonObj,$row);
		
		}
		
		$set['ALL_IN_ONE_VIDEO'] = $jsonObj;	

		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
 }
 else if(isset($_GET['cat_id']))
	{
		 $post_order_by=API_CAT_POST_ORDER_BY;

		$cat_id=$_GET['cat_id'];	

		$jsonObj= array();	
	
	    $query="SELECT * FROM tbl_video
		LEFT JOIN tbl_category ON tbl_video.cat_id= tbl_category.cid 
		where tbl_video.cat_id='".$cat_id."' AND tbl_video.status='1' ORDER BY tbl_video.id ".$post_order_by."";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row['id'] = $data['id'];
			$row['cat_id'] = $data['cat_id'];
			$row['video_type'] = $data['video_type'];
			$row['video_title'] = $data['video_title'];
			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];
			
			if($data['video_type']=='server_url' or $data['video_type']=='local')
			{
				$row['video_thumbnail_b'] = $file_path.'images/'.$data['video_thumbnail'];
				$row['video_thumbnail_s'] = $file_path.'images/thumbs/'.$data['video_thumbnail'];
			}
			else
			{
				$row['video_thumbnail_b'] = $data['video_thumbnail'];
				$row['video_thumbnail_s'] = $data['video_thumbnail'];
			}
 
			$row['video_duration'] = $data['video_duration'];
			$row['video_description'] = $data['video_description'];
			$row['rate_avg'] = $data['rate_avg'];
			$row['totel_viewer'] = $data['totel_viewer'];  

			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 

			array_push($jsonObj,$row);
		
		}
		
		$set['ALL_IN_ONE_VIDEO'] = $jsonObj;	
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
	
	}
	
	
	 else if(isset($_GET['video_id']))
 	{
 		$jsonObj= array();	

		$query="SELECT * FROM tbl_video
		LEFT JOIN tbl_category ON tbl_video.cat_id= tbl_category.cid
		WHERE tbl_video.id='".$_GET['video_id']."'";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			 
			
			$row['cat_id'] = $data['cat_id'];
			$row['category_name'] = $data['category_name'];

			$row['id'] = $data['id'];
			$row['video_type'] = $data['video_type'];
 			$row['video_title'] = $data['video_title'];
			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];
			
			if($data['video_type']=='server_url' or $data['video_type']=='local')
			{
				$row['video_thumbnail_b'] = $file_path.'images/'.$data['video_thumbnail'];
				$row['video_thumbnail_s'] = $file_path.'images/thumbs/'.$data['video_thumbnail'];
			}
			else
			{
				$row['video_thumbnail_b'] = $data['video_thumbnail'];
				$row['video_thumbnail_s'] = $data['video_thumbnail'];
			}
			
			$row['video_duration'] = $data['video_duration'];
			$row['video_description'] = $data['video_description'];
			$row['rate_avg'] = $data['rate_avg'];
			$row['totel_viewer'] = $data['totel_viewer'];  

			//Related Videos
			$query_2="SELECT * FROM tbl_video
			LEFT JOIN tbl_category ON tbl_video.cat_id= tbl_category.cid
			WHERE tbl_video.cat_id='".$data['cat_id']."' AND tbl_video.status='1' AND tbl_video.id!='".$data['id']."'";

			$sql2 = mysqli_query($mysqli,$query_2)or die(mysqli_error());
 			
 			if($sql2->num_rows > 0)
		   {
 			while($data_2 = mysqli_fetch_assoc($sql2))
			{
				$row2['cat_id'] = $data_2['cat_id'];
				$row2['category_name'] = $data_2['category_name'];

				$row2['id'] = $data_2['id'];
				$row2['video_type'] = $data_2['video_type'];
				$row2['video_title'] = $data_2['video_title'];
				$row2['video_url'] = $data_2['video_url'];
				$row2['video_id'] = $data_2['video_id'];
				
				if($data_2['video_type']=='server_url' or $data_2['video_type']=='local')
				{
					$row2['video_thumbnail_b'] = $file_path.'images/'.$data_2['video_thumbnail'];
					$row2['video_thumbnail_s'] = $file_path.'images/thumbs/'.$data_2['video_thumbnail'];
				}
				else
				{
					$row2['video_thumbnail_b'] = $data_2['video_thumbnail'];
					$row2['video_thumbnail_s'] = $data_2['video_thumbnail'];
				}
				
				$row2['video_duration'] = $data_2['video_duration'];
				$row2['video_description'] = $data_2['video_description'];
				$row2['rate_avg'] = $data_2['rate_avg'];
				$row2['totel_viewer'] = $data_2['totel_viewer'];  
				
				$related_data[]=$row2;

			}
			
			$row['related']=$related_data;
			
		   }
		   else
		   {
		       
		       $row['related']=array();
		   }
			
			

			//Comments
		      $qry3="SELECT * FROM tbl_comments where post_id='".$_GET['video_id']."'";
		      $result3=mysqli_query($mysqli,$qry3); 

		      if($result3->num_rows > 0)
		      {
		      		while ($row_comments=mysqli_fetch_array($result3)) {
 		      	
		 		      	$row3['video_id'] = $row_comments['post_id'];
 		 		      	$row3['user_name'] = $row_comments['user_name'];
		 		      	$row3['comment_text'] = $row_comments['comment_text'];

		 		      	$row['user_comments'][]= $row3;
				      }
		     
		      }
		      else
		      {	
		      		 
		      		$row['user_comments'][]= '';
		      }

			array_push($jsonObj,$row);
		
		}
			$view_qry=mysqli_query($mysqli,"UPDATE tbl_video SET totel_viewer= totel_viewer + 1 WHERE id = '".$_GET['video_id']."'");
		
		$set['ALL_IN_ONE_VIDEO'] = $jsonObj;	

		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
	
	}	
	else if(isset($_GET['search_text']))
 	{
		$jsonObj= array();	
 
		$query="SELECT * FROM tbl_video
		LEFT JOIN tbl_category ON tbl_video.cat_id= tbl_category.cid 
		WHERE tbl_video.status='1' AND tbl_video.video_title LIKE '%".$_GET['search_text']."%'  ORDER BY tbl_video.video_title";

		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());

		while($data = mysqli_fetch_assoc($sql))
		{
			$row['id'] = $data['id'];
			$row['cat_id'] = $data['cat_id'];
			$row['video_type'] = $data['video_type'];
			$row['video_title'] = $data['video_title'];
			$row['video_url'] = $data['video_url'];
			$row['video_id'] = $data['video_id'];

			if($data['video_type']=='server_url' or $data['video_type']=='local')
			{
				$row['video_thumbnail_b'] = $file_path.'images/'.$data['video_thumbnail'];
				$row['video_thumbnail_s'] = $file_path.'images/thumbs/'.$data['video_thumbnail'];
			}
			else
			{
				$row['video_thumbnail_b'] = $data['video_thumbnail'];
				$row['video_thumbnail_s'] = $data['video_thumbnail'];
			}

			$row['video_duration'] = $data['video_duration'];
			$row['video_description'] = $data['video_description'];
			$row['rate_avg'] = $data['rate_avg'];
 			$row['totel_viewer'] = $data['totel_viewer'];

			$row['cid'] = $data['cid'];
			$row['category_name'] = $data['category_name'];
			$row['category_image'] = $file_path.'images/'.$data['category_image'];
			$row['category_image_thumb'] = $file_path.'images/thumbs/'.$data['category_image'];
			 

			array_push($jsonObj,$row);
		
		}
		
		$set['ALL_IN_ONE_VIDEO'] = $jsonObj;	

		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
 	}
	
	else if(isset($_GET['user_register']))
	{
		
		$qry = "SELECT * FROM tbl_users WHERE   email = '".$_GET['email']."'"; 
		$result = mysqli_query($mysqli,$qry);
		$row = mysqli_fetch_assoc($result);
		
		if($row['email']!="")
		{
			$set['ALL_IN_ONE_VIDEO'][]=array('msg' => "Email address already used!",'success'=>'0');
		}
		else
		{ 
  
			$qry1="INSERT INTO tbl_users (`user_type`,`name`,`email`,`password`,`phone`,`status`) VALUES ('Normal','".$_GET['name']."','".$_GET['email']."','".$_GET['password']."','".$_GET['phone']."','1')"; 
            
            $result1=mysqli_query($mysqli,$qry1);  									 
					 
				
			$set['ALL_IN_ONE_VIDEO'][]=array('msg' => "Register successflly...!",'success'=>'1');
					
		}
	  
		
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
	
	}
	else if(isset($_GET['users_login']))
	{
		
	    $email = $_GET['email'];
 		$password = $_GET['password'];

	    $qry = "SELECT * FROM tbl_users WHERE  email = '".$email."' and password = '".$password."'";
		$result = mysqli_query($mysqli,$qry);
		$num_rows = mysqli_num_rows($result);
 		$row = mysqli_fetch_assoc($result);
		
    if ($num_rows > 0)
		{ 
					 
			     $set['ALL_IN_ONE_VIDEO'][]=array('user_id' => $row['id'],'name'=>$row['name'],'email'=>$row['email'],'success'=>'1');
 			 
		}		 
		else
		{
				 
 				$set['ALL_IN_ONE_VIDEO'][]=array('msg' =>'Login failed','success'=>'0');
 		}


	
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
	
	}
	
	else if(isset($_GET['user_profile']))
	{
		
		$qry = "SELECT * FROM tbl_users WHERE id = '".$_GET['id']."'"; 
		$result = mysqli_query($mysqli,$qry);
		 
		$row = mysqli_fetch_assoc($result);
	  				 
	    $set['ALL_IN_ONE_VIDEO'][]=array('user_id' => $row['id'],'name'=>$row['name'],'email'=>$row['email'],'phone'=>$row['phone'],'success'=>'1');


	

		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
	
	}
	
	else if(isset($_GET['user_profile_update']))
	{
		if($_GET['password']!="")
		{
			$user_edit= "UPDATE tbl_users SET name='".$_GET['name']."',email='".$_GET['email']."',password='".$_GET['password']."',phone='".$_GET['phone']."' WHERE id = '".$_GET['user_id']."'";	 
		}
		else
		{
			$user_edit= "UPDATE tbl_users SET name='".$_GET['name']."',email='".$_GET['email']."',phone='".$_GET['phone']."' WHERE id = '".$_GET['user_id']."'";	 
		}
   		
   		$user_res = mysqli_query($mysqli,$user_edit);	
	  				 
		$set['ALL_IN_ONE_VIDEO'][]=array('msg'=>'Updated','success'=>'1');

		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
	
	}
	else if(isset($_GET['user_status']))
	{
		$user_id = $_GET['user_id'];
		 
		$qry = "SELECT * FROM tbl_users WHERE status='1' and id = '".$user_id."'"; 
		$result = mysqli_query($mysqli,$qry);
		$num_rows = mysqli_num_rows($result);
		$row = mysqli_fetch_assoc($result);
		
    if ($num_rows > 0)
		{ 
					 
			     $set['ALL_IN_ONE_VIDEO'][]=array('message' => 'Enable','success'=>'1');
			 
		}		 
		else
		{
				 
 				$set['ALL_IN_ONE_VIDEO'][]=array('message' => 'Disable','success'=>'0');
		}


	
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();	
	}	
	
	else if(isset($_GET['forgot_pass']))
	{
		$host = $_SERVER['HTTP_HOST'];
		preg_match("/[^\.\/]+\.[^\.\/]+$/", $host, $matches);
        $domain_name=$matches[0];
         
	 	 
		$qry = "SELECT * FROM tbl_users WHERE email = '".$_GET['email']."'"; 
		$result = mysqli_query($mysqli,$qry);
		$row = mysqli_fetch_assoc($result);
		
		if($row['email']!="")
		{
 
			$to = $_GET['email'];
			// subject
			$subject = '[IMPORTANT] '.APP_NAME.' Forgot Password Information';
 			
			$message='<div style="background-color: #f9f9f9;" align="center"><br />
					  <table style="font-family: OpenSans,sans-serif; color: #666666;" border="0" width="600" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
					    <tbody>
					      <tr>
					        <td colspan="2" bgcolor="#FFFFFF" align="center"><img src="http://'.$_SERVER['SERVER_NAME'] . dirname($_SERVER['REQUEST_URI']).'/images/'.APP_LOGO.'" alt="header" /></td>
					      </tr>
					      <tr>
					        <td width="600" valign="top" bgcolor="#FFFFFF"><br>
					          <table style="font-family:OpenSans,sans-serif; color: #666666; font-size: 10px; padding: 15px;" border="0" width="100%" cellspacing="0" cellpadding="0" align="left">
					            <tbody>
					              <tr>
					                <td valign="top"><table border="0" align="left" cellpadding="0" cellspacing="0" style="font-family:OpenSans,sans-serif; color: #666666; font-size: 10px; width:100%;">
					                    <tbody>
					                      <tr>
					                        <td><p style="color: #262626; font-size: 28px; margin-top:0px;"><strong>Dear '.$row['name'].'</strong></p>
					                          <p style="color:#262626; font-size:20px; line-height:32px;font-weight:500;">Thank you for using '.APP_NAME.',<br>
					                            Your password is: '.$row['password'].'</p>
					                          <p style="color:#262626; font-size:20px; line-height:32px;font-weight:500;margin-bottom:30px;">Thanks you,<br />
					                            '.APP_NAME.'.</p></td>
					                      </tr>
					                    </tbody>
					                  </table></td>
					              </tr>
					               
					            </tbody>
					          </table></td>
					      </tr>
					      <tr>
					        <td style="color: #262626; padding: 20px 0; font-size: 20px; border-top:5px solid #52bfd3;" colspan="2" align="center" bgcolor="#ffffff">Copyright © '.APP_NAME.'.</td>
					      </tr>
					    </tbody>
					  </table>
					</div>';
 

			$headers = 'MIME-Version: 1.0' . "\r\n";
			$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
			$headers .= 'From: '.APP_NAME.' <'.APP_FROM_EMAIL.'>' . "\r\n";
			// Mail it
			@mail($to, $subject, $message, $headers);
 
			  
			$set['ALL_IN_ONE_VIDEO'][]=array('msg' => "Password has been sent on your mail!",'success'=>'1');
		}
		else
		{  	 
				
			$set['ALL_IN_ONE_VIDEO'][]=array('msg' => "Email not found in our database!",'success'=>'0');
					
		}

	
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();	
	}	
	else if(isset($_GET['video_rate']))
	{
		//search if the user(ip) has already gave a note
	      $ip = $_GET['device_id'];
	      $post_id = $_GET['post_id'];
	      $user_id = $_GET['user_id'];
	      $therate = $_GET['rate'];

	  
	      $query1 = mysqli_query($mysqli,"select * from tbl_rating where post_id  = '$post_id' and user_id = '$user_id'"); 
	      while($data1 = mysqli_fetch_assoc($query1)){
	        $rate_db1[] = $data1;
	      }
	      if(@count($rate_db1) == 0 ){
	      	 
	          
	  		$qry1="INSERT INTO tbl_rating (`post_id`,`user_id`,`rate`,`ip`) VALUES ('".$post_id."','".$user_id."','".$therate."','".$ip."')"; 
            $result1=mysqli_query($mysqli,$qry1);  
	      
	          //Total rate result
	           
	        $query = mysqli_query($mysqli,"select * from tbl_rating where post_id  = '$post_id' ");
	               
	         while($data = mysqli_fetch_assoc($query)){
	                    $rate_db[] = $data;
	                    $sum_rates[] = $data['rate'];
	               
	                }
	        
	                if(@count($rate_db)){
	                    $rate_times = count($rate_db);
	                    $sum_rates = array_sum($sum_rates);
	                    $rate_value = $sum_rates/$rate_times;
	                    $rate_bg = (($rate_value)/5)*100;
	                }else{
	                    $rate_times = 0;
	                    $rate_value = 0;
	                    $rate_bg = 0;
	                }
	         
	        $rate_avg=round($rate_value); 
	        
	      $sql="update tbl_video set total_rate=total_rate + 1,rate_avg='$rate_avg' where id='".$post_id."'";
	      mysqli_query($mysqli,$sql);
	        
	      $total_rat_sql="SELECT * FROM tbl_video WHERE id='".$post_id."'";
	      $total_rat_res=mysqli_query($mysqli,$total_rat_sql);
	      $total_rat_row=mysqli_fetch_assoc($total_rat_res);
	    
	         
	        $set['ALL_IN_ONE_VIDEO'][]=array('total_rate' =>$total_rat_row['total_rate'],'rate_avg' =>$total_rat_row['rate_avg'],'msg' => "You have succesfully rated",'success'=>'1');
	        
	      }else{
	                
	 
	        $set['ALL_IN_ONE_VIDEO'][]=array('msg' => "You have already rated",'success'=>'0');
	      }
	    
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
	
	}	
	else if(isset($_GET['video_comment']))
	{
 		$post_id = $_GET['post_id'];
	      $user_name = $_GET['user_name'];
	      $comment_text = $_GET['comment_text'];

			$qry1="INSERT INTO tbl_comments (`post_id`,`user_name`,`comment_text`) VALUES ('".$post_id."','".$user_name."','".$comment_text."')"; 
            $result1=mysqli_query($mysqli,$qry1);	


            $set['ALL_IN_ONE_VIDEO'][]=array('msg' => "Comment post successfully...",'success'=>'1');
	       
	    
		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();
	
	}	
	
	
	else if(isset($_GET['settings']))
	{
		  
		 $jsonObj= array();	

		$query="SELECT * FROM tbl_settings WHERE id='1'";
		$sql = mysqli_query($mysqli,$query)or die(mysqli_error());


		while($data = mysqli_fetch_assoc($sql))
		{
			 
			$row['app_name'] = $data['app_name'];
			$row['app_logo'] = $data['app_logo'];
			$row['app_version'] = $data['app_version'];
			$row['app_author'] = $data['app_author'];
			$row['app_contact'] = $data['app_contact'];
			$row['app_email'] = $data['app_email'];
			$row['app_website'] = $data['app_website'];
			$row['app_description'] = $data['app_description'];
			$row['app_developed_by'] = $data['app_developed_by'];

			$row['app_privacy_policy'] = stripslashes($data['app_privacy_policy']);
 			
 			$row['publisher_id'] = $data['publisher_id'];
 			$row['interstital_ad'] = $data['interstital_ad'];
			$row['interstital_ad_id'] = $data['interstital_ad_id'];
 			$row['banner_ad'] = $data['banner_ad'];
 			$row['banner_ad_id'] = $data['banner_ad_id'];
 			$row['interstital_ad_click'] = $data['interstital_ad_click'];

			array_push($jsonObj,$row);
		
		}
 
		$set['ALL_IN_ONE_VIDEO'] = $jsonObj;
		
	

		header( 'Content-Type: application/json; charset=utf-8' );
	    echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
		die();	
	}		
	 
	 
?>