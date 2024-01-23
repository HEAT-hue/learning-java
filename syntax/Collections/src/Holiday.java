import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Holiday {
    public static HashMap<String, Date> createMap() throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMMM d yyyy");

        HashMap<String, Date> map = new HashMap<String, Date>();

        // Add 10 names and date entries
        map.put("Stailone", (Date) df.parse("JUNE 12 1980"));
        map.put("Stailon", (Date) df.parse("JULY 1 1980"));
        map.put("Stailo", (Date) df.parse("AUGUST 1 1980"));
        map.put("Stail", (Date) df.parse("JANUARY 1 1980"));
        map.put("Stai", (Date) df.parse("FEBRUARY 1 1980"));
        map.put("Stalone", (Date) df.parse("MARCH 1 1980"));
        map.put("Stlone", (Date) df.parse("APRIL 1 1980"));
        map.put("Silone", (Date) df.parse("MAY 1 1980"));
        map.put("tailone", (Date) df.parse("JUNE 12 1980"));
        map.put("Stilone", (Date) df.parse("JULY 13 1980"));
        map.put("Staone", (Date) df.parse("AUGUST 14 1980"));

        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
        // Get map iterator
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();

        // Iterate through map while removing months JUNE | JULY | AUGUST
        while (iterator.hasNext()) {

            // Get entry
            Map.Entry<String, Date> pair = iterator.next();

            // Get month
            int month = pair.getValue().getMonth();

            // Compare if month is JUNE | JULY |AUGUST
            if (month > 4 && month < 9) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        // Call method to create map
        HashMap<String, Date> map = createMap();

        // Remove months JUNE | JULY | AUGUST
        removeAllSummerPeople(map);

        // Advanced loop to print out result
        for (Map.Entry<String, Date> pair : map.entrySet()) {
            String name = pair.getKey();
            Date date = pair.getValue();
            System.out.println(name + " " + date);
        }
    }
}
