package logic.codes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Code {

    @FunctionalInterface
    public interface PerWordImpl {
        void impl(String input, int wordStartIndex, int wordEndIndex, StringBuilder finalWords);
    }
    public String morph(String input) {
        throw new RuntimeException("Unimplemented Code used " + getClass().getName());
    }

    public String decipher(String input) {
        throw new RuntimeException("Unimplemented Code used " + getClass().getName());
    }

    protected String morphPerWord(String input, PerWordImpl logic) {
        if (input.trim().isEmpty()) {
            return input;
        }

        Matcher matchedWordBoundaries = Pattern.compile("[^a-zA-Z0-9'-]").matcher(input);

        StringBuilder finalWords = new StringBuilder();

        int wordStartIndex = 0;
        int wordEndIndex = 0;
        while(matchedWordBoundaries.find()) {
            wordEndIndex = matchedWordBoundaries.start();

            logic.impl(input, wordStartIndex, wordEndIndex, finalWords);

            wordStartIndex = matchedWordBoundaries.end();
        }

        if (wordStartIndex < input.length() - 1) {
            logic.impl(input, wordStartIndex, input.length(), finalWords);
        }

        return finalWords.toString();
    }

    protected void appendMatcher(String input, int index, StringBuilder finalWords) {
        if ((input.length() - 1) < index) {
            return;
        }

        finalWords.append(input.charAt(index));
    }

}
