package org.datacontract.schemas.BusinessObjects;

import java.util.Hashtable;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public final class User extends SoapObject {
	private org.datacontract.schemas.BusinessObjects.BusinessCard[] businessCards;

	private java.lang.String companyCity;

	private java.lang.String companyCountry;

	private java.lang.String companyName;

	private java.lang.String companyPostalCode;

	private java.lang.String companyStreet;

	private java.lang.String email;

	private java.lang.String firstName;

	private java.lang.Integer id;

	private java.lang.String lastName;

	private java.lang.String linkFacebook;

	private java.lang.String linkLinkedIn;

	private java.lang.String password;

	private java.lang.String phoneCompany;

	private java.lang.String phoneMobile;

	private java.lang.String salt;

	private org.datacontract.schemas.BusinessObjects.Video[] videos;

	private java.lang.String websiteCompany;

	private java.lang.String websitePersonal;

	private org.datacontract.schemas.BusinessObjects.BusinessCard[] businessCards2;

	private org.datacontract.schemas.BusinessObjects.Video[] videos2;

	public User() {
		super("", "");
	}

	public void setBusinessCards(
			org.datacontract.schemas.BusinessObjects.BusinessCard[] businessCards) {
		this.businessCards = businessCards;
	}

	public org.datacontract.schemas.BusinessObjects.BusinessCard[] getBusinessCards(
			org.datacontract.schemas.BusinessObjects.BusinessCard[] businessCards) {
		return this.businessCards;
	}

	public void setCompanyCity(java.lang.String companyCity) {
		this.companyCity = companyCity;
	}

	public java.lang.String getCompanyCity(java.lang.String companyCity) {
		return this.companyCity;
	}

	public void setCompanyCountry(java.lang.String companyCountry) {
		this.companyCountry = companyCountry;
	}

	public java.lang.String getCompanyCountry(java.lang.String companyCountry) {
		return this.companyCountry;
	}

	public void setCompanyName(java.lang.String companyName) {
		this.companyName = companyName;
	}

	public java.lang.String getCompanyName(java.lang.String companyName) {
		return this.companyName;
	}

	public void setCompanyPostalCode(java.lang.String companyPostalCode) {
		this.companyPostalCode = companyPostalCode;
	}

	public java.lang.String getCompanyPostalCode(
			java.lang.String companyPostalCode) {
		return this.companyPostalCode;
	}

	public void setCompanyStreet(java.lang.String companyStreet) {
		this.companyStreet = companyStreet;
	}

	public java.lang.String getCompanyStreet(java.lang.String companyStreet) {
		return this.companyStreet;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getEmail(java.lang.String email) {
		return this.email;
	}

	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	public java.lang.String getFirstName(java.lang.String firstName) {
		return this.firstName;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getId(java.lang.Integer id) {
		return this.id;
	}

	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}

	public java.lang.String getLastName(java.lang.String lastName) {
		return this.lastName;
	}

	public void setLinkFacebook(java.lang.String linkFacebook) {
		this.linkFacebook = linkFacebook;
	}

	public java.lang.String getLinkFacebook(java.lang.String linkFacebook) {
		return this.linkFacebook;
	}

	public void setLinkLinkedIn(java.lang.String linkLinkedIn) {
		this.linkLinkedIn = linkLinkedIn;
	}

	public java.lang.String getLinkLinkedIn(java.lang.String linkLinkedIn) {
		return this.linkLinkedIn;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getPassword(java.lang.String password) {
		return this.password;
	}

	public void setPhoneCompany(java.lang.String phoneCompany) {
		this.phoneCompany = phoneCompany;
	}

	public java.lang.String getPhoneCompany(java.lang.String phoneCompany) {
		return this.phoneCompany;
	}

	public void setPhoneMobile(java.lang.String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}

	public java.lang.String getPhoneMobile(java.lang.String phoneMobile) {
		return this.phoneMobile;
	}

	public void setSalt(java.lang.String salt) {
		this.salt = salt;
	}

	public java.lang.String getSalt(java.lang.String salt) {
		return this.salt;
	}

	public void setVideos(
			org.datacontract.schemas.BusinessObjects.Video[] videos) {
		this.videos = videos;
	}

	public org.datacontract.schemas.BusinessObjects.Video[] getVideos(
			org.datacontract.schemas.BusinessObjects.Video[] videos) {
		return this.videos;
	}

	public void setWebsiteCompany(java.lang.String websiteCompany) {
		this.websiteCompany = websiteCompany;
	}

	public java.lang.String getWebsiteCompany(java.lang.String websiteCompany) {
		return this.websiteCompany;
	}

	public void setWebsitePersonal(java.lang.String websitePersonal) {
		this.websitePersonal = websitePersonal;
	}

	public java.lang.String getWebsitePersonal(java.lang.String websitePersonal) {
		return this.websitePersonal;
	}

	public void setBusinessCards2(
			org.datacontract.schemas.BusinessObjects.BusinessCard[] businessCards2) {
		this.businessCards2 = businessCards2;
	}

	public org.datacontract.schemas.BusinessObjects.BusinessCard[] getBusinessCards2(
			org.datacontract.schemas.BusinessObjects.BusinessCard[] businessCards2) {
		return this.businessCards2;
	}

	public void setVideos2(
			org.datacontract.schemas.BusinessObjects.Video[] videos2) {
		this.videos2 = videos2;
	}

	public org.datacontract.schemas.BusinessObjects.Video[] getVideos2(
			org.datacontract.schemas.BusinessObjects.Video[] videos2) {
		return this.videos2;
	}

	public int getPropertyCount() {
		return 21;
	}

	public Object getProperty(int __index) {
		switch (__index) {
		case 1:
			return companyCity;
		case 2:
			return companyCountry;
		case 3:
			return companyName;
		case 4:
			return companyPostalCode;
		case 5:
			return companyStreet;
		case 6:
			return email;
		case 7:
			return firstName;
		case 8:
			return id;
		case 9:
			return lastName;
		case 10:
			return linkFacebook;
		case 11:
			return linkLinkedIn;
		case 12:
			return password;
		case 13:
			return phoneCompany;
		case 14:
			return phoneMobile;
		case 15:
			return salt;
		case 17:
			return websiteCompany;
		case 18:
			return websitePersonal;
		}
		return null;
	}

	public void setProperty(int __index, Object __obj) {
		if (__obj instanceof SoapPrimitive) {
			switch (__index) {
			case 1:
				companyCity = __obj.toString();
				break;
			case 2:
				companyCountry = __obj.toString();
				break;
			case 3:
				companyName = __obj.toString();
				break;
			case 4:
				companyPostalCode = __obj.toString();
				break;
			case 5:
				companyStreet = __obj.toString();
				break;
			case 6:
				email = __obj.toString();
				break;
			case 7:
				firstName = __obj.toString();
				break;
			case 8:
				id = Integer.parseInt(__obj.toString());
				break;
			case 9:
				lastName = __obj.toString();
				break;
			case 10:
				linkFacebook = __obj.toString();
				break;
			case 11:
				linkLinkedIn = __obj.toString();
				break;
			case 12:
				password = __obj.toString();
				break;
			case 13:
				phoneCompany = __obj.toString();
				break;
			case 14:
				phoneMobile = __obj.toString();
				break;
			case 15:
				salt = __obj.toString();
				break;
			case 17:
				websiteCompany = __obj.toString();
				break;
			case 18:
				websitePersonal = __obj.toString();
				break;
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int __index, Hashtable __table,
			PropertyInfo __info) {
		switch (__index) {
		case 0:
			__info.name = "businessCards";
		case 1:
			__info.name = "companyCity";
			__info.type = java.lang.String.class;
			break;
		case 2:
			__info.name = "companyCountry";
			__info.type = java.lang.String.class;
			break;
		case 3:
			__info.name = "companyName";
			__info.type = java.lang.String.class;
			break;
		case 4:
			__info.name = "companyPostalCode";
			__info.type = java.lang.String.class;
			break;
		case 5:
			__info.name = "companyStreet";
			__info.type = java.lang.String.class;
			break;
		case 6:
			__info.name = "email";
			__info.type = java.lang.String.class;
			break;
		case 7:
			__info.name = "firstName";
			__info.type = java.lang.String.class;
			break;
		case 8:
			__info.name = "id";
			__info.type = java.lang.Integer.class;
			break;
		case 9:
			__info.name = "lastName";
			__info.type = java.lang.String.class;
			break;
		case 10:
			__info.name = "linkFacebook";
			__info.type = java.lang.String.class;
			break;
		case 11:
			__info.name = "linkLinkedIn";
			__info.type = java.lang.String.class;
			break;
		case 12:
			__info.name = "password";
			__info.type = java.lang.String.class;
			break;
		case 13:
			__info.name = "phoneCompany";
			__info.type = java.lang.String.class;
			break;
		case 14:
			__info.name = "phoneMobile";
			__info.type = java.lang.String.class;
			break;
		case 15:
			__info.name = "salt";
			__info.type = java.lang.String.class;
			break;
		case 16:
			__info.name = "videos";
		case 17:
			__info.name = "websiteCompany";
			__info.type = java.lang.String.class;
			break;
		case 18:
			__info.name = "websitePersonal";
			__info.type = java.lang.String.class;
			break;
		case 19:
			__info.name = "businessCards2";
		case 20:
			__info.name = "videos2";
		}
	}

}
