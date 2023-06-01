package Backend;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfMethodes {
    public static String getStringFromPDF(String filePath){
        String contentOfPdf = null;
        try {
            File file = new File(filePath);
            PDDocument document = Loader.loadPDF(file);

            PDFTextStripper textStripper = new PDFTextStripper();
            contentOfPdf = textStripper.getText(document);
            document.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return contentOfPdf;
    }

    public static String getStringFromDATA(Data data){
        return getStringFromPDF(data.getFilePath());
    }
}
