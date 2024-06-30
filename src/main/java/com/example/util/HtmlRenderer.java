package com.example.util;

import org.springframework.util.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;

public class HtmlRenderer {
    public String render(String input) {
        if (StringUtils.hasText(input)) {
            return StringEscapeUtils.escapeHtml4(input);
        }
        return input;
    }
}