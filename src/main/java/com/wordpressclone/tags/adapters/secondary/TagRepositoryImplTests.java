package com.wordpressclone.tags.adapters.secondary;

import com.wordpressclone.tags.domain.entities.TagEntity;
import com.wordpressclone.tags.domain.ports.TagRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TagRepositoryImplTests {

    @Mock
    private TagRepositoryPort tagRepository;

    @BeforeEach
    public void setUp() {
        // Setup necessary mock behavior here.
    }

    @Transactional
    @Test
    public void testFindById() {
        Long id = 1L;
        TagEntity expectedTag = new TagEntity();
        expectedTag.setId(id);
        expectedTag.setName("Technology");

        when(tagRepository.findTagById(id)).thenReturn(Optional.of(expectedTag));

        assertEquals(Optional.of(expectedTag), tagRepository.findTagById(id));
    }

    @Transactional
    @Test
    public void testFindByName() {
        String name = "Technology#2020";
        TagEntity expectedTag = new TagEntity();
        expectedTag.setId(1L);
        expectedTag.setName(name);

        when(tagRepository.findTagByName(name)).thenReturn(Optional.of(expectedTag));

        assertEquals(Optional.of(expectedTag), tagRepository.findTagByName(name));
    }

    @Transactional
    @Test
    public void testFindAll() {
        TagEntity tag1 = new TagEntity();
        tag1.setId(1L);
        tag1.setName("Technology");

        TagEntity tag2 = new TagEntity();
        tag2.setId(2L);
        tag2.setName("Health & Wellness");

        List<TagEntity> expectedTags = Arrays.asList(tag1, tag2);

        when(tagRepository.findAllTags()).thenReturn(expectedTags);

        assertEquals(expectedTags, tagRepository.findAllTags());
    }
}