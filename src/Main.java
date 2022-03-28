package classesQueTask4;//package classes;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    private static Hotel hotel = new Hotel();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter input");
            String usrInput = input.next();
            switch (usrInput) {
                case "A":
                    System.out.println("Enter The number of guests for the room.");
                    int numberOfGuests = input.nextInt();
                    System.out.println("Additional information for the paying guest.");
                    System.out.println("Enter The first Name:");
                    input.nextLine();
                    String firstName = input.nextLine();
                    System.out.println("Enter The surname:");
                    String surName = input.nextLine();
                    System.out.println("Enter The Credit Card number.");
                    String creditCardNumber = input.nextLine();
                    Person person = new Person();
                    person.setCreditCardNumber(creditCardNumber);
                    person.setSurName(surName);
                    person.setFirstName(firstName);
                    person.setGuestNum(numberOfGuests);
                    hotel.addCustomersToRooms(person);
                    break;
                case "V":
                    hotel.viewsAllRooms();
                    break;
                case "E":
                    hotel.displayEmptyRooms();
                    break;
                case "D":
                    System.out.println("Enter Room Number Of The Customer You Want To Delete:");
                    int roomNum = input.nextInt();
                    hotel.deleteCustomerFromRoom(roomNum);
                    break;

                case "F":
                    System.out.println("Enter Customers Name to find room(s):");
                    String customerName = input.next();
                    hotel.findRoomFromCustomerName(customerName);
                case "S":
                    hotel.storeDataIntoFile();
                    break;
                case "L":
                    hotel.loadDataFromFile();
                    break;
                case "O":
                    System.out.println("Alphabetically Ordered Guest Names List:");
                    hotel.viewAlphabeticallyOrderedNames();
                    break;
                default:
                    System.out.println("input error");
            }
        }

    }
}

