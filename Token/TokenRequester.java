package com.example.ingrammicro.HP.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class TokenRequester {

    @Value("${Oauth2TokenUri}")
    String uri;

    @Value("${Oauth2grantType}")
    String grant_type;

    @Value("${Oauth2ClientId}")
    String client_id;

    @Value("${Oauth2ClientSecret}")
    String client_secret;

    @Value("${Oauth2Scope}")
    String scope;


    public TokenResponse getAccessToken(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", grant_type);
        map.add("client_id", client_id);
        map.add("client_secret", client_secret);
        map.add("scope", scope);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);


        ResponseEntity<TokenResponse> response = restTemplate.exchange(uri, HttpMethod.POST, entity, TokenResponse.class);

        return response.getBody();
    }

}
