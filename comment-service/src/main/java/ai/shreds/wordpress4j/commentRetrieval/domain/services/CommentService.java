package ai.shreds.wordpress4j.commentRetrieval.domain.services;

import ai.shreds.wordpress4j.commentRetrieval.domain.entities.OptionEntity;
import ai.shreds.wordpress4j.commentRetrieval.domain.ports.CommentRepositoryPort;
import ai.shreds.wordpress4j.commentRetrieval.application.dtos.CommentDTO;
import ai.shreds.wordpress4j.commentRetrieval.domain.entities.CommentEntity;
import ai.shreds.wordpress4j.commentRetrieval.domain.ports.OptionRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepositoryPort commentRepository;
    private final OptionRepositoryPort optionRepository;
    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CommentService(CommentRepositoryPort commentRepository, OptionRepositoryPort optionRepository) {
        this.commentRepository = commentRepository;
        this.optionRepository = optionRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable("comments")
    public List<CommentDTO> fetchAllComments() throws DataAccessException {
        logger.info("Fetching all comments from the database.");
        List<CommentDTO> comments = commentRepository.findAllComments().stream()
                .map(CommentDTO::fromDomainModel)
                .collect(Collectors.toList());

        OptionEntity siteurl = optionRepository.findByOptionName("siteurl");
        comments.forEach(comment -> comment.setLink(
                siteurl.getOptionValue() + "/?p=" + comment.getComment_post_ID() + "#comment-" + comment.getComment_ID()));

        logger.info("Successfully fetched all comments.");
        return comments;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "comment", key = "#id")
    public Optional<CommentDTO> fetchCommentById(Long id) throws DataAccessException {
        logger.info("Fetching comment by ID: {}", id);
        Optional<CommentDTO> commentDTO = commentRepository.findCommentById(id)
                .map(CommentDTO::fromDomainModel);

        OptionEntity siteurl = optionRepository.findByOptionName("siteurl");
        commentDTO.ifPresent(comment -> comment.setLink(
                siteurl.getOptionValue() + "/?p=" + comment.getComment_post_ID() + "#comment-" + comment.getComment_ID()));

        return commentDTO;
    }
}