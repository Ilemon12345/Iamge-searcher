package com.agileengine.api.imagestorage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
public class ImageMetadataApi {

    private String id;
    private String author;
    private String camera;
    private String tags;

    @JsonProperty("cropped_picture")
    private String croppedPictureUrl;

    @JsonProperty("full_picture")
    private String fullPictureUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
        ImageMetadataApi that = (ImageMetadataApi) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(author, that.author) &&
                Objects.equals(camera, that.camera) &&
                Objects.equals(tags, that.tags) &&
                Objects.equals(croppedPictureUrl, that.croppedPictureUrl) &&
                Objects.equals(fullPictureUrl, that.fullPictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, camera, tags, croppedPictureUrl, fullPictureUrl);
    }

    @Override
    public String toString() {
        return "ImageMetadataApi{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", camera='" + camera + '\'' +
                ", tags=" + tags +
                ", croppedPictureUrl='" + croppedPictureUrl + '\'' +
                ", fullPictureUrl='" + fullPictureUrl + '\'' +
                '}';
    }
}
