package com.agileengine.endpoint;

import com.agileengine.api.ImageUrlApi;
import com.agileengine.service.ImageMetadataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
@RestController
@RequestMapping("/image-searcher")
public class ImageMetadataEndpoint {

    private ImageMetadataService imageMetadataService;

    public ImageMetadataEndpoint(ImageMetadataService imageMetadataService) {
        this.imageMetadataService = imageMetadataService;
    }

    @GetMapping
    @RequestMapping("/search/{keyWord}")
    public List<ImageUrlApi> getMetadata(@PathVariable("keyWord") String keyWord) {
        return imageMetadataService
                .getMetadataListByKeyWord(keyWord)
                .stream()
                .map(metadata -> new ImageUrlApi(metadata.getCroppedPictureUrl(), metadata.getFullPictureUrl()))
                .collect(Collectors.toList());
    }
}
