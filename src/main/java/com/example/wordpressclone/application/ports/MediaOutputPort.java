package com.example.wordpressclone.application.ports;

import com.example.wordpressclone.application.dtos.MediaDTO;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Interface for output operations related to media items, defining the necessary methods for output interactions.
 */
public interface MediaOutputPort {

    /**
     * Outputs a list of media items.
     * This method formats data for presentation or further external API calls, ensuring it meets specific client or service requirements.
     *
     * @param mediaItems the list of media DTOs to be output
     * @return the list of formatted media DTOs
     */
    List<MediaDTO> outputMediaItems(@NotNull List<MediaDTO> mediaItems);

    /**
     * Outputs a single media item.
     * This method formats the media item's data for presentation or external output, adapting it to specific client or service formats.
     *
     * @param mediaItem the media DTO to be output
     * @return the formatted media DTO
     */
    MediaDTO outputSingleMediaImage(@NotNull MediaDTO mediaItem);

    /**
     * Handles errors during the output process of media items, ensuring that all exceptions are managed appropriately.
     *
     * @param e the exception to handle
     */
    void handleOutputError(@NotNull Exception e);
}