import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DifferentLetters {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Alphabet
        String abc = "abcdefghijklmnopqrstuvwxyz";
        char[] abcArray = abc.toCharArray();

        ArrayList<Character> alphabet = new ArrayList<>(26);
        for (char letter : abcArray) {
            alphabet.add(letter);
        }

        // Read in strings
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }


        // write your code here
        int[] count = new int[26];
        for (String word : list) {
            for (Character ch : word.toCharArray()) {
                if (!Character.isLetter(ch)) {
                    continue;
                }
                int index = alphabet.indexOf(ch);
                int letterCount = count[index];
                // et count
                count[index] = ++letterCount;
            }

        }

        for (int i = 0; i < alphabet.size(); ++i) {
            Character ch = alphabet.get(i);
            int letterCount = count[i];
            System.out.println(ch + " " + letterCount);
        }
    }
}
