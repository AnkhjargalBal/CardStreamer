package org.datacontract.schemas.BusinessObjects;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class BusinessCard extends SoapObject {
	private java.lang.Integer cardId;

	private org.datacontract.schemas.System_Drawing.Rectangle emailCoordinates;

	private org.datacontract.schemas.System_Drawing.Size imageSize;

	private java.lang.String imageUrl;

	private byte[] imgByte;

	private org.datacontract.schemas.System_Drawing.Rectangle nameCoordinates;

	private org.datacontract.schemas.BusinessObjects.OCRLine[] OCRLines;

	private java.lang.Boolean userDefinedNameArea;

	private java.lang.Integer userId;

	public BusinessCard() {
		super("", "");
	}

	public void setCardId(java.lang.Integer cardId) {
		this.cardId = cardId;
	}

	public java.lang.Integer getCardId(java.lang.Integer cardId) {
		return this.cardId;
	}

	public void setEmailCoordinates(
			org.datacontract.schemas.System_Drawing.Rectangle emailCoordinates) {
		this.emailCoordinates = emailCoordinates;
	}

	public org.datacontract.schemas.System_Drawing.Rectangle getEmailCoordinates(
			org.datacontract.schemas.System_Drawing.Rectangle emailCoordinates) {
		return this.emailCoordinates;
	}

	public void setImageSize(
			org.datacontract.schemas.System_Drawing.Size imageSize) {
		this.imageSize = imageSize;
	}

	public org.datacontract.schemas.System_Drawing.Size getImageSize(
			org.datacontract.schemas.System_Drawing.Size imageSize) {
		return this.imageSize;
	}

	public void setImageUrl(java.lang.String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public java.lang.String getImageUrl(java.lang.String imageUrl) {
		return this.imageUrl;
	}

	public void setImgByte(byte[] imgByte) {
		this.imgByte = imgByte;
	}

	public byte[] getImgByte(byte[] imgByte) {
		return this.imgByte;
	}

	public void setNameCoordinates(
			org.datacontract.schemas.System_Drawing.Rectangle nameCoordinates) {
		this.nameCoordinates = nameCoordinates;
	}

	public org.datacontract.schemas.System_Drawing.Rectangle getNameCoordinates(
			org.datacontract.schemas.System_Drawing.Rectangle nameCoordinates) {
		return this.nameCoordinates;
	}

	public void setOCRLines(
			org.datacontract.schemas.BusinessObjects.OCRLine[] OCRLines) {
		this.OCRLines = OCRLines;
	}

	public org.datacontract.schemas.BusinessObjects.OCRLine[] getOCRLines(
			org.datacontract.schemas.BusinessObjects.OCRLine[] OCRLines) {
		return this.OCRLines;
	}

	public void setUserDefinedNameArea(java.lang.Boolean userDefinedNameArea) {
		this.userDefinedNameArea = userDefinedNameArea;
	}

	public java.lang.Boolean getUserDefinedNameArea(
			java.lang.Boolean userDefinedNameArea) {
		return this.userDefinedNameArea;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.Integer getUserId(java.lang.Integer userId) {
		return this.userId;
	}

	public int getPropertyCount() {
		return 9;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 0:
			return cardId;
		case 1:
			return emailCoordinates;
		case 2:
			return imageSize;
		case 3:
			return imageUrl;
		case 5:
			return nameCoordinates;
		case 7:
			return userDefinedNameArea;
		case 8:
			return userId;
		}
		return null;
	}

	public void setProperty(int __index, Object __obj) {
		switch (__index) {
		case 0:
			cardId = (java.lang.Integer) __obj;
			break;
		case 1:
			emailCoordinates = (org.datacontract.schemas.System_Drawing.Rectangle) __obj;
			break;
		case 2:
			imageSize = (org.datacontract.schemas.System_Drawing.Size) __obj;
			break;
		case 3:
			imageUrl = (java.lang.String) __obj;
			break;
		case 5:
			nameCoordinates = (org.datacontract.schemas.System_Drawing.Rectangle) __obj;
			break;
		case 7:
			userDefinedNameArea = (java.lang.Boolean) __obj;
			break;
		case 8:
			userId = (java.lang.Integer) __obj;
			break;
		}
	}

	public void getPropertyInfo(int __index, @SuppressWarnings("rawtypes") Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "cardId";
			__info.type = java.lang.Integer.class;
			break;
		case 1:
			__info.name = "emailCoordinates";
			__info.type = org.datacontract.schemas.System_Drawing.Rectangle.class;
			break;
		case 2:
			__info.name = "imageSize";
			__info.type = org.datacontract.schemas.System_Drawing.Size.class;
			break;
		case 3:
			__info.name = "imageUrl";
			__info.type = java.lang.String.class;
			break;
		case 4:
			__info.name = "imgByte";
		case 5:
			__info.name = "nameCoordinates";
			__info.type = org.datacontract.schemas.System_Drawing.Rectangle.class;
			break;
		case 6:
			__info.name = "OCRLines";
		case 7:
			__info.name = "userDefinedNameArea";
			__info.type = java.lang.Boolean.class;
			break;
		case 8:
			__info.name = "userId";
			__info.type = java.lang.Integer.class;
			break;
		}
	}

}
