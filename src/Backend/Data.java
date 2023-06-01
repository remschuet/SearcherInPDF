package Backend;

import java.util.ArrayList;

public class Data {
    private final int score;
    private final int[] firstAndLast;
    private final String filePath;
    private final ArrayList<String> wordList;
    private final ArrayList<Integer> indexList;

    public Data(int score, int[] values, String filePath, ArrayList<String> wordList, ArrayList<Integer> indexList) {
        this.score = score;
        this.firstAndLast = values;
        this.filePath = filePath;
        this.wordList = wordList;
        this.indexList = indexList;
        MyMath.calculIdxStartEndSentence(this);
    }

    public int getScore() {
        return score;
    }

    public int[] getFirstAndLast() {
        return firstAndLast;
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public ArrayList<Integer> getIndexList() {
        return indexList;
    }
}
