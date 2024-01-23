import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AlphabeticalOrder {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] array = new String[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = reader.readLine();
        }

        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        //write your code here
        for (int i = 0; i < array.length; ++i) {
            int max = i;

            for (int j = i; j < array.length; ++j) {
                if (isGreaterThan(array[max], array[j])) {
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
        return a.compareTo(b) > 0;
    }
}
