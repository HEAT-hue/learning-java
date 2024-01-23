import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int odd;
    static int even;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader((new InputStreamReader((System.in))));

        String number = reader.readLine();

        int n = Integer.parseInt(number);

        for (int i = 0; i < number.length(); ++i) {

            int d = n % 10;

            if (d % 2 == 0)
                Main.even++;
            else
                Main.odd++;

            n  = n / 10;
        }

        System.out.println("Even: " + even + " Odd: " + odd);
    }
}