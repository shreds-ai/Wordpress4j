package com.example.wordpressclone.domain.value_objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the value object for media item properties like URL and MIME type.
 */
@Getter
@Setter
public class MediaItemValue {

    @JsonProperty("url")
    private String url;

    @JsonProperty("mimeType")
    private String mimeType;

    /**
     * Constructs a new MediaItemValue with specified URL and MIME type.
     * @param url The URL of the media item.
     * @param mimeType The MIME type of the media item.
     */
    public MediaItemValue(String url, String mimeType) {
        this.url = url;
        this.mimeType = mimeType;
    }

    /**
     * Returns a complete description combining URL and MIME type.
     * @return A string representation of the media item's properties.
     */
    public String getCompleteMediaDescription() {
        return "URL: " + url + ", MIME Type: " + mimeType;
    }

    /**
     * Validates the format of the URL.
     * Throws IllegalArgumentException if the URL format is not valid.
     */
    public void validateUrlFormat() {
        if (!url.matches("^(https?:\/\/)?([\da-z.-]+)\.([a-z.]{2,6})([\/\w \-]*)*\/?$")) {
            throw new IllegalArgumentException("Invalid URL format");
        }
    }
}