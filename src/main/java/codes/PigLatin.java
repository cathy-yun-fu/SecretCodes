package codes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PigLatin extends Code {

    Set<Character> vowels = new HashSet<>() {
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

    Set<Character> sometimeVowels = new HashSet<>() {
        {
            add('y');
            add('Y');
        }
    };

    public String morph(String input) {
        if (input.trim().isEmpty()) {
            return input;
        }
        String[] words = input.split(" ");

        StringBuilder finalWords = new StringBuilder();

        for(String word : words) {
            if (word.length() == 1) {
                finalWords.append(word).append(" ");
                continue;
            } else if (word.isEmpty()) {
                // An empty word means there was an extra space that should be kept
                finalWords.append(" ");
                continue;
            }
            char[] letters =  word.toCharArray();

            StringBuilder finalWord = new StringBuilder();

            int split = findSplit(letters);
            finalWord.append(Arrays.copyOfRange(letters, split, letters.length));
            finalWord.append(Arrays.copyOfRange(letters, 0, split));
            finalWord.append("ay");

            finalWords.append(finalWord).append(" ");

        }

        int numTrailingSpaces = countTrailingSpaces(input);

        if (numTrailingSpaces < 1) {
            // Clean up the last space
            finalWords.deleteCharAt(finalWords.length()-1);
        } else if (numTrailingSpaces > 1) {
            // Add more spaces
            finalWords.append(" ".repeat(numTrailingSpaces-1));
        }

        return finalWords.toString();
    }

    private int countTrailingSpaces(String input) {
        int numSpaces = 0;
        for (int i = input.length()-1; i >= 0; i--) {
            if (input.charAt(i) == ' ') {
                numSpaces++;
            } else {
                return numSpaces;
            }
        }
        return numSpaces;
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

}
