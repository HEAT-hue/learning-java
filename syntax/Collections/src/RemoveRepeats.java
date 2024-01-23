import java.util.*;

public class RemoveRepeats {
    public static HashMap<String, String> createMap() {
        //write your code here
        HashMap<String, String> map = new HashMap<>();

        // Add ten entries
        map.put("Emmanuel", "Onyejeme");
        map.put("Emmanue", "Onyejeme");
        map.put("Emmanu", "Chioma");
        map.put("Emman", "Lucy");
        map.put("Ony", "Emmanuel");
        map.put("Emma", "Adubi");
        map.put("Onyejeme", "Fasuc");
        map.put("Onyejem", "Daniel");
        map.put("Onyeje", "Moji");
        map.put("Onyej", "Emmanuel");

        return map;
    }

    public static void removeFirstNameDuplicates(Map<String, String> map) {
        List<String> list = new ArrayList<>(map.values());

        for (int i = 0; i < list.size(); ++i) {
            String value = list.get(i);

            for (int j = i + 1; j < list.size(); ++j) {
                String newValue = list.get(j);

                if (newValue.equals(value)) {
                    removeItemFromMapByValue(map, value);
                    break;
                }
            }
        }
    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value)) {
                map.remove(pair.getKey());
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> map = createMap();

        removeFirstNameDuplicates(map);

        System.out.println(map);
    }
}
