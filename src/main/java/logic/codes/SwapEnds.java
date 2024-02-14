package logic.codes;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwapEnds extends Code {
    
    @Override
    public String morph(String input) {
        return swap(input);
    }

    private String swap(String input) {
        if (input.trim().isEmpty()) {
            return input;
        }

        Matcher matchedWordBoundaries = Pattern.compile("[^a-zA-Z0-9'-]").matcher(input);

        StringBuilder finalWords = new StringBuilder();

        int wordStartIndex = 0;
        int wordEndIndex = 0;
        while(matchedWordBoundaries.find()) {
            wordEndIndex = matchedWordBoundaries.start();

            swapEndsImpl(input, wordStartIndex, wordEndIndex, finalWords);

            wordStartIndex = matchedWordBoundaries.end();
        }

        if (wordStartIndex < input.length() - 1) {
            swapEndsImpl(input, wordStartIndex, input.length(), finalWords);
        }

        return finalWords.toString();
    }

    private void swapEndsImpl(String input, int wordStartIndex, int wordEndIndex, StringBuilder finalWords) {
        String word = input.substring(wordStartIndex, wordEndIndex);

        if (word.isEmpty()) {
            // preserve symbols
            finalWords.append(input.charAt(wordStartIndex));
        } else if (word.length() == 1) {
            finalWords.append(word);
            appendMatcher(input, wordEndIndex, finalWords);
        } else {
            char[] letters =  word.toCharArray();

            StringBuilder finalWord = new StringBuilder();
            finalWord.append(letters[letters.length-1]);
            finalWord.append(Arrays.copyOfRange(letters, 1, letters.length-1));
            finalWord.append(letters[0]);
            finalWords.append(finalWord);

            appendMatcher(input, wordEndIndex, finalWords);
        }
    }

    public void appendMatcher(String input, int index, StringBuilder finalWords) {
        if ((input.length() - 1) < index) {
            return;
        }

        finalWords.append(input.charAt(index));
    }

    @Override
    public String decipher(String input) {
        return swap(input);
    }
}
