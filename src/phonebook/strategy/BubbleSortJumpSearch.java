package phonebook.strategy;

import phonebook.Contact;
import phonebook.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BubbleSortJumpSearch implements SortingAndSearching{

    public void start(List<Contact> records, List<Contact> people) {
        List<Contact> recordsCopy = new ArrayList<>(records);
        List<Contact> peopleCopy = new ArrayList<>(people);

        System.out.println("Start searching (bubble sort + jump search)...");

        // početno vrijeme za sortiranje u milisekundama:
        long startTimeForSorting = System.currentTimeMillis();
        // *** SORTIRANJE **
        bubbleSort(recordsCopy);
        // završno vrijeme za sortiranje u milisekundama:
        long endTimeSorting = System.currentTimeMillis();
        // "Duration" objekat za sortiranje:
        Duration d1 = Duration.ofMillis(endTimeSorting - startTimeForSorting);

        // početno vrijeme za pretragu:
        long startTimeSearching = System.currentTimeMillis();
        // *** PRETRAGA ***
        int size = peopleCopy.size();
        int count = 0;
        for (Contact c : peopleCopy) {
            Contact foundOrNot = jumpSearch(recordsCopy, c);
            if (foundOrNot != null) {
                count++;
            }
        }

        // završno vrijeme za pretragu:
        long endTimeSearching = System.currentTimeMillis();
        // "Duration" objekat za pretragu:
        Duration d2 = Duration.ofMillis(endTimeSearching - startTimeSearching);

        // ukupno trajanje sortiranja i pretrage:
        Duration wholeProcess = Duration.ofMillis(endTimeSearching - startTimeForSorting);

        System.out.printf("Found %d / %d entries. Time taken: %s", count, size, Utility.formatTimeTaken(wholeProcess));
        System.out.printf("Sorting time: %s", Utility.formatTimeTaken(d1));
        System.out.printf("Searching time: %s\n", Utility.formatTimeTaken(d2));
    }

    /*
        - implementacija "Jump Search" pretrage:
     */
    private Contact jumpSearch(List<Contact> recordsList, Contact currentContact) {

        int blockSize = (int) Math.floor(Math.sqrt(recordsList.size()));
        int currentLastIndex = blockSize - 1;

        while (currentLastIndex < recordsList.size() &&
                currentContact.compareTo(recordsList.get(currentLastIndex)) > 0) {

            currentLastIndex += blockSize;

        }

        for (int currentSearchIndex = currentLastIndex - blockSize + 1;
             currentSearchIndex < recordsList.size();
             currentSearchIndex++) {

            if (recordsList.get(currentSearchIndex).equals(currentContact)) {
                return recordsList.get(currentSearchIndex);
            }

        }
        return null;
    }

    /*
        - "bubble sort" koje sortira prema String polju objekta
     */
    private void bubbleSort(List<Contact> list) {

        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n -i - 1; j++) {

                if (list.get(j).compareTo(list.get(j + 1)) > 0) {

                    Contact swapContact = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, swapContact);
                }
            }
        }
    }
}
