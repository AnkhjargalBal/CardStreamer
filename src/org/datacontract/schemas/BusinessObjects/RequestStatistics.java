package org.datacontract.schemas.BusinessObjects;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class RequestStatistics extends SoapObject {
	private java.lang.Integer id;

	private java.lang.Integer OCRBestMatchEmailAreaCardId;

	private java.lang.Float OCRBestMatchEmailAreaValue;

	private java.lang.Integer OCRBestMatchNameAreaCardId;

	private java.lang.Float OCRBestMatchNameAreaValue;

	private org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem operatingSystem;

	private java.lang.String phoneLanguage;

	private java.util.Calendar reqEndDateTime;

	private java.lang.String reqImagePath;

	private java.util.Calendar reqStartDateTime;

	private java.lang.String resultSelection;

	private java.lang.Integer returnUserId;

	private java.lang.Float SURFBestMatchCardDistance;

	private java.lang.Integer SURFBestMatchCardId;

	private java.lang.Integer SURFBestMatchEmailAreaCardId;

	private java.lang.Float SURFBestMatchEmailAreaDistance;

	private java.lang.Integer SURFBestMatchNameAreaCardId;

	private java.lang.Float SURFBestMatchNameAreaDistance;

	public RequestStatistics() {
		super("", "");
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getId(java.lang.Integer id) {
		return this.id;
	}

	public void setOCRBestMatchEmailAreaCardId(
			java.lang.Integer OCRBestMatchEmailAreaCardId) {
		this.OCRBestMatchEmailAreaCardId = OCRBestMatchEmailAreaCardId;
	}

	public java.lang.Integer getOCRBestMatchEmailAreaCardId(
			java.lang.Integer OCRBestMatchEmailAreaCardId) {
		return this.OCRBestMatchEmailAreaCardId;
	}

	public void setOCRBestMatchEmailAreaValue(
			java.lang.Float OCRBestMatchEmailAreaValue) {
		this.OCRBestMatchEmailAreaValue = OCRBestMatchEmailAreaValue;
	}

	public java.lang.Float getOCRBestMatchEmailAreaValue(
			java.lang.Float OCRBestMatchEmailAreaValue) {
		return this.OCRBestMatchEmailAreaValue;
	}

	public void setOCRBestMatchNameAreaCardId(
			java.lang.Integer OCRBestMatchNameAreaCardId) {
		this.OCRBestMatchNameAreaCardId = OCRBestMatchNameAreaCardId;
	}

	public java.lang.Integer getOCRBestMatchNameAreaCardId(
			java.lang.Integer OCRBestMatchNameAreaCardId) {
		return this.OCRBestMatchNameAreaCardId;
	}

	public void setOCRBestMatchNameAreaValue(
			java.lang.Float OCRBestMatchNameAreaValue) {
		this.OCRBestMatchNameAreaValue = OCRBestMatchNameAreaValue;
	}

	public java.lang.Float getOCRBestMatchNameAreaValue(
			java.lang.Float OCRBestMatchNameAreaValue) {
		return this.OCRBestMatchNameAreaValue;
	}

	public void setOperatingSystem(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem getOperatingSystem(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem operatingSystem) {
		return this.operatingSystem;
	}

	public void setPhoneLanguage(java.lang.String phoneLanguage) {
		this.phoneLanguage = phoneLanguage;
	}

	public java.lang.String getPhoneLanguage(java.lang.String phoneLanguage) {
		return this.phoneLanguage;
	}

	public void setReqEndDateTime(java.util.Calendar reqEndDateTime) {
		this.reqEndDateTime = reqEndDateTime;
	}

	public java.util.Calendar getReqEndDateTime(
			java.util.Calendar reqEndDateTime) {
		return this.reqEndDateTime;
	}

	public void setReqImagePath(java.lang.String reqImagePath) {
		this.reqImagePath = reqImagePath;
	}

	public java.lang.String getReqImagePath(java.lang.String reqImagePath) {
		return this.reqImagePath;
	}

	public void setReqStartDateTime(java.util.Calendar reqStartDateTime) {
		this.reqStartDateTime = reqStartDateTime;
	}

	public java.util.Calendar getReqStartDateTime(
			java.util.Calendar reqStartDateTime) {
		return this.reqStartDateTime;
	}

	public void setResultSelection(java.lang.String resultSelection) {
		this.resultSelection = resultSelection;
	}

	public java.lang.String getResultSelection(java.lang.String resultSelection) {
		return this.resultSelection;
	}

	public void setReturnUserId(java.lang.Integer returnUserId) {
		this.returnUserId = returnUserId;
	}

	public java.lang.Integer getReturnUserId(java.lang.Integer returnUserId) {
		return this.returnUserId;
	}

	public void setSURFBestMatchCardDistance(
			java.lang.Float SURFBestMatchCardDistance) {
		this.SURFBestMatchCardDistance = SURFBestMatchCardDistance;
	}

	public java.lang.Float getSURFBestMatchCardDistance(
			java.lang.Float SURFBestMatchCardDistance) {
		return this.SURFBestMatchCardDistance;
	}

	public void setSURFBestMatchCardId(java.lang.Integer SURFBestMatchCardId) {
		this.SURFBestMatchCardId = SURFBestMatchCardId;
	}

	public java.lang.Integer getSURFBestMatchCardId(
			java.lang.Integer SURFBestMatchCardId) {
		return this.SURFBestMatchCardId;
	}

	public void setSURFBestMatchEmailAreaCardId(
			java.lang.Integer SURFBestMatchEmailAreaCardId) {
		this.SURFBestMatchEmailAreaCardId = SURFBestMatchEmailAreaCardId;
	}

	public java.lang.Integer getSURFBestMatchEmailAreaCardId(
			java.lang.Integer SURFBestMatchEmailAreaCardId) {
		return this.SURFBestMatchEmailAreaCardId;
	}

	public void setSURFBestMatchEmailAreaDistance(
			java.lang.Float SURFBestMatchEmailAreaDistance) {
		this.SURFBestMatchEmailAreaDistance = SURFBestMatchEmailAreaDistance;
	}

	public java.lang.Float getSURFBestMatchEmailAreaDistance(
			java.lang.Float SURFBestMatchEmailAreaDistance) {
		return this.SURFBestMatchEmailAreaDistance;
	}

	public void setSURFBestMatchNameAreaCardId(
			java.lang.Integer SURFBestMatchNameAreaCardId) {
		this.SURFBestMatchNameAreaCardId = SURFBestMatchNameAreaCardId;
	}

	public java.lang.Integer getSURFBestMatchNameAreaCardId(
			java.lang.Integer SURFBestMatchNameAreaCardId) {
		return this.SURFBestMatchNameAreaCardId;
	}

	public void setSURFBestMatchNameAreaDistance(
			java.lang.Float SURFBestMatchNameAreaDistance) {
		this.SURFBestMatchNameAreaDistance = SURFBestMatchNameAreaDistance;
	}

	public java.lang.Float getSURFBestMatchNameAreaDistance(
			java.lang.Float SURFBestMatchNameAreaDistance) {
		return this.SURFBestMatchNameAreaDistance;
	}

	public int getPropertyCount() {
		return 18;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 0:
			return id;
		case 1:
			return OCRBestMatchEmailAreaCardId;
		case 2:
			return OCRBestMatchEmailAreaValue;
		case 3:
			return OCRBestMatchNameAreaCardId;
		case 4:
			return OCRBestMatchNameAreaValue;
		case 5:
			return operatingSystem;
		case 6:
			return phoneLanguage;
		case 7:
			return reqEndDateTime;
		case 8:
			return reqImagePath;
		case 9:
			return reqStartDateTime;
		case 10:
			return resultSelection;
		case 11:
			return returnUserId;
		case 12:
			return SURFBestMatchCardDistance;
		case 13:
			return SURFBestMatchCardId;
		case 14:
			return SURFBestMatchEmailAreaCardId;
		case 15:
			return SURFBestMatchEmailAreaDistance;
		case 16:
			return SURFBestMatchNameAreaCardId;
		case 17:
			return SURFBestMatchNameAreaDistance;
		}
		return null;
	}

	public void setProperty(int __index, Object __obj) {
		switch (__index) {
		case 0:
			id = (java.lang.Integer) __obj;
			break;
		case 1:
			OCRBestMatchEmailAreaCardId = (java.lang.Integer) __obj;
			break;
		case 2:
			OCRBestMatchEmailAreaValue = (java.lang.Float) __obj;
			break;
		case 3:
			OCRBestMatchNameAreaCardId = (java.lang.Integer) __obj;
			break;
		case 4:
			OCRBestMatchNameAreaValue = (java.lang.Float) __obj;
			break;
		case 5:
			operatingSystem = (org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem) __obj;
			break;
		case 6:
			phoneLanguage = (java.lang.String) __obj;
			break;
		case 7:
			reqEndDateTime = (java.util.Calendar) __obj;
			break;
		case 8:
			reqImagePath = (java.lang.String) __obj;
			break;
		case 9:
			reqStartDateTime = (java.util.Calendar) __obj;
			break;
		case 10:
			resultSelection = (java.lang.String) __obj;
			break;
		case 11:
			returnUserId = (java.lang.Integer) __obj;
			break;
		case 12:
			SURFBestMatchCardDistance = (java.lang.Float) __obj;
			break;
		case 13:
			SURFBestMatchCardId = (java.lang.Integer) __obj;
			break;
		case 14:
			SURFBestMatchEmailAreaCardId = (java.lang.Integer) __obj;
			break;
		case 15:
			SURFBestMatchEmailAreaDistance = (java.lang.Float) __obj;
			break;
		case 16:
			SURFBestMatchNameAreaCardId = (java.lang.Integer) __obj;
			break;
		case 17:
			SURFBestMatchNameAreaDistance = (java.lang.Float) __obj;
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int __index, Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "id";
			__info.type = java.lang.Integer.class;
			break;
		case 1:
			__info.name = "OCRBestMatchEmailAreaCardId";
			__info.type = java.lang.Integer.class;
			break;
		case 2:
			__info.name = "OCRBestMatchEmailAreaValue";
			__info.type = java.lang.Float.class;
			break;
		case 3:
			__info.name = "OCRBestMatchNameAreaCardId";
			__info.type = java.lang.Integer.class;
			break;
		case 4:
			__info.name = "OCRBestMatchNameAreaValue";
			__info.type = java.lang.Float.class;
			break;
		case 5:
			__info.name = "operatingSystem";
			__info.type = org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem.class;
			break;
		case 6:
			__info.name = "phoneLanguage";
			__info.type = java.lang.String.class;
			break;
		case 7:
			__info.name = "reqEndDateTime";
			__info.type = java.util.Calendar.class;
			break;
		case 8:
			__info.name = "reqImagePath";
			__info.type = java.lang.String.class;
			break;
		case 9:
			__info.name = "reqStartDateTime";
			__info.type = java.util.Calendar.class;
			break;
		case 10:
			__info.name = "resultSelection";
			__info.type = java.lang.String.class;
			break;
		case 11:
			__info.name = "returnUserId";
			__info.type = java.lang.Integer.class;
			break;
		case 12:
			__info.name = "SURFBestMatchCardDistance";
			__info.type = java.lang.Float.class;
			break;
		case 13:
			__info.name = "SURFBestMatchCardId";
			__info.type = java.lang.Integer.class;
			break;
		case 14:
			__info.name = "SURFBestMatchEmailAreaCardId";
			__info.type = java.lang.Integer.class;
			break;
		case 15:
			__info.name = "SURFBestMatchEmailAreaDistance";
			__info.type = java.lang.Float.class;
			break;
		case 16:
			__info.name = "SURFBestMatchNameAreaCardId";
			__info.type = java.lang.Integer.class;
			break;
		case 17:
			__info.name = "SURFBestMatchNameAreaDistance";
			__info.type = java.lang.Float.class;
			break;
		}
	}

}
