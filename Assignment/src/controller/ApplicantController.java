package controller;

import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;
import boundary.ApplicantUI;
import static boundary.ApplicantUI.pressEnterToContinue;
import entity.Applicant;
import dao.Initializer;
import utility.InputUtility;
import utility.SystemLogger;

public class ApplicantController {

    private static int nextUserID = 1014;

    public static void manageApplicants(SortedListInterface<Applicant> applicants) {
        int choice = 0;
        ApplicantUI.applicantLogo();
        do {
            int menuChoice = ApplicantUI.manageApplicantMenu();
            switch (menuChoice) {
                case 1:
                    Applicant newApplicant = ApplicantUI.createApplicant(getSkillsOptions(), getFOSOptions(), getEducationOptions(), getLanguagesOptions());
                    if (newApplicant != null) {
                        applicants.addWithSort(newApplicant);
                        ApplicantUI.printSuccess("Applicant created successfully.");
                    } else {
                        ApplicantUI.printFailure("Applicant creation failed.");
                    }
                    choice = menuChoice;
                    break;
                case 2:
                    ApplicantUI.printApplicantList(applicants);
                    SystemLogger.log("Viewed All Applicant Details");
                    pressEnterToContinue();
                    choice = menuChoice;
                    break;
                case 3:
                    int indexToUpdate = ApplicantUI.selectApplicantIndex(applicants);
                    if (indexToUpdate != -1) {
                        ApplicantUI.updateApplicantDetails(applicants.viewDataAtIndex(indexToUpdate), 
                                getSkillsOptions(), getFOSOptions(), getEducationOptions(), getLanguagesOptions());
                    }
                    choice = menuChoice;
                    break;
                case 4:
                    int indexToRemove = ApplicantUI.selectApplicantIndex(applicants);
                    if (indexToRemove != -1) {
                        Applicant removed = removeApplicant(applicants, indexToRemove);
                        if (removed != null) {
                            ApplicantUI.printSuccess("Applicant removed successfully.");
                        }
                    }
                    choice = menuChoice;
                    break;
                case 5:
                    SortedListInterface<String> selectedSkills = InputUtility.selectMultipleOptions( "skills", getSkillsOptions());
                    SortedListInterface<String> selectedFOS = InputUtility.selectMultipleOptions("field of study", getFOSOptions());
                    SortedListInterface<String> selectedEducation = InputUtility.selectMultipleOptions("education level", getEducationOptions());
                    SortedListInterface<String> selectedLanguages = InputUtility.selectMultipleOptions("language", getLanguagesOptions());

                    int matchMode = ApplicantUI.selectMatchMode();
                    if (matchMode == 1) SystemLogger.log("Filter applicant with match Any");    
                    else SystemLogger.log("Filter applicant with match All"); 
        

                    SortedListInterface<Applicant> filtered = filterApplicants(applicants, selectedSkills, selectedFOS, selectedEducation, selectedLanguages, matchMode);

                    if (filtered.isEmpty()) {
                        System.out.println("No applicants found matching the selected criteria.");
                    } else {
                        ApplicantUI.printApplicantList("Filtered Applicants", filtered);
                    }

                    pressEnterToContinue();
                    choice = menuChoice;
                    break;
                case 6:
                    generateReports(applicants);
                    choice = menuChoice;
                    break;
                case 999:
                    SystemLogger.log("Access to Applicant System Logs");
                    SystemLogger.printLogs();
                    pressEnterToContinue();
                    choice = menuChoice;
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    choice = menuChoice;
                    break;
                default:
                    ApplicantUI.printInvalidChoice();
                    choice = menuChoice;
            }
        } while (choice != 0);
    }

    public static Applicant createApplicant(String name, String email, int age,
                                            SortedListInterface<String> skills,
                                            SortedListInterface<String> fos,
                                            SortedListInterface<String> education,
                                            SortedListInterface<String> language) {
        String userID = String.valueOf(nextUserID++);
        SystemLogger.log("Created new applicant: " + userID);
        return new Applicant(userID, name, email, age, skills, fos, education, language);
    }

