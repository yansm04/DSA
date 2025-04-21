package boundary;

import adt.SortedListInterface;
import controller.ApplicantController;
import entity.Applicant;
import entity.Application;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import utility.InputUtility;
import utility.utility1;

public class ApplicantUI {
    
    public static Scanner sc = new Scanner(System.in);
    
    public static void applicantLogo() {
    System.out.println(utility1.alignCenter("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~    _                _ _                 _                        ~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~   / \\   _ __  _ __ | (_) ___ __ _ _ __ | |_                      ~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~  / _ \\ | '_ \\| '_ \\| | |/ __/ _` | '_ \\| __|                  ~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~ / ___ \\| |_) | |_) | | | (_| (_| | | | | |_                       ~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~/_/  _\\_\\ .__/| .__/|_|_|\\___\\__,_|_| |_|\\__|             _    ~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~|  \\/  ||_| _ |_|_   __ _  __ _  ___ _ __ ___   ___ _ __ | |_      ~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~| |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~| |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_       ~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~|_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__|~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~                          |___/                                     ~", utility1.totalWidth));
    System.out.println(utility1.alignCenter("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", utility1.totalWidth));
    System.out.println();
}



    public static int manageApplicantMenu() {
        System.out.println(utility1.alignCenter("=== Manage Applicants ===", utility1.totalWidth));
        System.out.println(utility1.alignCenter("1. Create Applicant", utility1.totalWidth));
        System.out.println(utility1.alignCenter("2. View All Applicants", utility1.totalWidth));
        System.out.println(utility1.alignCenter("3. Update Applicant", utility1.totalWidth));
        System.out.println(utility1.alignCenter("4. Remove Applicant", utility1.totalWidth));
        System.out.println(utility1.alignCenter("5. Filter Applicant", utility1.totalWidth));
        System.out.println(utility1.alignCenter("6. Report Generation", utility1.totalWidth));
        System.out.println(utility1.alignCenter("0. Back to Main Menu", utility1.totalWidth));
        System.out.println(utility1.alignCenter("999. Access to Applicant System Logs", utility1.totalWidth));
        return InputUtility.readInt("Enter choice: ");
    }

    public static Applicant createApplicant(SortedListInterface<String> skillsOpt,
                                             SortedListInterface<String> fosOpt,
                                             SortedListInterface<String> eduOpt,
                                             SortedListInterface<String> langOpt) {

        System.out.println("\n--- Create New Applicant ---");
        String name = InputUtility.getNonEmptyString("Enter name: ");
        String email = InputUtility.getValidatedEmail("Enter email: ");
        int age = InputUtility.readInt("Enter age: ");
        SortedListInterface<String> skills = InputUtility.selectMultipleOptions("Select Skills:", skillsOpt);
        SortedListInterface<String> fos = InputUtility.selectMultipleOptions("Select Field of Study:", fosOpt);
        SortedListInterface<String> edu = InputUtility.selectMultipleOptions("Select Education:", eduOpt);
        SortedListInterface<String> lang = InputUtility.selectMultipleOptions("Select Languages:", langOpt);

        return ApplicantController.createApplicant(name, email, age, skills, fos, edu, lang);
    }

    public static void printApplicantList(SortedListInterface<Applicant> applicantList) {
        System.out.println("\n--- All Applicants ---");

        if (applicantList.isEmpty()) {
            System.out.println("No applicants found.");
            return;
        }

        for (int i = 0; i < applicantList.getSize(); i++) {
            Applicant applicant = applicantList.viewDataAtIndex(i);
            System.out.println(utility1.alignCenter("Applicant " + (i + 1), utility1.totalWidth));
            System.out.println(utility1.alignCenter("User ID: " + applicant.getUserID(), utility1.totalWidth));
            System.out.println(utility1.alignCenter("Name: " + applicant.getName(), utility1.totalWidth));
            System.out.println(utility1.alignCenter("Email: " + applicant.getEmail(), utility1.totalWidth));
            System.out.println(utility1.alignCenter("Age: " + applicant.getAge(), utility1.totalWidth));

            System.out.println(utility1.alignCenter("Skills: " + listToString(applicant.getSkills()), utility1.totalWidth));
            System.out.println(utility1.alignCenter("Field of Study: " + listToString(applicant.getFos()), utility1.totalWidth));
            System.out.println(utility1.alignCenter("Education: " + listToString(applicant.getEducation()), utility1.totalWidth));
            System.out.println(utility1.alignCenter("Languages: " + listToString(applicant.getLanguage()), utility1.totalWidth));


            SortedListInterface<Application> apps = applicant.getApplication();
            if (apps != null) {
                System.out.println(utility1.alignCenter("Applications: " + apps.getSize(), utility1.totalWidth));
            }

            System.out.println(utility1.alignCenter("-----------------------------", utility1.totalWidth));
        }
    }
    
