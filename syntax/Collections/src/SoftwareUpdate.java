import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SoftwareUpdate {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // List of addresses
        List<String> addresses = new ArrayList<>();

        while (true) {
            String family = reader.readLine();
            if (family.isEmpty()) {
                break;
            }

            addresses.add(family);
        }

        // Read the house number
        String city = reader.readLine();

        int index = addresses.indexOf(city);

        System.out.println(addresses.get(index + 1));
    }
}
