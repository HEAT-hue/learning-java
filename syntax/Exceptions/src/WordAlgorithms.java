import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WordAlgorithms {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();

        // read list of words from keyboard
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) {
                break;
            }
            list.add(s);
        }

        String[] array = list.toArray(new String[list.size()]);

        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        //write your code here

        for (int i = 0; i < array.length; ++i) {
            int max = i;
            boolean isWord = !isNumber(array[i]);

            for (int j = i; j < array.length; ++j) {
                boolean isCurrentWord = !isNumber(array[j]);

                if (isCurrentWord != isWord) {
                    continue;
                }

                if (!isWord) {
                    if (Integer.parseInt(array[max]) > Integer.parseInt(array[j])) {
                        max = j;
                    }
                }

                else if (isGreaterThan(array[max], array[j])) {
                    max = j;
                }
            }

            String temp = array[i];
            array[i] = array[max];
            array[max] = temp;
        }
    }

    // String comparison method: 'a' is greater than 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareToIgnoreCase(b) > 0;
    }


    // Is the passed string a number?
    public static boolean isNumber(String s) {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // The string contains a hyphen
                    || (!Character.isDigit(c) && c != '-') // or is not a number and doesn't start with a hyphen
                    || (i == 0 && c == '-' && chars.length == 1)) // or is a single hyphen
            {
                return false;
            }
        }
        return true;
    }
}
