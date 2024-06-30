package com.example.wordpressclone.application.use_cases;

import com.example.wordpressclone.domain.services.MediaService;
import com.example.wordpressclone.domain.exceptions.MediaItemNotFoundException;
import com.example.wordpressclone.domain.exceptions.DatabaseConnectionException;
import com.example.wordpressclone.application.dtos.MediaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FetchMediaByIdUseCase {

    private static final Logger logger = LoggerFactory.getLogger(FetchMediaByIdUseCase.class);
    private final MediaService mediaService;

    public FetchMediaByIdUseCase(MediaService mediaService) {
        if (mediaService == null) {
            throw new IllegalArgumentException("MediaService must not be null");
        }
        this.mediaService = mediaService;
    }

    public MediaDTO execute(Long id) throws IllegalArgumentException, MediaItemNotFoundException, DatabaseConnectionException {
        logger.info("Executing fetchMediaItemById with ID: {}", id);
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        }

        try {
            MediaDTO mediaDTO = mediaService.fetchMediaItemById(id);
            logger.info("Successfully retrieved media item with ID: {}", id);
            return mediaDTO;
        } catch (MediaItemNotFoundException e) {
            logger.error("Media item not found for ID: {}", id, e);
            throw e;
        } catch (DatabaseConnectionException e) {
            logger.error("Database connection error occurred while fetching media item with ID: {}", id, e);
            throw new DatabaseConnectionException("Database connection error.", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching media item with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred.", e);
        }
    }
}