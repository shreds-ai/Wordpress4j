package ai.shreds.wordpress4j.mediaRetrieval.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class MediaDetailsDTO {

    private int width;
    private int height;
    private String file;
    private int filesize;
    private Map<String, MediaSize> sizes;

    @Data
    @NoArgsConstructor
    public static class MediaSize {
        private String file;
        private String width;
        private String height;
        private String filesize;

        @JsonProperty("mime_type")
        private String mimeType;

        @JsonProperty("source_url")
        private String sourceUrl;
    }

    public static MediaDetailsDTO parseMediaDetails(String serializedString) {
        MediaDetailsDTO mediaDetails = new MediaDetailsDTO();
        Map<String, Object> data = parseMetaValue(serializedString);

        mediaDetails.setWidth((Integer) data.get("width"));
        mediaDetails.setHeight((Integer) data.get("height"));
        mediaDetails.setFile((String) data.get("file"));
        mediaDetails.setFilesize((Integer) data.get("filesize"));

        Map<String, Object> sizesData = (Map<String, Object>) data.get("sizes");
        Map<String, MediaDetailsDTO.MediaSize> sizes = new HashMap<>();

        for (Map.Entry<String, Object> entry : sizesData.entrySet()) {
            Map<String, Object> sizeData = (Map<String, Object>) entry.getValue();
            MediaDetailsDTO.MediaSize mediaSize = new MediaDetailsDTO.MediaSize();
            mediaSize.setFile(sizeData.get("file").toString());
            mediaSize.setWidth(sizeData.get("width").toString());
            mediaSize.setHeight(sizeData.get("height").toString());
            mediaSize.setFilesize(sizeData.get("filesize") != null ? sizeData.get("filesize").toString() : null);
            mediaSize.setMimeType((String) sizeData.get("mime-type"));
            // Assuming source_url is generated or fixed pattern based on file name
            mediaSize.setSourceUrl("http://localhost:8090/wp-content/uploads/" + sizeData.get("file"));

            sizes.put(entry.getKey(), mediaSize);
        }

        mediaDetails.setSizes(sizes);

        return mediaDetails;
    }

    public static Map<String, Object> parseMetaValue(String metaValue) {
        Map<String, Object> metaMap = new HashMap<>();

        metaMap.put("width", extractInteger(metaValue, "s:5:\"width\";i:", ";"));
        metaMap.put("height", extractInteger(metaValue, "s:6:\"height\";i:", ";"));
        metaMap.put("file", extractString(metaValue, "s:4:\"file\";s:48:\"", "\";"));
        metaMap.put("filesize", extractInteger(metaValue, "s:8:\"filesize\";i:", ";"));

        // Parse sizes map
        String sizesKey = "s:5:\"sizes\";a:";
        int sizesIndex = metaValue.indexOf(sizesKey) + sizesKey.length();
        String sizesValue = extractNestedMap(metaValue.substring(sizesIndex));
        metaMap.put("sizes", parseSizes(sizesValue, metaValue));

        // Parse image_meta map
        String imageMetaKey = "s:10:\"image_meta\";a:";
        int imageMetaIndex = metaValue.indexOf(imageMetaKey) + imageMetaKey.length();
        String imageMetaValue = extractNestedMap(metaValue.substring(imageMetaIndex));
        metaMap.put("image_meta", parseImageMeta(imageMetaValue));

        return metaMap;
    }

    private static int extractInteger(String source, String startMarker, String endMarker) {
        int startIndex = source.indexOf(startMarker) + startMarker.length();
        int endIndex = source.indexOf(endMarker, startIndex);
        return Integer.parseInt(source.substring(startIndex, endIndex));
    }

    private static String extractString(String source, String startMarker, String endMarker) {
        int startIndex = source.indexOf(startMarker) + startMarker.length();
        int endIndex = source.indexOf(endMarker, startIndex);
        return source.substring(startIndex, endIndex);
    }

    private static String extractNestedMap(String source) {
        int startIndex = source.indexOf("{") + 1;
        int endIndex = source.lastIndexOf("}");
        return source.substring(startIndex, endIndex);
    }

    private static Map<String, Object> parseSizes(String sizesValue, String metaValue) {
        Map<String, Object> sizesMap = new HashMap<>();

        sizesMap.put("medium", parseSubMap(sizesValue, "s:6:\"medium\";"));
        sizesMap.put("thumbnail", parseSubMap(sizesValue, "s:9:\"thumbnail\";"));
        sizesMap.put("medium_large", parseSubMap(sizesValue, "s:12:\"medium_large\";"));

        // Manually add the 'full' size
        Map<String, Object> fullSizeMap = new HashMap<>();
        fullSizeMap.put("file", extractString(metaValue, "s:4:\"file\";s:48:\"", "\";"));
        fullSizeMap.put("width", extractInteger(metaValue, "s:5:\"width\";i:", ";"));
        fullSizeMap.put("height", extractInteger(metaValue, "s:6:\"height\";i:", ";"));
        fullSizeMap.put("mime-type", "image/jpeg");
        fullSizeMap.put("source_url", "http://localhost:8090/wp-content/uploads/2024/06/" + extractString(metaValue, "s:4:\"file\";s:48:\"", "\";"));
        sizesMap.put("full", fullSizeMap);

        return sizesMap;
    }

    private static Map<String, Object> parseImageMeta(String imageMetaValue) {
        Map<String, Object> imageMetaMap = new HashMap<>();

        imageMetaMap.put("aperture", extractString(imageMetaValue, "s:8:\"aperture\";s:1:\"", "\";"));
        imageMetaMap.put("credit", extractString(imageMetaValue, "s:6:\"credit\";s:1:\"", "\";"));
        imageMetaMap.put("camera", extractString(imageMetaValue, "s:6:\"camera\";s:1:\"", "\";"));
        imageMetaMap.put("caption", extractString(imageMetaValue, "s:7:\"caption\";s:1:\"", "\";"));
        imageMetaMap.put("created_timestamp", extractString(imageMetaValue, "s:17:\"created_timestamp\";s:1:\"", "\";"));
        imageMetaMap.put("copyright", extractString(imageMetaValue, "s:9:\"copyright\";s:1:\"", "\";"));
        imageMetaMap.put("focal_length", extractString(imageMetaValue, "s:12:\"focal_length\";s:1:\"", "\";"));
        imageMetaMap.put("iso", extractString(imageMetaValue, "s:3:\"iso\";s:1:\"", "\";"));
        imageMetaMap.put("shutter_speed", extractString(imageMetaValue, "s:13:\"shutter_speed\";s:1:\"", "\";"));
        imageMetaMap.put("title", extractString(imageMetaValue, "s:5:\"title\";s:1:\"", "\";"));
        imageMetaMap.put("orientation", extractString(imageMetaValue, "s:11:\"orientation\";s:1:\"", "\";"));
        imageMetaMap.put("keywords", extractNestedMap(imageMetaValue.substring(imageMetaValue.indexOf("s:8:\"keywords\";a:") + 17)));

        return imageMetaMap;
    }

    private static Map<String, Object> parseSubMap(String subMapValue, String keyMarker) {
        int keyIndex = subMapValue.indexOf(keyMarker) + keyMarker.length();
        String subMap = extractNestedMap(subMapValue.substring(keyIndex));

        Map<String, Object> map = new HashMap<>();
        map.put("file", extractString(subMap, "s:4:\"file\";s:48:\"", "\";"));
        map.put("width", extractInteger(subMap, "s:5:\"width\";i:", ";"));
        map.put("height", extractInteger(subMap, "s:6:\"height\";i:", ";"));
        map.put("mime-type", extractString(subMap, "s:9:\"mime-type\";s:10:\"", "\";"));
        map.put("filesize", extractInteger(subMap, "s:8:\"filesize\";i:", ";"));

        return map;
    }

}
