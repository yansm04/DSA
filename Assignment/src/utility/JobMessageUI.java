/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Cason Soh
 */
public class JobMessageUI {

    public static void displayInvalidInputMsg() {
        System.out.println("❗ Invalid choice.");
    }

    public static void displayExitJobModuleMsg() {
        System.out.println("Exiting... Thank you for using the Job Module!");
    }

    public static void displayInvalidInput() {
        System.out.println("\nInvalid Input");
    }

    public static void displaySignUpSuccess() {
        System.out.println("Sign-up successful!");
    }

    public static void displayCompanyNameExistsMsg() {
        System.out.println("This company name has been used!");
    }

    public static void displayEmailExistsMsg() {
        System.out.println("This email has been used!");
    }

    public static void displayInvalidListNum() {
        System.out.println("Invalid choice. Please enter a number from the list.");
    }

    public static void displayReturnMainMenu() {
        System.out.println("Returning to the main menu.");
    }

    public static void displayInvalidInputYX() {
        System.out.println("Invalid input. Please enter 'y' to view another job or 'x' to quit.");
    }

    public static void displayReturnMessage() {
        System.out.println("Returning to the previous menu.");
    }

    public static void displayEnterNumJob(String phrase) {
        System.out.print("Enter the number of the job to " + phrase + ": ");
    }

    public static void displayInvalidNum() {
        System.out.println("Invalid input. Please enter a number.");
    }

    public static void displayLogoutMessage() {
        System.out.println("Logging out.");
    }

    public static void displayNotLoggedInMessage() {
        System.out.println("You are not logged in");
    }

    public static void displayJobOperationMessage(String jobTitle, String operation) {
        System.out.println("Job \"" + jobTitle + "\" has been " + operation);
    }

    public static void displayOperationCancelledMessage(String operation) {
        System.out.println(operation + " cancelled.");
    }

    public static void displayOptionSelected(String option) {
        System.out.println("You selected: " + option);
    }

    public static void displayOptionAlreadySelected(String option) {
        System.out.println("You have already selected: " + option);
    }

    public static void displayInvalidSalaryMsg() {
        System.out.println("Invalid input! Salary must be greater than 0.");
    }

    public static void displayInvalidTitleMsg() {
        System.out.println("Title must be between 1 and 20 characters. Please enter again.");
    }

    public static void displayInvalidDescMsg() {
        System.out.print("Description (max 50 characters, or 'c' to cancel): ");
    }

    public static void displayJobAttributeUpdated(String attribute, String newValue) {
        System.out.println(attribute + " updated successfully to: " + newValue);
    }

    public static void displayChangeReverted(String attribute, String oldValue) {
        System.out.println("Change reverted. " + attribute + " remains: " + oldValue);
    }

    public static void displayContinueMessage() {
        System.out.print("\nPress Enter to continue");
    }
    
    public static void displayCannotDeleteMsg() {
        System.out.print("\nCannot delete job, there are still application(s) left");
    }
}
