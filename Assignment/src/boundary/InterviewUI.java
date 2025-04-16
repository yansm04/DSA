/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Interview;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import utility.utility1;

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
        System.out.println(utility1.alignCenter("     |      3. Update Scheduled Interview     |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      4. Remove Interview               |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      5. Exit                           |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +========================================+", utility1.totalWidth));
        System.out.println();
        System.out.printf("%67sEnter your choice (1-5): ", " ");
        return Integer.parseInt(sc.nextLine());
    }

    public static void currentApplicationHeading(String companyName) {
        System.out.printf("\n%55s Current Applications to be Scheduled for %s\n\n", " ", companyName);
        System.out.printf("%30s%s\n", "", "+=========================================================================================================+");
        System.out.printf("%30s| %s | %14s %10s | %20s %10s | %11s %4s | %s |\n", "", "Application ID", "Job", "", "Applicant", "", "Status", "", "Score");
        System.out.printf("%30s%s\n", "", "+================|===========================|=================================|==================|=======+");

    }

    public static void displayApplication(int applicationID, String jobTitle, String applicantName, String status, double score) {
        System.out.printf("%30s| %-14d | %-25s | %-31s | %-16s | %5.1f |\n", "",
                applicationID, jobTitle, applicantName, status, score * 100);
    }

    public static void currentApplicationFooter() {
        System.out.printf("%30s%s\n", "", "+================|===========================|=================================|==================|=======+");
    }

    public static void displayNoApplication() {
        System.out.println("\u001B[31m" + utility1.alignCenter("   +==========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[31m" + utility1.alignCenter("   |          No application found!           |", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[31m" + utility1.alignCenter("   +==========================================+", utility1.totalWidth) + "\u001B[0m");
    }

    public static void displayNoInterview() {
        System.out.println("\u001B[31m" + utility1.alignCenter("   +==========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[31m" + utility1.alignCenter("   |           No interview found!            |", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[31m" + utility1.alignCenter("   +==========================================+", utility1.totalWidth) + "\u001B[0m");
    }

    public static void quitAndBackGuide() {
        System.out.println(utility1.alignCenter("          +=====================================================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("          |                Enter '-111' : Back to Previous Input                |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("          |                Enter '-999' : Back to Previous Menu                 |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("          +=====================================================================+", utility1.totalWidth));
        System.out.println();
    }

    public static void dashLine() {
        System.out.println();
        System.out.println(utility1.alignCenter("          =============================================================================", utility1.totalWidth));
        System.out.println();
    }

    public static int promptApplicationId() {
        while (true) {
            System.out.printf("\n%50s %s", " ", "Enter an Application ID to schedule an interview: ");
            int appId = Integer.parseInt(sc.nextLine());

            if (appId == -111 || appId == -999) {
                return -999;
            } else {
                return appId;
            }
        }
    }

    public static String promptInterviewType() {
        while (true) {
            System.out.printf("%50s %s", " ", "Select interview type (A = On-site, B = Online): ");
            String type = sc.nextLine().toUpperCase();

            if (type.equals("-111") || type.equals("-999")) {
                return type;
            } else {
                switch (type) {
                    case "A":
                        return "On-site";
                    case "B":
                        return "Online";
                    default:
                        System.err.printf("%50s %s", " ", "Invalid input, only A (On-site) or B (Online) is allowed\n\n");
                }
            }
        }
    }

    public static String promptDateTime() {
        while (true) {
            System.out.printf("%50s %s\n\n", " ", "Interview time must be at least one day ahead and fall within business hours (Mon-Fri, 9am-5pm).");
            System.out.printf("%50s %s", " ", "Enter interview date and time (format: dd-MM-yyyy HH:mm): ");
            return sc.nextLine().trim();
        }
    }

    public static void printConflictTimeError() {
        System.err.printf("%50s %s", " ", "There's a scheduling conflict with an existing interview during the chosen time slot\n");
    }

    public static void printOutScheduleWindowError(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String start = startDate.format(dateFormatter);
        String end = endDate.format(dateFormatter);
        System.err.printf("%50s Interview date is outside the current scheduling window (%s to %s)\n", " ", start, end);
    }

    public static String promptConfirmation() {
        System.out.printf("%50s %s", " ", "Confirm (Y = yes / N = no / Q = Quit)? ");
        return sc.nextLine().toLowerCase();
    }

    public static void printErrorOption() {
        System.err.printf("%51sInvalid input, please enter your response again\n\n", " ");
    }

    public static void displayInterviewDetails(Interview interview) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateTimeStr = interview.getInterviewDateTime().format(dateFormatter);
        System.out.println();
        System.out.println(utility1.alignCenter("+==================================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("|                 Interview Found                  |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("|==================================================|", utility1.totalWidth));
        interviewDetailsBody(interview.getInterviewID(), interview.getInterviewType(), dateTimeStr,
                interview.getApplication().getApplicant().getName(),
                interview.getApplication().getJob().getTitle(), interview.getStatus());
    }

    public static void displayUpdatedInterview(Interview interview) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateTimeStr = interview.getInterviewDateTime().format(dateFormatter);
        System.out.println();
        System.out.println("\u001B[32m" + utility1.alignCenter("+==================================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("|         Interview scheduled successfully!        |", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("|==================================================|", utility1.totalWidth) + "\u001B[0m");
        interviewDetailsBody(interview.getInterviewID(), interview.getInterviewType(), dateTimeStr,
                interview.getApplication().getApplicant().getName(),
                interview.getApplication().getJob().getTitle(), interview.getStatus());
        System.out.printf("\n%59sNotified applicant: %s\n", " ", interview.getApplication().getApplicant().getName());
    }

    private static void interviewDetailsBody(String interviewId, String interviewType, String dateTimeStr, String applicant, String jobTitle, String status) {
        System.out.printf("%54s| Interview ID          : %-25s|\n", " ", interviewId);
        System.out.printf("%54s| Interview Type        : %-25s|\n", " ", interviewType);
        System.out.printf("%54s| Date and Time         : %-25s|\n", " ", dateTimeStr);
        System.out.printf("%54s| Applicant             : %-25s|\n", " ", applicant);
        System.out.printf("%54s| Job                   : %-25s|\n", " ", jobTitle);
        System.out.printf("%54s| Status                : %-25s|\n", " ", status);
        System.out.println(utility1.alignCenter("+==================================================+", utility1.totalWidth));
    }

    public static void printConfirmedInterview(String interviewId, String interviewType, String dateTimeStr, String applicant, String jobTitle, String status) {
        System.out.println();
        System.out.println(utility1.alignCenter("+==================================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("|              New Interview Details               |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("|==================================================|", utility1.totalWidth));
        interviewDetailsBody(interviewId, interviewType, dateTimeStr, applicant, jobTitle, status);
    }

    public static void printSuccessMessage(String applicant) {
        System.out.println();
        System.out.println("\u001B[32m" + utility1.alignCenter("   +===========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("   |      Interview scheduled successfully!    |", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("   +===========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.printf("\n%59sNotified applicant: %s\n", " ", applicant);
    }

    public static String promptContinueOrNot() {
        System.out.printf("\n%50s %s", " ", "Do you want to schedule another interview (Y = yes / N = no)? ");
        return sc.nextLine();
    }

    public static String promptUpdateOrNot() {
        System.out.printf("\n%50s %s", " ", "Do you want to update another interview (Y = yes / N = no)? ");
        return sc.nextLine();
    }

    public static String promptInterviewId() {
        System.out.printf("\n%51sEnter Interview ID for update: ", "");
        return sc.nextLine();
    }

    public static void printInterviewIdNotFound() {
        System.err.println(utility1.alignCenter("Interview not found", utility1.totalWidth));
    }

    public static void printCannotUpdateInterview() {
        System.err.println(utility1.alignCenter("Update is allowed only if at least one calendar day before the interview", utility1.totalWidth));
    }

    public static void printInvalidDateTime() {
        System.err.printf("%50s %s", " ", "Invalid date time, please enter again\n");
    }
}
