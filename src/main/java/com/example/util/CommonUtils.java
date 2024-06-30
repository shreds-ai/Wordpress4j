package com.example.util;

import com.example.util.HtmlRenderer;

public class CommonUtils {
    private static HtmlRenderer htmlRenderer = new HtmlRenderer();

    public static String renderHtml(String input) {
        return htmlRenderer.render(input);
    }
}