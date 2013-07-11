package org.datacontract.schemas.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class UserRequestResult extends SoapObject {
	private org.datacontract.schemas.BusinessObjects.OCRLine[] OCRLinesList;

	private org.datacontract.schemas.BusinessObjects.OCRWord[] OCRWordList;

	private org.datacontract.schemas.BusinessObjects.User user;

	public UserRequestResult() {
		super("", "");
	}

	public void setOCRLinesList(
			org.datacontract.schemas.BusinessObjects.OCRLine[] OCRLinesList) {
		this.OCRLinesList = OCRLinesList;
	}

	public org.datacontract.schemas.BusinessObjects.OCRLine[] getOCRLinesList(
			org.datacontract.schemas.BusinessObjects.OCRLine[] OCRLinesList) {
		return this.OCRLinesList;
	}

	public void setOCRWordList(
			org.datacontract.schemas.BusinessObjects.OCRWord[] OCRWordList) {
		this.OCRWordList = OCRWordList;
	}

	public org.datacontract.schemas.BusinessObjects.OCRWord[] getOCRWordList(
			org.datacontract.schemas.BusinessObjects.OCRWord[] OCRWordList) {
		return this.OCRWordList;
	}

	public void setUser(
			org.datacontract.schemas.BusinessObjects.User user) {
		this.user = user;
	}

	public org.datacontract.schemas.BusinessObjects.User getUser(
			org.datacontract.schemas.BusinessObjects.User user) {
		return this.user;
	}

	public int getPropertyCount() {
		return 3;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 2:
			return user;
		}
		return null;
	}

	public void setProperty(int __index, Object __obj) {
		switch (__index) {
		case 2:
			user = (org.datacontract.schemas.BusinessObjects.User) __obj;
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int __index, Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "OCRLinesList";
		case 1:
			__info.name = "OCRWordList";
		case 2:
			__info.name = "user";
			__info.type = org.datacontract.schemas.BusinessObjects.User.class;
			break;
		}
	}

}
