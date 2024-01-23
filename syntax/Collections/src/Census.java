import java.util.HashMap;
import java.util.Map;

public class Census {
    public static HashMap<String, String> createMap() {
        HashMap<String, String> map = new HashMap<>();

        // Add ten entries
        map.put("Emmanuel", "Onyejeme");
        map.put("Emmanuel", "Onyejeme");
        map.put("Emmanuel", "Onyejeme");
        map.put("Emmanuel", "Onyejeme");
        map.put("Emmanuel", "Onyejeme");
        map.put("Onyejeme", "Emmanuel");
        map.put("Onyejem", "Emmanuel");
        map.put("Onyeje", "Emmanuel");
        map.put("Onyej", "Emmanuel");
        map.put("Ony", "Emmanuel");

        return map;
    }

    public static int getSameFirstNameCount(HashMap<String, String> map, String name) {
        int count = 0;

//        Iterate through map
        for (Map.Entry<String, String> pair: map.entrySet()) {
            String pairname = pair.getValue();
            if (pairname.equals(name)){
                count++;
            }
        }

        return count;
    }

    public static int getSameLastNameCount(HashMap<String, String> map, String lastName) {
        //write your code here
        int count = 0;

//        iterate through map
        for (Map.Entry<String, String> pair: map.entrySet()) {
            String pairname = pair.getKey();
            if (pairname.equals(lastName)){
                count++;
            }
        }

        return count;
    }
}
