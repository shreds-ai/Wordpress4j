package ai.shreds.wordpress4j.mediaRetrieval.application.dtos;

import ai.shreds.wordpress4j.mediaRetrieval.domain.entities.MediaItemEntity;
import ai.shreds.wordpress4j.mediaRetrieval.domain.entities.MediaMetaEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("date")
    private LocalDateTime date;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("date_gmt")
    private LocalDateTime dateGmt;

    @JsonProperty("guid")
    private RenderedDTO guid;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("modified")
    private LocalDateTime modified;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    @JsonProperty("modified_gmt")
    private LocalDateTime modifiedGmt;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("status")
    private String status;

    @JsonProperty("type")
    private String type;

    @JsonProperty("link")
    private String link;

    @NotNull
    @JsonProperty("title")
    private MediaDTO.RenderedDTO title;

    @JsonProperty("author")
    private Long author;

    @JsonProperty("featured_media")
    private long featuredMedia;

    @JsonProperty("comment_status")
    private String commentStatus;

    @JsonProperty("ping_status")
    private String pingStatus;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("mime_type")
    private String mimeType;

    @JsonProperty("media_details")
    private MediaDetailsDTO mediaDetails;

    @NotNull
    @JsonProperty("source_url")
    private String sourceUrl;

    public static MediaDTO fromEntity(MediaItemEntity entity) {
        MediaDTO dto = new MediaDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getPostDate());
        dto.setDateGmt(entity.getPostDateGmt());
        dto.setGuid(new RenderedDTO(entity.getGuid()));
        dto.setModified(entity.getPostModified());
        dto.setModifiedGmt(entity.getPostModifiedGmt());
        dto.setSlug(entity.getPostName());
        dto.setStatus(entity.getPostStatus());
        dto.setType(entity.getPostType());
        dto.setLink("");
        dto.setTitle(new RenderedDTO(entity.getPostTitle()));
        dto.setAuthor(entity.getPostAuthor());
        dto.setFeaturedMedia(0);
        dto.setCommentStatus(entity.getCommentStatus());
        dto.setPingStatus(entity.getPingStatus());
        dto.setMediaType(entity.getPostType());
        dto.setMimeType(entity.getPostMimeType());
        Optional<MediaMetaEntity> meta = entity.getMediaMetaEntities().stream()
                .filter(mediaMetaEntity ->
                        mediaMetaEntity.getMetaKey().equals("_wp_attachment_metadata")).findFirst();
        meta.ifPresent(mediaMetaEntity -> dto.setMediaDetails(MediaDetailsDTO.parseMediaDetails(mediaMetaEntity.getMetaValue())));
        dto.setSourceUrl(entity.getGuid());
        return dto;
    }


    static class RenderedDTO {
        @JsonProperty("rendered")
        private String rendered;

        public RenderedDTO(String rendered) {
            this.rendered = rendered;
        }
    }
}