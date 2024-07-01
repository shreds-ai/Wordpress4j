package ai.shreds.wordpress4j.mediaRetrieval.adapters.primary;

import ai.shreds.wordpress4j.mediaRetrieval.application.dtos.MediaDTO;
import ai.shreds.wordpress4j.mediaRetrieval.application.use_cases.FetchMediaByIdUseCase;
import ai.shreds.wordpress4j.mediaRetrieval.application.use_cases.FetchMediaUseCase;
import ai.shreds.wordpress4j.mediaRetrieval.domain.exceptions.MediaItemNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MediaController {

    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);
    private final FetchMediaUseCase fetchMediaUseCase;
    private final FetchMediaByIdUseCase fetchMediaByIdUseCase;

    @Autowired
    public MediaController(FetchMediaUseCase fetchMediaUseCase, FetchMediaByIdUseCase fetchMediaByIdUseCase) {
        this.fetchMediaUseCase = fetchMediaUseCase;
        this.fetchMediaByIdUseCase = fetchMediaByIdUseCase;
    }

    @GetMapping("/media")
    public ResponseEntity<List<MediaDTO>> getAllMediaItems() throws MediaItemNotFoundException {
        List<MediaDTO> mediaItems = fetchMediaUseCase.fetchAllMedia();
        return ResponseEntity.ok(mediaItems);
    }

    @GetMapping("/media/{id}")
    public ResponseEntity<MediaDTO> getMediaItemById(@NotNull @Range(min = 1) @PathVariable Long id) throws MediaItemNotFoundException {
        MediaDTO mediaItem = fetchMediaByIdUseCase.execute(id);
        return ResponseEntity.ok(mediaItem);
    }

    @ExceptionHandler(MediaItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleMediaNotFound(MediaItemNotFoundException e) {
        logger.error("Media item not found: ", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Media item not found");
    }

}