package com.agileengine.client.imagestorage;

import com.agileengine.api.imagestorage.ImageMetadataApi;
import com.agileengine.api.imagestorage.PictureListApi;
import com.agileengine.client.AbstractClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
@Component
public class ImageStorageClient extends AbstractClient {

    @Value("${image.storage.host}")
    private String host;

    private String pictureListUrl;
    private String imageByIdUrl;

    public ImageStorageClient(RestTemplateBuilder restTemplateBuilder, ImageStorageAuthClient authClient) {
        super(restTemplateBuilder, authClient);
    }

    @PostConstruct
    public void init() {
        pictureListUrl = host + "/images?page={pageNumber}";
        imageByIdUrl =  host + "/images/{imageId}";
    }

    public ImageMetadataApi getImageMetadata(String imageId) {
        return performGetRequest(imageByIdUrl, ImageMetadataApi.class, imageId);
    }

    public PictureListApi getPictureList(int pageNumber) {
        return performGetRequest(pictureListUrl, PictureListApi.class, pageNumber);
    }
}
