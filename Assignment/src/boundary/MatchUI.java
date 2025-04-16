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
}
