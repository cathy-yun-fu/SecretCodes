package logic.codes;

import java.util.Arrays;

public class SwapEnds extends Code {
    
    @Override
    public String morph(String input) {
        return swap(input);
    }

    private String swap(String input) {
        String[] words = input.split(" ");

        StringBuilder finalWords = new StringBuilder();
        if (input.charAt(0) == ' ') {
            finalWords.append(" ");
        }

        for(String word : words) {
            if (word.length() == 1) {
                finalWords.append(word + " ");
                continue;
            }
            char[] letters =  word.toCharArray();

            StringBuilder finalWord = new StringBuilder();
            finalWord.append(letters[letters.length-1]);
            finalWord.append(Arrays.copyOfRange(letters, 1, letters.length-1));
            finalWord.append(letters[0]);

            finalWords.append(finalWord + " ");
        }

        if (input.charAt(input.length()-1) != ' ') {
            finalWords.deleteCharAt(finalWords.length()-1);
        }

        return finalWords.toString();
    }

    @Override
    public String decipher(String input) {
        return swap(input);
    }
}
