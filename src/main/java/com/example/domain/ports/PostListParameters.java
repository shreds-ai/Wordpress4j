package com.example.domain.ports;

import java.util.List;
import com.example.domain.value_objects.PostStatus;
import lombok.Data;

@Data
public class PostListParameters {
    private String context = "view";
    private int page = 1;
    private int perPage = 10;
    private String search = null;
    private String after = null;
    private String modifiedAfter = null;
    private List<Integer> author = null;
    private List<Integer> authorExclude = null;
    private String before = null;
    private String modifiedBefore = null;
    private List<Integer> exclude = null;
    private List<Integer> include = null;
    private int offset = 0;
    private String order = "desc";
    private String orderBy = "date";
    private List<String> slug = null;
    private PostStatus status = PostStatus.PUBLISH;
    private List<Integer> categories = null;
    private List<Integer> categoriesExclude = null;
    private List<Integer> tags = null;
    private List<Integer> tagsExclude = null;
    private boolean sticky = false;

    // Validations
    public void validateOrderField(String order) {
        if (!order.equals("asc") && !order.equals("desc")) {
            throw new IllegalArgumentException("Invalid order: " + order);
        }
    }

    // Date conversion
    public LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    // Logging
    public void logDataProcessing(String data) {
        System.out.println("Processing data: " + data);
    }
}