import codes.Code;
import codes.PigLatin;
import codes.SwapEnds;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Logic {

    ArrayList<Code> translationsToApply = new ArrayList<>();

    public void setup() {
//        SwapEnds swapEnds = new SwapEnds();
//        translationsToApply.add(swapEnds);

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

    public static void main(String[] args) {

        Logic program = new Logic();
        String output = program.run("this is the input string");
        System.out.println(output);

        // Pig latin expected output:
        // isthay isay ethay inputay ingstray

    }
}