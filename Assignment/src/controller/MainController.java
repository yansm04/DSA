/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedListInterface;
import adt.SortedDoublyLinkedList;
import boundary.MainMenuUI;
import dao.Initializer;
import entity.Applicant;
import entity.Company;
import entity.Application;
import utility.utility1;

/**
 *
 * @author Acer
 */
public class MainController {

    public static void runAll() {
        SortedListInterface<Company> companies = initializeJobs();
        SortedListInterface<Applicant> applicants = initializeApplicant();
        SortedListInterface<Application> rejectedApplication = new SortedDoublyLinkedList<>();

        while (true) {
            int selection = userTypeMenu(companies);

            if (selection == 1) {
                //ApplicationController.mainApplication(companies, applicants);
                while (true) {
                    Applicant selectedApplicant = selectApplicant(applicants);
                    if (selectedApplicant == null) {
                        break;
                    }
                    displayApplicantMenu(selectedApplicant, companies, applicants);
                }
            } else if (selection == 2) {
                while (true) {
                    Company selectedCompany = selectCompany(companies, rejectedApplication);
                    if (selectedCompany == null) {
                        break;
                    }
                    companyMenu(selectedCompany, applicants, rejectedApplication);
                }
            } else {
                return;
            }
        }

//        userTypeMenu(companies);
//        ApplicationController.mainApplication(companies,applicants);
    }

    public static SortedListInterface<Company> initializeJobs() {
        return Initializer.initializeCompanyJob();
    }

    public static SortedListInterface<Applicant> initializeApplicant() {
        return Initializer.initializeApplicant();
    }

    public static int userTypeMenu(SortedListInterface<Company> companies) {
        Company company = new Company();
        while (true) {
            utility1.clearScreen();
            MainMenuUI.mainLogo();
            try {
                int choice = MainMenuUI.userTypeMenuUI();

                switch (choice) {
                    case 1:
                        //selectStudent(); // ltr implement
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
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

    public static int selectCompanyMenu(SortedListInterface<Company> companies) {
        MainMenuUI.selectHeader();
        for (int i = 0; i < companies.getSize(); i++) {
            Company c = companies.viewDataAtIndex(i);
            String display = (i + 1) + ". " + c.getCompanyName();
            MainMenuUI.printOptions(display);
        }
        return MainMenuUI.selectFooter();
    }

    public static Company selectCompany(SortedListInterface<Company> companies, SortedListInterface<Application> rejectedApplication) {
        while (true) {
            utility1.clearScreen();
            MainMenuUI.mainLogo();
            try {
                int choice = selectCompanyMenu(companies);

                if (choice == 0) {
                    return null;
                } else if (choice == 999) {
                    // some crud company function here
                    companyModuleMenu(companies, rejectedApplication);

                } else if (choice >= 1 && choice <= companies.getSize()) {
                    Company selectedCompany = companies.viewDataAtIndex(choice - 1);
                    return selectedCompany;
//                    companyMenu(selectedCompany);
                } else {
                    MainMenuUI.printInvalidMenuChoice();
                    MainMenuUI.pressEnterToContinue();
                }
            } catch (NumberFormatException e) {
                MainMenuUI.printInvalidMenuChoice();
                MainMenuUI.pressEnterToContinue();
            }
        }

    }

    public static void companyMenu(Company company, SortedListInterface<Applicant> applicants, SortedListInterface<Application> rejectedApplication) {
        JobController jobController = new JobController();

        while (true) {

            utility1.clearScreen();
            MainMenuUI.mainLogo();
            try {
                int choice = MainMenuUI.companyMenuUI();

                switch (choice) {
                    case 1:
                        // manage interview here
                        InterviewController.cmpInterviewMenu(company, applicants);
                        break;
                    case 2:
                        MatchingController.mainMatch(company, applicants, rejectedApplication);
                        break;
                    //Company view Applications with matched results
                    case 3:
                        jobController.setCompany(company);
                        //jobController.setCompanies(companies);
                        jobController.viewJobs();
                        break;
                    case 4:
                        jobController.setCompany(company);
                        jobController.addJob();
                        break;
                    case 5:
                        jobController.setCompany(company);
                        jobController.editJob();
                        break;
                    case 6:
                        jobController.setCompany(company);
                        jobController.deleteJob();
                        break;
                    case 0:
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

    public static void companyModuleMenu(SortedListInterface<Company> companies, SortedListInterface<Application> rejectedApplication) {
        JobController jobController = new JobController();
        while (true) {
            utility1.clearScreen();
            MainMenuUI.mainLogo();
            try {
                int choice = MainMenuUI.manageCompanyMenuUI();

                switch (choice) {
                    case 1:
                        jobController.signUp(companies);
                        break;
                    case 2:
                        jobController.generateReport(companies);
                        MainMenuUI.pressEnterToContinue();
                        break;
                    case 3:
                        jobController.filterJobs(companies);
                        MainMenuUI.pressEnterToContinue();
                        break;
                    case 4:
                        MatchingController.matchReport(companies, rejectedApplication);
                        break;
                    case 5:
                        InterviewController.interviewReport(companies);
                    case 0:
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

    public static Applicant selectApplicant(SortedListInterface<Applicant> applicants) {
        while (true) {
            utility1.clearScreen();
            MainMenuUI.mainLogo();
            try {
                int choice = selectApplicantMenu(applicants);

                if (choice == 0) {
                    return null;
                } else if (choice == 999) {
                    // some crud applicant function here

                } else if (choice >= 1 && choice <= applicants.getSize()) {
                    Applicant selectedApplicant = applicants.viewDataAtIndex(choice - 1);
                    return selectedApplicant;
                    //displayApplicantMenu(selectedApplicant);
                } else {
                    MainMenuUI.printInvalidMenuChoice();
                    MainMenuUI.pressEnterToContinue();
                }
            } catch (NumberFormatException e) {
                MainMenuUI.printInvalidMenuChoice();
                MainMenuUI.pressEnterToContinue();
            }
        }
    }

    public static int selectApplicantMenu(SortedListInterface<Applicant> applicants) {
        MainMenuUI.selectHeader();
        for (int i = 0; i < applicants.getSize(); i++) {
            Applicant a = applicants.viewDataAtIndex(i);
            String display = (i + 1) + ". " + a.getName();
            MainMenuUI.printOptions(display);
        }
        return MainMenuUI.selectFooter();

    }

    public static void displayApplicantMenu(Applicant applicant, SortedListInterface<Company> companies, SortedListInterface<Applicant> applicants) {
        while (true) {
            utility1.clearScreen();
            MainMenuUI.mainLogo();
            try {
                int choice = MainMenuUI.applicantMenuUI();

                switch (choice) {
                    case 1:
                        ApplicationController.mainApplication(applicant, companies, applicants);
                        break;
                    case 2:
                        InterviewController.appInterviewMenu(applicant);
                        break;
                    case 3:
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

}
