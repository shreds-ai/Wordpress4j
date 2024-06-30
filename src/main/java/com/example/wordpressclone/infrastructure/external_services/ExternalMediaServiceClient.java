package com.example.wordpressclone.infrastructure.external_services;

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
import com.example.wordpressclone.application.dtos.MediaDTO;
import com.example.wordpressclone.application.ports.ExternalMediaServicePort;
import org.springframework.boot.actuate.metrics.Metrics;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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

    /**
     * Fetches media data from an external service.
     *
     * @param externalMediaId the ID of the media to fetch
     * @return a list of MediaDTO objects or an empty list if none found
     * @throws IllegalArgumentException if externalMediaId is null or empty
     */
    @HystrixCommand(fallbackMethod = 'handleExternalServiceDown')
    public List<MediaDTO> fetchExternalMediaData(String externalMediaId) {
        if (externalMediaId == null || externalMediaId.trim().isEmpty()) {
            throw new IllegalArgumentException("External media ID cannot be null or empty");
        }
        try {
            HttpHeaders headers = configureHeaders();
            String url = serviceUrl + "/media/" + externalMediaId;
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

    /**
     * Fallback method for fetchExternalMediaData when the external service is unavailable.
     *
     * @return an empty list indicating no data could be retrieved.
     */
    private List<MediaDTO> handleExternalServiceDown() {
        logger.info("External media service is currently unavailable, returning empty list.");
        return Collections.emptyList();
    }

    private HttpHeaders configureHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}