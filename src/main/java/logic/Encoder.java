package logic;

import logic.codes.Code;
import logic.codes.PigLatin;

import java.util.ArrayList;

public class Encoder {

    ArrayList<Code> translationsToApply = new ArrayList<>();

    private void setup() {
        PigLatin pigLatin = new PigLatin();
        translationsToApply.add(pigLatin);
    }

    public String run (String inputString) {
        setup();

        String text = inputString;
        for (Code c : translationsToApply.stream().toList()) {
            text = c.morph(text);
        }
        return text;
    }
}