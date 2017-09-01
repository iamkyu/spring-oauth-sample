package io.iamkyu.kakao;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

public class KakaoConnectionFactory extends OAuth2ConnectionFactory<Kakao> {

    public KakaoConnectionFactory(String providerId, OAuth2ServiceProvider<Kakao> serviceProvider, ApiAdapter<Kakao> apiAdapter) {
        super(providerId, serviceProvider, apiAdapter);
    }
}
