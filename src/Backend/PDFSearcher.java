package Backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFSearcher {
    private final ArrayList<String> listWord;

    public PDFSearcher(ArrayList listWord){
        this.listWord = listWord;
    }

    public void AfficherNbrFoisTrouve(String filePath) {
        try {
            File file = new File(filePath);
            PDDocument document = Loader.loadPDF(file);

            PDFTextStripper textStripper = new PDFTextStripper();
            String content = textStripper.getText(document);

            if (estProche(content))
                System.out.println("Les mots sont proches");
            else
                System.out.println("Les mots sont loins");

            document.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean estProche(String texte) {
        // Divise le texte en mots en utilisant les espaces ou les points comme séparateurs
        String[] mots = texte.split("\\s+|\\.");

        ArrayList<Integer> indexWord = new ArrayList<>();
        for (int i = 0; i < listWord.size(); i++) {
            indexWord.add(i, -1);
        }

        int indexMot1 = -1;
        int indexMot2 = -1;

        for (int posCurWord = 0; posCurWord < mots.length; posCurWord++) {
            // Supprime les points du mot courant
            // FIX ME, ajouter les lettres majuscules
            String motCourant = mots[posCurWord].replaceAll("\\.", "");

            // Ajouter l'index si le mot est bon
            for(String word : listWord)
                // S'ils sont égaux
                if (motCourant.equals(word))
                    // Mettre dans indexWord a la bonne pos l'index
                    indexWord.set(listWord.indexOf(word), posCurWord);


            boolean wordFound = true;
            for (int i = 0; i < listWord.size(); i++)
                if (indexWord.get(i) == -1) {
                    wordFound = false;
                    break;
                }

            if (wordFound) {
                int distanceList = Math.abs(indexWord.get(0) - indexWord.get(indexWord.size() - 1));
                if (indexWord.get(0) < indexWord.size() - 1)
                    distanceList = Math.abs(indexWord.get(indexWord.size() - 1) - indexWord.get(0));

                if (distanceList <= CONST.WORD_DIST) {
                    DisplayText.displayIfWordCloseArray(listWord, indexWord);
                    DisplayText.displaySentenceFromText(texte, indexWord.get(0));
                    return true;
                }
                else
                    // Réinitialise les index pour rechercher d'autres occurrences
                    for(int index : indexWord)
                        index = -1;
            }
        }
        return false;
    }
}

