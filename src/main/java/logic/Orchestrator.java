package logic;

import logic.codes.Code;
import logic.codes.PigLatin;
import logic.codes.SwapEnds;

import java.util.*;

public class Orchestrator {

    ArrayList<Code> translationsToApply = new ArrayList<>();

    Map<org.funstuff.secretcodes.Code, Code> codeRegistry = Map.of(
            org.funstuff.secretcodes.Code.SWAP_ENDS, new SwapEnds(),
            org.funstuff.secretcodes.Code.PIG_LATIN, new PigLatin()
    );

    public Orchestrator(List<org.funstuff.secretcodes.Code> codesToApply) {
        for (org.funstuff.secretcodes.Code code : codesToApply) {
            if (codeRegistry.get(code) != null) {
                translationsToApply.add(codeRegistry.get(code));
            } else {
                throw new RuntimeException("Unsupported enum value for Code found " + code);
            }
        }
    }

    public String run (String inputString) {
        String text = inputString;
        for (Code c : translationsToApply.stream().toList()) {
            text = c.morph(text);
        }
        return text;
    }
}