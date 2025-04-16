package boundary;

import utility.utility1;

public class MatchUI {

    public static void MatchingHeader(String jobTitle) {
        System.out.println("\nJob: " + jobTitle);
        System.out.println("----------------------------");
    }

    public static void MatchingResultRow(int index, String name, double score, String details) {
        System.out.printf("%d. %s (Score: %.2f)\n   %s\n", index, name, score, details);
    }

    public static void MatchingFooter() {
        System.out.println("--------------------------------------------------------------------------\n");
    }

    public static void showRejectedApplicant(String companyName,String jobTitle, String applicantName, double score) {
        System.out.println("\n\n\n-- Rejected Applicant --");
        System.out.println("Company    : " + companyName);
        System.out.println("Job Title  : " + jobTitle);
        System.out.println("Applicant  : " + applicantName);
        System.out.println("Match Score: " + String.format("%.2f", score));
        System.out.println("Reason     : Score below 0.4 threshold");
    }

    public static void rejectedOnes() {
        System.out.println("\n=======================================");
        System.out.println("           Rejected Applicants         ");
        System.out.println("=======================================");

    }

}
