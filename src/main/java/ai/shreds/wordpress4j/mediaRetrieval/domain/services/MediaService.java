package ai.shreds.wordpress4j.mediaRetrieval.domain.services;

import ai.shreds.wordpress4j.mediaRetrieval.domain.entities.MediaItemEntity;
import ai.shreds.wordpress4j.mediaRetrieval.domain.exceptions.MediaItemNotFoundException;
import ai.shreds.wordpress4j.mediaRetrieval.domain.ports.MediaRepositoryPort;
import ai.shreds.wordpress4j.mediaRetrieval.application.dtos.MediaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling business logic related to media retrieval.
 */
@Service
public class MediaService {

    private final MediaRepositoryPort mediaRepositoryPort;

    /**
     * Constructs a new MediaService with the specified MediaRepositoryPort.
     * @param mediaRepositoryPort the media repository port
     */
    public MediaService(MediaRepositoryPort mediaRepositoryPort) {
        this.mediaRepositoryPort = mediaRepositoryPort;
    }

    /**
     * Fetches all media items from the repository and maps them to MediaDTO objects.
     * @return a list of MediaDTO objects
     */
    public List<MediaDTO> fetchAllMediaItems() {
        return mediaRepositoryPort.findAllMediaItems().stream()
                .map(MediaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single media item by ID from the repository and maps it to a MediaDTO object.
     * If the item is not found, throws MediaItemNotFoundException.
     * @param id the ID of the media item
     * @return the MediaDTO object
     * @throws MediaItemNotFoundException if the media item is not found
     */
    public MediaDTO fetchMediaItemById(Long id) throws MediaItemNotFoundException {
        return mediaRepositoryPort.findMediaItemById(id)
                .map(MediaDTO::fromEntity)
                .orElseThrow(() -> new MediaItemNotFoundException("Media item not found with id: " + id));
    }
}