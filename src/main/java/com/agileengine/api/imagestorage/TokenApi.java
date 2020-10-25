package com.agileengine.api.imagestorage;

import java.util.Objects;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
public class TokenApi {

    private boolean auth;
    private String token;

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenApi tokenApi = (TokenApi) o;
        return auth == tokenApi.auth &&
                Objects.equals(token, tokenApi.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auth, token);
    }

    @Override
    public String toString() {
        return "TokenApi{" +
                "auth=" + auth +
                ", token='" + token + '\'' +
                '}';
    }
}
