package org.datacontract.schemas.BusinessObjects;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class OCRLine extends SoapObject {
	private java.lang.Double avgConfidency;

	private java.lang.Integer cardId;

	private org.datacontract.schemas.System_Drawing.Rectangle lineArea;

	private java.lang.Integer lineIndex;

	private java.lang.String lineText;

	private java.lang.Integer OCRLineId;

	public OCRLine() {
		super("", "");
	}

	public void setAvgConfidency(java.lang.Double avgConfidency) {
		this.avgConfidency = avgConfidency;
	}

	public java.lang.Double getAvgConfidency(java.lang.Double avgConfidency) {
		return this.avgConfidency;
	}

	public void setCardId(java.lang.Integer cardId) {
		this.cardId = cardId;
	}

	public java.lang.Integer getCardId(java.lang.Integer cardId) {
		return this.cardId;
	}

	public void setLineArea(
			org.datacontract.schemas.System_Drawing.Rectangle lineArea) {
		this.lineArea = lineArea;
	}

	public org.datacontract.schemas.System_Drawing.Rectangle getLineArea(
			org.datacontract.schemas.System_Drawing.Rectangle lineArea) {
		return this.lineArea;
	}

	public void setLineIndex(java.lang.Integer lineIndex) {
		this.lineIndex = lineIndex;
	}

	public java.lang.Integer getLineIndex(java.lang.Integer lineIndex) {
		return this.lineIndex;
	}

	public void setLineText(java.lang.String lineText) {
		this.lineText = lineText;
	}

	public java.lang.String getLineText(java.lang.String lineText) {
		return this.lineText;
	}

	public void setOCRLineId(java.lang.Integer OCRLineId) {
		this.OCRLineId = OCRLineId;
	}

	public java.lang.Integer getOCRLineId(java.lang.Integer OCRLineId) {
		return this.OCRLineId;
	}

	public int getPropertyCount() {
		return 6;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 0:
			return avgConfidency;
		case 1:
			return cardId;
		case 2:
			return lineArea;
		case 3:
			return lineIndex;
		case 4:
			return lineText;
		case 5:
			return OCRLineId;
		}
		return null;
	}

	public void setProperty(int __index, Object __obj) {
		switch (__index) {
		case 0:
			avgConfidency = (java.lang.Double) __obj;
			break;
		case 1:
			cardId = (java.lang.Integer) __obj;
			break;
		case 2:
			lineArea = (org.datacontract.schemas.System_Drawing.Rectangle) __obj;
			break;
		case 3:
			lineIndex = (java.lang.Integer) __obj;
			break;
		case 4:
			lineText = (java.lang.String) __obj;
			break;
		case 5:
			OCRLineId = (java.lang.Integer) __obj;
			break;
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public void getPropertyInfo(int __index, Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "avgConfidency";
			__info.type = java.lang.Double.class;
			break;
		case 1:
			__info.name = "cardId";
			__info.type = java.lang.Integer.class;
			break;
		case 2:
			__info.name = "lineArea";
			__info.type = org.datacontract.schemas.System_Drawing.Rectangle.class;
			break;
		case 3:
			__info.name = "lineIndex";
			__info.type = java.lang.Integer.class;
			break;
		case 4:
			__info.name = "lineText";
			__info.type = java.lang.String.class;
			break;
		case 5:
			__info.name = "OCRLineId";
			__info.type = java.lang.Integer.class;
			break;
		}
	}

}