    public static Applicant removeApplicant(SortedListInterface<Applicant> list, int index) {
        if (index >= 0 && index < list.getSize()) {
            Applicant applicant = list.viewDataAtIndex(index);
            SystemLogger.log("Deleted existing applicant: " + applicant.getUserID());
            return list.removeByIndex(index);
        } else {
            System.out.println("Invalid index. No applicant removed.");
            return null;
        }
    }

    public static void updateName(Applicant applicant, String newName) {
        SystemLogger.log("Updated applicant name: " + applicant.getUserID());
        applicant.setName(newName);
    }

    public static void updateEmail(Applicant applicant, String newEmail) {
        SystemLogger.log("Updated applicant email: " + applicant.getUserID());
        applicant.setEmail(newEmail);
    }

    public static void updateAge(Applicant applicant, int newAge) {
        SystemLogger.log("Updated applicant age: " + applicant.getUserID());
        applicant.setAge(newAge);
    }

    public static void updateSkills(Applicant applicant, SortedListInterface<String> newSkills) {
        SystemLogger.log("Updated applicant skills: " + applicant.getUserID());
        applicant.setSkills(newSkills);
    }

    public static void updateEducation(Applicant applicant, SortedListInterface<String> newEducation) {
        SystemLogger.log("Updated applicant educations: " + applicant.getUserID());
        applicant.setEducation(newEducation);
    }

    public static void updateFieldOfStudy(Applicant applicant, SortedListInterface<String> newFos) {
        SystemLogger.log("Updated applicant fields of study: " + applicant.getUserID());
        applicant.setFos(newFos);
    }

    public static void updateLanguages(Applicant applicant, SortedListInterface<String> newLanguages) {
        SystemLogger.log("Updated applicant languages: " + applicant.getUserID());
        applicant.setLanguage(newLanguages);
    }

    public static void viewOwnProfile(Applicant applicant) {
        ApplicantUI.viewOwnProfile(applicant);
        SystemLogger.log("Applicant " + applicant.getUserID() + " viewed him/her infomation.");
    }

    public static void updateOwnProfile(Applicant applicant) {
        SystemLogger.log("Applicant " + applicant.getUserID() + " updated him/her infomation.");
        ApplicantUI.updateOwnProfile(applicant, getSkillsOptions(), getFOSOptions(), getEducationOptions(), getLanguagesOptions());
    }
    
    public static void viewOwnApplications(Applicant applicant) {
        SystemLogger.log("Applicant " + applicant.getUserID() + " viewed him/her application.");
        ApplicantUI.viewOwnApplications(applicant);
    }
    
    public static SortedListInterface<Applicant> filterApplicants(
        SortedListInterface<Applicant> applicants,
        SortedListInterface<String> selectedSkills,
        SortedListInterface<String> selectedFOS,
        SortedListInterface<String> selectedEdu,
        SortedListInterface<String> selectedLang,
        int matchMode 
    ) {
        SortedListInterface<Applicant> result = new SortedDoublyLinkedList<>();

        for (int i = 0; i < applicants.getSize(); i++) {
            Applicant app = applicants.viewDataAtIndex(i);

            boolean skillMatch = matchesAnyOrAll(app.getSkills(), selectedSkills, matchMode);
            boolean fosMatch = matchesAnyOrAll(app.getFos(), selectedFOS, matchMode);
            boolean eduMatch = matchesAnyOrAll(app.getEducation(), selectedEdu, matchMode);
            boolean langMatch = matchesAnyOrAll(app.getLanguage(), selectedLang, matchMode);

            if (matchMode == 1) {
                if (skillMatch || fosMatch || eduMatch || langMatch) {
                    result.addWithSort(app);
                }
            }  else if (matchMode == 2) {
                if (skillMatch && fosMatch && eduMatch && langMatch) {
                    result.addWithSort(app);
                }
            }
 
        }

        return result;
    }

