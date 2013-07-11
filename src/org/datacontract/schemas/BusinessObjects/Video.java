package org.datacontract.schemas.BusinessObjects;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Video extends SoapObject {
	private java.lang.String description;

	private java.lang.Integer userID;

	private java.lang.Integer videoID;

	private java.lang.String videoUrl;

	public Video() {
		super("", "");
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getDescription(java.lang.String description) {
		return this.description;
	}

	public void setUserID(java.lang.Integer userID) {
		this.userID = userID;
	}

	public java.lang.Integer getUserID(java.lang.Integer userID) {
		return this.userID;
	}

	public void setVideoID(java.lang.Integer videoID) {
		this.videoID = videoID;
	}

	public java.lang.Integer getVideoID(java.lang.Integer videoID) {
		return this.videoID;
	}

	public void setVideoUrl(java.lang.String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public java.lang.String getVideoUrl(java.lang.String videoUrl) {
		return this.videoUrl;
	}

	public int getPropertyCount() {
		return 4;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 0:
			return description;
		case 1:
			return userID;
		case 2:
			return videoID;
		case 3:
			return videoUrl;
		}
		return null;
	}

	public void setProperty(int __index, Object __obj) {
		switch (__index) {
		case 0:
			description = (java.lang.String) __obj;
			break;
		case 1:
			userID = (java.lang.Integer) __obj;
			break;
		case 2:
			videoID = (java.lang.Integer) __obj;
			break;
		case 3:
			videoUrl = (java.lang.String) __obj;
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int __index, Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "description";
			__info.type = java.lang.String.class;
			break;
		case 1:
			__info.name = "userID";
			__info.type = java.lang.Integer.class;
			break;
		case 2:
			__info.name = "videoID";
			__info.type = java.lang.Integer.class;
			break;
		case 3:
			__info.name = "videoUrl";
			__info.type = java.lang.String.class;
			break;
		}
	}

}
