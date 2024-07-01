package com.example.application.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data transfer object for detailed post information, used to transfer data between the primary adapter and the application layer.
 *
 * @author Data Transfer Object
 */

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostDetailsDTO {
    private Long id;
    private String date;
    private String dateGmt;
    private RenderedField guid;
    private String modified;
    private String modifiedGmt;
    private String slug;
    private String status;
    private String type;
    private String link;
    private RenderedField title;
    private ContentField content;
    private ExcerptField excerpt;
    private Long author;
    private Long featuredMedia;
    private String commentStatus;
    private String pingStatus;
    private Boolean sticky;
    private String template;
    private String format;
    private MetaField meta;
    private List<Long> categories;
    private List<Long> tags;
    private LinksField _links;

    @Data
    @NoArgsConstructor
    public static class RenderedField {
        private String rendered;
    }

    @Data
    @NoArgsConstructor
    public static class ContentField {
        private String rendered;
        private Boolean protectedContent = false;
    }

    @Data
    @NoArgsConstructor
    public static class ExcerptField {
        private String rendered;
        private Boolean protectedContent = false;
    }

    @Data
    @NoArgsConstructor
    public static class MetaField {
        private String footnotes = "";
    }

    @Data
    @NoArgsConstructor
    public static class LinksField {
        private List<LinkField> self;
        private List<LinkField> collection;
        private List<LinkField> about;
        private List<LinkField> author;
        private List<LinkField> replies;
        private List<VersionHistoryField> versionHistory;
        private List<PredecessorVersionField> predecessorVersion;
        private List<LinkField> wpFeaturedmedia;
        private List<LinkField> wpAttachment;
        private List<TermField> wpTerm;
        private List<CuriesField> curies;

        @Data
        @NoArgsConstructor
        public static class LinkField {
            private String href;
            private Boolean embeddable;
        }

        @Data
        @NoArgsConstructor
        public static class VersionHistoryField {
            private Integer count;
            private String href;
        }

        @Data
        @NoArgsConstructor
        public static class PredecessorVersionField {
            private Long id;
            private String href;
        }

        @Data
        @NoArgsConstructor
        public static class TermField {
            private String taxonomy;
            private Boolean embeddable;
            private String href;
        }

        @Data
        @NoArgsConstructor
        public static class CuriesField {
            private String name;
            private String href;
            private Boolean templated;
        }
    }
}
