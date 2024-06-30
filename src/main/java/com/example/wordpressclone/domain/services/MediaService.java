package com.example.wordpressclone.domain.services;

import com.example.wordpressclone.domain.entities.MediaItemEntity;
import com.example.wordpressclone.domain.ports.MediaRepositoryPort;
import com.example.wordpressclone.application.dtos.MediaDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling business logic related to media retrieval.
 */
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
    public List<MediaDTO> fetchAllMedia...  (truncated for brevity)