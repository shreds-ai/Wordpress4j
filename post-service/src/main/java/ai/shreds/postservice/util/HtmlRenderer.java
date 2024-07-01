package ai.shreds.postservice.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.StringUtils;


public class HtmlRenderer {
    public String render(String input) {
        if (StringUtils.hasText(input)) {
            return StringEscapeUtils.escapeHtml4(input);
        }
        return input;
    }
}