package com.agileengine.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
public class AbstractClient {

    private RestTemplateBuilder restTemplateBuilder;
    private AuthClient authClient;

    private RestTemplate restTemplate;

    public AbstractClient(RestTemplateBuilder restTemplateBuilder, AuthClient authClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.authClient = authClient;

        restTemplate = configureRestTemplate();
    }

    protected <T> T performPostRequest(String url, Object request, Class<T> responseClass) {
        return restTemplate.postForObject(url, request, responseClass);
    }

    protected <T> T performGetRequest(String url, Class<T> responseClass, Object... variables) {
        return restTemplate.getForObject(url, responseClass, variables);
    }

    protected RestTemplate configureRestTemplate() {
        return restTemplateBuilder
                .additionalInterceptors((request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + authClient.getToken());

                    ClientHttpResponse response = execution.execute(request, body);
                    if (response.getStatusCode() == UNAUTHORIZED) {
                        authClient.auth();
                    }

                    request.getHeaders().add("Authorization", "Bearer " + authClient.getToken());
                    return execution.execute(request, body);
                }).build();
    }
}
