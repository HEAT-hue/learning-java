import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class National {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s = reader.readLine();

        char[] charArray = s.toCharArray();

        String currentWord = "";

        String result = "";

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];

            if (!Character.isLetter(ch) || i == charArray.length - 1) {

                if (!currentWord.isEmpty()) {
                    String upStr = capitalize(currentWord);
                    result += upStr;
                    currentWord = "";
                }

                result += ch;

                continue;
            }

            currentWord += ch;
        }

        System.out.println(result);
    }

    public static String capitalize(String s) {

        if (s.isEmpty()) {
            return s;
        }

        String newString = s.substring(0, 1).toUpperCase() + s.substring(1);
        return newString;
    }
}
