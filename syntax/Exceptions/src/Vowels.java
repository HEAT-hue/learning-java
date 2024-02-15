import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Vowels {

//    Hold set of vowels
    public static Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) throws IOException {
        // File stream to read data
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // read data from user
        String word = reader.readLine();

        // Concatenate each vowel or consonant gotten to produce final result
        String vowels = "";
        String consonants = "";

        // Convert string to char array
        for (char c : word.toCharArray()) {

            // Check if character is vowel or consonant
            if (!Character.isWhitespace(c)) {
                if (isVowel(c)) {
                    vowels += c;
                } else {
                    consonants += c;
                }
            }
        }

        // Print result with space attached in between
        printCharWithSpace(vowels);
        printCharWithSpace(consonants);
    }

    /*
    * Check if a character is vowel or not
    * */
    public static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return vowels.contains(c);
    }

    public static void printCharWithSpace(String s) {
        String word = "";
        for (char ch : s.toCharArray()) {
            word += " " + ch;
        }

        System.out.println(word.substring(1));
    }
}