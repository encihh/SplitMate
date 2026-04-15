import java.io.IOException;
import java.util.Scanner;

public class Utilities {
      
    Scanner sc = new Scanner(System.in);
    private String input;
    private int choice;
    
    public void display_header() {
        System.out.println("\n");
        System.out.println("\t                    _______  _______  ___      ___   _______  __   __  _______  _______  _______ ");
        System.out.println("\t                   |       ||       ||   |    |   | |       ||  |_|  ||   _   ||       ||       |");
        System.out.println("\t                   |  _____||    _  ||   |    |   | |_     _||       ||  |_|  ||_     _||    ___|");
        System.out.println("\t                   | |_____ |   |_| ||   |    |   |   |   |  |       ||       |  |   |  |   |___ ");
        System.out.println("\t                   |_____  ||    ___||   |___ |   |   |   |  |       ||       |  |   |  |    ___|");
        System.out.println("\t                    _____| ||   |    |       ||   |   |   |  |   _   ||   _   |  |   |  |   |___ ");
        System.out.println("\t                   |_______||___|    |_______||___|   |___|  |__| |__||__| |__|  |___|  |_______|");
        System.out.println("\n");
    }    

    public void display_main_menu(){
        System.out.println("\t                              ==============================");
        System.out.println("\t                              |       MAIN MENU            |");
        System.out.println("\t                              ==============================");
        System.out.println("\t                              | 1. Create new Group        |");
        System.out.println("\t                              | 2. Search Group            |");
        System.out.println("\t                              | 3. Edit Group              |");
        System.out.println("\t                              | 4. Track Payments          |");
        System.out.println("\t                              | 5. Save Summary            |");
        System.out.println("\t                              | 6. Exit                    |");
        System.out.println("\t                              ==============================\n");
    }
    
    public void display_editgroup_menu(){
        System.out.println("\t                              ==============================");
        System.out.println("\t                              |     EDIT GROUP MENU        |");
        System.out.println("\t                              ==============================");
        System.out.println("\t                              | 1. Rename Group            |");
        System.out.println("\t                              | 2. Add Member              |");
        System.out.println("\t                              | 3. Remove Member           |");
        System.out.println("\t                              | 4. Delete Group            |");
        System.out.println("\t                              | 5. Back                    |");
        System.out.println("\t                              ==============================\n");
    }
    
    public void display_trackpayment_menu(){
        System.out.println("\t                              ==============================");
        System.out.println("\t                              |   TRACK PAYMENT MENU       |");
        System.out.println("\t                              ==============================");
        System.out.println("\t                              | 1. Manage Member Share     |");
        System.out.println("\t                              | 2. Payment Record          |");
        System.out.println("\t                              | 3. View Summary            |");
        System.out.println("\t                              | 4. Back                    |");
        System.out.println("\t                              ==============================\n");
    }
    
    public int get_user_input(int min, int max) {
        while (true) {
            try {
                System.out.print("\n                              Please enter your choice: ");
                input = sc.nextLine();
                choice = Integer.parseInt(input);
                
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("\t                              Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("\t                              Invalid input. Please enter a valid number.");
            }
        }
    }    
    
    public static void clear() {
        String os = System.getProperty("os.name");

        try {
            // If it's Windows, use 'cls' to clear the console
            if (os.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                pb.inheritIO().start().waitFor();
            }
            // If it's Unix or Linux, use 'clear' to clear the console
            else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                pb.inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearAndDisplayHeader() {
        clear();
        display_header();
    }
    
    public static void sleep() {
        try {
            Thread.sleep(2000); // Sleep for 3000 milliseconds (3 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
