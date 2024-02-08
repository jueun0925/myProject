
package com.hk.motong.apidto;


public class UserOobDto {
	private String access_token;
	private String scope;
	private String client_use_code;
	public UserOobDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserOobDto(String access_token, String scope, String client_use_code) {
		super();
		this.access_token = access_token;
		this.scope = scope;
		this.client_use_code = client_use_code;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getClient_use_code() {
		return client_use_code;
	}
	public void setClient_use_code(String client_use_code) {
		this.client_use_code = client_use_code;
	}
	@Override
	public String toString() {
		return "UserOobDto [access_token=" + access_token + ", scope=" + scope + ", client_use_code=" + client_use_code
				+ "]";
	}
	
	
	
}