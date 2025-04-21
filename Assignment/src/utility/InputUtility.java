/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;
import adt.SortedListInterface;
import adt.SortedDoublyLinkedList;

import java.util.Scanner;
/**
 *
 * @author user
 */

public class InputUtility {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int readInt() {
        return readInt("");
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    public static SortedListInterface<String> selectMultipleOptions(String prompt, SortedListInterface<String> options) {
        SortedListInterface<String> selectedOptions = new SortedDoublyLinkedList<>();

        boolean[] selected = new boolean[options.getSize()];
        int choice;

        do {
            System.out.println("\n" + prompt); 
            for (int i = 0; i < options.getSize(); i++) { 
                String marker = selected[i] ? "[x]" : "[ ]";
                System.out.printf("%d. %s %s\n", i + 1, marker, options.viewDataAtIndex(i));
            }
            System.out.println("0. Done selecting");

            choice = readInt("Select option (0 to finish): ");

            if (choice == 0) {
                break;
            }

            if (choice < 1 || choice > options.getSize()) { 
                System.out.println("Invalid option. Please try again.");
            } else {
                int index = choice - 1;
                String option = options.viewDataAtIndex(index);
                if (selected[index]) {
                    System.out.println("Option already selected.");
                } else {
                    selected[index] = true;
                    selectedOptions.addWithSort(option);
                    System.out.println("Added: " + option);
                }
            }
        } while (true);

        return selectedOptions;
    }
    
    public static String getNonEmptyString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }


    public static String getValidatedEmail(String prompt) {
        String email;
        while (true) {
            System.out.print(prompt);
            email = scanner.nextLine().trim();
            if (email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                break;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }
        return email;
    }

}

