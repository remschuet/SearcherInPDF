package Backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFSearcher {
    private final ArrayList<String> listWord;
    private final ArrayList<Integer> indexWord;

    public PDFSearcher(ArrayList<String> listWord){
        this.listWord = listWord;
        this.indexWord = new ArrayList<>();
    }

    public void AfficherNbrFoisTrouve(String filePath) {
        try {
            File file = new File(filePath);
            PDDocument document = Loader.loadPDF(file);

            PDFTextStripper textStripper = new PDFTextStripper();
            String content = textStripper.getText(document);

            if (ifWordAreCloser(content))
                System.out.println("Les mots sont proches");
            else
                System.out.println("Les mots sont loins");

            document.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean ifWordAreCloser(String texte) {
        // Divise le texte en mots en utilisant les espaces ou les points comme séparateurs
        String[] mots = texte.split("\\s+|\\.");

        resetIndexWordList();

        for (int idxCurrentWord = 0; idxCurrentWord < mots.length; idxCurrentWord++) {
            // Supprime les points des mots courant                         
            // FIX ME, ajouter les lettres majuscules
            String motCourant = mots[idxCurrentWord].replaceAll("\\.", "");

            // verify if words are equals
            setIdxIfWordEqual(motCourant, idxCurrentWord);

            if (areAllWordFound()) {
                int distance = calculDistBtwFirstAndLast();

                if (distance <= CONST.WORD_DIST) {
                    DisplayText.displayIfWordClose(listWord, indexWord);
                    DisplayText.displaySentenceFromText(texte, indexWord);
                    return true;
                }
                else
                    // Réinitialise les index pour rechercher d'autres occurrences
                    resetIndexWordList();
            }
        }
        return false;
    }

    private void resetIndexWordList(){
        // Reset to -1 indexWord
        for (int i = 0; i < listWord.size(); i++)
            indexWord.add(i, -1);
    }
    
    private void setIdxIfWordEqual(String motCourant, int idxCurrentWord){
        // Set index if word are equals
        for(String word : listWord)
            // S'ils sont égaux
            if (motCourant.equals(word))
                // Mettre dans indexWord a la bonne pos l'index
                indexWord.set(listWord.indexOf(word), idxCurrentWord);
    }
    
    private boolean areAllWordFound(){
        // Verify if all word are not equal to -1 (not found)
        for (int i = 0; i < listWord.size(); i++)
            if (indexWord.get(i) == -1)
                return false;
        return true;
    }
    
    private int calculDistBtwFirstAndLast(){
        // calcul distance between the first and the last word
        int distance = Math.abs(indexWord.get(0) - indexWord.get(indexWord.size() - 1));
        if (indexWord.get(0) < indexWord.size() - 1)
            distance = Math.abs(indexWord.get(indexWord.size() - 1) - indexWord.get(0));
        return distance;
    }
}

