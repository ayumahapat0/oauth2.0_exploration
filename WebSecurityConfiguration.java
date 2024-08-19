package com.example.ingrammicro.HP.config;

import com.example.ingrammicro.HP.Token.TokenRequester;
import com.example.ingrammicro.HP.Token.TokenResponse;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class WebSecurityConfiguration {

    @Autowired
    private TokenRequester requester;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        TokenResponse response = requester.getAccessToken();
        return requestTemplate -> {requestTemplate.header("Authorization", "Bearer " + response.getAccessToken());};
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomerErrorDecoderHPIngram();
    }

    @Bean
    public HPIngramResponseInterceptor responseInterceptor(){return new HPIngramResponseInterceptor();}
}
