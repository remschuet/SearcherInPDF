package Backend;

import java.util.ArrayList;

public class Frontend {
    public void testWithString(){
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
        String searchWord1 = "fonction";
        String searchWord2 = "renvoyer";
        String searchWord3 = "retour";

        //commence fonction 51, 50

        ArrayList<String> listWord = new ArrayList<>();
        listWord.add(searchWord1);
        listWord.add(searchWord2);
        listWord.add(searchWord3);

        PDFSearcher pdfSearcher = new PDFSearcher(listWord);
        pdfSearcher.AfficherNbrFoisTrouve(filePath);
    }
}
