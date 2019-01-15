package com.magento.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ResponseMagento")
public class ResponseMagento implements Serializable {

	private static final long serialVersionUID = -2312522006757066070L;

	private String codeResponse;
	private String message;
	
	public ResponseMagento() {
		codeResponse = "Ok";
		message = "This List Order is valid";
	}

	public String getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(String codeResponse) {
		this.codeResponse = codeResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
		
}