package com.agileengine.api.imagestorage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
public class PictureApi {
    private String id;

    @JsonProperty("cropped_picture")
    private String croppedPictureUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCroppedPictureUrl() {
        return croppedPictureUrl;
    }

    public void setCroppedPictureUrl(String croppedPictureUrl) {
        this.croppedPictureUrl = croppedPictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PictureApi that = (PictureApi) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(croppedPictureUrl, that.croppedPictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, croppedPictureUrl);
    }

    @Override
    public String toString() {
        return "PictureApi{" +
                "id='" + id + '\'' +
                ", croppedPictureUrl='" + croppedPictureUrl + '\'' +
                '}';
    }
}
