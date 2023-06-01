package Backend;

import java.util.ArrayList;

public class PDFSearcher {
    private final ArrayList<String> listWord;
    private final ArrayList<Integer> indexWord;
    private final ArrayList<Data> dataList;
    private final String filePath;

    public PDFSearcher(ArrayList<String> listWord, String filePath){
        this.listWord = listWord;
        this.indexWord = new ArrayList<>();
        this.dataList = new ArrayList<>();
        this.filePath = filePath;
    }

    public boolean ifWordAreCloser(String texte) {
        // Divise le texte en mots en utilisant les espaces ou les points comme séparateurs
        String[] mots = texte.split(CONST.REGEX_SPACE_POINT);

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
                    addData();
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

    private void addData(){
       dataList.add(new Data(CONST.DFT_SCORE, MyMath.calculFirstLastIndex(indexWord), filePath, listWord, indexWord));
    }

    public ArrayList<Integer> getIndexWord() {
        return indexWord;
    }

    public ArrayList<Data> getDataList() {
        return dataList;
    }
}

