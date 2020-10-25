package com.agileengine.api;

import java.util.Objects;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
public class ImageUrlApi {
    private String croppedPictureUrl;
    private String fullPictureUrl;

    public ImageUrlApi() {
    }

    public ImageUrlApi(String croppedPictureUrl, String fullPictureUrl) {
        this.croppedPictureUrl = croppedPictureUrl;
        this.fullPictureUrl = fullPictureUrl;
    }

    public String getCroppedPictureUrl() {
        return croppedPictureUrl;
    }

    public void setCroppedPictureUrl(String croppedPictureUrl) {
        this.croppedPictureUrl = croppedPictureUrl;
    }

    public String getFullPictureUrl() {
        return fullPictureUrl;
    }

    public void setFullPictureUrl(String fullPictureUrl) {
        this.fullPictureUrl = fullPictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageUrlApi that = (ImageUrlApi) o;
        return Objects.equals(croppedPictureUrl, that.croppedPictureUrl) &&
                Objects.equals(fullPictureUrl, that.fullPictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(croppedPictureUrl, fullPictureUrl);
    }

    @Override
    public String toString() {
        return "ImageUrlApi{" +
                "croppedPictureUrl='" + croppedPictureUrl + '\'' +
                ", fullPictureUrl='" + fullPictureUrl + '\'' +
                '}';
    }
}
