/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;
import controller.JobController;
import entity.Company;
import entity.Job;
import java.util.InputMismatchException;
import java.util.Scanner;
import utility.utility1;
import utility.JobMessageUI;
import utility.utility1;

/**
 *
 * @author Cason Soh
 */
public class JobUI {

    Scanner sc = new Scanner(System.in);

    public void resetLine() {
        sc.nextLine();
    }

    public int displayEmpModuleMenu() {
        System.out.println(" ________                      __                                  ____    ____               __          __         ");
        System.out.println("|_   __  |                    [  |                                |_   \\  /   _|             |  ]        [  |        ");
        System.out.println("  | |_ \\_| _ .--..--.  _ .--.  | |  .--.   _   __  .---.  _ .--.    |   \\/   |   .--.    .--.| | __   _   | | .---.  ");
        System.out.println("  |  _| _ [ `.-. .-. |[ '/'`\\ \\| |/ .'`\\ \\[ \\ [  ]/ /__\\[ `/'`\\]   | |\\  /| | / .'`\\ \\/ /'`\\\' |[  | | |  | |/ /__\\\\ ");
        System.out.println(" _| |__/ | | | | | | | | \\__/ || || \\__. | \\ '/ / | \\__., | |      _| |\\/_| |_| \\__. || \\__/  | | \\_/ |, | || \\__., ");
        System.out.println("|________|[___||__||__]| ;.__/[___]'.__.'[\\_:  /   '.__.'[___]    |_____||_____|'.__.'  '.__.;__]'.__.'_/[___]'.__.' ");
        System.out.println("                      [__|                \\__.'                                                                      ");
        System.out.println("\n================================================== Company Portal ==================================================");
        System.out.println("1. Signup");
        System.out.println("2. Login");
        System.out.println("3. Filter Jobs");
        System.out.println("4. Generate Report");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        int menuOption = sc.nextInt();
        resetLine();
        System.out.println("\n\n");
        return menuOption;
    }

    public void displaySignUpHeader() {
        System.out.println("   ______                                                          ______    _                                   ");
        System.out.println(" .' ___  |                                                       .' ____ \\  (_)                                  ");
        System.out.println("/ .'   \\_|  .--.   _ .--..--.  _ .--.   ,--.   _ .--.    _   __  | (___ \\_| __   .--./) _ .--.  __   _  _ .--.   ");
        System.out.println("| |       / .'`\\ \\[ `.-. .-. |[ '/'`\\ `'\\_ : [ `.-. |  [ \\ [  ]  _.____`. [  | / /'`\\;[ `.-. |[  | | |[ '/'`\\ \\ ");
        System.out.println("\\ `.___.'\\| \\__. | | | | | | | | \\__/ |// | |, | | | |   \\ '/ /  | \\____) | | | \\ \\._// | | | | | \\_/ |,| \\__/ | ");
        System.out.println(" `.____ .' '.__.' [___||__||__]| ;.__/ \\'-;__/[___||__][\\_:  /    \\______.'[___].',__` [___||__]'.__.'_/| ;.__/  ");
        System.out.println("                              [__|                      \\__.'                  ( ( __))                [__|      ");
        System.out.println("========================================================================================");
    }

    public String getCompanyNameForSignUp() {
        String companyName;
        while (true) {
            System.out.print("Enter your company name: ");
            companyName = sc.nextLine().trim();
            if (companyName.isEmpty()) {
                System.out.println("Company name cannot be empty.");
                continue;
            }
            return companyName;
        }
    }

