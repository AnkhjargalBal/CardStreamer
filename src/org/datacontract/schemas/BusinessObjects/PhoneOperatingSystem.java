package org.datacontract.schemas.BusinessObjects;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class PhoneOperatingSystem extends SoapObject {
	private java.lang.String _value_;

	@SuppressWarnings("rawtypes")
	public java.util.HashMap _table_;

	public java.lang.String _Undefined;

	public java.lang.String _Android;

	public java.lang.String _Windows;

	public java.lang.String _Iphone;

	public org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Undefined;

	public org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Android;

	public org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Windows;

	public org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Iphone;

	public PhoneOperatingSystem() {
		super("", "");
	}

	public void set_value_(java.lang.String _value_) {
		this._value_ = _value_;
	}

	public java.lang.String get_value_(java.lang.String _value_) {
		return this._value_;
	}

	@SuppressWarnings("rawtypes")
	public void set_table_(java.util.HashMap _table_) {
		this._table_ = _table_;
	}

	@SuppressWarnings("rawtypes")
	public java.util.HashMap get_table_(java.util.HashMap _table_) {
		return this._table_;
	}

	public void set_Undefined(java.lang.String _Undefined) {
		this._Undefined = _Undefined;
	}

	public java.lang.String get_Undefined(java.lang.String _Undefined) {
		return this._Undefined;
	}

	public void set_Android(java.lang.String _Android) {
		this._Android = _Android;
	}

	public java.lang.String get_Android(java.lang.String _Android) {
		return this._Android;
	}

	public void set_Windows(java.lang.String _Windows) {
		this._Windows = _Windows;
	}

	public java.lang.String get_Windows(java.lang.String _Windows) {
		return this._Windows;
	}

	public void set_Iphone(java.lang.String _Iphone) {
		this._Iphone = _Iphone;
	}

	public java.lang.String get_Iphone(java.lang.String _Iphone) {
		return this._Iphone;
	}

	public void setUndefined(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Undefined) {
		this.Undefined = Undefined;
	}

	public org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem getUndefined(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Undefined) {
		return this.Undefined;
	}

	public void setAndroid(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Android) {
		this.Android = Android;
	}

	public org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem getAndroid(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Android) {
		return this.Android;
	}

	public void setWindows(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Windows) {
		this.Windows = Windows;
	}

	public org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem getWindows(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Windows) {
		return this.Windows;
	}

	public void setIphone(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Iphone) {
		this.Iphone = Iphone;
	}

	public org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem getIphone(
			org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem Iphone) {
		return this.Iphone;
	}

	public int getPropertyCount() {
		return 8;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 0:
			return _value_;
		case 1:
			return _table_;
		case 2:
			return _Undefined;
		case 3:
			return _Android;
		case 4:
			return _Windows;
		case 5:
			return _Iphone;
		case 6:
			return Undefined;
		case 7:
			return Android;
		case 8:
			return Windows;
		case 9:
			return Iphone;
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public void setProperty(int __index, Object __obj) {
		switch (__index) {
		case 0:
			_value_ = (java.lang.String) __obj;
			break;
		case 1:
			_table_ = (java.util.HashMap) __obj;
			break;
		case 2:
			_Undefined = (java.lang.String) __obj;
			break;
		case 3:
			_Android = (java.lang.String) __obj;
			break;
		case 4:
			_Windows = (java.lang.String) __obj;
			break;
		case 5:
			_Iphone = (java.lang.String) __obj;
			break;
		case 6:
			Undefined = (org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem) __obj;
			break;
		case 7:
			Android = (org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem) __obj;
			break;
		case 8:
			Windows = (org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem) __obj;
			break;
		case 9:
			Iphone = (org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem) __obj;
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int __index, Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "_value_";
			__info.type = java.lang.String.class;
			break;
		case 1:
			__info.name = "_table_";
			__info.type = java.util.HashMap.class;
			break;
		case 2:
			__info.name = "_Undefined";
			__info.type = java.lang.String.class;
			break;
		case 3:
			__info.name = "_Android";
			__info.type = java.lang.String.class;
			break;
		case 4:
			__info.name = "_Windows";
			__info.type = java.lang.String.class;
			break;
		case 5:
			__info.name = "_Iphone";
			__info.type = java.lang.String.class;
			break;
		case 6:
			__info.name = "Undefined";
			__info.type = org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem.class;
			break;
		case 7:
			__info.name = "Android";
			__info.type = org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem.class;
			break;
		case 8:
			__info.name = "Windows";
			__info.type = org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem.class;
			break;
		case 9:
			__info.name = "Iphone";
			__info.type = org.datacontract.schemas.BusinessObjects.PhoneOperatingSystem.class;
			break;
		}
	}

}
