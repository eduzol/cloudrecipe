package com.zola.recipe.domain.security;

public class ApplicationKey {

	
	private int appKeyId;
	
	private String keyId;
	
	private String keySecret;

	public int getAppKeyId() {
		return appKeyId;
	}

	public void setAppKeyId(int appKeyId) {
		this.appKeyId = appKeyId;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getKeySecret() {
		return keySecret;
	}

	public void setKeySecret(String keySecret) {
		this.keySecret = keySecret;
	}

	@Override
	public String toString() {
		return "ApplicationKey [appKeyId=" + appKeyId + ", keyId=" + keyId
				+ ", keySecret=" + keySecret + "]";
	}

	
}
