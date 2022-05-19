package phonebook;

import phonebook.strategy.*;

import java.util.*;

public class Main {
    public static void main(String[] args)  {
        Utility utility = new Utility();

        List<Contact> initialRecords = utility.readRecords("./src/phonebook/files/directory.txt");
        List<Contact> initialPeople = utility.readPeople("./src/phonebook/files/find.txt");

        StrategyContext st = new StrategyContext();

        st.setAlgorithm(new LinearSearch());
        st.beginWorking(initialRecords, initialPeople);

        st.setAlgorithm(new BubbleSortJumpSearch());
        st.beginWorking(initialRecords, initialPeople);

        st.setAlgorithm(new QuickSortBinarySearch());
        st.beginWorking(initialRecords, initialPeople);

        st.setAlgorithm(new HashTableSearch());
        st.beginWorking(initialRecords, initialPeople);

    }
}

