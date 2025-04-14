/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;

/**
 *
 * @author Acer
 */
public class ApplyUI {

     public static void header() {
        
        System.out.printf("| %-5s | %-20s | %-20s | %-15s | %-60s | %-35s |\n","No.", "Company","Job", "Location", "Requirement", "Description");
        
    }

    public static void displayAllJob(int jobNo,String companyName, String jobName, String location, 
                                     String requirement, String description) {
        System.out.printf("| %-5d | %-20s | %-20s | %-15s | %-60s | %-35s |\n", jobNo,
                          companyName, jobName, location, requirement, description);
    }
    
    
    
    public static void printFloor(int index){
        for(int i =0; i<index;i++){
            System.out.print("=");
        }
        System.out.println("");
    }
    
    public static int printMenu(){
        System.out.println("Select Job According to Number ( Type \"0\" to exit) ");
        System.out.print("Your selection: ");
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        return selection;
        
    }
    public static char applyConfirmation(String companyName,String companyEmail, String jobTitle, String jobLocation, String reqSkill, String reqEdLevel, String language, String desc, String fos ,int salary){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n\nYou are applying for the company: ");
        printFloor(62);
        System.out.printf("| %-20s | %-35s |\n", "Company", "Email");
        printFloor(62);
        System.out.printf("| %-20s | %-35s |\n",companyName,companyEmail);
        printFloor(62);
        
        System.out.println("\n\n");
        System.out.println("\nJob Information: ");
        printFloor(224);
        System.out.printf("| %-20s | %-20s | %-30s | %-30s | %-20s | %-40s | %-30s | %-10s |\n", "Job", "Location", "Required Skills", "Required Education Level","Language", "description", "Field Of Study","Salary" );
        printFloor(224);
        System.out.printf("| %-20s | %-20s | %-30s | %-30s | %-20s | %-40s | %-30s | %-10d |\n", jobTitle, jobLocation, reqSkill, reqEdLevel,language, desc, fos,salary );
        printFloor(224);
        
        System.out.println("");
        System.out.print("Confirm Application? (Y/N): ");

        char confirm = scanner.next().charAt(0);
        return confirm;
        
    }
    
    
    /** Applicants **/
    public static void showApplicantHeader(){
        printFloor(32);
        System.out.printf("| %-5s | %-20s |\n","No.","Student Name");
        printFloor(32);
    }
    public static void showApplicant(int index, String applicantName){
        System.out.printf("| %-5d | %-20s |\n", index,applicantName);
    }
    public static int selectApplicant(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect Applicant According to Number: (Type \"0\" to exit)");
        System.out.print("Your Selection : ");
        int selection = scanner.nextInt();
        return selection;
    }
    public static char getInput(){
        Scanner scanner = new Scanner (System.in);
        char confirm = scanner.next().charAt(0);
        return confirm;
    }
}
