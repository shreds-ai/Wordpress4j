package ai.shreds.postservice.util;

public class CommonUtils {
    private static HtmlRenderer htmlRenderer = new HtmlRenderer();

    public static String renderHtml(String input) {
        return htmlRenderer.render(input);
    }
}