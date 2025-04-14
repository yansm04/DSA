/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;
import utility.utility1;

/**
 *
 * @author USER
 */
public class MainMenuUI {

    public static Scanner sc = new Scanner(System.in);

    public static void mainLogo() {
        System.out.println(utility1.alignCenter("   _________    _      _______ _____  _____ ____    ____ _________   ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("  |  _   _  |  / \\    |_   __ \\_   _||_   _|_   \\  /   _|  _   _  |  ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("  |_/ | | \\_| / _ \\     | |__) || |    | |   |   \\/   | |_/ | | \\_|  ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("      | |    / ___ \\    |  __ / | '    ' |   | |\\  /| |     | |      ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     _| |_ _/ /   \\ \\_ _| |  \\ \\_\\ \\__/ /   _| |_\\/_| |_   _| |_      ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("    |_____|____| |____|____| |___|`.__.'   |_____||_____| |_____|    ", utility1.totalWidth));

        System.out.println(utility1.alignCenter("   _____ ____  _____ _________ ________ _______    ____  _____   ______   ____  ____ _____ _______    _______    ___   _______  _________    _      _____     ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("  |_   _|_   \\|_   _|  _   _  |_   __  |_   __ \\  |_   \\|_   _|.' ____ \\ |_   ||   _|_   _|_   __ \\  |_   __ \\ .'   `.|_   __ \\|  _   _  |  / \\    |_   _|    ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("    | |   |   \\ | | |_/ | | \\_| | |_ \\_| | |__) |   |   \\ | |  | (___ \\_|  | |__| |   | |   | |__) |   | |__) /  .-.  \\ | |__) |_/ | | \\_| / _ \\     | |      ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("    | |   | |\\ \\| |     | |     |  _| _  |  __ /    | |\\ \\| |   _.____`.   |  __  |   | |   |  ___/    |  ___/| |   | | |  __ /    | |    / ___ \\    | |   _  ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("   _| |_ _| |_\\   |_   _| |_   _| |__/ |_| |  \\ \\_ _| |_\\   |_ | \\____) | _| |  | |_ _| |_ _| |_      _| |_   \\  `-'  /_| |  \\ \\_ _| |_ _/ /   \\ \\_ _| |__/ | ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("  |_____|_____|\\____| |_____| |________|____| |___|_____|\\____| \\______.'|____||____|_____|_____|    |_____|   `.___.'|____| |___|_____|____| |____|________| ", utility1.totalWidth));
        System.out.println();
    }

    public static int userTypeMenuUI() {
        System.out.println(utility1.alignCenter("     +=======================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      Select an option to proceed      |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +=======================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |         1. Student/Applicant          |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |         2. Company                    |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |         3. Exit                       |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +=======================================+", utility1.totalWidth));
        System.out.println();
        System.out.printf("%67sEnter your choice (1-3): ", " ");
        return Integer.parseInt(sc.nextLine());

    }

    public static int companyMenuUI() {
        System.out.println(utility1.alignCenter("     +=======================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      Select an option to proceed      |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +=======================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |         1. Manage Interview           |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |         2. View Applications          |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |         3. Exit                       |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +=======================================+", utility1.totalWidth));
        System.out.println();
        System.out.printf("%67sEnter your choice (1-3): ", " ");
        return Integer.parseInt(sc.nextLine());

    }


    public static void selectHeader() {
        System.out.println(utility1.alignCenter("     +=======================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      Select an option to proceed      |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +=======================================+", utility1.totalWidth));
    }

    public static void printOptions(String display) {
        System.out.println(utility1.alignCenter(display, utility1.totalWidth)); //
    }

    public static int selectFooter() {
        System.out.println(utility1.alignCenter("              0. Exit                         ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("            999. Manage                       ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +=======================================+", utility1.totalWidth));
        System.out.println("");
        System.out.printf("%67sEnter your choice: ", " ");
        return Integer.parseInt(sc.nextLine());
    }

    public static void pressEnterToContinue() {
        System.out.printf("%66s %s", " ", "Press enter to continue...");
        sc.nextLine();
    }

    public static void printInvalidMenuChoice() {
        System.err.println(utility1.alignCenter("Invalid choice! Please choose one of the available options.", utility1.totalWidth));
    }

}
