package codes;

import logic.codes.PigLatin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PigLatinTest {

    // since stateless, we declare one instance for test
    PigLatin pigLatin = new PigLatin();

    private static class TestParams {
        String input;
        String expectedOutput;

        TestParams(String input, String expectedOutput){
            this.input = input;
            this.expectedOutput = expectedOutput;
        }
    }

    @Test
    public void givenEmptyString_testMorph_expectEmptyString() {
        TestParams zeroLengthString = new TestParams("", "");
        TestParams stringWithNoWords = new TestParams("    ","    ");

        Assertions.assertEquals(zeroLengthString.expectedOutput , pigLatin.morph(zeroLengthString.input));
        Assertions.assertEquals(stringWithNoWords.expectedOutput , pigLatin.morph(stringWithNoWords.input));
    }

    @Test
    public void givenPhraseWithVowels_testMorph_expectPigLatinPhrase() {
        /*
            This tests pig latin on words that do not start with vowels. A 'y' at the start of a word is not treated
            as a vowel.
            There is a leading space and an ending space that is expected to be preserved.
         */
        TestParams phraseOne = new TestParams(" No vowels yet Here t ", " oNay owelsvay etyay ereHay t ");

        /*
            This tests pig latin on words starting with vowels.
            This also tests that a 'y' not at the start is treated as a vowel.
         */
        TestParams phraseTwo = new TestParams(" oweee you are embarrassing me cyan "," oweeeay ouyay areay embarrassingay emay yancay ");

        Assertions.assertEquals(phraseOne.expectedOutput , pigLatin.morph(phraseOne.input));
        Assertions.assertEquals(phraseTwo.expectedOutput , pigLatin.morph(phraseTwo.input));
    }

    @Test
    public void givenPhraseDifferentLeadingOrTrailingSpaces_testMorph_expectPigLatinPhrase() {
        /*
            This phrase does not have leading or trailing spaces
         */
        TestParams phraseOne = new TestParams("no spaces around the output eh a t","onay acesspay arounday ethay outputay ehay a t");

        /*
            This phrase does not have spaces at all
         */
        TestParams phraseTwo = new TestParams("whatarespaceseven","atarespacesevenwhay");

        /*
            This has multiple leading and trailing spaces
         */
        TestParams phraseThree = new TestParams("   weeeOneBigWordy     ","   eeeOneBigWordyway     ");

        Assertions.assertEquals(phraseOne.expectedOutput , pigLatin.morph(phraseOne.input));
        Assertions.assertEquals(phraseTwo.expectedOutput , pigLatin.morph(phraseTwo.input));
        Assertions.assertEquals(phraseThree.expectedOutput , pigLatin.morph(phraseThree.input));
    }

    @Test
    public void givenPhraseWithSymbols_testMorph_expectPigLatinPhrase() {
        TestParams punctuationString = new TestParams(" should've could've would've but nope?!?.", " ould'veshay ould'vecay ould'veway utbay openay?!?.");

        Assertions.assertEquals(punctuationString.expectedOutput , pigLatin.morph(punctuationString.input));
    }
}
