package Backend;

import java.util.ArrayList;

public class MyMath {

    public static int countOccurrences(String content, String searchWord) {
        int count = 0;
        int startIndex = 0;

        while ((startIndex = content.toLowerCase().indexOf(searchWord.toLowerCase(), startIndex)) != -1) {
            count++;
            startIndex += searchWord.length();
        }

        return count;
    }

    public static int[] calculIdxStartEndSentence(Data data){
        // Calcul the index of the first and the last index in the sentence
        String contentOfPdf = PdfMethodes.getStringFromDATA(data);

        // split for the space
        String[] mots = contentOfPdf.split(CONST.REGEX_SPACE);

        int[] firstAndLast = data.getFirstAndLast();

        // Display first word of the sentence
        int firstWordIdx = firstAndLast[0];
        int lastWordIdx = firstAndLast[1];

        while (firstWordIdx > 0 && !mots[firstWordIdx - 1].endsWith("."))
            firstWordIdx--;

        while (lastWordIdx < mots.length && !mots[lastWordIdx].endsWith("."))
            lastWordIdx++;

        System.out.println("First Word : " + mots[firstWordIdx]);
        System.out.println("Last Word : " + mots[lastWordIdx]);

        return new int[]{firstWordIdx, lastWordIdx};

    }

    public static int[] calculFirstLastIndex(ArrayList<Integer> wordIndex){
        int startIndex = wordIndex.get(0);
        int lastIndex = wordIndex.get(0);

        for (Integer index : wordIndex) {
            if (index > lastIndex)
                lastIndex = index;
            else if (index < startIndex)
                startIndex = index;
        }

//        startIndex -= CONST.NBR_WORD_DISPLAY;
  //      lastIndex += CONST.NBR_WORD_DISPLAY;

        if (startIndex < 0)
            startIndex = 0;
        if (lastIndex < 0)
            lastIndex = 0;

        return new int[]{startIndex, lastIndex};
    }
}
