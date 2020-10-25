package com.agileengine.client.imagestorage;

import com.agileengine.api.imagestorage.ApiKey;
import com.agileengine.api.imagestorage.TokenApi;
import com.agileengine.client.AuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
@Component
public class ImageStorageAuthClient implements AuthClient {

    @Value("${image.storage.host}")
    private String host;

    @Value("${image.storage.auth.api.key}")
    private String apiKey;

    private String authUrl;

    private volatile TokenApi token;

    private RestTemplate restTemplate;

    public ImageStorageAuthClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        authUrl = host + "/auth";

        auth();
    }

    public String getToken() {
        return token.getToken();
    }

    public void auth() {
        ApiKey key = new ApiKey();
        key.setApiKey(apiKey);

        token = restTemplate.postForObject(authUrl, key, TokenApi.class);
    }
}
