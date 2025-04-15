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
public class InterviewUI {

    public static Scanner sc = new Scanner(System.in);

    public static void interviewLogo() {
        System.out.println(utility1.alignCenter("           _____ ____  _____ _________ ________ _______ ____   ____ _____ ________ ____      ____ ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("          |_   _|_   \\|_   _|  _   _  |_   __  |_   __ |_  _| |_  _|_   _|_   __  |_  _|    |_  _|", utility1.totalWidth));
        System.out.println(utility1.alignCenter("            | |   |   \\ | | |_/ | | \\_| | |_ \\_| | |__) |\\ \\   / /   | |   | |_ \\_| \\ \\  /\\  / /  ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("            | |   | |\\ \\| |     | |     |  _| _  |  __ /  \\ \\ / /    | |   |  _| _   \\ \\/  \\/ /   ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("           _| |_ _| |_\\   |_   _| |_   _| |__/ |_| |  \\ \\_ \\ ' /    _| |_ _| |__/ |   \\  /\\  /    ", utility1.totalWidth));
        System.out.println(utility1.alignCenter("          |_____|_____|\\____| |_____| |________|____| |___| \\_/    |_____|________|    \\/  \\/     ", utility1.totalWidth));
        System.out.println();
    }

    public static int cmpMenuUI() {
        System.out.println(utility1.alignCenter("     +========================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      Select a function to proceed      |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +========================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      1. Create Interview               |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      2. View Current Interviews        |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      3. Update Interview Details       |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      4. Remove Interview               |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      5. Exit                           |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +========================================+", utility1.totalWidth));
        System.out.println();
        System.out.printf("%67sEnter your choice (1-5): ", " ");
        return Integer.parseInt(sc.nextLine());
    }

    public static void currentApplicationHeading(String companyName) {
        System.out.printf("\n%58s Current Applications for %s\n\n", " ", companyName);
        System.out.printf("%30s%s\n", "", "+=================================================================================================+");
        System.out.printf("%30s| %s | %14s %10s | %20s %10s | %11s %4s |\n", "", "Application ID", "Job", "", "Applicant", "", "Status", "");
        System.out.printf("%30s%s\n", "", "+================|===========================|=================================|==================+");

    }

    public static void displayApplication(int applicationID, String jobTitle, String applicantName, String status) {
        System.out.printf("%30s| %-14d | %-25s | %-31s | %-16s |\n", "",
                applicationID, jobTitle, applicantName, status);
    }

    public static void currentApplicationFooter() {
        System.out.printf("%30s%s\n", "", "+=================================================================================================+");
    }

    public static void displayNoApplication() {
        System.out.println("\u001B[31m" + utility1.alignCenter("   +==========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[31m" + utility1.alignCenter("   |          No application found!           |", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[31m" + utility1.alignCenter("   +==========================================+", utility1.totalWidth) + "\u001B[0m");
    }

}
