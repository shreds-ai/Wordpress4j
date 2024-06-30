package com.example.wordpressclone.application.ports;

import com.example.wordpressclone.application.dtos.MediaDTO;
import java.util.List;
import java.io.IOException;
import java.lang.IllegalArgumentException;

/**
 * Interface for defining methods for interacting with external media services.
 * This interface facilitates the fetching of media item details from an external service.
 */
public interface ExternalMediaServicePort {

    /**
     * Fetches detailed information about media items from an external service using the provided media ID.
     * @param mediaId the media ID to fetch details for, must not be null
     * @return a list of MediaDTO containing media details
     */
    List<MediaDTO> fetchExternalMediaDetails(String mediaId);

    /**
     * Handles exceptions specifically related to media retrieval operations.
     * @param e the specific exception to handle, can be IOException or IllegalArgumentException
     */
    void handleExternalMediaError(Exception e);

    /**
     * Logs errors based on the context of the failure during interactions with external services.
     * @param e the exception to log, must not be null
     * @param context the context of the error, provides additional detail for error logging
     */
    void logExternalServiceErrors(Exception e, String context);
}