/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedListInterface;
import adt.SortedDoublyLinkedList;
import dao.Initializer;
import entity.Applicant;
import entity.Company;


/**
 *
 * @author Acer
 */
public class MainController {
    public static void runAll(){
        SortedListInterface<Company> companies = initializeJobs();
        SortedListInterface<Applicant> applicants = initializeApplicant();
        ApplicationController.mainApplication(companies,applicants);
    }
      public static SortedListInterface<Company> initializeJobs() {
        return Initializer.initializeCompanyJob();
    }

    public static SortedListInterface<Applicant> initializeApplicant() {
        return Initializer.initializeApplicant();
    }
}
