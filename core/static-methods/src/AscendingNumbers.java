import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class AscendingNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        final int ARRAY_LENGTH = 5;

//        Create an array with default values of 0
        int[] numbers = new int[ARRAY_LENGTH];

//        Populate array with numbers
        for (int i = 0; i < 5; ++i) {
            int n = Integer.parseInt(r.readLine());

            numbers[i] = n;
        }
        
        Arrays.sort(numbers);

        // Print result
        for (int number: numbers) {
            System.out.println(number);
        }

    }


}
