package com.example.wordpressclone.adapters.primary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.example.wordpressclone.application.dtos.MediaDTO;
import com.example.wordpressclone.application.use_cases.FetchMediaUseCase;
import com.example.wordpressclone.application.use_cases.FetchMediaByIdUseCase;
import com.example.wordpressclone.domain.exceptions.MediaItemNotFoundException;
import com.example.wordpressclone.domain.exceptions.DatabaseConnectionException;
import org.hibernate.validator.constraints.Range;

@RestController
public class MediaController {

    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);
    private final FetchMediaUseCase fetchMediaUseCase;
    private final FetchMediaByIdUseCase fetchMediaByIdUseCase;

    public MediaController(FetchMediaUseCase fetchMediaUseCase, FetchMediaByIdUseCase fetchMediaByIdUseCase) {
        this.fetchMediaUseCase = fetchMediaUseCase;
        this.fetchMediaByIdUseCase = fetchMediaByIdUseCase;
    }

    @GetMapping("/media")
    public ResponseEntity<List<MediaDTO>> getAllMediaItems() {
        List<MediaDTO> mediaItems = fetchMediaUseCase.fetchAllMedia();
        return ResponseEntity.ok(mediaItems);
    }

    @GetMapping("/media/{id}")
    public ResponseEntity<MediaDTO> getMediaItemById(@NotNull @Range(min = 1) @PathVariable Long id) {
        MediaDTO mediaItem = fetchMediaByIdUseCase.execute(id);
        return ResponseEntity.ok(mediaItem);
    }

    @ExceptionHandler(MediaItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleMediaNotFound(MediaItemNotFoundException e) {
        logger.error("Media item not found: ", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Media item not found");
    }

    @ExceptionHandler(DatabaseConnectionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleDatabaseError(DatabaseConnectionException e) {
        logger.error("Database connection error: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database connection error");
    }
}