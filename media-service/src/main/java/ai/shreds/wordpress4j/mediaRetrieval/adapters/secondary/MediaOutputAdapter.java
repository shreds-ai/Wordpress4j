package ai.shreds.wordpress4j.mediaRetrieval.adapters.secondary;

import ai.shreds.wordpress4j.mediaRetrieval.application.dtos.MediaDTO;
import ai.shreds.wordpress4j.mediaRetrieval.application.ports.MediaOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class MediaOutputAdapter implements MediaOutputPort {

    private static final Logger logger = LoggerFactory.getLogger(MediaOutputAdapter.class);

    @Override
    public List<MediaDTO> outputMediaItems(List<MediaDTO> mediaItems) {
        mediaItems.forEach(media -> {
            if (media != null) logger.info("Outputting media: " + media);
        });
        return mediaItems;
    }

    @Override
    public MediaDTO outputSingleMediaImage(MediaDTO mediaItem) {
        if (mediaItem != null) {
            logger.info("Outputting single media item: " + mediaItem);
        }
        return mediaItem;
    }

    @Override
    public void handleOutputError(Exception e) {
        logger.error("Error during media output: " + e.getMessage(), e);
    }
}