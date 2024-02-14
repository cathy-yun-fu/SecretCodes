package logic.codes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatin extends Code {

    private static final Set<Character> vowels = new HashSet<>() {
        {
            add('a');
            add('A');
            add('e');
            add('E');
            add('i');
            add('I');
            add('o');
            add('O');
            add('u');
            add('U');
        }
    };

    private static final Set<Character> sometimeVowels = new HashSet<>() {
        {
            add('y');
            add('Y');
        }
    };

    @Override
    public String morph(String input) {
        return super.morphPerWord(input, (input1, wordStartIndex, wordEndIndex, finalWords) -> {
            String word = input1.substring(wordStartIndex, wordEndIndex);

            if (word.isEmpty()) {
                // preserve symbols
                finalWords.append(input1.charAt(wordStartIndex));
            } else if (word.length() == 1) {
                // todo: review the following decision:
                // In this version of Pig Latin, single letter words aren't morphed
                finalWords.append(word);
                appendMatcher(input1, wordEndIndex, finalWords);
            } else {
                char[] letters = word.toCharArray();
                StringBuilder finalWord = new StringBuilder();

                int split = findSplit(letters);
                finalWord.append(Arrays.copyOfRange(letters, split, letters.length));
                finalWord.append(Arrays.copyOfRange(letters, 0, split));
                finalWord.append("ay");

                finalWords.append(finalWord);
                appendMatcher(input1, wordEndIndex, finalWords);
            }
        });
    }

    private int findSplit(char[] letters) {
        if (vowels.contains(letters[0])){
            return 0;
        }

        for (int i = 1; i < letters.length; i++) {
            if (vowels.contains(letters[i]) || sometimeVowels.contains(letters[i])) {
                return i;
            }
        }

        return letters.length-1;
    }

    @Override
    public String decipher(String input) {
        return super.decipher(input);
    }
}
