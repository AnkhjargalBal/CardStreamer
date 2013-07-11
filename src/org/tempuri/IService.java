package org.tempuri;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;

import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;

@SuppressWarnings("deprecation")
public final class IService {
	public org.datacontract.schemas.BusinessObjects.User getUser(
			java.lang.Integer userId, java.lang.String hashedPassword)
			throws Exception {
		SoapObject _client = new SoapObject("", "getUser");
		_client.addProperty("userId", userId);
		_client.addProperty("hashedPassword", hashedPassword);
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		AndroidHttpTransport _ht = new AndroidHttpTransport(
				Configuration.getWsUrl());
		_ht.call("", _envelope);
		SoapObject _ret = (SoapObject) _envelope.getResponse();
		int _len = _ret.getPropertyCount();
		org.datacontract.schemas.BusinessObjects.User _returned = new org.datacontract.schemas.BusinessObjects.User();
		for (int _i = 0; _i < _len; _i++) {
			_returned.setProperty(_i, _ret.getProperty(_i));
		}
		return _returned;
	}

	public org.datacontract.schemas.WebService.UserRequestResult getUserBasedOnImageAndroid(
			byte[] pictureData) throws Exception {
		SoapObject _client = new SoapObject("", "getUserBasedOnImageAndroid");
		_client.addProperty(
				"pictureData",
				new org.ksoap2.serialization.SoapPrimitive(SoapEnvelope.ENC,
						"base64", org.kobjects.base64.Base64
								.encode(pictureData)));
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		AndroidHttpTransport _ht = new AndroidHttpTransport(
				Configuration.getWsUrl());
		_ht.call("", _envelope);
		SoapObject _ret = (SoapObject) _envelope.getResponse();
		int _len = _ret.getPropertyCount();
		org.datacontract.schemas.WebService.UserRequestResult _returned = new org.datacontract.schemas.WebService.UserRequestResult();
		for (int _i = 0; _i < _len; _i++) {
			_returned.setProperty(_i, _ret.getProperty(_i));
		}
		return _returned;
	}

	public org.datacontract.schemas.WebService.UserRequestResult getUserBasedOnImage(
			byte[] pictureStream,
			org.datacontract.schemas.BusinessObjects.RequestStatistics statistics)
			throws Exception {
		SoapObject _client = new SoapObject("", "getUserBasedOnImage");
		_client.addProperty(
				"pictureStream",
				new org.ksoap2.serialization.SoapPrimitive(SoapEnvelope.ENC,
						"base64", org.kobjects.base64.Base64
								.encode(pictureStream)));
		_client.addProperty("statistics", statistics);
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		AndroidHttpTransport _ht = new AndroidHttpTransport(
				Configuration.getWsUrl());
		_ht.call("", _envelope);
		SoapObject _ret = (SoapObject) _envelope.getResponse();
		int _len = _ret.getPropertyCount();
		org.datacontract.schemas.WebService.UserRequestResult _returned = new org.datacontract.schemas.WebService.UserRequestResult();
		for (int _i = 0; _i < _len; _i++) {
			_returned.setProperty(_i, _ret.getProperty(_i));
		}
		return _returned;
	}

	/** this is the key method */
	private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

	private static final String SOAP_ACTION = "http://tempuri.org/IService/GetUserBasedOnOCR";

	public org.datacontract.schemas.BusinessObjects.User getUserBasedOnOCR(
			java.lang.String recognizedText) throws Exception {
		SoapObject _client = new SoapObject(WSDL_TARGET_NAMESPACE,
				"GetUserBasedOnOCR");
		_client.addProperty("recognizedText", recognizedText);
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		_envelope.dotNet = true;
		HttpTransportSE _ht = new HttpTransportSE(Configuration.getWsUrl());
		_ht.call(SOAP_ACTION, _envelope);
		SoapObject _ret = (SoapObject) _envelope.getResponse();

		int _len = _ret.getPropertyCount();
		org.datacontract.schemas.BusinessObjects.User _returned = new org.datacontract.schemas.BusinessObjects.User();
		for (int _i = 0; _i < _len; _i++) {
			_returned.setProperty(_i, _ret.getProperty(_i));
		}

		return _returned;
	}

	public void insertCardSURFData(
			org.datacontract.schemas.BusinessObjects.BusinessCard card,
			java.lang.Boolean name) throws Exception {
		SoapObject _client = new SoapObject("", "insertCardSURFData");
		_client.addProperty("card", card);
		_client.addProperty("name", name);
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		AndroidHttpTransport _ht = new AndroidHttpTransport(
				Configuration.getWsUrl());
		_ht.call("", _envelope);
	}

	public void removeBusinessCard(java.lang.Integer cardId) throws Exception {
		SoapObject _client = new SoapObject("", "removeBusinessCard");
		_client.addProperty("cardId", cardId);
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		AndroidHttpTransport _ht = new AndroidHttpTransport(
				Configuration.getWsUrl());
		_ht.call("", _envelope);
	}

	public void updateCardNameArea(
			org.datacontract.schemas.BusinessObjects.BusinessCard card)
			throws Exception {
		SoapObject _client = new SoapObject("", "updateCardNameArea");
		_client.addProperty("card", card);
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		AndroidHttpTransport _ht = new AndroidHttpTransport(
				Configuration.getWsUrl());
		_ht.call("", _envelope);
	}

	public org.datacontract.schemas.BusinessObjects.UserPassSaltPair getUserSaltPairByEmail(
			java.lang.String email) throws Exception {
		SoapObject _client = new SoapObject("", "getUserSaltPairByEmail");
		_client.addProperty("email", email);
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		AndroidHttpTransport _ht = new AndroidHttpTransport(
				Configuration.getWsUrl());
		_ht.call("", _envelope);
		SoapObject _ret = (SoapObject) _envelope.getResponse();
		int _len = _ret.getPropertyCount();
		org.datacontract.schemas.BusinessObjects.UserPassSaltPair _returned = new org.datacontract.schemas.BusinessObjects.UserPassSaltPair();
		for (int _i = 0; _i < _len; _i++) {
			_returned.setProperty(_i, _ret.getProperty(_i));
		}
		return _returned;
	}

	public java.lang.Boolean createUser(
			org.datacontract.schemas.BusinessObjects.User user)
			throws Exception {
		SoapObject _client = new SoapObject("", "createUser");
		_client.addProperty("user", user);
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		AndroidHttpTransport _ht = new AndroidHttpTransport(
				Configuration.getWsUrl());
		_ht.call("", _envelope);
		return (java.lang.Boolean) _envelope.getResponse();
	}

	public java.lang.Boolean updateUser(
			org.datacontract.schemas.BusinessObjects.User user)
			throws Exception {
		SoapObject _client = new SoapObject("", "updateUser");
		_client.addProperty("user", user);
		SoapSerializationEnvelope _envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		_envelope.bodyOut = _client;
		AndroidHttpTransport _ht = new AndroidHttpTransport(
				Configuration.getWsUrl());
		_ht.call("", _envelope);
		return (java.lang.Boolean) _envelope.getResponse();
	}

}
