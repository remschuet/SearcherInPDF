package Backend;

import java.util.ArrayList;

public class Frontend {
    public void testWithString() {
        // USELESS
        String texte = "Le chat est sur le tapis. Le chat dort sur le tapis.";
        String mot1 = "chat";
        String mot2 = "tapis";

        ArrayList<String> listWord = new ArrayList<>();
        listWord.add(mot1);
        listWord.add(mot2);

        //PDFSearcher pdfSearcher = new PDFSearcher();
        //pdfSearcher.ifWordAreCloser(texte);
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
        PDFSearcher pdfSearcher = new PDFSearcher(listWord, filePath);

        // Get content in String from pdf
        String contentPDF = PdfMethodes.getStringFromPDF(filePath);
        // Get if word a close
        boolean isClose = pdfSearcher.ifWordAreCloser(contentPDF);

        // Display
        DisplayText.displayIfWordClose(isClose);
        DisplayText.displayWordAndPosition(listWord, pdfSearcher.getIndexWord());

       ArrayList<Data> listData = pdfSearcher.getDataList();
       DisplayText.displayListData(listData);

       DisplayText.displaySentenceFromData(listData.get(0));


        MyMath.calculIdxStartEndSentence(listData.get(0));

    }
}