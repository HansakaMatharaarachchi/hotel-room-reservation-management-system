package classesQueTask4;//package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Hotel {
    private Room[] rooms = new Room[8];
    // We restrict waiting list to be less than 8.
    // because having too many in waiting list is impractical in real world.
    // Keeps waiting ids in the circular queue.
    private CQueue waitingIDList = new CQueue(8);
    //keep persons mapped to waiting id.
    Map<Integer, Person> waitingGuests = new HashMap<Integer, Person>();

    public Hotel() {
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = new Room();
        }
    }

    public void addCustomersToRooms(Person person) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getPayingGuest() == null) {
                rooms[i].setPayingGuest(person);
                System.out.println("room " + (i + 1) + " is now occupied by " + person.getFirstName());
                return;
            }
        }
        System.out.println("All rooms are already occupied! Therefore adding " + person.getFirstName() + " to waiting list");
        addToWaitingList(person);
    }

    private void addToWaitingList(Person person) {
        Random rand = new Random();
        int waitingID = rand.nextInt(1000);
        waitingGuests.put(waitingID, person);
        waitingIDList.enQueue(waitingID);
    }

    public void viewsAllRooms() {
        for (int x = 0; x < rooms.length; x++) {
            if (rooms[x].getPayingGuest() == null) {
                System.out.println("room " + (x + 1) + " is empty");
            } else {
                System.out.println("room " + (x + 1) + " is occupied by " + rooms[x].getPayingGuest().getFirstName());
            }
        }
    }

    public void displayEmptyRooms() {
        for (int x = 0; x < rooms.length; x++) {
            if (rooms[x].getPayingGuest() == null) {
                System.out.println("room " + (x + 1) + " is empty");
            }
        }
    }

    public void deleteCustomerFromRoom(int roomNumber) {

        if (rooms[roomNumber - 1].getPayingGuest() == null) {
            System.out.println("room " + roomNumber + " is already empty");
        } else {
            rooms[roomNumber - 1].setPayingGuest(null);
            System.out.println("room " + roomNumber + " is now empty");
        }


        if (!waitingIDList.isEmpty()) {
            int waitingID = waitingIDList.deQueue();
            Person person = waitingGuests.remove(waitingID);
            rooms[roomNumber - 1].setPayingGuest(person);
            System.out.println("Customer " + person.getFirstName() + " added successfully to room " + roomNumber + "from waiting list");
        }
    }

    public void findRoomFromCustomerName(String customerName) {
        ArrayList<Integer> customersRooms = new ArrayList<>();
        for (int x = 0; x < rooms.length; x++) {
            if (rooms[x].getPayingGuest() != null && rooms[x].getPayingGuest().getFirstName().equals(customerName)) {
                customersRooms.add(x);
                System.out.print("Room " + (x + 1) + ", ");
            }
        }
        System.out.print("\b\b");

        if (customersRooms.size() == 0) {
            System.out.println(customerName + " is not in our rooms");
        } else if (customersRooms.size() == 1) {
            System.out.println(" is occupied by " + customerName);
        } else {
            System.out.println(" are occupied by " + customerName);
        }
    }

    public void storeDataIntoFile() throws FileNotFoundException {
        PrintWriter writeFile = new PrintWriter(new FileOutputStream("classesStoredData.txt"));
        for (int x = 0; x < rooms.length; x++) {
            if (rooms[x].getPayingGuest() != null) {
                writeFile.println("room " + (x + 1) + " is occupied by: " + rooms[x].getPayingGuest().getFirstName());
            }
        }
        writeFile.close();
        System.out.println("done");
    }

    public void loadDataFromFile() throws FileNotFoundException {
        Scanner file = new Scanner(new File("classesStoredData.txt"));
        String textLine;
        while (file.hasNextLine()) {
            textLine = file.nextLine();
            Person person = new Person();
            person.setFirstName(textLine.substring(23));
            // Setting guest number to 1 for now
            person.setGuestNum(1);
            addCustomersToRooms(person);
        }
    }

    public void viewAlphabeticallyOrderedNames() {
        ArrayList<String> sortedArr = new ArrayList<>();
        for (int x = 0; x < rooms.length; x++) {
            if (rooms[x].getPayingGuest() != null) {
                sortedArr.add(rooms[x].getPayingGuest().getFirstName());
            }
        }

        int n = sortedArr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (isLarge(sortedArr.get(j).toLowerCase(), sortedArr.get(j + 1).toLowerCase())) {
                    String temp = sortedArr.get(j);
                    sortedArr.set(j , sortedArr.get(j + 1));
                    sortedArr.set(j + 1, temp);
                }
            }
        }
        for (String s : sortedArr) {
            System.out.println(s);
        }

    }

    private static boolean isLarge(String first, String second) {
        int firstLen = first.length();
        for (int i = 0; i < firstLen; i++) {
            if (first.charAt(i) > second.charAt(i)) {
                return true;
            } else if (first.charAt(i) < second.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
