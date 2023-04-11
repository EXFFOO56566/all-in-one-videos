package com.example.item;

public class ItemComment {
	
	private String CommentId;
	private String CommentName;
	private String CommentMsg;
	private String CommentImage;
	
	public String getCategoryId() {
		return CommentId;
	}
 	public void setCommentId(String CommentId) {
		this.CommentId = CommentId;
	}

	public String getCommentName() {
		return CommentName;
	}
 	public void setCommentName(String CommentName) {
		this.CommentName = CommentName;
	}
	
	public String getCommentMsg() { return CommentMsg; }
	public void setCommentMsg(String CommentMsg) { this.CommentMsg=CommentMsg; }

	public String getCommentImage() { return CommentImage; }
	public void setCommentImage(String CommentImage) { this.CommentImage=CommentImage; }


}
