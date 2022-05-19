package phonebook.strategy;

import phonebook.Contact;
import phonebook.Utility;

import java.time.Duration;
import java.util.*;

public class HashTableSearch implements SortingAndSearching{

    public void start(List<Contact> records, List<Contact> people) {
        System.out.println("Start searching (hash table)...");

        // početno vrijeme za ubacivanje u milisekundama:
        long startTimeForFilling = System.currentTimeMillis();
        // popunjavanje mape:
        Map<String, Contact> filledMap = fillHashTable(records);
        int size = people.size();
        // završno vrijeme za sortiranje u milisekundama:
        long endTimeFilling = System.currentTimeMillis();
        // "Duration" objekat za popunjavanje:
        Duration d1 = Duration.ofMillis(endTimeFilling - startTimeForFilling);

        // početno vrijeme za pretragu:
        long startTimeSearching = System.currentTimeMillis();
        // *** PRETRAGA ***
        int count = 0;
        for (Contact c : people) {
            Contact foundOrNot = filledMap.getOrDefault(c.getFirstName(), null);
            if (foundOrNot != null) {
                count++;
            }
        }
        // završno vrijeme za pretragu:
        long endTimeSearching = System.currentTimeMillis();
        // "Duration" objekat za pretragu:
        Duration d2 = Duration.ofMillis(endTimeSearching - startTimeSearching);

        // ukupno trajanje sortiranja i pretrage:
        Duration wholeProcess = Duration.ofMillis(endTimeSearching - startTimeForFilling);

        System.out.printf("Found %d / %d entries. Time taken: %s", count, size, Utility.formatTimeTaken(wholeProcess));
        System.out.printf("Creating time: %s", Utility.formatTimeTaken(d1));
        //System.out.printf("Searching time: %s\n", d2.getNano());
        System.out.printf("Searching time: %s\n", Utility.formatTimeTaken(d2));

    }
    private Map<String, Contact> fillHashTable(List<Contact> list) {
        Map<String, Contact> map = new LinkedHashMap<>();
        list.forEach(x -> map.put(x.getFirstName(), x));

        return map;
    }
}
