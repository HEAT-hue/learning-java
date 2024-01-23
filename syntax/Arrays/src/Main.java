import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SimpleTimeZone;

public class Main {

    public static void main(String[] args) throws IOException {
        int[] list = {7, 87, 3, 23, 8};

        for (int i = 0; i < list.length; ++i) {
            int max = i;

            // Go through the list to find the max
            for (int j = i; j < list.length; ++j) {
                if (list[j] > list[max]) {
                    max = j;
                }
            }

            int temp = list[i];
            list[i] = list[max];
            list[max] = temp;
        }

        for (int s : list) {
            System.out.println(s);
        }

    }
}