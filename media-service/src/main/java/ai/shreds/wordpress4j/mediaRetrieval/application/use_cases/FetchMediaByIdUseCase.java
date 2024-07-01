package ai.shreds.wordpress4j.mediaRetrieval.application.use_cases;

import ai.shreds.wordpress4j.mediaRetrieval.domain.services.MediaService;
import ai.shreds.wordpress4j.mediaRetrieval.domain.exceptions.MediaItemNotFoundException;
import ai.shreds.wordpress4j.mediaRetrieval.application.dtos.MediaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FetchMediaByIdUseCase {

    private static final Logger logger = LoggerFactory.getLogger(FetchMediaByIdUseCase.class);
    private final MediaService mediaService;

    public FetchMediaByIdUseCase(MediaService mediaService) {
        if (mediaService == null) {
            throw new IllegalArgumentException("MediaService must not be null");
        }
        this.mediaService = mediaService;
    }

    public MediaDTO execute(Long id) throws IllegalArgumentException, MediaItemNotFoundException {
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
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching media item with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred.", e);
        }
    }
}