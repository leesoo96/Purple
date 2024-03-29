package com.purple.demo.model.security;

import java.util.Map;

import com.purple.demo.config.oauth.SocialType;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {
	public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }
	
	@Override
    public String getId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {        
        return null;
    }

	@Override
	public String getProvider() {		
		return SocialType.NAVER.getValue();
	}
}
