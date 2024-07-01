package ai.shreds.postservice.application.mappers;

import ai.shreds.postservice.application.dtos.PostDetailsDTO;
import ai.shreds.postservice.domain.entities.PostEntity;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PostDetailsMapper {

    public static PostDetailsDTO toDTO(PostEntity postEntity, String baseUrl) {
        PostDetailsDTO dto = new PostDetailsDTO();

        dto.setId(postEntity.getId());
        dto.setDate(DateTimeFormatter.ISO_INSTANT.format(postEntity.getPostDate()));
        dto.setDateGmt(DateTimeFormatter.ISO_INSTANT.format(postEntity.getPostDateGmt()));

        // guid field
        PostDetailsDTO.RenderedField guidField = new PostDetailsDTO.RenderedField();
        guidField.setRendered(postEntity.getGuid());
        dto.setGuid(guidField);

        dto.setModified(DateTimeFormatter.ISO_INSTANT.format(postEntity.getPostModified()));
        dto.setModifiedGmt(DateTimeFormatter.ISO_INSTANT.format(postEntity.getPostModifiedGmt()));
        dto.setSlug(postEntity.getPostName());
        dto.setStatus(postEntity.getPostStatus());
        dto.setType(postEntity.getPostType());
        dto.setLink(baseUrl + "/" + postEntity.getPostName() + "/");

        // title field
        PostDetailsDTO.RenderedField titleField = new PostDetailsDTO.RenderedField();
        titleField.setRendered(postEntity.getPostTitle());
        dto.setTitle(titleField);

        // content field
        PostDetailsDTO.ContentField contentField = new PostDetailsDTO.ContentField();
        contentField.setRendered(postEntity.getPostContent());
        contentField.setProtectedContent(false); // Assuming content is not protected
        dto.setContent(contentField);

        // excerpt field
        PostDetailsDTO.ExcerptField excerptField = new PostDetailsDTO.ExcerptField();
        excerptField.setRendered(postEntity.getPostExcerpt());
        excerptField.setProtectedContent(false); // Assuming excerpt is not protected
        dto.setExcerpt(excerptField);

        dto.setAuthor(postEntity.getPostAuthor().getId());
        dto.setFeaturedMedia(postEntity.getFeaturedMedia());
        dto.setCommentStatus(postEntity.getCommentStatus());
        dto.setPingStatus(postEntity.getPingStatus());
        dto.setSticky(false);
        dto.setTemplate("");
        dto.setFormat("standard");

        // meta field
        PostDetailsDTO.MetaField metaField = new PostDetailsDTO.MetaField();
        dto.setMeta(metaField);

        dto.setCategories(postEntity.getCategories().stream().map(category -> category.getId()).collect(Collectors.toList()));
        dto.setTags(postEntity.getTags().stream().map(tag -> tag.getId()).collect(Collectors.toList()));

        // _links field
        PostDetailsDTO.LinksField linksField = new PostDetailsDTO.LinksField();
        linksField.setSelf(List.of(createLinkField(baseUrl + "/wp-json/wp/v2/posts/" + postEntity.getId())));
        linksField.setCollection(List.of(createLinkField(baseUrl + "/wp-json/wp/v2/posts")));
        linksField.setAbout(List.of(createLinkField(baseUrl + "/wp-json/wp/v2/types/post")));
        linksField.setAuthor(List.of(createLinkField(baseUrl + "/wp-json/wp/v2/users/" + postEntity.getPostAuthor(), true)));
        linksField.setReplies(List.of(createLinkField(baseUrl + "/wp-json/wp/v2/comments?post=" + postEntity.getId(), true)));
        linksField.setVersionHistory(List.of(createVersionHistoryField(1, baseUrl + "/wp-json/wp/v2/posts/" + postEntity.getId() + "/revisions")));
        linksField.setPredecessorVersion(List.of(createPredecessorVersionField(postEntity.getId(), baseUrl + "/wp-json/wp/v2/posts/" + postEntity.getId() + "/revisions/9")));
        linksField.setWpFeaturedmedia(List.of(createLinkField(baseUrl + "/wp-json/wp/v2/media/" + postEntity.getFeaturedMedia(), true)));
        linksField.setWpAttachment(List.of(createLinkField(baseUrl + "/wp-json/wp/v2/media?parent=" + postEntity.getId())));
        linksField.setWpTerm(List.of(
                createTermField("category", true, baseUrl + "/wp-json/wp/v2/categories?post=" + postEntity.getId()),
                createTermField("post_tag", true, baseUrl + "/wp-json/wp/v2/tags?post=" + postEntity.getId())
        ));
        linksField.setCuries(List.of(createCuriesField("wp", "https://api.w.org/{rel}", true)));

        dto.set_links(linksField);

        return dto;
    }

    private static PostDetailsDTO.LinksField.LinkField createLinkField(String href) {
        PostDetailsDTO.LinksField.LinkField linkField = new PostDetailsDTO.LinksField.LinkField();
        linkField.setHref(href);
        return linkField;
    }

    private static PostDetailsDTO.LinksField.LinkField createLinkField(String href, Boolean embeddable) {
        PostDetailsDTO.LinksField.LinkField linkField = new PostDetailsDTO.LinksField.LinkField();
        linkField.setHref(href);
        linkField.setEmbeddable(embeddable);
        return linkField;
    }

    private static PostDetailsDTO.LinksField.VersionHistoryField createVersionHistoryField(Integer count, String href) {
        PostDetailsDTO.LinksField.VersionHistoryField versionHistoryField = new PostDetailsDTO.LinksField.VersionHistoryField();
        versionHistoryField.setCount(count);
        versionHistoryField.setHref(href);
        return versionHistoryField;
    }

    private static PostDetailsDTO.LinksField.PredecessorVersionField createPredecessorVersionField(Long id, String href) {
        PostDetailsDTO.LinksField.PredecessorVersionField predecessorVersionField = new PostDetailsDTO.LinksField.PredecessorVersionField();
        predecessorVersionField.setId(id);
        predecessorVersionField.setHref(href);
        return predecessorVersionField;
    }

    private static PostDetailsDTO.LinksField.TermField createTermField(String taxonomy, Boolean embeddable, String href) {
        PostDetailsDTO.LinksField.TermField termField = new PostDetailsDTO.LinksField.TermField();
        termField.setTaxonomy(taxonomy);
        termField.setEmbeddable(embeddable);
        termField.setHref(href);
        return termField;
    }

    private static PostDetailsDTO.LinksField.CuriesField createCuriesField(String name, String href, Boolean templated) {
        PostDetailsDTO.LinksField.CuriesField curiesField = new PostDetailsDTO.LinksField.CuriesField();
        curiesField.setName(name);
        curiesField.setHref(href);
        curiesField.setTemplated(templated);
        return curiesField;
    }
}
