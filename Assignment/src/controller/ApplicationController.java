/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedDoublyLinkedList;
import boundary.ApplyUI;
import boundary.MainMenuUI;
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

    public static void mainApplication(Applicant applicant, SortedListInterface<Company> companies, SortedListInterface<Applicant> applicants) {

        //Intitialize COmpany with jobs and applicants
//        SortedListInterface<Company> companies = initializeJobs();
//        SortedListInterface<Applicant> applicants = initializeApplicant();
        //Select Applicant here
        boolean continueApplying = true;
        while (continueApplying) {

            int jobNo = displayAllJobs(companies);
            int selection = ApplyUI.printMenu();
            Job selectedJob = applyJob(applicant, selection, companies, jobNo);
            if (selectedJob != null) {

                selectedJob = applyConfirmation(applicant, selectedJob);

                if (selectedJob != null) {
                    if (hasAlreadyApplied(applicant, selectedJob)) {
                        System.out.println("You have already applied for this job. Application cancelled.");
                        continue;
                    }
                    // Add application to both applicant and job
//                    applicant = addApplicationToApplicant(applicant, selectedJob);
//                    selectedJob = addApplicationToJob(applicant, selectedJob);
                    addApplication(applicant, selectedJob);

                    // Update the lists after the application
                    updateListsAfterApplication(applicant, selectedJob, applicants, companies);

                    // Print the applicant's applications and all applications
                    //printApplicantApplications(applicant);
                    //printAllApplicantApplications(applicants);
                    //printAllCompanyJobApplications(companies);
                    System.out.println("\nYou have successfully applied for a job");
                }
            }
            System.out.println("\nDo you want to apply for another job? (y/n)");
            char continueChoice = ApplyUI.getInput();
            if (continueChoice != 'y' && continueChoice != 'Y') {
                continueApplying = false;
            }
        }
        System.out.println("Thank you for your application");

    }

//    public static SortedListInterface<Company> initializeJobs() {
//        return Initializer.initializeCompanyJob();
//    }
//
//    public static SortedListInterface<Applicant> initializeApplicant() {
//        return Initializer.initializeApplicant();
//    }
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
                ApplyUI.displayAllJob(jobNo, tempComp.getCompanyName(), tempJob.getTitle(), tempJob.getLocation(), reqSkills + ", " + reqEdu + ", " + languages, tempJob.getDesc());
                jobNo++;
            }

        }
        ApplyUI.printFloor(174);
        return jobNo;
    }

    public static Job applyJob(Applicant applicant, int selection, SortedListInterface<Company> companies, int totalJobs) {
        if (selection < 0 || selection > totalJobs) {
            System.out.println("Invalid job selection! Please select a valid option.");
            return null;
        } else if (selection == 0) {
            System.out.println("\n\n Exiting.......\n\n");
        }
        String str = Integer.toString(selection);
        int count = 1;

        if (utility1.isNumber(str)) {
            for (int i = 0; i < companies.getSize(); i++) {
                SortedListInterface<Job> jobs = companies.viewDataAtIndex(i).getJob();

                for (int j = 0; j < jobs.getSize(); j++) {
                    if (count == selection) {
                        return jobs.viewDataAtIndex(j);
                    }
                    count++;
                }
            }
        }
        else
            MainMenuUI.printInvalidMenuChoice();

        return null;
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

//    public static Applicant addApplicationToApplicant(Applicant applicant, Job selectedJob) {
//        if (applicant.getApplication() == null) {
//            applicant.setApplication(new SortedDoublyLinkedList<>());
//        }
//        SortedListInterface<Application> applicationsApplicant = applicant.getApplication();
//        SortedListInterface<Application> applicationsJob = selectedJob.getApplication();
//
//        //setting up application
//        Application application = new Application(selectedJob, applicant, "Not Approved", null);
//        application.setApplicant(applicant);
//
//        //setting up application in applicant
//        applicationsApplicant.addWithSort(application);
//        applicant.setApplication(applicationsApplicant);
//
//        return applicant;
//
//    }
//    public static Job addApplicationToJob(Applicant applicant, Job selectedJob) {
//        if (applicant.getApplication() == null) {
//            applicant.setApplication(new SortedDoublyLinkedList<>());
//        }
//        SortedListInterface<Application> applicationsApplicant = applicant.getApplication();
//        SortedListInterface<Application> applicationsJob = selectedJob.getApplication();
//
//        //setting up application
//        Application application = new Application(selectedJob, applicant, "Not Approved", null);
//        application.setApplicant(applicant);
//
//        //setting up application in applicant
//        applicationsJob.addWithSort(application);
//        selectedJob.setApplication(applicationsApplicant);
//
//        return selectedJob;
//    }
    public static void addApplication(Applicant applicant, Job selectedJob) {
        if (applicant.getApplication() == null) {
            applicant.setApplication(new SortedDoublyLinkedList<>());
        }
        if (selectedJob.getApplication() == null) {
            selectedJob.setApplication(new SortedDoublyLinkedList<>());
        }

        Application application = new Application(selectedJob, applicant, "Not Approved", null);
        application.setApplicant(applicant);

        applicant.getApplication().addWithSort(application);
        selectedJob.getApplication().addWithSort(application);
    }

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

    //For debug purpose
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
                    System.out.println(" - Job: " + app.getJob().getTitle()
                            + " at " + app.getJob().getCompany().getCompanyName()
                            + " | Status: " + app.getStatus());
                }
            }
        }
    }

    public static boolean hasAlreadyApplied(Applicant applicant, Job job) {
        SortedListInterface<Application> applications = applicant.getApplication();
        if (applications == null) {
            return false;
        }

        for (int i = 0; i < applications.getSize(); i++) {
            Application app = applications.viewDataAtIndex(i);
            if (app.getJob().getJobID().equals(job.getJobID())) {
                return true;
            }
        }
        return false;
    }

    //For Debug purpose
    public static void printAllCompanyJobApplications(SortedListInterface<Company> companies) {
        System.out.println("\n🏢📋 Applications under All Companies' Jobs:");

        if (companies == null || companies.getSize() == 0) {
            System.out.println(" - No companies found.");
            return;
        }

        for (int i = 0; i < companies.getSize(); i++) {
            Company company = companies.viewDataAtIndex(i);
            System.out.println("\n🏢 Company: " + company.getCompanyName());

            SortedListInterface<Job> jobs = company.getJob();
            if (jobs == null || jobs.getSize() == 0) {
                System.out.println(" - No jobs found.");
                continue;
            }

            for (int j = 0; j < jobs.getSize(); j++) {
                Job job = jobs.viewDataAtIndex(j);
                System.out.println("  💼 Job: " + job.getTitle());

                SortedListInterface<Application> apps = job.getApplication();
                if (apps == null || apps.getSize() == 0) {
                    System.out.println("   - No applications found.");
                } else {
                    for (int k = 0; k < apps.getSize(); k++) {
                        Application app = apps.viewDataAtIndex(k);
                        System.out.println("   - Applicant: " + app.getApplicant().getName() + " | Status: " + app.getStatus());
                    }
                }
            }
        }
    }

}
