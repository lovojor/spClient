package com.oncedev.service.conf;



public class SharePointProperties {

    private String username;
    private String password;
    private String endpointToken;
    private String endpointDomain;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getEndpointToken() {
		return endpointToken;
	}

	public void setEndpointToken(String endpoint) {
		this.endpointToken = endpoint;
	}

	public String getEndpointDomain() {
		return endpointDomain;
	}

	public void setEndpointDomain(String endpointDomain) {
		this.endpointDomain = endpointDomain;
	}
}