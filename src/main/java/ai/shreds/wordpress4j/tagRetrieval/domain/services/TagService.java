package ai.shreds.wordpress4j.tagRetrieval.domain.services;

import ai.shreds.wordpress4j.tagRetrieval.domain.entities.TagEntity;
import ai.shreds.wordpress4j.tagRetrieval.domain.ports.TagRepositoryPort;
import ai.shreds.wordpress4j.tagRetrieval.domain.ports.TagServicePort;
import ai.shreds.wordpress4j.tagRetrieval.domain.exceptions.TagNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements TagServicePort {

    private final TagRepositoryPort tagRepositoryPort;
    private static final Logger logger = LoggerFactory.getLogger(TagService.class);

    public TagService(TagRepositoryPort tagRepositoryPort) {
        this.tagRepositoryPort = tagRepositoryPort;
    }

    @Transactional(readOnly = true)
    public List<TagEntity> retrieveAllTags() throws DataAccessException {
        logger.info("Retrieving all tags");
        try {
            return tagRepositoryPort.findAllTags();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve all tags", e);
            throw e;
        }
    }

    @Transactional
    public TagEntity retrieveTagById(Long id) throws DataAccessException, TagNotFoundException {
        logger.info("Retrieving tag by ID: {}", id);
        Optional<TagEntity> result = tagRepositoryPort.findTagById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            logger.error("Tag not found for ID: {}", id);
            throw new TagNotFoundException("Tag not found for ID: " + id);
        }
    }

    @Override
    @Transactional
    public TagEntity retrieveTagByName(String name) throws DataAccessException, TagNotFoundException {
        logger.info("Retrieving tag by name: {}", name);
        Optional<TagEntity> result = tagRepositoryPort.findTagByName(name);
        if (result.isPresent()) {
            return result.get();
        } else {
            logger.error("Tag not found with name: {}", name);
            throw new TagNotFoundException("Tag not found with name: " + name);
        }
    }
}