    public String getCompanyEmailForSignUp() {
        String email;
        while (true) {
            System.out.print("Enter your company email: ");
            email = sc.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email cannot be empty.");
                continue;
            }
            return email;
        }
    }

    public void displayNewCompanyId(String companyId) {
        System.out.println("New Company ID: " + companyId);
    }

    public void displayLoginHeader() {
        System.out.println("   ______                                                         _____                     _            ");
        System.out.println(" .' ___  |                                                       |_   _|                   (_)           ");
        System.out.println("/ .'   \\_|  .--.   _ .--..--.  _ .--.   ,--.   _ .--.    _   __    | |       .--.   .--./) __   _ .--.   ");
        System.out.println("| |       / .'`\\ \\[ `.-. .-. |[ '/'`\\ `'\\_ : [ `.-. |  [ \\ [   ]   | |   _ / .'`\\ \\/ /'`\\;[  | [ `.-. |  ");
        System.out.println("\\ `.___.'\\| \\__. | | | | | | | | \\__/ |// | |, | | | |   \\ '/ /   _| |__/ || \\__. |\\ \\._// | |  | | | |  ");
        System.out.println(" `.____ .' '.__.' [___||__||__]| ;.__/ \\'-;__/[___||__][\\_:  /   |________| '.__.' .',__` [___][___||__] ");
        System.out.println("                              [__|                      \\__.'                     ( ( __))               ");
        System.out.println("\nSelect a company to log in:");
    }

    public void displayNoCompaniesRegistered() {
        System.out.println("No companies registered yet. Please sign up first.");
    }

    public void displayCompanyListForLogin(SortedListInterface<Company> companies) {
        for (int i = 0; i < companies.getSize(); i++) {
            System.out.printf("%d. %s\n", (i + 1), companies.viewDataAtIndex(i).getCompanyName());
        }
    }

    public int getCompanyChoiceForLogin() {
        System.out.print("Enter the number of the company you want to log in (or 0 to go back to Main Menu): ");
        String choiceInput = sc.nextLine();
        try {
            return Integer.parseInt(choiceInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    public void displayLoginSuccess(String companyName) {
        System.out.println("Login successful for " + companyName + "!");
    }

    public void displayCompanyMenuHeader(String companyName) {
        System.out.println("\n--- Welcome, " + companyName + " ---");
        System.out.println("1. View My Job");
        System.out.println("2. Add New Job");
        System.out.println("3. Edit Job");
        System.out.println("4. Delete Job");
        System.out.println("Enter any character to quit");
        System.out.print("Enter your choice: ");
    }

    public int getCompanyMenuChoice() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            JobMessageUI.displayInvalidListNum();
            return -1;
        }
    }

    public static void noJobMessage() {
        System.out.println("|                                         No jobs posted yet.                                         |");
    }

    public void printHeader() {
        System.out.println("=============================================================================================================================================================================================================================================");
        System.out.printf("| %-5s | %-20s | %-20s | %-30s | %-30s | %-20s | %-40s | %-30s | %-10s |\n",
                "No.", "Job Title", "Location", "Required Skills", "Required Education Level", "Language", "Description", "Field Of Study", "Salary");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void printBottom() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void printJobDetailsRow(int index, String title, String location, String skills, String education, String language, String description, String fos, int salary, int status) {
        String statusString = (status == 0) ? "Closed" : "Open";
        System.out.printf("| %-5d | %-20s | %-20s | %-30s | %-30s | %-20s | %-40s | %-30s | %-10d | %-10s |\n",
                index, title, location, skills, education, language, description, fos, salary, statusString);
    }

    public void printJobDetailsRowWithComp(int index, String title, String location, String skills, String education, String language, String description, String fos, int salary, int status, String companyName) {
        String statusString = (status == 0) ? "Closed" : "Open";
        System.out.printf("| %-5d | %-20s | %-20s | %-30s | %-30s | %-20s | %-40s | %-30s | %-10d | %-20s |\n",
                index, title, location, skills, education, language, description, fos, salary, companyName);
    }

    public int getJobChoice(int maxChoice) {
        String input = sc.nextLine();
        try {
            int choice = Integer.parseInt(input);
            return choice;
        } catch (NumberFormatException e) {
            JobMessageUI.displayInvalidNum();
            return -1;
        }
    }

    public void displayFullJobDetails(Job job) {
        System.out.println("========================= Job Details =========================");
        System.out.println("Job ID: " + job.getJobID());
        System.out.println("Title: " + job.getTitle());
        System.out.println("Description: " + job.getDesc());
        System.out.println("Location: " + job.getLocation());
        System.out.println("Salary: RM" + job.getSalary());
        System.out.println("Required Skills: " + utility1.listToString(job.getReqSkill()));
        System.out.println("Required Education Level: " + utility1.listToString(job.getReqEdLevel()));
        System.out.println("Languages: " + utility1.listToString(job.getLanguage()));
        System.out.println("Field Of Study: " + utility1.listToString(job.getFos()));
        System.out.println("===============================================================");
    }

    public boolean promptViewAnotherJob() {
        System.out.print("View another job detail? (y/x): ");
        String input = sc.nextLine().trim().toLowerCase();
        if (!input.equals("y") && !input.equals("x")) {
            JobMessageUI.displayInvalidInputYX();
            return promptViewAnotherJob(); // Recursive call to re-prompt
        }
        return input.equals("y");
    }

    public boolean confirmJobDeletion(String jobTitle) {
        System.out.print("Are you sure you want to delete \"" + jobTitle + "\"? (y - yes / anykey - no): ");
        return sc.nextLine().trim().equalsIgnoreCase("Y");
    }

    public boolean promptContinueOperation() {
        System.out.print("Continue deleting another job? (y - yes / anykey - no): ");
        String input = sc.nextLine().trim().toLowerCase();
        return input.equals("y");
    }

    public void displayAddJobHeader() {
        System.out.println("\n--- Add New Job ---");
    }

    public void displayGeneratedJobID(String jobID) {
        System.out.println("Job ID (auto-generated): " + jobID);
    }

    public String getJobTitle() {
        while (true) {
            System.out.print("Title (max 20 characters, or 'c' to cancel): ");
            String inputTitle = sc.nextLine();
            if (inputTitle.equalsIgnoreCase("c")) {
                return null;
            }
            if (inputTitle.length() <= 20 && !inputTitle.isEmpty()) {
                return inputTitle;
            } else {
                JobMessageUI.displayInvalidTitleMsg();
            }
        }
    }

    public String getJobDescription() {
        while (true) {
            System.out.print("Description (max 50 characters, or 'c' to cancel): ");
            String inputDesc = sc.nextLine();
            if (inputDesc.equalsIgnoreCase("c")) {
                return null;
            }
            if (inputDesc.length() <= 50) {
                return inputDesc;
            } else {
                JobMessageUI.displayInvalidDescMsg();
            }
        }
    }

    public String getJobLocation() {
        String[] states = {
            "Johor", "Kedah", "Kelantan", "Melaka", "Negeri Sembilan", "Pahang", "Perak", "Perlis", "Pulau Pinang", "Sabah", "Sarawak", "Selangor", "Terengganu", "Kuala Lumpur"
        };
        System.out.println("\nAvailable Locations:");
        for (int i = 0; i < states.length; i++) {
            System.out.println((i + 1) + ". " + states[i]);
        }

        while (true) {
            System.out.print("Enter the number corresponding to your desired location (or 0 to cancel): ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (choice == 0) {
                    return null;
                }
                if (choice >= 1 && choice <= states.length) {
                    String location = states[choice - 1];
                    System.out.println("You selected: " + location);
                    return location;
                } else {
                    JobMessageUI.displayInvalidListNum();
                }
            } else {
                JobMessageUI.displayInvalidNum();
                sc.next();
            }
        }
    }

    public int getJobSalary() {
        while (true) {
            System.out.print("Salary (RM, enter -1 to cancel): ");
            if (sc.hasNextInt()) {
                int salary = sc.nextInt();
                sc.nextLine();
                if (salary == -1) {
                    return -1;
                }
                if (salary >= 0) {
                    return salary;
                } else {
                    JobMessageUI.displayInvalidSalaryMsg();
                }
            } else {
                JobMessageUI.displayInvalidNum();
                sc.next();
            }
        }
    }

    public SortedListInterface<String> getSingleEducationLevelFromUI(SortedListInterface<String> educationLevels) {
        SortedListInterface<String> selectedEducationList = new SortedDoublyLinkedList<>();
        System.out.println("\nAvailable Education Levels:");
        for (int i = 0; i < educationLevels.getSize(); i++) {
            System.out.println((i + 1) + ". " + educationLevels.viewDataAtIndex(i));
        }

        while (true) {
            System.out.print("Enter the number corresponding to the required education level (or 0 to cancel): ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 0) {
                    return null;
                }
                if (choice >= 1 && choice <= educationLevels.getSize()) {
                    String selectedEducation = educationLevels.viewDataAtIndex(choice - 1);
                    System.out.println("You selected: " + selectedEducation);
                    selectedEducationList.addWithSort(selectedEducation);
                    return selectedEducationList;
                } else {
                    JobMessageUI.displayInvalidListNum();
                }
            } else {
                JobMessageUI.displayInvalidNum();
                sc.next();
            }
        }
    }

    public String getMultipleOptionChoice(String categoryName, SortedListInterface<String> options) {
        System.out.println("\nAvailable " + categoryName + ":");
        for (int i = 0; i < options.getSize(); i++) {
            System.out.println((i + 1) + ". " + options.viewDataAtIndex(i));
        }
        System.out.print("Your choice (or 0 to finish): ");
        return sc.nextLine();
    }

    public void displaySelectedOptions(String categoryName, SortedListInterface<String> selected) {
        if (!selected.isEmpty()) {
            System.out.println("\nCurrently selected " + categoryName + ": " + utility1.listToString(selected));
        }
    }

    public boolean promptAddMoreOptions(String categoryName) {
        System.out.print("Do you want to add more " + categoryName + "? (y/n): ");
        String continueInput = sc.nextLine().trim().toLowerCase();
        return continueInput.equals("y");
    }

    public boolean confirmAddJob(Job job) {
        displayFullJobDetails(job);
        System.out.print("Confirm adding job \"" + job.getTitle() + "\"? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();
        return confirm.equals("y");
    }

    public boolean promptAddAnotherJob() {
        System.out.print("Add another job? (y/n): ");
        String answer = sc.nextLine().trim().toLowerCase();
        return answer.equals("y");
    }

    public void displayFinalSelectedOptions(String categoryName, SortedListInterface<String> selected) {
        System.out.println("\nFinal selected " + categoryName + ":");
        if (selected.isEmpty()) {
            System.out.println("No " + categoryName + " selected.");
        } else {
            for (int i = 0; i < selected.getSize(); i++) {
                System.out.println("- " + selected.viewDataAtIndex(i));
            }
        }
    }

    public int getJobIndexToEdit(int maxIndex) {
        System.out.print("Enter the number of the job to edit (or 0 to go back): ");
        try {
            int jobIndex = Integer.parseInt(sc.nextLine());
            if (jobIndex == 0) {
                return -1;
            }
            if (jobIndex < 1 || jobIndex > maxIndex) {
                JobMessageUI.displayInvalidListNum();
                return getJobIndexToEdit(maxIndex);
            }
            return jobIndex - 1;
        } catch (NumberFormatException e) {
            JobMessageUI.displayInvalidNum();
            return getJobIndexToEdit(maxIndex);
        }
    }

    public void displayEditJobMenu(Job job) {
        displayFullJobDetails(job);
        System.out.println("Editing Job: " + job.getTitle() + " - " + job.getJobID());
        System.out.println("Choose an attribute to edit:");
        System.out.println("1. Title");
        System.out.println("2. Description");
        System.out.println("3. Location");
        System.out.println("4. Salary (RM)");
        System.out.println("5. Skills");
        System.out.println("6. Education Levels");
        System.out.println("7. Fields of Study");
        System.out.println("8. Languages");
        System.out.println("8. Job Status");
        System.out.println("0. Back to Company Menu");
        printBottom();
    }

    public String getEditAttributeChoice() {
        System.out.print("Enter your choice: ");
        return sc.nextLine();
    }

    public String getNewJobTitle(String currentTitle) {
        System.out.print("Enter new title (max 20 characters, or press Enter to keep current: \"" + currentTitle + "\"): ");
        String inputTitle = sc.nextLine();
        return inputTitle.isEmpty() ? null : (inputTitle.length() <= 20 ? inputTitle : getNewJobTitle("Title exceeds limit. Current: " + currentTitle));
    }

    public String getNewJobDescription(String currentDesc) {
        System.out.print("Enter new description (max 50 characters, or press Enter to keep current: \"" + currentDesc + "\"): ");
        String inputDesc = sc.nextLine();
        return inputDesc.isEmpty() ? null : (inputDesc.length() <= 50 ? inputDesc : getNewJobDescription("Description exceeds limit. Current: " + currentDesc));
    }

    public String getNewJobLocation(String[] states, String currentLocation) {
        System.out.println("Available Locations:");
        for (int i = 0; i < states.length; i++) {
            System.out.println((i + 1) + ". " + states[i]);
        }
        System.out.println("Current Location: " + currentLocation);

        while (true) {
            System.out.print("Enter the number for the new location: ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("current")) {
                return null; // Return null to indicate no change
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= states.length) {
                    return states[choice - 1];
                } else {
                    JobMessageUI.displayInvalidListNum();
                }
            } catch (NumberFormatException e) {
                JobMessageUI.displayInvalidListNum();
            }
        }
    }

    public Integer getNewJobSalary(int currentSalary) {
        System.out.print("Enter new salary (RM, enter 'current' to keep: " + currentSalary + "): ");
        String inputSalary = sc.nextLine().trim().toLowerCase();
        if (inputSalary.equals("current")) {
            return null;
        }
        try {
            return Integer.parseInt(inputSalary);
        } catch (NumberFormatException e) {
            JobMessageUI.displayInvalidSalaryMsg();
            return getNewJobSalary(currentSalary); // Reprompt
        }
    }

    public SortedListInterface<String> getNewEducationLevel(SortedListInterface<String> availableLevels, SortedListInterface<String> currentLevel) {
        System.out.println("\n--- Select Education Level ---");
        System.out.println("Available Education Levels:");
        for (int i = 0; i < availableLevels.getSize(); i++) {
            System.out.println((i + 1) + ". " + availableLevels.viewDataAtIndex(i));
        }

        System.out.println("\nCurrent Education Level: " + (currentLevel.isEmpty() ? "None" : utility1.listToString(currentLevel)));

        while (true) {
            System.out.print("Enter the number for the new education level (or enter 'current' to keep current): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("current")) {
                return null; // Return null to indicate no change
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= availableLevels.getSize()) {
                    String selectedEducation = availableLevels.viewDataAtIndex(choice - 1);
                    SortedListInterface<String> selectedEducationList = new SortedDoublyLinkedList<>(); // Create a new list
                    selectedEducationList.addWithSort(selectedEducation);
                    System.out.println("You selected: " + selectedEducation);
                    return selectedEducationList;
                } else {
                    JobMessageUI.displayInvalidListNum();
                }
            } catch (NumberFormatException e) {
                JobMessageUI.displayInvalidListNum();
            }
        }
    }

    public Integer getNewJobStatus(int currentStatus) {
        System.out.println("\nCurrent Job Status: " + (currentStatus == 0 ? "Closed" : "Open"));
        System.out.println("Select new job status:");
        System.out.println("1. Open");
        System.out.println("0. Closed");

        while (true) {
            System.out.print("Enter your choice (0 or 1): ");
            String input = sc.nextLine().trim();

            try {
                int newStatus = Integer.parseInt(input);
                if (newStatus == 0 || newStatus == 1) {
                    return newStatus;
                } else {
                    JobMessageUI.displayInvalidListNum();
                }
            } catch (NumberFormatException e) {
                JobMessageUI.displayInvalidListNum();
            }
        }
    }

    public boolean confirmChange(String attribute, String oldValue, String newValue) {
        System.out.println("Current " + attribute + ": \"" + oldValue + "\"");
        System.out.println("New " + attribute + ": \"" + newValue + "\"");
        System.out.print("Confirm change? (y/n): ");
        return sc.nextLine().trim().toLowerCase().equals("y");
    }

    public SortedListInterface<String> editMultipleOptions(String categoryName, SortedListInterface<String> availableOptions, SortedListInterface<String> currentSelected) {
        SortedListInterface<String> selected = new SortedDoublyLinkedList<>();
        for (int i = 0; i < currentSelected.getSize(); i++) {
            selected.addWithSort(currentSelected.viewDataAtIndex(i));
        }

        boolean addingMore = true;
        while (addingMore) {
            displaySelectedOptions("Current " + categoryName, selected); // Modified categoryName for display
            String choice = getMultipleOptionChoice("available " + categoryName, availableOptions);

            if (choice.equals("0")) {
                addingMore = false;
            } else {
                try {
                    int i = Integer.parseInt(choice.trim()) - 1;
                    if (i >= 0 && i < availableOptions.getSize()) {
                        String chosen = availableOptions.viewDataAtIndex(i);
                        if (!selected.contains(chosen)) {
                            selected.addWithSort(chosen);
                            System.out.println("Added: " + chosen);
                        } else {
                            // Find the node to remove
                            int indexToRemove = selected.indexOf(chosen);
                            if (indexToRemove != -1) {
                                selected.removeByIndex(indexToRemove);
                                System.out.println("Removed: " + chosen);
                            }
                        }
                    } else {
                        System.out.println("Invalid option number: " + (i + 1));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + choice);
                }
            }
            if (addingMore) {
                System.out.print("Add/remove more " + categoryName + "? (y/n): ");
                addingMore = sc.nextLine().trim().toLowerCase().equals("y");
            }
        }
        displayFinalSelectedOptions(categoryName, selected);
        System.out.print("Confirm changes to " + categoryName + "? (y/n): ");
        return sc.nextLine().trim().toLowerCase().equals("y") ? selected : null;
    }

    public void displayFilterJobMenu() {
        System.out.println("\n--- Filter Jobs ---");
        System.out.println("Select attributes to filter by (enter 0 to finish):");
        System.out.println("1. Job Title");
        System.out.println("2. Location");
        System.out.println("3. Salary Range");
        System.out.println("4. Required Skills");
        System.out.println("5. Required Education Level");
        System.out.println("6. Field of Study");
        System.out.println("7. Language");
        System.out.println("8. Job Status");
        System.out.println("0. Apply Filters");
        System.out.print("Enter your choice: ");
    }

    public String getFilterChoice() {
        return sc.nextLine().trim();
    }

    public String getFilterTitle() {
        System.out.print("Enter job title to filter (case-insensitive, partial match, or 'any' for no filter): ");
        String title = sc.nextLine().trim();
        return title.isEmpty() ? "any" : title;
    }

    public String getFilterLocation() {
        String[] states = {
            "Johor", "Kedah", "Kelantan", "Melaka", "Negeri Sembilan", "Pahang", "Perak", "Perlis", "Pulau Pinang", "Sabah", "Sarawak", "Selangor", "Terengganu", "Kuala Lumpur"
        };
        System.out.println("\nAvailable Locations:");
        for (int i = 0; i < states.length; i++) {
            System.out.println((i + 1) + ". " + states[i]);
        }
        System.out.println("0. Any location");
        while (true) {
            System.out.print("Enter the number corresponding to the location (or 0 for any): ");
            String input = sc.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    return "any";
                }
                if (choice >= 1 && choice <= states.length) {
                    return states[choice - 1];
                } else {
                    JobMessageUI.displayInvalidListNum();
                }
            } catch (NumberFormatException e) {
                JobMessageUI.displayInvalidNum();
            }
        }
    }

    public int[] getFilterSalaryRange() {
        int[] range = new int[]{-1, -1};
        System.out.println("Enter salary range (RM, enter -1 for min or max to ignore)");
        while (true) {
            System.out.print("Minimum salary: ");
            String minInput = sc.nextLine().trim();
            try {
                range[0] = Integer.parseInt(minInput);
                break;
            } catch (NumberFormatException e) {
                JobMessageUI.displayInvalidNum();
            }
        }
        while (true) {
            System.out.print("Maximum salary: ");
            String maxInput = sc.nextLine().trim();
            try {
                range[1] = Integer.parseInt(maxInput);
                break;
            } catch (NumberFormatException e) {
                JobMessageUI.displayInvalidNum();
            }
        }
        return range;
    }

    public SortedListInterface<String> getFilterMultipleOptions(String categoryName, SortedListInterface<String> options) {
        SortedListInterface<String> selected = new SortedDoublyLinkedList<>();
        boolean addingMore = true;
        while (addingMore) {
            displaySelectedOptions(categoryName, selected);
            String choice = getMultipleOptionChoice(categoryName, options);
            if (choice.equals("0")) {
                addingMore = false;
            } else {
                try {
                    int i = Integer.parseInt(choice.trim()) - 1;
                    if (i >= 0 && i < options.getSize()) {
                        String chosen = options.viewDataAtIndex(i);
                        if (!selected.contains(chosen)) {
                            selected.addWithSort(chosen);
                            System.out.println("Added: " + chosen);
                        } else {
                            System.out.println("Already selected: " + chosen);
                        }
                    } else {
                        JobMessageUI.displayInvalidListNum();
                    }
                } catch (NumberFormatException e) {
                    JobMessageUI.displayInvalidNum();
                }
            }
            if (addingMore) {
                addingMore = promptAddMoreOptions(categoryName);
            }
        }
        displayFinalSelectedOptions(categoryName, selected);
        return selected.isEmpty() ? null : selected;
    }

    public Integer getFilterJobStatus() {
        System.out.println("Select job status to filter:");
        System.out.println("1. Open");
        System.out.println("0. Closed");
        System.out.println("2. Any");
        while (true) {
            System.out.print("Enter your choice (0, 1, or 2): ");
            String input = sc.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 0 && choice <= 2) {
                    return choice == 2 ? null : choice;
                } else {
                    JobMessageUI.displayInvalidListNum();
                }
            } catch (NumberFormatException e) {
                JobMessageUI.displayInvalidNum();
            }
        }
    }

    public void printReport(SortedListInterface<JobController.JobTitleApplication> jobTitleList,SortedListInterface<Job> topJobsBySalary,Company topCompany,int topCompanyApplicationCount) {
        System.out.println("=============================================================");
        System.out.println("           Comprehensive Summary Report (Company & Job)       ");
        System.out.println("=============================================================");

        // Top 5 Job Titles by Application Count
        System.out.println("\nTop 5 Hottest Job Types Applied by Applicants:");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-20s | %-12s\n", "Job Type", "Applications");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < Math.min(5, jobTitleList.getSize()); i++) {
            JobController.JobTitleApplication jta = jobTitleList.viewDataAtIndex(i);
            System.out.printf("%-20s | %-12d\n", jta.jobTitle, jta.applicationCount);
        }
        System.out.println("-------------------------------------------------------------");

        // Top 5 Jobs by Salary
        System.out.println("\nTop 5 Highest Salary Jobs:");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < Math.min(5, topJobsBySalary.getSize()); i++) {
            Job job = topJobsBySalary.viewDataAtIndex(i);
            System.out.printf("%-7s | %-20s | RM%-10d | %-20s\n",
                    job.getJobID(), job.getTitle(), job.getSalary(), job.getCompany().getCompanyName());
        }
        System.out.println("-------------------------------------------------------------");

        // Company with Highest Application Count
        System.out.println("\nTop Applied Company:");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-7s | %-20s | %-12d\n",
                topCompany.getCompanyId(), topCompany.getCompanyName(), topCompanyApplicationCount);
        System.out.println("=============================================================");
    }
}
