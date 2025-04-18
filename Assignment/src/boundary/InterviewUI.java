/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Interview;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        System.out.println(utility1.alignCenter("     |      2. View Scheduled Interviews      |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      3. Update Scheduled Interview     |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      4. Remove Interview               |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      5. View Global Interviews         |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      6. Results Ranking                |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      7. Exit                           |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +========================================+", utility1.totalWidth));
        System.out.println();
        System.out.printf("%67sEnter your choice (1-7): ", " ");
        return Integer.parseInt(sc.nextLine());
    }

    public static int appMenuUI() {
        System.out.println(utility1.alignCenter("     +========================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      Select a function to proceed      |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +========================================+", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      1. View Current Interviews        |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      2. Forfeit Interview              |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     |      3. Exit                           |", utility1.totalWidth));
        System.out.println(utility1.alignCenter("     +========================================+", utility1.totalWidth));
        System.out.println();
        System.out.printf("%67sEnter your choice (1-3): ", " ");
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
            System.out.printf("%50s %s\n\n", " ", "Interview time must be at least 5 mins ahead and fall within business hours (Mon-Fri, 9am-5pm).");
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
        return sc.nextLine().toLowerCase().trim();
    }

    public static void printErrorOption() {
        System.err.printf("%51sInvalid input, please enter your response again\n\n", " ");
    }

    public static void displayInterviewDetails(Interview interview) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateTimeStr = "";
        if (interview.getInterviewDateTime() != null) {
            dateTimeStr = interview.getInterviewDateTime().format(dateFormatter);
        } else {
            dateTimeStr = "-";
        }
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
        System.err.println(utility1.alignCenter("Any changes is allowed only if at least 1 min before the interview", utility1.totalWidth));
    }

    public static void printInvalidDateTime() {
        System.err.printf("%50s %s", " ", "Invalid date time, please enter again\n");
    }

    public static String promptRemoveInterviewId() {
        System.out.printf("\n%51sEnter Interview ID to cancel/reject: ", "");
        return sc.nextLine().trim();
    }

    public static String promptForfeitInterviewId() {
        System.out.printf("\n%51sEnter Interview ID to forfeit (Q to quit): ", "");
        return sc.nextLine().trim();
    }

    public static String promptCancellationOption() {
        while (true) {
            System.out.printf("%51sChoose an option:", "");
            System.out.printf("\n%51sA. Cancel Interview (Application Remains)", "");
            System.out.printf("\n%51sB. Reject Application (Interview won't Remain)", "");
            System.out.printf("\n%51sOption: ", "");
            String option = sc.nextLine().toUpperCase();

            if (option.equals("-111") || option.equals("-999")) {
                return option;
            } else {
                switch (option) {
                    case "A":
                        return "A";
                    case "B":
                        return "B";
                    default:
                        System.err.printf("%50s %s", " ", "Invalid input, only 'A' or 'B' is allowed\n\n");
                }
            }
        }
    }

    public static void displayRemoveInterviewMessage(String applicant) {
        System.out.println();
        System.out.println("\u001B[32m" + utility1.alignCenter("   +===========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("   |      Interview cancelled successfully!    |", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("   +===========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.printf("\n%59sNotified applicant: %s\n", " ", applicant);
    }

    public static void displayRejecteApplicationMessage(String applicant) {
        System.out.println();
        System.out.println("\u001B[32m" + utility1.alignCenter("   +===========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("   |     Application removed successfully!     |", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("   +===========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.printf("\n%59sNotified applicant: %s\n", " ", applicant);
    }

    public static String promptDeleteOrNot() {
        System.out.printf("\n%50s %s", " ", "Do you want to delete another interview (Y = yes / N = no)? ");
        return sc.nextLine().trim();
    }

    public static void viewInterviewHeading(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateStr = date.format(dateFormatter);
        System.out.printf("\n%65s Interviews on %s\n", "", dateStr);
        interviewTableLabel();
    }

    public static void interviewTableLabel() {
        System.out.printf("%10s%s\n", "", "+=================================================================================================================================================+");
        System.out.printf("%10s| %s | %s | %5s %-1s |%15s %11s | %20s %10s | %7s %3s | %13s %10s |\n", "", "Interview ID", "Application ID", "Type", "", "Job", "", "Applicant", "", "Time", "", "Company", "");
        System.out.printf("%10s%s\n", "", "+==============|================|=========|============================|=================================|=============|==========================+");

    }

    public static void viewInterviewBody(String intvId, int appID, String type, String job, String applicant, LocalDateTime start, LocalDateTime end, String company) {
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");
        String s = start.format(timeFmt);
        String e = end.format(timeFmt);
        System.out.printf("%10s| %-12s | %-14s | %-7s | %-26s | %-31s | %s-%s | %-24s |\n", "", intvId, appID,
                type, job, applicant, s, e, company);
    }

    public static void viewInterviewFooter() {
        System.out.printf("%10s%s\n", "", "+==============|================|=========|============================|=================================|=============|==========================+");
    }

    public static void displayForfeitMessage() {
        System.out.println();
        System.out.println("\u001B[32m" + utility1.alignCenter("   +===========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("   |      Interview forfeoted successfully!    |", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[32m" + utility1.alignCenter("   +===========================================+", utility1.totalWidth) + "\u001B[0m");
    }

    public static void wrongForfeitAttemptError() {
        System.err.printf("%50s %s", " ", "You can only forfeit your own interviews\n");
    }

    public static void printNoFinishedInterview() {
        System.out.println("\u001B[31m" + utility1.alignCenter("   +==========================================+", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[31m" + utility1.alignCenter("   |        No finished interviews yet         |", utility1.totalWidth) + "\u001B[0m");
        System.out.println("\u001B[31m" + utility1.alignCenter("   +==========================================+", utility1.totalWidth) + "\u001B[0m");
    }

    public static void printCurrentFinishInterview(String intvId, String name, String job) {
        System.out.printf("\n%50s Interview %s for %s (Job: %s)\n", " ", intvId, name, job);
    }

    public static int promptScore(String type) {
        System.out.printf("\n%50s Enter the %s for this interview: ", " ", type);
        return Integer.parseInt(sc.nextLine().trim());
    }

    public static void invalidScore(String type) {
        System.err.printf("%50s The %s score must be from 1 to 50\n", " ", type);
    }

    public static void interviewResultSummaryHeading() {
        System.out.printf("%20s%s\n", "", "+=====================================================================================================================+");
        System.out.printf("%20s|%46s INTERVIEW RESULTS SUMMARY %44s|\n", "", "", "");
        System.out.printf("%20s%s\n", "", "+=====================================================================================================================+");
        System.out.printf("%20s| %s | %s | %20s %10s |%15s %11s | %s | %7s%1s |\n", "", "Interview ID",
                "Applicant ID", "Applicant", "", "Job", "", "Total Score", "Status", "");
        System.out.printf("%20s%s\n", "", "+==============|==============|=================================|============================|=============|==========+");
    }

    public static void interviewResultSummaryBody(String intvId, String appId, String applicant, String job, int score, String status) {
        System.out.printf("%20s| %-12s | %-12s | %-31s | %-26s | %-11d | %-8s |\n", "", intvId, appId,
                applicant, job, score, status);
    }

    public static void interviewResultSummaryFooter() {
        System.out.printf("%20s%s\n", "", "+=====================================================================================================================+");
    }

    public static void successRateHeading() {
        System.out.printf("%52s %45s\n", "", "SUCCESS RECRUITMENT RATE BY COMPANY");
        System.out.printf("%52s %s\n", "", "+=======================================================+");
        System.out.printf("%52s | %s | %13s %10s | %s |\n", "",
                "Company ID", "Company", "", "Recruited (%)");
        System.out.printf("%52s %s\n", "", "+============|==========================|===============+");
    }

    public static void successRateFooter() {
        System.out.printf("%52s%s\n", "", "+============|==========================|===============+");
    }

    public static void successRateBody(String cmpId, String cmpName, double percent) {
        System.out.printf("%52s | %-10s | %-24s | %-13.1f |\n", "", cmpId, cmpName, percent);
    }

    public static void avgScoreHeading() {
        System.out.printf("%40s %68s\n", "", "AVERAGE INTERVIEW SCORE (ALL / HIRED / REJECTED)");
        System.out.printf("%40s %s\n", "", "+================================================================================+");
        System.out.printf("%40s | %s |%15s %11s | %s | %s | %s |\n", "",
                "Job ID", "Job", "", "Avg (All)", "Avg (Hired)", "Avg (Rejected)");
        System.out.printf("%40s %s\n", "", "+========|============================|===========|=============|================+");
    }

    public static void avgScoreFooter() {
        System.out.printf("%40s %s\n", "", "+========|============================|===========|=============|================+");
    }

    public static void avgScoreBody(String id, String job, String all, String hired, String rejected) {
        System.out.printf("%40s | %-6s | %-26s | %-9s | %-11s | %-14s |\n",
                "", id, job, all, hired, rejected);
    }

    public static void upcomingInterviewHeading() {
        System.out.printf("%72s UPCOMING INTERVIEWS\n", "");
        System.out.printf("%5s%s\n", "", "+==============================================================================================================================================================+");
        System.out.printf("%5s| %7s %-2s | %s | %s | %5s %-1s |%15s %11s | %20s %10s | %7s %3s | %13s %10s |\n", "", "Date", "", "Interview ID", "Application ID", "Type", "", "Job", "", "Applicant", "", "Time", "", "Company", "");
        System.out.printf("%5s%s\n", "", "+============|==============|================|=========|============================|=================================|=============|==========================+");
    }

    public static void upcomingInterviewFooter() {
        System.out.printf("%5s%s\n", "", "+============|==============|================|=========|============================|=================================|=============|==========================+");
    }

    public static void upcomingInterviewBody(LocalDate date, String intvId, int appId, String type, String job, String applicant, LocalDateTime start, LocalDateTime end, String company) {
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");
        String s = start.format(timeFmt);
        String e = end.format(timeFmt);
        String d = date.format(dateFmt);
        System.out.printf("%5s| %s | %-12s | %-14d | %-7s | %-26s | %-31s | %s-%s | %-24s |\n", "", d,
                intvId, appId, type, job, applicant, s, e, company);

    }

    public static void drawBarChart(String title, int[] counts, String[] labels, int max) {
        // chart width in blocks
        final int CHART_WIDTH = 40;

        final String GREEN_BG = "\u001B[42m";
        final String RESET = "\u001B[0m";
        final String BLOCK = GREEN_BG + " " + RESET;  // single block
        final String AXIS = "-";
        // title
        System.out.printf("%60s %30s\n\n", "", title);

        // scale line (0 and max)
        System.out.printf("%55s ", "0");
        for (int i = 0; i < CHART_WIDTH; i++) {
            System.out.print(" ");
        }
        System.out.printf(" %d\n\n", max);
        System.out.printf("%55s \n", "^");
        // bars
        for (int i = 0; i < counts.length; i++) {
            String label = labels[i];
            int value = counts[i];

            // calculate bar length
            int barLen = (int) Math.round((value * 1.0 / max) * CHART_WIDTH);

            // print label (left‑padded to 10 chars)
            System.out.printf("%43s %-10s| ", "", label);

            // print blocks
            for (int b = 0; b < barLen; b++) {
                System.out.print(BLOCK);
            }

            // value
            System.out.printf(" (%d)\n", value);
        }
        System.out.printf("%55s_", "");
        for (int i = 0; i < CHART_WIDTH; i++) {
            System.out.print(AXIS);
        }
        System.out.print(">");
        System.out.println();
    }

    public static void interviewReportDashLine() {
        System.out.println();
        System.out.println(utility1.alignCenter("======================================================================================================================================", utility1.totalWidth));
        System.out.println();
    }

    public static void interviewReportHeading() {
        interviewReportDashLine();
        System.out.println(utility1.alignCenter("TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY", utility1.totalWidth));
        System.out.println(utility1.alignCenter("INTERNSHIP APPLICATION PROGRAM", utility1.totalWidth));
        System.out.println(utility1.alignCenter("INTERVIEW SUMMARY REPORT - AS OF " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy HH:mm")), utility1.totalWidth));
        interviewReportDashLine();
    }

}
