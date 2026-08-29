package uk.co.jb303.service;

import java.io.InputStream;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class SpriteService {

    // Cache key format: "normal:1" or "shiny:1" -> full data:image/png;base64,... URI
    private static final ConcurrentHashMap<String, String> SPRITE_CACHE = new ConcurrentHashMap<>();
    
    // Transparent 1x1 fallback in case a sprite file is missing
    private static final String EMPTY_PNG_DATA_URI = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=";

    public static String getSpriteDataUri(Integer speciesId, boolean isShiny) {
        if (speciesId == null) {
            return EMPTY_PNG_DATA_URI;
        }

        String type = isShiny ? "shiny" : "normal";
        String key = type + ":" + speciesId;

        return SPRITE_CACHE.computeIfAbsent(key, k -> loadAndEncode(type, speciesId));
    }

    private static String loadAndEncode(String type, int speciesId) {
        String path = "db/changelog/data/sprites/" + type + "/" + speciesId + ".png";
        try (InputStream is = SpriteService.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                return EMPTY_PNG_DATA_URI;
            }
            byte[] bytes = is.readAllBytes();
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            return EMPTY_PNG_DATA_URI;
        }
    }
}