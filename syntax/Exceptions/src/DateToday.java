import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToday {
    public static String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String dateInput = reader.readLine();

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateInput);
        System.out.println(date.toString());

        int monthInt = date.getMonth();
        String month = months[monthInt];
        int day = date.getDate();
        int year = date.getYear();

        System.out.println(month + " " + day + ", " + (year + 1900));

    }
}
