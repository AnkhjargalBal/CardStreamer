package org.datacontract.schemas.System_Drawing;

import android.annotation.SuppressLint;
import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

@SuppressLint("UseValueOf")
public final class Size extends SoapObject {
	private int height;

	private int width;

	public Size() {
		super("", "");
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight(int height) {
		return this.height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth(int width) {
		return this.width;
	}

	public int getPropertyCount() {
		return 2;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 0:
			return new Integer(height);
		case 1:
			return new Integer(width);
		}
		return null;
	}

	public void setProperty(int __index, Object __obj) {
		switch (__index) {
		case 0:
			height = Integer.parseInt(__obj.toString());
			break;
		case 1:
			width = Integer.parseInt(__obj.toString());
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int __index, Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "height";
			__info.type = Integer.class;
			break;
		case 1:
			__info.name = "width";
			__info.type = Integer.class;
			break;
		}
	}

}
