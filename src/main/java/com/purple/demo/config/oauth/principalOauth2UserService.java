package com.purple.demo.config.oauth;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.nimbusds.oauth2.sdk.OAuth2Error;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.model.security.OAuth2UserInfo;
import com.purple.demo.model.security.OAuth2UserInfoFactory;
import com.purple.demo.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails.UserInfoEndpoint;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;


@Component
public class principalOauth2UserService extends DefaultOAuth2UserService{
    private static final String MISSING_USER_INFO_URI_ERROR_CODE = "missing_user_info_uri";
	private static final String MISSING_USER_NAME_ATTRIBUTE_ERROR_CODE = "missing_user_name_attribute";
	private static final String INVALID_USER_INFO_RESPONSE_ERROR_CODE = "invalid_user_info_response";

    private static final ParameterizedTypeReference<Map<String, Object>> PARAMETERIZED_RESPONSE_TYPE = new ParameterizedTypeReference<Map<String, Object>>() {
	};

	private Converter<OAuth2UserRequest, RequestEntity<?>> requestEntityConverter = new OAuth2UserRequestEntityConverter();
	private RestOperations restOperations;
    
    @Autowired
	private UserServiceImpl myUserService;

    public principalOauth2UserService() {
		RestTemplate restTemplate = new RestTemplate();  // RestTemplate => REST API 호출이후 응답을 받을 때까지 기다리는 동기방식이다
		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler()); // OAuth2ErrorResponseErrorHandler =>  OAuth 2.0 에러를 (400 Bad Request) 처리
		this.restOperations = restTemplate;
	}
    
    //구글로부터 받은 userRequest 데이터 후처리 돠는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)throws OAuth2AuthenticationException{

        Assert.notNull(userRequest, "userRequest cannot be null");
        //아래랑 위에랑 같은말입니다
        /*if (userRequest == null) {
            throw new IllegalArgumentException("userRequest cannot be null");
        }*/  

        //끝점
        UserInfoEndpoint uiep = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint();
        System.out.println("uiep : " +  uiep);
       
        String uri = uiep.getUri();  //url
        /*if (!StringUtils.hasText(uri)) {
			OAuth2Error oauth2Error = new OAuth2Error(MISSING_USER_INFO_URI_ERROR_CODE,
					"Missing required UserInfo Uri in UserInfoEndpoint for Client Registration: "
							+ userRequest.getClientRegistration().getRegistrationId(), // 
					null);
			throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString());
		}*/
		System.out.println("uri : " +  uri);

        String userNameAttributeName = uiep.getUserNameAttributeName();  // google : sub , facebook 외 : id 를 가져오기 위함이다
		System.out.println("userNameAttributeName : " +  uiep.getUserNameAttributeName());
		/*if (!StringUtils.hasText(userNameAttributeName)) {
			OAuth2Error oauth2Error = new OAuth2Error(MISSING_USER_NAME_ATTRIBUTE_ERROR_CODE,
					"Missing required \"user name\" attribute name in UserInfoEndpoint for Client Registration: "
							+ userRequest.getClientRegistration().getRegistrationId(),
					null);
			throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString());
		}*/

        RequestEntity<?> request = this.requestEntityConverter.convert(userRequest);
		ResponseEntity<Map<String, Object>> response;
		/*try {*/
			response = this.restOperations.exchange(request, PARAMETERIZED_RESPONSE_TYPE);
		/*} catch (OAuth2AuthorizationException ex) {
			OAuth2Error oauth2Error = ex.getError();
			StringBuilder errorDetails = new StringBuilder();
			errorDetails.append("Error details: [");
			errorDetails.append("UserInfo Uri: ")
					.append(userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri());
			errorDetails.append(", Error Code: ").append(oauth2Error.getErrorCode());
			if (oauth2Error.getDescription() != null) {
				errorDetails.append(", Error Description: ").append(oauth2Error.getDescription());
			}
			errorDetails.append("]");
			oauth2Error = new OAuth2Error(INVALID_USER_INFO_RESPONSE_ERROR_CODE,
					"An error occurred while attempting to retrieve the UserInfo Resource: " + errorDetails.toString(),
					null);
			throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString(), ex);
		} catch (RestClientException ex) {
			OAuth2Error oauth2Error = new OAuth2Error(INVALID_USER_INFO_RESPONSE_ERROR_CODE,
					"An error occurred while attempting to retrieve the UserInfo Resource: " + ex.getMessage(), null);
			throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString(), ex);
		}*/

        Map<String, Object> userAttributes = getUserAttributes(response);  //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스입니다. 
		Set<GrantedAuthority> authorities = new LinkedHashSet<>();
		authorities.add(new OAuth2UserAuthority(userAttributes));
		OAuth2AccessToken token = userRequest.getAccessToken();
		for (String authority : token.getScopes()) {
			authorities.add(new SimpleGrantedAuthority("SCOPE_" + authority));
			System.out.println("token : " + token.getTokenValue());
        }

        OAuth2User oAuth2User = new DefaultOAuth2User(authorities, userAttributes, userNameAttributeName);

        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the
            // OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }
    
    //커스터마이징 할 수 있는 부분
	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
				oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		/*
		//카카오 경우 이메일을 받기 어려움
		if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		 */		
		
		UserPrincipal user = (UserPrincipal) myUserService.loadUserByUsername(oAuth2UserInfo.getProvider(),
				oAuth2UserInfo.getId());
		if (user == null ) { // insert
			UserPrincipal userjoin = new UserPrincipal();
			userjoin.setUser_id(oAuth2UserInfo.getId());
			userjoin.setUser_email(oAuth2UserInfo.getEmail());
			// p.setUser_profileimg(oAuth2UserInfo.getImageUrl());
			userjoin.setUser_name(oAuth2UserInfo.getName());
			userjoin.setUser_auth("ROLE_USER");
			userjoin.setOauth_id(oAuth2UserInfo.getId());
			userjoin.setOauth_typ(oAuth2UserInfo.getProvider());
			System.out.println("oAuth2UserInfo.getProvider()oAuth2UserInfo.getProvider()oAuth2UserInfo.getProvider():    " + oAuth2UserInfo.getProvider());
			myUserService.join(userjoin);
			 

		}
		
		return (UserPrincipal) myUserService.loadUserByUsername(oAuth2UserInfo.getProvider(),
		oAuth2UserInfo.getId());
	}


    private Map<String, Object> getUserAttributes(ResponseEntity<Map<String, Object>> response) {
		Map<String, Object> userAttributes = response.getBody();
		if (userAttributes.containsKey("response")) { 	// 네이버는 HTTP response body에 response 안에 id 값을 포함한 유저정보를 넣어주므로 유저정보를 빼내기 위한 작업을 함
			LinkedHashMap responseData = (LinkedHashMap) userAttributes.get("response");
			userAttributes.putAll(responseData);
			userAttributes.remove("response");
		} else if(userAttributes.containsKey("properties")) { //카카오는 properties 안에 nickname값이 포함되어 있음
			LinkedHashMap propertiesData = (LinkedHashMap) userAttributes.get("properties");
			userAttributes.putAll(propertiesData);
			userAttributes.remove("properties");
		}
		return userAttributes;
	}
}
