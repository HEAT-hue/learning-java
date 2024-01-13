import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        // Read input from user
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter an input number");

        // Convert input to double
        double t = Double.parseDouble(reader.readLine());

        // Close the input stream reader
        reader.close();

        // Calculate the remainder of t divided by 5, which is the cycle length of the
        // traffic light
        double remainder = t % 5;

        // Declare a string variable to store the color of the light
        String color;

        // Use if-else statements to determine the color based on the remainder
        if (remainder < 3) {
            // The light is green for the first 3 minutes of each cycle
            color = "green";
        } else if (remainder < 4) {
            // The light is yellow for the fourth minute of each cycle
            color = "yellow";
        } else {
            // The light is red for the fifth minute of each cycle
            color = "red";
        }

        // Display the result
        System.out.println("The color of the traffic light is " + color + ".");
    }
}