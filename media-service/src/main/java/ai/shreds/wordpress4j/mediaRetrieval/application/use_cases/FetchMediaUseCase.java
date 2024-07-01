package ai.shreds.wordpress4j.mediaRetrieval.application.use_cases;

import ai.shreds.wordpress4j.mediaRetrieval.application.dtos.MediaDTO;
import ai.shreds.wordpress4j.mediaRetrieval.domain.exceptions.MediaItemNotFoundException;
import ai.shreds.wordpress4j.mediaRetrieval.domain.services.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FetchMediaUseCase {

    private static final Logger logger = LoggerFactory.getLogger(FetchMediaUseCase.class);
    private final MediaService mediaService;

    public FetchMediaUseCase(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public List<MediaDTO> fetchAllMedia() throws MediaItemNotFoundException {
        logger.info("Starting to fetch all media items");
        try {
            List<MediaDTO> mediaItems = mediaService.fetchAllMediaItems();
            logger.debug("Fetched " + mediaItems.size() + " media items.");
            return mediaItems;
        } catch (Exception e) {
            logger.error("Media service is currently unavailable", e);
            throw new MediaItemNotFoundException("Media service is unavailable", e);
        }
    }
}