package ai.shreds.wordpress4j.tagRetrieval.adapters.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, HttpStatus status, LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String formatDetails() {
        return String.format("Time: %s\nStatus: %d [%s]\nMessage: %s", timestamp, status.value(), status.getReasonPhrase(), message);
    }

}
