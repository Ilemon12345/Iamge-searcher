package com.agileengine.client;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
public interface AuthClient {

    String getToken();

    void auth();
}
