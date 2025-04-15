/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedListInterface;
import boundary.InterviewUI;
import boundary.MainMenuUI;
import entity.Applicant;
import entity.Application;
import entity.Company;
import entity.Job;
import utility.utility1;

public class InterviewController {

    public static void cmpInterviewMenu(Company company, SortedListInterface<Applicant> applicants) {
        while (true) {
            utility1.clearScreen();
            InterviewUI.interviewLogo();

            try {
                int choice = InterviewUI.cmpMenuUI();

                switch (choice) {
                    case 1:
                        createInterview(company);
                        break;
                    case 2:
                        viewInterview(company, applicants);
                        break;
                    case 3:
                        updateInterview();
                        break;
                    case 4:
                        removeInterview();
                        break;
                    case 5:
                        return;
                    default:
                        MainMenuUI.printInvalidMenuChoice();
                        MainMenuUI.pressEnterToContinue();
                        break;
                }
            } catch (NumberFormatException e) {
                MainMenuUI.printInvalidMenuChoice();
                MainMenuUI.pressEnterToContinue();
            }
        }
    }

    public static void createInterview(Company company) {
        displayCompanyApplications(company);
//        while (true) {
//            utility1.clearScreen();
//            InterviewUI.interviewLogo();

        // Display all applications for the company's jobs
//        displayCompanyApplications(company);
//            // Get user selection
//            int choice = InterviewUI.selectApplicationMenu(applicationCount);
//
//            if (choice == 0) {
//                return; // Exit to company menu
//            } else if (choice >= 1 && choice <= applicationCount) {
//                // Process the selected application
//                Application selectedApplication = getApplicationByIndex(company, choice - 1);
//                if (selectedApplication != null) {
//                    handleInterview(selectedApplication, company, applicants);
//                } else {
//                    InterviewUI.printInvalidSelection();
//                }
//            } else {
//                InterviewUI.printInvalidSelection();
//            }
//            InterviewUI.pressEnterToContinue();
        //}
    }

    private static void displayCompanyApplications(Company company) {
        utility1.clearScreen();
        InterviewUI.interviewLogo();
        SortedListInterface<Job> jobs = company.getJob();
        int count = 0;
        boolean headingDisplayed = false;

        for (int i = 0; i < jobs.getSize(); i++) {
            Job job = jobs.viewDataAtIndex(i);
            SortedListInterface<Application> applications = job.getApplication();

            if (applications != null && applications.getSize() > 0) {
                if (!headingDisplayed) {
                    InterviewUI.currentApplicationHeading(company.getCompanyName());
                    headingDisplayed = true;
                }
                for (int j = 0; j < applications.getSize(); j++) {
                    Application app = applications.viewDataAtIndex(j);
                    if (app != null) {
                        count++;
                        InterviewUI.displayApplication(
                                app.getApplicationID(),
                                app.getJob().getTitle(),
                                app.getApplicant().getName(),
                                app.getStatus());
                    }
                }
            }
        }

        if (count > 0) {
            InterviewUI.currentApplicationFooter();
        }

        if (count == 0) {
            InterviewUI.displayNoApplication();
        }
        System.out.println("test");
        MainMenuUI.pressEnterToContinue();
    }

    public static void viewInterview(Company company, SortedListInterface<Applicant> applicants) {
        displayCompanyApplications(company);

    }

    public static void updateInterview() {

    }

    public static void removeInterview() {

    }
}
