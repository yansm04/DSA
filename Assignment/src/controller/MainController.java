/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedListInterface;
import boundary.MainMenuUI;
import dao.Initializer;
import entity.Applicant;
import entity.Company;
import utility.utility1;

/**
 *
 * @author Acer
 */
public class MainController {

    public static void runAll() {
        SortedListInterface<Company> companies = initializeJobs();
        SortedListInterface<Applicant> applicants = initializeApplicant();
        userTypeMenu(companies);
        ApplicationController.mainApplication(companies,applicants);
    }

    public static SortedListInterface<Company> initializeJobs() {
        return Initializer.initializeCompanyJob();
    }

    public static SortedListInterface<Applicant> initializeApplicant() {
        return Initializer.initializeApplicant();
    }

    public static void userTypeMenu(SortedListInterface<Company> companies) {
        while (true) {
            utility1.clearScreen();
            MainMenuUI.mainLogo();
            try {
                int choice = MainMenuUI.userTypeMenuUI();

                switch (choice) {
                    case 1:
                        //selectStudent(); // ltr implement
                        break;
                    case 2:
                        selectCompany(companies);
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

    public static int selectCompanyMenu(SortedListInterface<Company> companies) {
        MainMenuUI.selectHeader();
        for (int i = 0; i < companies.getSize(); i++) {
            Company c = companies.viewDataAtIndex(i);
            String display = (i + 1) + ". " + c.getCompanyName();
            MainMenuUI.printOptions(display);
        }
        return MainMenuUI.selectFooter();
    }

    public static void selectCompany(SortedListInterface<Company> companies) {
        while (true) {
            utility1.clearScreen();
            MainMenuUI.mainLogo();
            try {
                int choice = selectCompanyMenu(companies);

                if (choice == 0) {
                    return;
                } else if (choice == 999) {
                    // some crud company function here

                } else if (choice >= 1 && choice <= companies.getSize()) {
                    Company selectedCompany = companies.viewDataAtIndex(choice - 1);
                    companyMenu(selectedCompany);
                    MainMenuUI.pressEnterToContinue();
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

    public static void companyMenu(Company company) {
        while (true) {
            utility1.clearScreen();
            MainMenuUI.mainLogo();
            try {
                int choice = MainMenuUI.companyMenuUI();

                switch (choice) {
                    case 1:
                        // manage interview here
                        break;
                    case 2:
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
