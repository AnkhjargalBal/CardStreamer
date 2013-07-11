package org.datacontract.schemas.BusinessObjects;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class UserPassSaltPair extends SoapObject {
	private java.lang.String passwordSalt;

	private java.lang.Integer userId;

	public UserPassSaltPair() {
		super("", "");
	}

	public void setPasswordSalt(java.lang.String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public java.lang.String getPasswordSalt(java.lang.String passwordSalt) {
		return this.passwordSalt;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.Integer getUserId(java.lang.Integer userId) {
		return this.userId;
	}

	public int getPropertyCount() {
		return 2;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 0:
			return passwordSalt;
		case 1:
			return userId;
		}
		return null;
	}

	public void setProperty(int __index, Object __obj) {
		switch (__index) {
		case 0:
			passwordSalt = (java.lang.String) __obj;
			break;
		case 1:
			userId = (java.lang.Integer) __obj;
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int __index, Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "passwordSalt";
			__info.type = java.lang.String.class;
			break;
		case 1:
			__info.name = "userId";
			__info.type = java.lang.Integer.class;
			break;
		}
	}

}