    private static boolean matchesAnyOrAll(
        SortedListInterface<String> applicantAttr,
        SortedListInterface<String> selected,
        int matchMode
    ) {
        if (selected == null || selected.getSize() == 0) return matchMode == 2; 

        int matchCount = 0;
        for (int i = 0; i < selected.getSize(); i++) {
            String item = selected.viewDataAtIndex(i);
            for (int j = 0; j < applicantAttr.getSize(); j++) {
                if (item.equalsIgnoreCase(applicantAttr.viewDataAtIndex(j))) {
                    matchCount++;
                    break;
                }
            }
        }

        
        if (matchMode == 1) return matchCount > 0;           
        else return matchCount == selected.getSize();          
    }



    public static SortedListInterface<String> getSkillsOptions() {
        return Initializer.initializeSkills();
    }

    public static SortedListInterface<String> getFOSOptions() {
        return Initializer.initializeFieldsOfStudy();
    }

    public static SortedListInterface<String> getEducationOptions() {
        return Initializer.initializeEducationLevels();
    }

    public static SortedListInterface<String> getLanguagesOptions() {
        return Initializer.initializeLanguages();
    }
    
    public static void generateReports(SortedListInterface<Applicant> applicants) {
        int choice= 0;
        do {
            int menuChoice = ApplicantUI.generateReportMenu();

            switch (menuChoice) {
                case 1:
                    generateTotalApplicantsReport(applicants);
                    pressEnterToContinue();
                    choice = menuChoice;
                    break;
                case 2:
                    generateSkillsDistributionReport(applicants);
                    pressEnterToContinue();
                    choice = menuChoice;
                    break;
                case 3:
                    generateLanguageProficiencyReport(applicants);
                    pressEnterToContinue();
                    choice = menuChoice;
                    break;
                case 4:
                    generateEducationLevelSummaryReport(applicants);
                    pressEnterToContinue();
                    choice = menuChoice;
                    break;
                case 5:
                    generateMostActiveApplicantsReport(applicants);
                    pressEnterToContinue();
                    choice = menuChoice;
                    break;
                case 0:
                    System.out.println("Returning to Applicant Management...");
                    choice = menuChoice;
                    break;
                default:
                    ApplicantUI.printInvalidChoice();
                    choice = menuChoice;
                    break;
            }
        } while (choice != 0);
    }

    // Total Applicants Report
    private static void generateTotalApplicantsReport(SortedListInterface<Applicant> applicants) {
        System.out.println("Generating Total Applicants Report...");
        int totalApplicants = applicants.getSize();
        System.out.println("Total Applicants: " + totalApplicants);
        ApplicantUI.printReportSuccess("Total applicants report generated successfully.");
        SystemLogger.log("Generated Total Applicants Report");
    }


    public static void generateHeader(String title) {
        System.out.println("============================================================");
        System.out.printf("%35s\n", title);
        System.out.println("============================================================\n");
    }

    public static void generateFooter() {
        System.out.println("\n============================================================");
        System.out.printf("%35s\n", "END OF REPORT");
        System.out.println("============================================================\n");
    }

    public static boolean generateSkillsDistributionReport(SortedListInterface<Applicant> applicants) {
        SortedListInterface<String> allSkills = getSkillsOptions();
        boolean hasData = false;

        ApplicantUI.printReportHeader("SKILLS DISTRIBUTION REPORT");
        System.out.printf("%-25s | %s\n", "Skill", "No. of Applicants");
        System.out.println("---------------------------+--------------------");

        for (int i = 0; i < allSkills.getSize(); i++) {
            String skill = allSkills.viewDataAtIndex(i);
            int count = 0;
            for (int j = 0; j < applicants.getSize(); j++) {
                if (applicants.viewDataAtIndex(j).getSkills().contains(skill)) {
                    count++;
                }
            }
            if (count > 0) {
                hasData = true;
                System.out.printf("%-25s | %d\n", skill, count);
            }
        }
        SystemLogger.log("Generated Skills Distribution Report");
        if (!hasData) {
            System.out.println("No data to display.");
        }
        ApplicantUI.printReportFooter();
        return hasData;
    }

