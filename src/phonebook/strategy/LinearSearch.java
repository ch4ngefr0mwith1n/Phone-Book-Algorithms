package phonebook.strategy;

import phonebook.Contact;
import phonebook.Utility;

import java.time.Duration;
import java.util.List;

public class LinearSearch implements SortingAndSearching {

    @Override
    public void start(List<Contact> records, List<Contact> people) {
        System.out.println("Start searching (linear search)...");

        int entriesFound = 0;
        int size = people.size();

        // početno vrijeme u milisekundama:
        long startTime = System.currentTimeMillis();

        for (Contact c1 : people) {
            for (Contact c2 : records) {
                if (c1.equals(c2)) {
                    entriesFound++;
                }
            }
        }

        // završno vrijeme u milisekundama:
        long endTime = System.currentTimeMillis();
        // razlika u milisekundama:
        Duration d = Duration.ofMillis(endTime - startTime);

        System.out.printf("Found %d / %d entries. Time taken: %s\n", entriesFound, size, Utility.formatTimeTaken(d));
    }
}
