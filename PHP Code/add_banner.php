<?php include("includes/header.php");

	require("includes/function.php");
	require("language/language.php");

	require_once("thumbnail_images.class.php");

	 
	
	if(isset($_POST['submit']) and isset($_GET['add']))
	{
	
	   $banner_image=rand(0,99999)."_".$_FILES['banner_image']['name'];
		 	 
       //Main Image
	   $tpath1='images/'.$banner_image; 			 
       $pic1=compress_image($_FILES["banner_image"]["tmp_name"], $tpath1, 80);
	 
		//Thumb Image 
	   $thumbpath='images/thumbs/'.$banner_image;		
       $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'200','200');   
 
          
       $data = array( 
			    'banner_name'  =>  $_POST['banner_name'],
			    'banner_image'  =>  $banner_image,
			    'banner_url'  =>  $_POST['banner_url']
			    );		

 		$qry = Insert('tbl_home_banner',$data);	

 	   
		$_SESSION['msg']="10"; 
		header( "Location:manage_home_banner.php");
		exit;	

		 
		
	}
	
	if(isset($_GET['banner_id']))
	{
			 
			$qry="SELECT * FROM tbl_home_banner where id='".$_GET['banner_id']."'";
			$result=mysqli_query($mysqli,$qry);
			$row=mysqli_fetch_assoc($result);

	}
	if(isset($_POST['submit']) and isset($_POST['banner_id']))
	{
		 
		 if($_FILES['banner_image']['name']!="")
		 {		


				$img_res=mysqli_query($mysqli,'SELECT * FROM tbl_home_banner WHERE cid='.$_GET['banner_id'].'');
			    $img_res_row=mysqli_fetch_assoc($img_res);
			

			    if($img_res_row['banner_image']!="")
		        {
					unlink('images/thumbs/'.$img_res_row['banner_image']);
					unlink('images/'.$img_res_row['banner_image']);
			     }

 				   $banner_image=rand(0,99999)."_".$_FILES['banner_image']['name'];
		 	 
			       //Main Image
				   $tpath1='images/'.$banner_image; 			 
			       $pic1=compress_image($_FILES["banner_image"]["tmp_name"], $tpath1, 80);
				 
					//Thumb Image 
				   $thumbpath='images/thumbs/'.$banner_image;		
			       $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'200','200');

                    $data = array(
					    'banner_name'  =>  $_POST['banner_name'],
					    'banner_image'  =>  $banner_image,
					    'banner_url'  =>  $_POST['banner_url']
						);

					$category_edit=Update('tbl_home_banner', $data, "WHERE id = '".$_POST['banner_id']."'");

		 }
		 else
		 {

					 $data = array(
			          'banner_name'  =>  $_POST['banner_name'],
			          'banner_url'  =>  $_POST['banner_url']
						);	
 
			         $category_edit=Update('tbl_home_banner', $data, "WHERE id = '".$_POST['banner_id']."'");

		 }

		     
		$_SESSION['msg']="11"; 
		header( "Location:add_banner.php?banner_id=".$_POST['banner_id']);
		exit;
 
	}


?>
<div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title"><?php if(isset($_GET['banner_id'])){?>Edit<?php }else{?>Add<?php }?> Banner</div>
            </div>
          </div>
          <div class="clearfix"></div>
          <div class="row mrg-top">
            <div class="col-md-12">
               
              <div class="col-md-12 col-sm-12">
                <?php if(isset($_SESSION['msg'])){?> 
               	 <div class="alert alert-success alert-dismissible" role="alert"> <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                	<?php echo $client_lang[$_SESSION['msg']] ; ?></a> </div>
                <?php unset($_SESSION['msg']);}?>	
              </div>
            </div>
          </div>
          <div class="card-body mrg_bottom"> 
            <form action="" name="addeditcategory" method="post" class="form form-horizontal" enctype="multipart/form-data">
            	<input  type="hidden" name="banner_id" value="<?php echo $_GET['banner_id'];?>" />

              <div class="section">
                <div class="section-body">
                  <div class="form-group">
                    <label class="col-md-3 control-label">Banner Title :-</label>
                    <div class="col-md-6">
                      <input type="text" name="banner_name" id="banner_name" value="<?php if(isset($_GET['banner_id'])){echo $row['banner_name'];}?>" class="form-control" required>
                    </div>
                  </div>                   
                  <div class="form-group">
                    <label class="col-md-3 control-label">Bannel Image :-</label>
                    <div class="col-md-6">
                      <div class="fileupload_block">
                        <input type="file" name="banner_image" value="fileupload" id="fileupload"  <?php if(!isset($_GET['banner_id'])) {?>required<?php }?>>
                            <?php if(isset($_GET['banner_id']) and $row['banner_image']!="") {?>
                        	  <div class="fileupload_img"><img type="image" src="images/<?php echo $row['banner_image'];?>" alt="banner image" style="width: 172px;"/></div>
                        	<?php } else {?>
                        	  <div class="fileupload_img"><img type="image" src="assets/images/add-image.png" alt="banner image" /></div>
                        	<?php }?>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">URL :-</label>
                    <div class="col-md-6">
                      <input type="text" name="banner_url" id="banner_url" value="<?php if(isset($_GET['banner_id'])){echo $row['banner_url'];}?>" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-md-9 col-md-offset-3">
                      <button type="submit" name="submit" class="btn btn-primary">Save</button>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
        
<?php include("includes/footer.php");?>       
