package api;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getInstance();

    /**
     * method that allows staff to navigate AdminiView menu
     */
    public static void selectAdminiViewOptions() {

        int adminSelection = 0;
        try {
            adminSelection = adminiViewOptions();
        } catch (InputMismatchException e) {
            System.out.println("Please make sure you only enter numbers that " +
                    "are available to select on your menu.  Please re-enter your selection.\n");
        }

        switch (adminSelection) {
            case 1 -> //method to view all customers created in system
                    seeAllCustomers();
            case 2 -> //method to view all rooms created in system
                    seeAllRooms();
            case 3 -> //method to view all reservations created in system
                    seeAllReservations();
            case 4 -> //method to retrieve/open admin menu
                    addARoom();
            case 5 ->  //method to return back to main menu
                    returnToMainMenu();
            case 6 -> //exits application
                    System.exit(0);
            default -> selectAdminiViewOptions();

        }
    }

    public static int adminiViewOptions() {
        System.out.println("Welcome to AdminiView");
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("1: See all customers");
        System.out.println("2: See all rooms");
        System.out.println("3: See all reservations");
        System.out.println("4: Add a room");
        System.out.println("5: Back to Main Menu");
        System.out.println("--------------------------------------------------");
        System.out.println("Please make your selection.");
        System.out.println();

        Scanner option = new Scanner(System.in);
        int adminSelection = option.nextInt();
        System.out.println("Thank you for selection, you will now be taken to the appropriate page.");
        System.out.println();
        return adminSelection;
    }

    /**
     * method that allows staff to see a full listing of all customers created within system
     */
    public static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        System.out.println();
        selectAdminiViewOptions();
    }

    /**
     * method that allows staff to see a full listing of all rooms created within system
     */
    public static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        rooms.forEach(System.out::println);
        System.out.println();
        selectAdminiViewOptions();
    }

    /**
     * method that allows staff to see a full listing of all reservations created within system
     */
    public static void seeAllReservations() {
        AdminResource.displayAllReservations();
        selectAdminiViewOptions();
    }

    /**
     * @throws NumberFormatException  exception to catch faulty/bad input for room number
     * @throws InputMismatchException exception to catch faulty/bad input for room price and/or room type
     *                                method to add a room to the Collection<IRoom> rooms to store all rooms created within app
     */
    public static void addARoom() throws NumberFormatException, InputMismatchException {
        //scanner to capture user input
        Scanner input = new Scanner(System.in);

        //capture room number to add to list of rooms
        System.out.println("Enter room number: ");
        String roomNumber = input.nextLine();
        try {
            Integer.parseInt(roomNumber);
        } catch (NumberFormatException e) {
            System.out.println("Please be sure to input only " +
                    "numeric values for the room number(s).\n");
            System.out.println("Please try again.\n");
            addARoom();
        }

        //capture the price of the room
        double price = 0;

        System.out.println("Enter room price: ");

        while (!input.hasNextDouble()) {
            System.out.println("Error. Price must be a Number >=0!");
            input.nextLine();
        }
        price = input.nextDouble();

        //capture room type
        System.out.println("Enter room type: SINGLE(1) / DOUBLE(2): ");
        RoomType roomType = null;
        int choice=0;
        while(choice!=1 && choice !=2){
            try{
                choice=input.nextInt();
                if(choice!=1 && choice!=2){
                    System.out.println("Invalid input, Insert 1 for SINGLE or 2 for DOUBLE");
                    choice = input.nextInt();
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input, Insert 1 for SINGLE or 2 for DOUBLE");
                input.next();
            }
        }
        if(choice==1){
            roomType= RoomType.SINGLE;
        }
        else if(choice ==2){
            roomType= roomType.DOUBLE;
        }

        IRoom room = new Room(roomNumber, price, roomType);
        List<IRoom> rooms = new ArrayList<>();
        rooms.add(room);

        System.out.println("Would you like to add another room: Y/N?");
        String roomOption = input.next().toUpperCase();
        switch (roomOption) {
            case "Y" -> {
                adminResource.addRoom(rooms);
                addARoom();
            }
            case "N" -> {
                adminResource.addRoom(rooms);
                selectAdminiViewOptions();
                System.out.println();
            }
            default -> selectAdminiViewOptions();

        }
    }

    /**
     * method to return to main menu of application
     */
    public static void returnToMainMenu() {
        MainMenu.selectMainMenuOptions();
    }
}