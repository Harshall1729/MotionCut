import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;

    public LinkShortener() {
        shortToLongMap = new HashMap<>();
        longToShortMap = new HashMap<>();
    }

    // Generate a random short URL
    private String generateShortURL() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortURL = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            shortURL.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return shortURL.toString();
    }

    // Shorten a long URL
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return longToShortMap.get(longURL);
        }

        String shortURL = generateShortURL();
        while (shortToLongMap.containsKey(shortURL)) {
            shortURL = generateShortURL();
        }

        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);
        return shortURL;
    }

    // Expand a short URL
    public String expandURL(String shortURL) {
        if (!shortToLongMap.containsKey(shortURL)) {
            return "Short URL not found.";
        }
        return shortToLongMap.get(shortURL);
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();

        // Example usage
        String longURL = "https://github.com/Harshall1729/MotionCut.git";
        String shortURL = linkShortener.shortenURL(longURL);
        System.out.println("Shortened URL: " + shortURL);

        String expandedURL = linkShortener.expandURL(shortURL);
        System.out.println("Expanded URL: " + expandedURL);
    }
}
