package org.datacontract.schemas.BusinessObjects;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class OCRWord extends SoapObject {
	private java.lang.Integer line;

	private org.datacontract.schemas.System_Drawing.Rectangle rectangleArea;

	private java.lang.String text;

	public OCRWord() {
		super("", "");
	}

	public void setLine(java.lang.Integer line) {
		this.line = line;
	}

	public java.lang.Integer getLine(java.lang.Integer line) {
		return this.line;
	}

	public void setRectangleArea(
			org.datacontract.schemas.System_Drawing.Rectangle rectangleArea) {
		this.rectangleArea = rectangleArea;
	}

	public org.datacontract.schemas.System_Drawing.Rectangle getRectangleArea(
			org.datacontract.schemas.System_Drawing.Rectangle rectangleArea) {
		return this.rectangleArea;
	}

	public void setText(java.lang.String text) {
		this.text = text;
	}

	public java.lang.String getText(java.lang.String text) {
		return this.text;
	}

	public int getPropertyCount() {
		return 3;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 0:
			return line;
		case 1:
			return rectangleArea;
		case 2:
			return text;
		}
		return null;
	}

	public void setProperty(int __index, Object __obj) {
		switch (__index) {
		case 0:
			line = (java.lang.Integer) __obj;
			break;
		case 1:
			rectangleArea = (org.datacontract.schemas.System_Drawing.Rectangle) __obj;
			break;
		case 2:
			text = (java.lang.String) __obj;
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int __index, Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "line";
			__info.type = java.lang.Integer.class;
			break;
		case 1:
			__info.name = "rectangleArea";
			__info.type = org.datacontract.schemas.System_Drawing.Rectangle.class;
			break;
		case 2:
			__info.name = "text";
			__info.type = java.lang.String.class;
			break;
		}
	}

}
