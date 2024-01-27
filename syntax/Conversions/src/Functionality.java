import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Functionality {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> map = new HashMap<>();

        while (true) {
            String number = reader.readLine();
            if (number.isEmpty()) {
                break;
            }

            String word = reader.readLine();
            if (word.isEmpty()) {
                map.put(" ", number);
                break;
            }

            map.put(word, number);
        }

        for (Map.Entry<String, String> pair : map.entrySet()) {
            String key = pair.getKey();
            String value = pair.getValue();
            System.out.println(value + " " + key);
        }
    }
}
