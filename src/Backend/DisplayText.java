package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayText {
    public static void displayWordAndPosition(ArrayList<String> arrayWord, ArrayList<Integer> arrayIndex){
        System.out.println("Nous sommes dans displayWordAndPosition.");
        for (int i = 0; i < arrayWord.size(); i++){
            System.out.println("mot N. " + (i + 1) + " " + arrayWord.get(i) + " trouvé à au mot numéro : " + arrayIndex.get(i));
        }

        System.out.println("\\n\n");
    }

    public static void displayWithScore(){
        return;
    }

    public static void displaySentenceFromText(String text, ArrayList<Integer> wordIndex, int[] FirstLast) {
        // Divise le texte en mots en utilisant les espaces ou les points comme séparateurs
        String[] mots = text.split("\\s+|\\.");

        System.out.println("\nNous sommes dans displaySentenceFromText : ");
        // Verify with the first element
        if (wordIndex.get(0) < 0 || wordIndex.get(0) >= mots.length) {
            System.out.println("Index de mot invalide.");
            return;
        }

        boolean found;
        for (int i = FirstLast[0]; i <= FirstLast[1]; i++) {
            found = false;
            for (Integer index : wordIndex)
                if (i == index) {
                    System.out.print("[" + mots[i] + "] ");
                    found = true;
            }
            if (! found) {
                System.out.print(mots[i] + " ");
            }
        }
        System.out.println("\n\\n\n");
    }

    public static void displayIfWordClose(Boolean closer){
        if (closer)
            System.out.println("Les mots sont proches.");
        else
            System.out.println("Les mots sont loin.");
    }
}