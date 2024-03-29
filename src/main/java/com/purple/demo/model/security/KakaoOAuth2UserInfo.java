package com.purple.demo.model.security;

import java.util.Map;

import com.purple.demo.config.oauth.SocialType;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
	public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }
	
	@Override
    public String getId() {		
		int id = (int)attributes.get("id");
        return Integer.toString(id);
    }

    @Override
    public String getName() {
        return (String) attributes.get("nickname");
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
		return SocialType.KAKAO.getValue();
	}

  

}
