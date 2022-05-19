package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utility {


    /*
        - metoda za formatiranje vremena:
     */
    public static String formatTimeTaken(Duration duration) {
        int minutes = duration.toMinutesPart();
        int seconds = duration.toSecondsPart();
        int miliseconds = duration.toMillisPart();

        return String.format("%d min. %d sec. %d ms.\n", minutes, seconds, miliseconds);
    }

    public List<Contact> readRecords(String path) {

        File file = new File(path);

        List<Contact> recordsForSearching = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                Contact currentContact = createContact(scanner.nextLine());
                recordsForSearching.add(currentContact);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + file);
        }

        return recordsForSearching;
    }

    public List<Contact> readPeople(String path) {

        File file = new File(path);

        List<Contact> peopleToSearch = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                Contact currentContact = createContact(scanner.nextLine());
                peopleToSearch.add(currentContact);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + path);
        }

        return peopleToSearch;
    }

    /*
        - metoda koja provjerava da li se String sastoji isključivo od cifara:
     */
    private boolean isNumeric(String text) {
        return text.chars().allMatch(x -> Character.isDigit(x));
    }

    /*
        - metoda koja mapira redove teksta u "Contact" objekte:
     */
    private Contact createContact(String line) {
        List<String> words = List.of(line.trim().split("\\s+"));
        switch (words.size()) {
            case 1:
                return new Contact.Builder()
                        .firstname(words.get(0))
                        .build();
            case 2:
                // provjera da li je tekst u formatu cifara:
                if (isNumeric(words.get(0))) {
                    return new Contact.Builder()
                            .phonenumber(words.get(0))
                            .firstname(words.get(1))
                            .build();
                }
                return new Contact.Builder()
                        .firstname(words.get(0))
                        .lastname(words.get(1))
                        .build();
            case 3:
                return new Contact.Builder()
                        .phonenumber(words.get(0))
                        .firstname(words.get(1))
                        .lastname(words.get(2))
                        .build();
            default:
                return null;
        }
    }

}
