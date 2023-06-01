package Backend;

import java.util.ArrayList;

public class DisplayText {
    public static void displayIfWordClose(ArrayList<String> arrayWord, ArrayList<Integer> arrayindex){
        System.out.println("Nous sommes dans estProche.");
        for (int i = 0; i < arrayWord.size(); i++){
            System.out.println("mot N. " + (i + 1) + " " + arrayWord.get(i) + " trouvé à au mot numéro : " + arrayindex.get(i));
        }

        System.out.println("\\n\n");
    }

    public static void displaySentenceFromText(String text, int wordIndex) {
        // Divise le texte en mots en utilisant les espaces ou les points comme séparateurs
        String[] mots = text.split("\\s+|\\.");

        System.out.println("\nNous sommes dans displaySentenceFromText : ");
        if (wordIndex < 0 || wordIndex >= mots.length) {
            System.out.println("Index de mot invalide.");
            return;
        }

        int startIndex = Math.max(0, wordIndex - 5);
        int endIndex = Math.min(mots.length - 1, wordIndex + 5);

        for (int i = startIndex; i <= endIndex; i++) {
            if (i == wordIndex) {
                System.out.print("[" + mots[i] + "] ");
            } else {
                System.out.print(mots[i] + " ");
            }
        }
        System.out.println("\n\\n\n");
    }

}