    public static boolean generateEducationLevelSummaryReport(SortedListInterface<Applicant> applicants) {
        SortedListInterface<String> allEducation = getEducationOptions();
        boolean hasData = false;

        ApplicantUI.printReportHeader("EDUCATION LEVEL SUMMARY");
        System.out.printf("%-30s | %s\n", "Education Level", "No. of Applicants");
        System.out.println("--------------------------------+--------------------");

        for (int i = 0; i < allEducation.getSize(); i++) {
            String edu = allEducation.viewDataAtIndex(i);
            int count = 0;
            for (int j = 0; j < applicants.getSize(); j++) {
                if (applicants.viewDataAtIndex(j).getEducation().contains(edu)) {
                    count++;
                }
            }
            if (count > 0) {
                hasData = true;
                System.out.printf("%-30s | %d\n", edu, count);
            }
        }
        SystemLogger.log("Generated Educations Distribution Report");
        if (!hasData) {
            System.out.println("No data to display.");
        }
        ApplicantUI.printReportFooter();
        return hasData;
    }

    public static boolean generateLanguageProficiencyReport(SortedListInterface<Applicant> applicants) {
        SortedListInterface<String> allLanguages = getLanguagesOptions();
        boolean hasData = false;

        ApplicantUI.printReportHeader("LANGUAGE PROFICIENCY REPORT");
        System.out.printf("%-20s | %s\n", "Language", "No. of Applicants");
        System.out.println("----------------------+--------------------");

        for (int i = 0; i < allLanguages.getSize(); i++) {
            String lang = allLanguages.viewDataAtIndex(i);
            int count = 0;
            for (int j = 0; j < applicants.getSize(); j++) {
                if (applicants.viewDataAtIndex(j).getLanguage().contains(lang)) {
                    count++;
                }
            }
            if (count > 0) {
                hasData = true;
                System.out.printf("%-20s | %d\n", lang, count);
            }
        }
        SystemLogger.log("Generated Langueges Distribution Report");
        if (!hasData) {
            System.out.println("No data to display.");
        }
        ApplicantUI.printReportFooter();
        return hasData;
    }

    public static boolean generateMostActiveApplicantsReport(SortedListInterface<Applicant> applicants) {
        SortedListInterface<Applicant> activeApplicants = new SortedDoublyLinkedList<>();
        SortedListInterface<Integer> activityCounts = new SortedDoublyLinkedList<>();

        for (int i = 0; i < applicants.getSize(); i++) {
            Applicant applicant = applicants.viewDataAtIndex(i);
            int activityCount = applicant.getApplication() == null ? 0 : applicant.getApplication().getSize();
            if (activityCount > 0) {
                activeApplicants.addWithSort(applicant);
                activityCounts.addWithSort(activityCount);
            }
        }

        // Bubble sort by descending number of applications
        for (int i = 0; i < activeApplicants.getSize() - 1; i++) {
            for (int j = i + 1; j < activeApplicants.getSize(); j++) {
                if (activityCounts.viewDataAtIndex(i) < activityCounts.viewDataAtIndex(j)) {
                    int tempCount = activityCounts.viewDataAtIndex(i);
                    activityCounts.updateNodeByIndex(i, activityCounts.viewDataAtIndex(j));
                    activityCounts.updateNodeByIndex(j, tempCount);

                    Applicant tempApplicant = activeApplicants.viewDataAtIndex(i);
                    activeApplicants.updateNodeByIndex(i, activeApplicants.viewDataAtIndex(j));
                    activeApplicants.updateNodeByIndex(j, tempApplicant);
                }
            }
        }

        ApplicantUI.printReportHeader("MOST ACTIVE APPLICANTS");

        if (activeApplicants.isEmpty()) {
            System.out.println("No data to display.");
            ApplicantUI.printReportFooter();
            return false;
        }

        System.out.printf("%-25s | %-10s\n", "Applicant Name (ID)", "Applications");
        System.out.println("-----------------------------+-------------");

        for (int i = 0; i < activeApplicants.getSize(); i++) {
            Applicant applicant = activeApplicants.viewDataAtIndex(i);
            int count = activityCounts.viewDataAtIndex(i);
            System.out.printf("%-25s | %d\n", applicant.getName() + " (" + applicant.getUserID() + ")", count);
        }
        SystemLogger.log("Generated Active Applicant Report");
        ApplicantUI.printReportFooter();
        return true;
    }      
        
} 
