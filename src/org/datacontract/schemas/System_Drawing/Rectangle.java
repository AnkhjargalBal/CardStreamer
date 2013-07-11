package org.datacontract.schemas.System_Drawing;

import android.annotation.SuppressLint;
import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

@SuppressLint("UseValueOf")
public final class Rectangle extends SoapObject {
	private int height;

	private int width;

	private int x;

	private int y;

	public Rectangle() {
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

	public void setX(int x) {
		this.x = x;
	}

	public int getX(int x) {
		return this.x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY(int y) {
		return this.y;
	}

	public int getPropertyCount() {
		return 4;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 0:
			return new Integer(height);
		case 1:
			return new Integer(width);
		case 2:
			return new Integer(x);
		case 3:
			return new Integer(y);
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
		case 2:
			x = Integer.parseInt(__obj.toString());
			break;
		case 3:
			y = Integer.parseInt(__obj.toString());
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
		case 2:
			__info.name = "x";
			__info.type = Integer.class;
			break;
		case 3:
			__info.name = "y";
			__info.type = Integer.class;
			break;
		}
	}

}
