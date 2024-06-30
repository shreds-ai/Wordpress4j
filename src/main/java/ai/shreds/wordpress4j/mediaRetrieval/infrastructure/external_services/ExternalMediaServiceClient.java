package ai.shreds.wordpress4j.mediaRetrieval.infrastructure.external_services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;
import java.net.SocketTimeoutException;
import ai.shreds.wordpress4j.mediaRetrieval.application.dtos.MediaDTO;
import ai.shreds.wordpress4j.mediaRetrieval.application.ports.ExternalMediaServicePort;

/**
 * Client to interact with external media services.
 *
 * Provides methods to fetch media data from an external service.
 */
@Component
public class ExternalMediaServiceClient implements ExternalMediaServicePort {

    private static final Logger logger = LoggerFactory.getLogger(ExternalMediaServiceClient.class);
    private final RestTemplate restTemplate;
    private final String serviceUrl = "http://external-media-service.com";

    public ExternalMediaServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private List<MediaDTO> handleExternalServiceDown(String externalMediaId) {
        logger.info("External media service is currently unavailable, returning empty list.");
        return Collections.emptyList();
    }

    private void handleRateLimiting() {
        // Logic to handle rate limiting could include retry mechanisms or delay tactics
        logger.info("Handling rate limiting for media data requests.");
    }

    @Override
    public List<MediaDTO> fetchExternalMediaDetails(String mediaId) {
        if (mediaId == null || mediaId.trim().isEmpty()) {
            throw new IllegalArgumentException("External media ID cannot be null or empty");
        }
        try {
            HttpHeaders headers = configureHeaders();
            String url = serviceUrl + "/media/" + mediaId;
            MediaDTO[] response = restTemplate.getForObject(url, MediaDTO[].class, headers);
            if (response != null) {
                return List.of(response);
            } else {
                return Collections.emptyList();
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error encountered: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return Collections.emptyList();
        } catch (Exception e) {
            logger.error("Failed to fetch media data: ", e);
            return Collections.emptyList();
        }
    }

    @Override
    public void handleExternalMediaError(Exception e) {

    }

    @Override
    public void logExternalServiceErrors(Exception e, String context) {

    }

    private HttpHeaders configureHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}