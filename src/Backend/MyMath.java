package Backend;

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
}
