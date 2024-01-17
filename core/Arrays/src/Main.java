import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> strings = new ArrayList<>();

        String s = reader.readLine();

        strings.add(s);

        int max = s.length();
        int min = s.length();

        int mi = 0;
        int mni = 0;

        for (int i = 1; i < 5; ++i) {
            s = reader.readLine();

            strings.add(s);

            int sLen = s.length();

            if (sLen > max) {
                mi = i;
                max = sLen;
            }

            if (sLen < min) {
                mni = i;
                min = sLen;
            }
        }

        if (mi < mni)
            System.out.println(strings.get(mi));
        else
            System.out.println(strings.get(mni));
    }
}