package codes;

import logic.codes.SwapEnds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SwapEndsTest {

    // since stateless, we declare one instance for test
    SwapEnds swapEnds = new SwapEnds();

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
        SwapEndsTest.TestParams zeroLengthString = new SwapEndsTest.TestParams("", "");
        SwapEndsTest.TestParams stringWithNoWords = new SwapEndsTest.TestParams("    ","    ");

        Assertions.assertEquals(zeroLengthString.expectedOutput , swapEnds.morph(zeroLengthString.input));
        Assertions.assertEquals(stringWithNoWords.expectedOutput , swapEnds.morph(stringWithNoWords.input));
    }

    @Test
    public void givenPhraseDifferentLeadingOrTrailingSpaces_testMorph_expectSwapEndsPhrase() {
        /*
            This phrase does not have leading or trailing spaces
         */
        SwapEndsTest.TestParams phraseOne = new SwapEndsTest.TestParams("no spaces","on spaces");

        /*
            This phrase does not have spaces at all
         */
        SwapEndsTest.TestParams phraseTwo = new SwapEndsTest.TestParams("whatarespaceseven","nhatarespacesevew");

        /*
            This has multiple leading and trailing spaces
         */
        SwapEndsTest.TestParams phraseThree = new SwapEndsTest.TestParams("   WeeeOneBigWordy     ","   yeeeOneBigWordW     ");

        Assertions.assertEquals(phraseOne.expectedOutput , swapEnds.morph(phraseOne.input));
        Assertions.assertEquals(phraseTwo.expectedOutput , swapEnds.morph(phraseTwo.input));
        Assertions.assertEquals(phraseThree.expectedOutput , swapEnds.morph(phraseThree.input));
    }

    @Test
    public void givenPhraseWithSymbols_testMorph_expectSwapEndsPhrase() {
        SwapEndsTest.TestParams punctuationString = new SwapEndsTest.TestParams(" Should've could've would've but Nope?!?.", " ehould'vS eould'vc eould'vw tub eopN?!?.");

        Assertions.assertEquals(punctuationString.expectedOutput , swapEnds.morph(punctuationString.input));
    }
}
