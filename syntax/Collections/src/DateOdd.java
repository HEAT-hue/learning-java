import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * When working with dates, you need to specify the format of the string passed
 *
 * */

public class DateOdd {
    public static void main(String[] args) throws ParseException {
        System.out.println(isDateOdd("MAY 2 2013"));
    }

    public static boolean isDateOdd(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM d yyyy");
        Date fdate = dateFormat.parse(date);
        Date sdate = dateFormat.parse("JANUARY 1 " + (fdate.getYear() + 1900));

        long smilli = sdate.getTime();
        long fmili = fdate.getTime();

        long diff = fmili - smilli;

        long days = diff / (1000 * 60 * 60 * 24);

        return days % 2 == 0;
    }
}
