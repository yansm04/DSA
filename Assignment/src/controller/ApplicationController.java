/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedDoublyLinkedList;
import boundary.ApplyUI;
import entity.Company;
import entity.Applicant;
import entity.Application;
import entity.Job;
import utility.utility1;

import dao.Initializer;
import adt.SortedListInterface;

/**
 *
 * @author Acer
 */
public class ApplicationController {

    public static void mainApplication() {

        //Intitialize COmpany with jobs and applicants
        SortedListInterface<Company> companies = initializeJobs();
        SortedListInterface<Applicant> applicants = initializeApplicant();

        //Select Applicant here
        Applicant applicant = selectApplicant(applicants);

        //current applicant who is using the system
        //Display all jobs
        int jobNo = displayAllJobs(companies);
        int selection = ApplyUI.printMenu();
        Job selectedJob;

        selectedJob = applyJob(applicant, selection, companies, jobNo);
        selectedJob = applyConfirmation(applicant, selectedJob);

        applicant = addApplicationToApplicant(applicant, selectedJob);
        selectedJob = addApplicationToJob(applicant, selectedJob);
        updateListsAfterApplication(applicant, selectedJob, applicants, companies);

        printApplicantApplications(applicant);
        printAllApplicantApplications(applicants);

    }

    public static SortedListInterface<Company> initializeJobs() {
        return Initializer.initializeCompanyJob();
    }

    public static SortedListInterface<Applicant> initializeApplicant() {
        return Initializer.initializeApplicant();
    }

    public static int displayAllJobs(SortedListInterface<Company> companies) {
        ApplyUI.printFloor(174);
        ApplyUI.header();
        ApplyUI.printFloor(174);

        SortedListInterface<Job> jobs = new SortedDoublyLinkedList<>();

        Company tempComp = new Company();
        Job tempJob = new Job();
        int jobNo = 1;

        for (int i = 0; i < companies.getSize(); i++) {
            tempComp = companies.viewDataAtIndex(i);
            jobs = tempComp.getJob();
            for (int j = 0; j < jobs.getSize(); j++) {
                tempJob = jobs.viewDataAtIndex(j);

                String reqSkills = utility1.listToString(tempJob.getReqSkill());
                String reqEdu = utility1.listToString(tempJob.getReqEdLevel());
                String languages = utility1.listToString(tempJob.getLanguage());
                tempJob = jobs.viewDataAtIndex(j);
                ApplyUI.displayAllJob(jobNo, tempComp.getCompanyName(), tempJob.getTitle(), tempJob.getLocation(), tempJob.getReqSkill() + " " + tempJob.getReqEdLevel() + " " + tempJob.getLanguage(), tempJob.getDesc());
                jobNo++;
            }

        }
        ApplyUI.printFloor(174);
        return jobNo;
    }

    public static Job applyJob(Applicant applicant, int selection, SortedListInterface<Company> companies, int jobNo) {
        SortedListInterface<Job> jobs = new SortedDoublyLinkedList<>();

        Job selectedJob = null;

        char confirmation = 'n';
        if (selection < 0 || selection >= jobNo) {
            System.out.println("Invalid job selection! Please select valid selection");

        }
        int currentJobNo = 1;

        for (int i = 0; i < companies.getSize(); i++) {
            Company tempComp = companies.viewDataAtIndex(i);
            jobs = tempComp.getJob();
            for (int j = 0; j < jobs.getSize(); j++) {
                if (currentJobNo == selection) {
                    selectedJob = jobs.viewDataAtIndex(j);

                    break;

                }
                currentJobNo++;
            }
        }

        return selectedJob;
    }

    public static Job applyConfirmation(Applicant applicant, Job selectedJob) {
        char confirmation;
        String skillStr = utility1.listToString(selectedJob.getReqSkill());
        String eduStr = utility1.listToString(selectedJob.getReqEdLevel());
        String langStr = utility1.listToString(selectedJob.getLanguage());
        String fosStr = utility1.listToString(selectedJob.getFos());

        confirmation = ApplyUI.applyConfirmation(selectedJob.getCompany().getCompanyName(), selectedJob.getCompany().getEmail(), selectedJob.getTitle(), selectedJob.getLocation(), skillStr, eduStr, langStr, selectedJob.getDesc(), fosStr, selectedJob.getSalary());
        if (confirmation != 'y' && confirmation != 'Y') {
            System.out.println("Cancelled application");
            return null;

        } else {

            return selectedJob;

        }
    }

    public static Applicant addApplicationToApplicant(Applicant applicant, Job selectedJob) {
        if (applicant.getApplication() == null) {
            applicant.setApplication(new SortedDoublyLinkedList<>());
        }
        SortedListInterface<Application> applicationsApplicant = applicant.getApplication();
        SortedListInterface<Application> applicationsJob = selectedJob.getApplication();

        //setting up application
        Application application = new Application(selectedJob, applicant, "Not Approved", null);
        application.setApplicant(applicant);

        //setting up application in applicant
        applicationsApplicant.addWithSort(application);
        applicant.setApplication(applicationsApplicant);

        return applicant;

    }

