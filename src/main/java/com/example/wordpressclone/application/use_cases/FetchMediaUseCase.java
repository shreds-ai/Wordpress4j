package com.example.wordpressclone.application.use_cases;

import com.example.wordpressclone.application.dtos.MediaDTO;
import com.example.wordpressclone.domain.exceptions.DatabaseConnectionException;
import com.example.wordpressclone.domain.exceptions.MediaDataAccessException;
import com.example.wordpressclone.domain.exceptions.MediaServiceUnavailableException;
import com.example.wordpressclone.domain.services.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class FetchMediaUseCase {

    private static final Logger logger = LoggerFactory.getLogger(FetchMediaUseCase.class);
    private final MediaService mediaService;

    public FetchMediaUseCase(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public List<MediaDTO> fetchAllMedia() throws MediaDataAccessException, DatabaseConnectionException, MediaServiceUnavailableException {
        logger.info("Starting to fetch all media items");
        try {
            List<MediaDTO> mediaItems = mediaService.fetchAllMediaItems();
            if (mediaItems == null) {
                logger.error("No media items found, potentially empty list.");
                throw new MediaDataAccessException("No media items available.");
            }
            logger.debug("Fetched " + mediaItems.size() + " media items.");
            return mediaItems;
        } catch (Exception e) {
            logger.error("Media service is currently unavailable", e);
            throw new MediaServiceUnavailableException("Media service is unavailable", e);
        }
    }
}