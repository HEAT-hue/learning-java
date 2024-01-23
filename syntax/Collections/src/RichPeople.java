import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RichPeople {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Emmanuel", 2000);
        map.put("mmanuel", 1500);
        map.put("manuel", 400);
        map.put("anuel", 300);
        map.put("uel", 200);
        map.put("Emmanue", 100);
        map.put("Emmanu", 2500);
        map.put("Emman", 400);
        map.put("Emma", 500);
        map.put("Em", 700);

        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = iterator.next();

            int salary = pair.getValue();

            if (salary < 500) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();

        removeItemFromMap(map);

        System.out.println(map);
    }
}