    public static Job addApplicationToJob(Applicant applicant, Job selectedJob) {
        if (applicant.getApplication() == null) {
            applicant.setApplication(new SortedDoublyLinkedList<>());
        }
        SortedListInterface<Application> applicationsApplicant = applicant.getApplication();
        SortedListInterface<Application> applicationsJob = selectedJob.getApplication();

        //setting up application
        Application application = new Application(selectedJob, applicant, "Not Approved", null);
        application.setApplicant(applicant);

        //setting up application in applicant
        applicationsJob.addWithSort(application);
        selectedJob.setApplication(applicationsApplicant);

        return selectedJob;
    }

//    public static void addApplication(Applicant applicant, Application application, ListInterface<Application> applications, Job selectedJob) {
//        selectedJob.setApplication(applications);
//        application = new Application(selectedJob.getJobID(), "Not Approved", null);
//        application.setApplicantID(applicant.getUserID());
//
//        applications = applicant.getApplication();
//        if (applications == null) {  // Fix: Ensure applications is initialized
//            applications = new DoublyLinkedList<>();
//        }
//
//        applications.addWithSort(application);
//        applicant.setApplication(applications);
//        System.out.println(application.getApplicationID());
//
//    }
    public static Applicant selectApplicant(SortedListInterface<Applicant> applicants) {
        int selection;
        ApplyUI.showApplicantHeader();

        for (int i = 0; i < applicants.getSize(); i++) {
            Applicant applicant = applicants.viewDataAtIndex(i);
            ApplyUI.showApplicant(i + 1, applicant.getName());

        }
        ApplyUI.printFloor(32);
        selection = ApplyUI.selectApplicant();
        for (int i = 0; i < applicants.getSize(); i++) {
            if ((selection - 1) == i) {
                Applicant applicant = applicants.viewDataAtIndex(i);
                System.out.println(applicant.getName());
                return applicant;
            }
        }
        return null;
    }

    public static void updateListsAfterApplication(Applicant applicant, Job selectedJob, SortedListInterface<Applicant> applicants, SortedListInterface<Company> companies) {
        // Update applicant in the applicants list
        for (int i = 0; i < applicants.getSize(); i++) {
            if (applicants.viewDataAtIndex(i).getUserID().equals(applicant.getUserID())) {
                applicants.updateNodeByIndex(i, applicant);
                break;
            }
        }

        // Update job in its respective company
        for (int i = 0; i < companies.getSize(); i++) {
            Company comp = companies.viewDataAtIndex(i);
            SortedListInterface<Job> jobs = comp.getJob();
            for (int j = 0; j < jobs.getSize(); j++) {
                Job job = jobs.viewDataAtIndex(j);
                if (job.getJobID().equals(selectedJob.getJobID())) {
                    jobs.updateNodeByIndex(j, selectedJob);
                    comp.setJob(jobs);
                    companies.updateNodeByIndex(i, comp);
                    break;
                }
            }
        }
    }

    public static void printApplicantApplications(Applicant applicant) {
        System.out.println("\n Applications under Applicant " + applicant.getName() + ":");

        SortedListInterface<Application> appsApplicant = applicant.getApplication();
        if (appsApplicant == null || appsApplicant.getSize() == 0) {
            System.out.println(" - No applications found.");
            return;
        }

        for (int i = 0; i < appsApplicant.getSize(); i++) {
            Application app = appsApplicant.viewDataAtIndex(i);
            System.out.println(" - Job: " + app.getJob().getTitle()
                    + " at " + app.getJob().getCompany().getCompanyName()
                    + " | Status: " + app.getStatus());
        }
    }
    public static void printAllApplicantApplications(SortedListInterface<Applicant> applicants) {
    System.out.println("\n📄 Applications under All Applicants:");

    if (applicants == null || applicants.getSize() == 0) {
        System.out.println(" - No applicants found.");
        return;
    }

    // Iterate through all applicants
    for (int i = 0; i < applicants.getSize(); i++) {
        Applicant applicant = applicants.viewDataAtIndex(i);
        System.out.println("\n📑 Applicant: " + applicant.getName());

        SortedListInterface<Application> appsApplicant = applicant.getApplication();
        if (appsApplicant == null || appsApplicant.getSize() == 0) {
            System.out.println(" - No applications found.");
        } else {
            // Iterate through each application of the applicant
            for (int j = 0; j < appsApplicant.getSize(); j++) {
                Application app = appsApplicant.viewDataAtIndex(j);
                System.out.println(" - Job: " + app.getJob().getTitle() + 
                                   " at " + app.getJob().getCompany().getCompanyName() +
                                   " | Status: " + app.getStatus());
            }
        }
    }
}

}
