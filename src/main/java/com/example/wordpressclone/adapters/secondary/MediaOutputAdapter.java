package com.example.wordpressclone.adapters.secondary;

import com.example.wordpressclone.application.dtos.MediaDTO;
import com.example.wordpressclone.application.ports.MediaOutputPort;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.io.IOException;
import java.sql.SQLException;

@Component
public class MediaOutputAdapter implements MediaOutputPort {

    @Autowired
    private Logger logger;

    @Override
    public CompletableFuture<List<MediaDTO>> outputMediaItems(List<MediaDTO> mediaItems) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                mediaItems.forEach(media -> {
                    if (media != null) logger.info("Outputting media: " + media);
                });
            } catch (IOException e) {
                logger.error("IO Exception during media output: ", e);
            } catch (SQLException e) {
                logger.error("SQL Exception during media output: ", e);
            }
            return mediaItems;
        });
    }

    @Override
    public CompletableFuture<MediaDTO> outputSingleMediaItem(MediaDTO mediaItem) {
        return CompletableFuture.supplyAsync(() -> {
            if (mediaItem != null) {
                try {
                    logger.info("Outputting single media item: " + mediaItem);
                } catch (Exception e) {
                    logger.error("Error during single media output: ", e);
                }
                return mediaItem;
            }
            return null;
        });
    }

    @Override
    public void handleOutputError(Exception e) {
        logger.error("Error during media output: ", e);
    }

    public void outputMedia(MediaDTO media) {
        if (media != null) {
            try {
                logger.info("Media output: " + media);
            } catch (Exception e) {
                logger.error("Error during media output: ", e);
            }
        }
    }

    public void outputImage(MediaDTO media) {
        if (media != null && "image".equals(media.getFileType())) {
            try {
                logger.info("Outputting image: " + media);
            } catch (Exception e) {
                logger.error("Error during image output: ", e);
            }
        }
    }

    public void outputVideo(MediaDTO media) {
        if (media != null && "video".equals(media.getFileType())) {
            try {
                logger.info("Outputting video: " + media);
            } catch (Exception e) {
                logger.error("Error during video output: ", e);
            }
        }
    }

    public void outputDocument(MediaDTO media) {
        if (media != null && "document".equals(media.getFileType())) {
            try {
                logger.info("Outputting document: " + media);
            } catch (Exception e) {
                logger.error("Error during document output: ", e);
            }
        }
    }

    public CompletableFuture<Void> outputMediaAsync(MediaDTO media) {
        return CompletableFuture.runAsync(() -> outputMedia(media));
    }
}