package phonebook.strategy;

import phonebook.Contact;
import phonebook.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSortBinarySearch implements SortingAndSearching{
    public void start(List<Contact> records, List<Contact> people) {
        List<Contact> recordsCopy = new ArrayList<>(records);
        List<Contact> peopleCopy = new ArrayList<>(people);

        System.out.println("Start searching (quick sort + binary search)...");

        // početno vrijeme za sortiranje u milisekundama:
        long startTimeForSorting = System.currentTimeMillis();
        // *** SORTIRANJE **
        quickSort(recordsCopy, 0, recordsCopy.size() -1);
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
            Contact foundOrNot = binarySearch(recordsCopy, c);
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


    private Contact binarySearch(List<Contact> recordsList, Contact currentContact) {
        int index = Collections.binarySearch(recordsList, currentContact);

        if (index != -1) {
            return recordsList.get(index);
        }
        return null;
    }

    public void quickSort(List<Contact> list, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(list, left, right);
            quickSort(list, left, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, right);
        }
    }

    public int partition(List<Contact> list, int left, int right) {
        int partitionIndex = left;
        Contact pivot = list.get(right);

        for (int i = left; i < right; i++) {
            if (list.get(i).compareTo(pivot) < 0) {
                swap(list, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(list, partitionIndex, right);

        return partitionIndex;
    }

    private void swap(List<Contact> list, int i, int j) {
        Contact temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }


}
