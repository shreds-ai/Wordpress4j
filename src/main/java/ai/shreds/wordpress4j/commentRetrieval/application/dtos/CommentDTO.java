package ai.shreds.wordpress4j.commentRetrieval.application.dtos;

import ai.shreds.wordpress4j.commentRetrieval.domain.entities.CommentEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Data Transfer Object for transferring comment data within the application layer and to/from the adapters.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    @JsonProperty("id")
    private Long comment_ID;

    @JsonProperty("post")
    private Long comment_post_ID;

    @JsonProperty("parent")
    private Long comment_parent;

    @JsonProperty("author")
    private Long comment_author_id;

    @JsonProperty("author_name")
    private String comment_author;

    @JsonProperty("author_url")
    private String comment_author_url;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private LocalDateTime comment_date;

    @JsonProperty("date_gmt")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private LocalDateTime comment_date_gmt;

    @JsonProperty("content")
    private RenderedDTO comment_content;

    @JsonProperty("link")
    private String link;

    @JsonProperty("status")
    private String comment_approved;

    @JsonProperty("type")
    private String comment_type;

    @JsonProperty("author_avatar_urls")
    private Map<String, String> authorAvatarUrls;

    @JsonProperty("meta")
    private List<String> meta;

    /**
     * Validates that the date and dateGmt fields represent the same point in time.
     */
    private void validateDateConsistency() {
        if (!comment_date.atOffset(ZoneOffset.UTC).isEqual(comment_date_gmt.atOffset(ZoneOffset.UTC))) {
            throw new IllegalArgumentException("Date and Date GMT must represent the same point in time");
        }
    }

    /**
     * Validates that all URLs in the authorAvatarUrls map are valid and not malformed.
     */
    private void validateAuthorAvatarUrls() {
        for (String url : authorAvatarUrls.values()) {
            try {
                new URL(url).toURI();
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid URL in authorAvatarUrls: " + url);
            }
        }
    }

    public static CommentDTO fromDomainModel(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setComment_ID(entity.getComment_ID());
        dto.setComment_post_ID(entity.getComment_post_ID());
        dto.setComment_parent(entity.getComment_parent());
        dto.setComment_author_id(entity.getUser_id());
        dto.setComment_author(entity.getComment_author());
        dto.setComment_author_url(entity.getComment_author_url());
        dto.setComment_date(entity.getComment_date());
        dto.setComment_date_gmt(entity.getComment_date_gmt());
        dto.setComment_content(new RenderedDTO(entity.getComment_content()));
        dto.setComment_approved(Objects.equals(entity.getComment_approved(), "1") ? "approved" : "unapproved");
        dto.setComment_type(entity.getComment_type());
        dto.setAuthorAvatarUrls(entity.getAuthorAvatarUrls());
        dto.setMeta(entity.getComment_meta().stream().map(meta -> meta.getMetaKey() + ": " + meta.getMetaValue()).collect(Collectors.toList()));

        return dto;
    }

    static class RenderedDTO {
        @JsonProperty("rendered")
        private String rendered;

        public RenderedDTO(String rendered) {
            this.rendered = "<p>" + rendered.replace("\n", "<br />\n") + "</p>\n";
        }
    }

}