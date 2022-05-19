package phonebook.strategy;

import phonebook.Contact;

import java.util.List;

public class StrategyContext {

    private SortingAndSearching algorithm;

    public void setAlgorithm(SortingAndSearching algorithm) {
        this.algorithm = algorithm;
    }

    public void beginWorking(List<Contact> records, List<Contact> people) {
        this.algorithm.start(records, people);
    }
}
