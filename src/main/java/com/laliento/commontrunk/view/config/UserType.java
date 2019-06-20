package com.laliento.commontrunk.view.config;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
public enum UserType {
	ADMIN("admin"),
	USER("user"),
	DELIVERY("delivery"),
	LALO("lalo");
	UserType(String userType) {
		this.userType = userType;
	}
	private final String userType;
	public String getUserType() {return userType;}
}
