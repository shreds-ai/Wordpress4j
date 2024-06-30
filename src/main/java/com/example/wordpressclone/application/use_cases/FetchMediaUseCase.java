package com.example.wordpressclone.application.use_cases;

import com.example.wordpressclone.application.dtos.MediaDTO;
import com.example.wordpressclone.domain.exceptions.MediaItemNotFoundException;
import com.example.wordpressclone.domain.services.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

public class FetchMediaUseCase {

    private static final Logger logger = LoggerFactory.getLogger(FetchMediaUseCase.class);
    private final MediaService mediaService;

    public FetchMediaUseCase(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public List<MediaDTO> fetchAllMedia() throws MediaItemNotFoundException {
        logger.info("Starting to fetch all media items");
        try {
            List<MediaDTO> mediaItems = Optional.ofNullable(mediaService.fetchAllMediaItems()).orElseThrow(() -> new MediaItemNotFoundException("No media items available."));
            logger.debug("Fetched " + mediaItems.size() + " media items.");
            return mediaItems;
        } catch (Exception e) {
            logger.error("Media service is currently unavailable", e);
            throw new MediaItemNotFoundException("Media service is unavailable", e);
        }
    }
}