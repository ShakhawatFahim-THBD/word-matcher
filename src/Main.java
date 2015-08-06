import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final String dictionaryFilePath = "/resources/file/dictionary.txt";
    private static final String contentFilePath = "/resources/file/content.txt";
    private static final String endOfFileRegex = "\\Z";
    private static final String whiteSpaceRegex = "\\s+";

    public static void main(String[] args) throws FileNotFoundException {
        Set<String> dictionaryWordSet = getMatcherDictionary();
        Set<String> contentWordSet = getContentWordSet();

        Set<String> intersectionSet = new HashSet<String>(dictionaryWordSet);
        intersectionSet.retainAll(contentWordSet);

        System.out.println("Total match: " + intersectionSet.size());
        System.out.println("Matches: " + intersectionSet);

    }

    public static Set<String> getMatcherDictionary() throws FileNotFoundException {
        return extractWordSetFromFile(dictionaryFilePath);
    }

    public static Set<String> getContentWordSet() {
        return extractWordSetFromFile(contentFilePath);
    }

    public static Set<String> extractWordSetFromFile(String filePath) {
        InputStream inputStream = Main.class.getResourceAsStream(filePath);
        Scanner scan = new Scanner(inputStream).useDelimiter(endOfFileRegex);
        String inputStr = scan.next();

        return new HashSet<String>(Arrays.asList(inputStr.split(whiteSpaceRegex)));
    }
}
