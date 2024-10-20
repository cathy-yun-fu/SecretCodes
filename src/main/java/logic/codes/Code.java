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

    // morphPerWord divides the input into words by searching for "non-word characters" (see regex in method).
    // (only lettes, numbers, ' and - can make up words)
    // If two non-word chars are next to each other, it is an empty word and the PerWordImpl can choose to process or
    // ignore it.
    // Otherwise, the characters between two non-word chars is a word
    protected String morphPerWord(String input, PerWordImpl logic) {
        if (input.trim().isEmpty()) {
            return input;
        }

        // Todo: future optimization: regex should process a series of non-word chars as a matched group
        Matcher matchedWordBoundaries = Pattern.compile("[^a-zA-Z0-9'-]").matcher(input);

        StringBuilder finalWords = new StringBuilder();

        int wordStartIndex = 0;
        int wordEndIndex = 0;
        while(matchedWordBoundaries.find()) {

            // the inclusive start of the "space/symbol" matched are the exclusive ends of words
            wordEndIndex = matchedWordBoundaries.start();

            logic.impl(input, wordStartIndex, wordEndIndex, finalWords);

            // the exclusive end of the "spaces/symbol" matched, is the inclusive start to the next possible word
            wordStartIndex = matchedWordBoundaries.end();
        }

        // handles if input does not end with a "non-word char"
        if (wordStartIndex < input.length()) {
            logic.impl(input, wordStartIndex, input.length(), finalWords);
        }

        return finalWords.toString();
    }

    // appendWordBoundary adds the matched "non-word" char into output
    // Ex: there!are -> ehert!era
    protected void appendWordBoundary(String input, int index, StringBuilder finalWords) {
        if ((input.length() - 1) < index) {
            return;
        }

        finalWords.append(input.charAt(index));
    }

}
