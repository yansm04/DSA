/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;
import entity.*;
import boundary.*;

/**
 *
 * @author Acer
 */
public class MatchingController {

    public static void mainMatch(Company company, SortedListInterface<Applicant> applicants, SortedListInterface<Application> rejectedApplication) {
        int noOfMatch;
        int noOfNode = 0;
        noOfNode = findNoOfMatch(company, applicants, 1, rejectedApplication);

    }

    public static void matchReport(SortedListInterface<Company> companies, SortedListInterface<Application> rejectedApplication) {
        for (int i = 0; i < companies.getSize(); i++) {
            Company company = companies.viewDataAtIndex(i);
            System.out.println("\n=======================================");
            System.out.println("Matching Results for Company: " + company.getCompanyName());
            System.out.println("=======================================");

            findNoOfMatch(company, null, 0, rejectedApplication); // We reuse existing logic
        }

        MatchUI.rejectedOnes();

        for (int i = 0; i < rejectedApplication.getSize(); i++) {
            Application app = rejectedApplication.viewDataAtIndex(i);
            MatchUI.showRejectedApplicant(
                    app.getJob().getCompany().getCompanyName(),
                    app.getJob().getTitle(),
                    app.getApplicant().getName(),
                    app.getScore()
            );
        }

        System.out.print("\nPress any key to return: ");
        ApplyUI.getInput();
    }

    public static int findNoOfMatch(Company company, SortedListInterface<Applicant> applicants, int sohai, SortedListInterface<Application> rejectedApplication) {
        int noOfNode;
        double weight;
        Job job = new Job();
        for (int i = 0; i < company.getJob().getSize(); i++) {
            noOfNode = 0;
            job = company.getJob().viewDataAtIndex(i);
            SortedListInterface<String> reqSkill = job.getReqSkill();
            SortedListInterface<String> reqEdLevel = job.getReqEdLevel();
            SortedListInterface<String> fos = job.getFos();
            SortedListInterface<String> language = job.getLanguage();

            noOfNode = reqSkill.getSize() + reqEdLevel.getSize() + fos.getSize() + language.getSize();

            weight = 1.0 / noOfNode;
            for (int j = 0; j < job.getApplication().getSize(); j++) {
                Application app = job.getApplication().viewDataAtIndex(j);
                Applicant applicant = app.getApplicant();

                int matchCount = 0;

                matchCount += countMatches(job.getReqSkill(), applicant.getSkills());
                matchCount += countMatches(job.getReqEdLevel(), applicant.getEducation());
                matchCount += countMatches(job.getFos(), applicant.getFos());
                matchCount += countMatches(job.getLanguage(), applicant.getLanguage());

                double score = matchCount * weight;
                app.setScore(score);
                if (score < 0.4) {
                    rejectedApplication.addWithSort(app);

                }

                // printApplicationsDescendingScore(job.getApplication(), job.getTitle());
            }

            for (int j = 0; j < job.getApplication().getSize(); j++) {
                Application app = job.getApplication().viewDataAtIndex(j);
                //Remove from job's list
                if (app.getScore() < 0.4) {
                    Applicant applicant = app.getApplicant();
                    job.getApplication().removeByIndex(j);

                    // Remove from applicant's list
                    SortedListInterface<Application> applicantApps = applicant.getApplication();
                    for (int k = 0; k < applicantApps.getSize(); k++) {
                        if (applicantApps.viewDataAtIndex(k).equals(app)) {
                            applicantApps.removeByIndex(k);
                            break;
                        }
                    }
                    j--; // step back index
                }
            }

            printApplicationsDescendingScore(job.getApplication(), job.getTitle(), job.getCompany().getCompanyName());
            // System.out.println("\n\nNo of Node: "+ noOfNode);

        }
        

        if (sohai == 1) {
            for (int k = 0; k < rejectedApplication.getSize(); k++) {
                Application tempApp = rejectedApplication.viewDataAtIndex(k);
                if (tempApp.getJob().getCompany().getCompanyName().equals(job.getCompany().getCompanyName())) {
                    MatchUI.showRejectedApplicant(
                            tempApp.getJob().getCompany().getCompanyName(),
                            tempApp.getJob().getTitle(),
                            tempApp.getApplicant().getName(),
                            tempApp.getScore()
                    );
                }
            }
            System.out.println("Please press anything to continue: ");
            char random = ApplyUI.getInput();
        }
        return 0;
    }

    private static int countMatches(SortedListInterface<String> jobReqs, SortedListInterface<String> applicantData) {
        int count = 0;
        for (int i = 0; i < jobReqs.getSize(); i++) {
            String req = jobReqs.viewDataAtIndex(i);
            for (int j = 0; j < applicantData.getSize(); j++) {
                if (req.equalsIgnoreCase(applicantData.viewDataAtIndex(j))) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static int findReqSkill(Company company, int noOfNode, int jobNo) {
        Job job = company.getJob().viewDataAtIndex(jobNo);
        SortedListInterface<String> reqSkill = job.getReqSkill();
        noOfNode = noOfNode + reqSkill.getSize();
        return noOfNode;
    }

    public static int findReqEdLevel(Company company, int noOfNode, int jobNo) {
        Job job = company.getJob().viewDataAtIndex(jobNo);
        SortedListInterface<String> reqEdLevel = job.getReqEdLevel();
        noOfNode = noOfNode + reqEdLevel.getSize();
        return noOfNode;
    }

    public static int findFos(Company company, int noOfNode, int jobNo) {
        Job job = company.getJob().viewDataAtIndex(jobNo);
        SortedListInterface<String> fos = job.getFos();
        noOfNode = noOfNode + fos.getSize();
        return noOfNode;
    }

    public static int findLanguage(Company company, int noOfNode, int jobNo) {
        Job job = company.getJob().viewDataAtIndex(jobNo);
        SortedListInterface<String> language = job.getLanguage();
        noOfNode = noOfNode + language.getSize();
        return noOfNode;
    }

    private static void printApplicationsDescendingScore(SortedListInterface<Application> applications, String jobTitle, String companyName) {
        SortedListInterface<Application> sortedApps = new SortedDoublyLinkedList<>();

        for (int i = 0; i < applications.getSize(); i++) {
            Application app = applications.viewDataAtIndex(i);

            // Find correct position to insert (descending order)
            int insertIndex = 0;
            while (insertIndex < sortedApps.getSize()
                    && app.getScore() < sortedApps.viewDataAtIndex(insertIndex).getScore()) {
                insertIndex++;
            }

            sortedApps.addAtIndex(app, insertIndex);
        }
        MatchUI.MatchingHeader(jobTitle);

        // Print sorted results
        for (int i = 0; i < sortedApps.getSize(); i++) {
            Application app = sortedApps.viewDataAtIndex(i);
            String applicantName = app.getApplicant().getName();
            double score = app.getScore();
            String details = buildDetails(app.getApplicant());
            MatchUI.MatchingResultRow(i + 1, applicantName, score, details);

        }
    }

    private static String buildDetails(Applicant applicant) {
        StringBuilder details = new StringBuilder();

        if (applicant.getSkills().getSize() > 0) {
            details.append("Skills: ");
            for (int i = 0; i < applicant.getSkills().getSize(); i++) {
                details.append(applicant.getSkills().viewDataAtIndex(i));
                if (i < applicant.getSkills().getSize() - 1) {
                    details.append(", ");
                }
            }
        }

        return details.toString();
    }

}
