import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTokenizer {
    public static List<String> tokenizeText(String inputText) {
        //String[] words = inputText.split("[\\p{Punct}\\s]+");
        String[] words = inputText.split("[^a-zA-Z0-9/]+");

        List<String> tokens = new ArrayList<>();
        for (String word : words) {
            tokens.add(word.toLowerCase());
        }
        return tokens;
    }
    public static List<String> tokenizeTextWithoutNumbers(String inputText) {
        //String[] words = inputText.split("[\\p{Punct}\\s]+");
        String[] words = inputText.split("[^a-zA-Z]+");

        List<String> tokens = new ArrayList<>();
        for (String word : words) {
            tokens.add(word.toLowerCase());
        }
        return tokens;
    }

    public static boolean isDistancePattern(String text) {
        if (text == null || text.length() < 3) {
            return false;
        }

        // Define the pattern for the expected format
        Pattern pattern = Pattern.compile("\\b\\w+\\s*/\\d+\\s+\\w+\\b");

        // Match the pattern against the input string
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }
}