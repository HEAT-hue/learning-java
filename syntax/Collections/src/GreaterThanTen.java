import java.util.HashSet;

public class GreaterThanTen {
    public static HashSet<Integer> createSet() {
        // Create a hash set hee
        HashSet<Integer> set = new HashSet<>();

//      Add 20 numbers to the set
        for (int i = 5; i < 30; ++i) {
            set.add(i);
        }

        return set;
    }

    public static HashSet<Integer> removeAllNumbersGreaterThan10(HashSet<Integer> set) {
        // Create a new set and copy all numbers less than 10
        HashSet<Integer> newSet = new HashSet<>();

        for (int n: set){
            if (n < 11) {
                newSet.add(n);
            }
        }

        return newSet;
    }
}
