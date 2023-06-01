package Backend;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    public static void displaySentenceFromData(Data data){
        try {
            File file = new File(data.getFilePath());
            PDDocument document = Loader.loadPDF(file);

            PDFTextStripper textStripper = new PDFTextStripper();
            String contentOfPdf = textStripper.getText(document);

           String[] mots = contentOfPdf.split(CONST.REGEX_SPACE_POINT);

            System.out.println("\nNous sommes dans displaySentenceFromData : ");
            // Verify with the first element

            int[] FirstLast = MyMath.calculIdxStartEndSentence(data);
            boolean found;
            for (int i = FirstLast[0]; i <= FirstLast[1]; i++) {
                found = false;
                for (Integer index : data.getIndexList())
                    if (i == index) {
                        System.out.print("[" + mots[i] + "] ");
                        found = true;
                    }
                if (! found) {
                    System.out.print(mots[i] + " ");
                }
            }
            System.out.println("\n\n");

            document.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void displayIfWordClose(Boolean closer){
        if (closer)
            System.out.println("Les mots sont proches.");
        else
            System.out.println("Les mots sont loin.");
    }

    public static void displayData(Data data){
        System.out.println("Infos sur les Datas" + data.getFilePath());
        System.out.println("Nom fichier : " + data.getFilePath());
        System.out.println("score : " + data.getScore());

        for (int i = 0; i < data.getWordList().size(); i++)
            System.out.println("Mot : { " + data.getWordList().get(i) + " }. id : " + data.getIndexList().get(i));

        int[] firstAndLast = data.getFirstAndLast();
        System.out.println("index min : " + firstAndLast[0]);
        System.out.println("index max : " + firstAndLast[1]);
    }

    public static void displayListData(ArrayList<Data> listData){
        for (Data data : listData)
            displayData(data);
        System.out.println("\n\n");
    }
}