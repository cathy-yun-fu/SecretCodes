package logic.codes;

import java.util.Arrays;

public class SwapEnds extends Code {
    
    @Override
    public String morph(String input) {
        return swapWrapper(input);
    }

    private String swapWrapper(String input) {
        return super.morphPerWord(input, (input1, wordStartIndex, wordEndIndex, finalWords) -> {
            String word = input1.substring(wordStartIndex, wordEndIndex);

            if (word.isEmpty()) {
                // preserve symbols
                finalWords.append(input1.charAt(wordStartIndex));
            } else if (word.length() == 1) {
                finalWords.append(word);
                appendWordBoundary(input1, wordEndIndex, finalWords);
            } else {
                char[] letters =  word.toCharArray();

                StringBuilder finalWord = new StringBuilder();
                finalWord.append(letters[letters.length-1]);
                finalWord.append(Arrays.copyOfRange(letters, 1, letters.length-1));
                finalWord.append(letters[0]);
                finalWords.append(finalWord);

                appendWordBoundary(input1, wordEndIndex, finalWords);
            }
        });
    }

    @Override
    public String decipher(String input) {
        return swapWrapper(input);
    }

}
