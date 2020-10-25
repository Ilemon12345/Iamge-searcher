package com.agileengine.service;

import com.agileengine.api.imagestorage.ImageMetadataApi;
import com.agileengine.api.imagestorage.PictureApi;
import com.agileengine.api.imagestorage.PictureListApi;
import com.agileengine.client.imagestorage.ImageStorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author yevhenii yurkov
 * @since 0.0.1
 */
@Service
public class ImageMetadataService {

    @Value("${image.storage.cache.reload-period}")
    private int reloadPeriod = 10;

    private TaskScheduler taskScheduler;

    private ImageStorageClient imageStorageClient;

    private AtomicReference<ImageMetadataCache> imageMetadataCache;

    public ImageMetadataService(ImageStorageClient imageStorageClient, TaskScheduler taskScheduler) {
        this.imageStorageClient = imageStorageClient;
        this.taskScheduler = taskScheduler;

        imageMetadataCache = new AtomicReference<>(new ImageMetadataCache());
    }

    @PostConstruct
    public void init() {
        imageMetadataCache.get().init();
        startSchedulerToReloadMetadataCache();
    }

    public List<ImageMetadataApi> getMetadataListByKeyWord(String keyWord) {
        ImageMetadataCache cache = imageMetadataCache.get();
        List<String> metadataIds = cache.getMetadataIdsByKeyWord(keyWord);

        if (metadataIds == null || metadataIds.isEmpty()) {
            return Collections.emptyList();
        }

        return cache.getMetadataListByIds(metadataIds);
    }

    private void startSchedulerToReloadMetadataCache() {
        taskScheduler.scheduleWithFixedDelay(() -> {
            ImageMetadataCache newCache = new ImageMetadataCache();
            newCache.init();
            imageMetadataCache.set(newCache);
        }, TimeUnit.MINUTES.toMillis(reloadPeriod));
    }

    class ImageMetadataCache {
        private final Map<String, ImageMetadataApi> imageMetadataById = new HashMap<>();
        private final Map<String, List<String>> imageMetadataIdsByKeyWord = new HashMap<>();

        void init() {
            PictureListApi pictureList;
            int page = 1;

            do {
                pictureList = imageStorageClient.getPictureList(page);
                getAndAddMetadataIntoCacheByPictureList(pictureList);
                page++;
            } while (pictureList.isHasMore());
        }

        List<String> getMetadataIdsByKeyWord(String keyWord) {
            return imageMetadataIdsByKeyWord.get(keyWord);
        }

        List<ImageMetadataApi> getMetadataListByIds(List<String> ids) {
            return ids
                    .stream()
                    .map(imageMetadataById::get)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        private void getAndAddMetadataIntoCacheByPictureList(PictureListApi pictureList) {
            pictureList
                    .getPictures()
                    .stream()
                    .map(PictureApi::getId)
                    .forEach(this::getAndAddMetadataIntoCache);
        }

        private void getAndAddMetadataIntoCache(String imageId) {
            ImageMetadataApi metadata = imageStorageClient.getImageMetadata(imageId);
            String metadataId = metadata.getId();

            imageMetadataById.put(metadata.getId(), metadata);
            addEntryToImageMetadataIdsByKeyWord(metadata.getId(), metadata.getId());
            addEntryToImageMetadataIdsByKeyWord(metadata.getAuthor(), metadataId);
            addEntryToImageMetadataIdsByKeyWord(metadata.getCamera(), metadataId);
            addEntryToImageMetadataIdsByKeyWord(metadata.getCroppedPictureUrl(), metadataId);
            addEntryToImageMetadataIdsByKeyWord(metadata.getFullPictureUrl(), metadataId);
            addTagsASKeyWords(metadata.getTags(), metadataId);
        }

        private void addTagsASKeyWords(String tags, String id) {
            for (String tag : tags.split(" ")) {
                if (!tag.isEmpty()) {
                    String tagWithoutSharpSymbol = tag.substring(1);
                    addEntryToImageMetadataIdsByKeyWord(tagWithoutSharpSymbol, id);
                }
            }
        }

        private void addEntryToImageMetadataIdsByKeyWord(String keyWord, String id) {
            List<String> metadataIds = imageMetadataIdsByKeyWord.computeIfAbsent(keyWord, k -> new ArrayList<>());
            metadataIds.add(id);
        }
    }
}
