import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Input
 */
public class Input {

    public static void main(String[] args) throws IOException {
        try {
            // Creates a buffering character-input stream that uses a default-sized input
            // buffer.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Reads a line of text terminated by a carriage return
            String name = bufferedReader.readLine();

            // Reads a line of text terminated by a carriage return
            String sAge = bufferedReader.readLine();

            // Close input stream
            bufferedReader.close();

            int nAge = Integer.parseInt(sAge);

            System.out.println("Name is: " + name + " Age is: " + nAge);

        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}