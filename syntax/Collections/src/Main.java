import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("January");
        list.add("February");
        list.add("March");
        list.add("April");
        list.add("May");
        list.add("June");
        list.add("July");
        list.add("August");
        list.add("September");
        list.add("October");
        list.add("November");
        list.add("December");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String month = reader.readLine();

        System.out.println(month + " is month " + (list.indexOf(month) + 1));
    }
}