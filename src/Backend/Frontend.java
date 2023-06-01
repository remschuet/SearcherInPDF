package Backend;

import java.util.ArrayList;
import java.util.HashMap;

public class Frontend {
    public void testWithString() {
        // USELESS
        String texte = "Le chat est sur le tapis. Le chat dort sur le tapis.";
        String mot1 = "chat";
        String mot2 = "tapis";

        ArrayList<String> listWord = new ArrayList<>();
        listWord.add(mot1);
        listWord.add(mot2);

        PDFSearcher pdfSearcher = new PDFSearcher(listWord);
        pdfSearcher.ifWordAreCloser(texte);
    }

    public void testWithPdf(){
        String filePath = "test.pdf";
        String searchWord1 = "avoir";
        String searchWord2 = "sans";
        String searchWord3 = "fonction";
        //commence fonction 51, 50

        // add ArrayList of word searching
        ArrayList<String> listWord = new ArrayList<>();
        listWord.add(searchWord1);
        listWord.add(searchWord2);
        listWord.add(searchWord3);

        // Create PDFSearcher
        PDFSearcher pdfSearcher = new PDFSearcher(listWord);

        // Get content in String from pdf
        String contentPDF = pdfSearcher.getStringFromPDF(filePath);
        // Get if word a close
        boolean isClose = pdfSearcher.ifWordAreCloser(contentPDF);
        // Get index and score
        HashMap<Integer, Integer> hashMapScore = pdfSearcher.getIndexWithScore();

        // Display
        DisplayText.displayIfWordClose(isClose);
        DisplayText.displayWordAndPosition(listWord, pdfSearcher.getIndexWord());
        DisplayText.displaySentenceFromText(contentPDF, pdfSearcher.getIndexWord(), MyMath.calculFirstLastIndex(pdfSearcher.getIndexWord()));
    }
}