    private static String listToString(SortedListInterface<String> list) {
        if (list == null || list.isEmpty()) return "-";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            sb.append(list.viewDataAtIndex(i));
            if (i < list.getSize() - 1) sb.append(", ");
        }
        return sb.toString();
    }



    public static int selectApplicantIndex(SortedListInterface<Applicant> applicants) {
        printApplicantList(applicants);
        if (applicants.isEmpty()) return -1;
        int index = InputUtility.readInt("Enter applicant number: ") - 1;
        if (index < 0 || index >= applicants.getSize()) {
            System.out.println(utility1.alignCenter("Invalid index.", utility1.totalWidth));
            return -1;
        }
        return index;
    }

    public static void updateApplicantDetails(Applicant applicant,
                                              SortedListInterface<String> skillsOpt,
                                              SortedListInterface<String> fosOpt,
                                              SortedListInterface<String> eduOpt,
                                              SortedListInterface<String> langOpt) {
        int choice;
        do {
            System.out.println();
            System.out.println(utility1.alignCenter("--- Update Applicant ---", utility1.totalWidth));
            System.out.println(utility1.alignCenter("1. Name", utility1.totalWidth));
            System.out.println(utility1.alignCenter("2. Email", utility1.totalWidth));
            System.out.println(utility1.alignCenter("3. Age", utility1.totalWidth));
            System.out.println(utility1.alignCenter("4. Skills", utility1.totalWidth));
            System.out.println(utility1.alignCenter("5. Field of Study", utility1.totalWidth));
            System.out.println(utility1.alignCenter("6. Education", utility1.totalWidth));
            System.out.println(utility1.alignCenter("7. Languages", utility1.totalWidth));
            System.out.println(utility1.alignCenter("0. Done", utility1.totalWidth));

            choice = InputUtility.readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    ApplicantController.updateName(applicant, InputUtility.getNonEmptyString("Enter new name: "));
                    break;
                case 2:
                    ApplicantController.updateEmail(applicant, InputUtility.getValidatedEmail("Enter new email: "));
                    break;
                case 3:
                    ApplicantController.updateAge(applicant, InputUtility.readInt("Enter new age: "));
                    break;
                case 4:
                    ApplicantController.updateSkills(applicant, InputUtility.selectMultipleOptions("Select new Skills:", skillsOpt));
                    break;
                case 5:
                    ApplicantController.updateFieldOfStudy(applicant, InputUtility.selectMultipleOptions("Select new FOS:", fosOpt));
                    break;
                case 6:
                    ApplicantController.updateEducation(applicant, InputUtility.selectMultipleOptions("Select new Education:", eduOpt));
                    break;
                case 7:
                    ApplicantController.updateLanguages(applicant, InputUtility.selectMultipleOptions("Select new Languages:", langOpt));
                    break;
                case 0:
                    System.out.println(utility1.alignCenter("Update complete.", utility1.totalWidth));
                    break;
                default:
                    printInvalidChoice();
            }
        } while (choice != 0);
    }

    public static void viewOwnProfile(Applicant applicant) {
        System.out.println();
        System.out.println(utility1.alignCenter("--- Your Profile ---", utility1.totalWidth));
        System.out.println(utility1.alignCenter("UserID: " + applicant.getUserID(), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Name: " + applicant.getName(), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Email: " + applicant.getEmail(), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Age: " + applicant.getAge(), utility1.totalWidth));
        printList("Skills", applicant.getSkills());
        printList("Field of Study", applicant.getFos());
        printList("Education", applicant.getEducation());
        printList("Languages", applicant.getLanguage());
    }

    public static void updateOwnProfile(Applicant applicant,
                                        SortedListInterface<String> skillsOpt,
                                        SortedListInterface<String> fosOpt,
                                        SortedListInterface<String> eduOpt,
                                        SortedListInterface<String> langOpt) {
        updateApplicantDetails(applicant, skillsOpt, fosOpt, eduOpt, langOpt);
    }
    
    public static void viewOwnApplications(Applicant applicant) {
    System.out.println(utility1.alignCenter("\n--- Your Applications ---", utility1.totalWidth));

    SortedListInterface<Application> apps = applicant.getApplication();

    if (apps == null || apps.isEmpty()) {
        System.out.println(utility1.alignCenter("You have not applied to any jobs.", utility1.totalWidth));
        return;
    }

    for (int i = 0; i < apps.getSize(); i++) {
        Application app = apps.viewDataAtIndex(i);

        System.out.println(utility1.alignCenter("Application " + (i + 1), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Status: " + app.getStatus(), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Job Title: " + app.getJob().getTitle(), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Job Description: " + app.getJob().getDesc(), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Job Location: " + app.getJob().getLocation(), utility1.totalWidth));

        System.out.println(utility1.alignCenter("Required Skills: " + listToString(app.getJob().getReqSkill()), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Field of Study: " + listToString(app.getJob().getFos()), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Education Level: " + listToString(app.getJob().getReqEdLevel()), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Languages Required: " + listToString(app.getJob().getLanguage()), utility1.totalWidth));

        System.out.println(utility1.alignCenter("Company Name: " + app.getJob().getCompany().getCompanyName(), utility1.totalWidth));
        System.out.println(utility1.alignCenter("Company Email: " + app.getJob().getCompany().getEmail(), utility1.totalWidth));

        System.out.println(utility1.alignCenter("-----------------------------", utility1.totalWidth));

    }
}
    
    public static int selectMatchMode() {
        while (true) {
            System.out.println(utility1.alignCenter("\nSelect Match Mode:", utility1.totalWidth));
            System.out.println(utility1.alignCenter("1. Match ANY (at least one)", utility1.totalWidth));
            System.out.println(utility1.alignCenter("2. Match ALL (must match all)", utility1.totalWidth));
            System.out.print(utility1.alignCenter("Enter choice (1 or 2): ", utility1.totalWidth));
            String input = sc.nextLine().trim();

            if (input.equals("1") || input.equals("2")) {
                return Integer.parseInt(input);
            } else {
                System.out.println(utility1.alignCenter("Invalid choice. Please enter 1 or 2.", utility1.totalWidth));
            }
        }
    }
    
    public void filterApplicants(SortedListInterface<Applicant> applicants) {
        System.out.println(utility1.alignCenter("\n--- Filter Applicants ---", utility1.totalWidth));

        SortedListInterface<String> skills = InputUtility.selectMultipleOptions("skills", ApplicantController.getSkillsOptions());
        SortedListInterface<String> fos = InputUtility.selectMultipleOptions( "field of study", ApplicantController.getFOSOptions());
        SortedListInterface<String> edu = InputUtility.selectMultipleOptions("education level", ApplicantController.getEducationOptions());
        SortedListInterface<String> lang = InputUtility.selectMultipleOptions("language", ApplicantController.getLanguagesOptions());

        int matchMode = selectMatchMode();

        SortedListInterface<Applicant> filtered = ApplicantController.filterApplicants(
            applicants, skills, fos, edu, lang, matchMode
        );

        if (filtered.isEmpty()) {
            System.out.println(utility1.alignCenter("No applicants found matching the selected criteria.", utility1.totalWidth));
        } else {
            printApplicantList("Filtered Applicants", filtered);
        }

        pressEnterToContinue();
    }
    
    
    public static int generateReportMenu() {
        System.out.println(utility1.alignCenter("------ Report Generation ------", utility1.totalWidth));
        System.out.println(utility1.alignCenter("1. Total Applicants Report", utility1.totalWidth));
        System.out.println(utility1.alignCenter("2. Skills Distribution Report", utility1.totalWidth));
        System.out.println(utility1.alignCenter("3. Language Proficiency Report", utility1.totalWidth));
        System.out.println(utility1.alignCenter("4. Education Level Summary Report", utility1.totalWidth));
        System.out.println(utility1.alignCenter("5. Most Active Applicants Report", utility1.totalWidth));
        System.out.println(utility1.alignCenter("0. Return to Applicant Management", utility1.totalWidth));
        System.out.print(utility1.alignCenter("Enter your choice: ", utility1.totalWidth));

        return InputUtility.readInt(); 
    }

    public static void printReportSuccess(String message) {
        System.out.println(utility1.alignCenter(message, utility1.totalWidth));
    }

    public static void printSuccess(String message) {
        System.out.println(utility1.alignCenter("✅ " + message, utility1.totalWidth));
    }

    public static void printFailure(String message) {
        System.out.println(utility1.alignCenter("❌ " + message, utility1.totalWidth));
    }

    public static void printInvalidChoice() {
        System.out.println(utility1.alignCenter("❌ Invalid choice.", utility1.totalWidth));
    }

    public static void pressEnterToContinue() {
        System.out.print(utility1.alignCenter("Press enter to continue...", utility1.totalWidth));
        sc.nextLine();
    }


    private static void printList(String title, SortedListInterface<String> list) {
        System.out.println(utility1.alignCenter(title + ":", utility1.totalWidth));
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(utility1.alignCenter("  - " + list.viewDataAtIndex(i), utility1.totalWidth));
        }
    }

    
    public static void printApplicantList(String title, SortedListInterface<Applicant> list) {
        System.out.println(utility1.alignCenter("\n--- " + title + " ---", utility1.totalWidth));
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(utility1.alignCenter((i + 1) + ". " + list.viewDataAtIndex(i), utility1.totalWidth));
        }
    }

    public static void printReportHeader(String title) {
        System.out.println("===============================================================================");
        System.out.println("                      INTERNSHIP APPLICATION SYSTEM");
        System.out.println("                          " + title.toUpperCase());
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Generated at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy, hh:mm a")));
        System.out.println("===============================================================================");
    }

    public static void printReportFooter() {
        System.out.println("===============================================================================");
        System.out.println("END OF REPORT");
        System.out.println("===============================================================================");
    }




}
