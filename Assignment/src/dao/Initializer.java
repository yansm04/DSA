/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import adt.ListInterface;
import entity.*;

/**
 *
 * @author Acer
 */
public class Initializer {

    public static ListInterface<Company> initializeCompanyJob() {
        ListInterface<Company> companies = new DoublyLinkedList<>();
        
        Company tempCompany;
        Job tempJob1, tempJob2;
        ListInterface<Job> jobList;

// Company 1
        jobList = new DoublyLinkedList<>();
    tempCompany = new Company("C101", "Tech Innovators", "hr@techinnovators.com");

    ListInterface<String> skill1 = new DoublyLinkedList<>();
    skill1.addWithSort("Java"); skill1.addWithSort("Python");
    ListInterface<String> ed1 = new DoublyLinkedList<>();
    ed1.addWithSort("Bachelor's Degree");
    ListInterface<String> fos1 = new DoublyLinkedList<>();
    fos1.addWithSort("Computer Science");
    ListInterface<String> lang1 = new DoublyLinkedList<>();
    lang1.addWithSort("English");

    tempJob1 = new Job(tempCompany, "J101", "Software Engineer", "Develop and maintain software", 
        skill1, ed1, fos1, lang1, 6000, "Kuala Lumpur", new DoublyLinkedList<>());

    ListInterface<String> skill2 = new DoublyLinkedList<>();
    skill2.addWithSort("SQL"); skill2.addWithSort("Excel");
    ListInterface<String> ed2 = new DoublyLinkedList<>();
    ed2.addWithSort("Bachelor's Degree");
    ListInterface<String> fos2 = new DoublyLinkedList<>();
    fos2.addWithSort("Statistics");
    ListInterface<String> lang2 = new DoublyLinkedList<>();
    lang2.addWithSort("English");

    tempJob2 = new Job(tempCompany, "J102", "Data Analyst", "Analyze company data", 
        skill2, ed2, fos2, lang2, 5500, "Selangor", new DoublyLinkedList<>());

    jobList.addWithSort(tempJob1);
    jobList.addWithSort(tempJob2);
    tempCompany.setJob(jobList);
    companies.addWithSort(tempCompany);

    // Company 2 - Creative Minds
    jobList = new DoublyLinkedList<>();
    tempCompany = new Company("C102", "Creative Minds", "careers@creativeminds.com");

    ListInterface<String> skill3 = new DoublyLinkedList<>();
    skill3.addWithSort("SEO"); skill3.addWithSort("Content Writing");
    ListInterface<String> ed3 = new DoublyLinkedList<>();
    ed3.addWithSort("Bachelor's Degree");
    ListInterface<String> fos3 = new DoublyLinkedList<>();
    fos3.addWithSort("Marketing");
    ListInterface<String> lang3 = new DoublyLinkedList<>();
    lang3.addWithSort("English");

    tempJob1 = new Job(tempCompany, "J103", "Marketing Executive", "Develop marketing campaigns",
        skill3, ed3, fos3, lang3, 4500, "Penang", new DoublyLinkedList<>());

    ListInterface<String> skill4 = new DoublyLinkedList<>();
    skill4.addWithSort("Adobe Illustrator"); skill4.addWithSort("Photoshop");
    ListInterface<String> ed4 = new DoublyLinkedList<>();
    ed4.addWithSort("Diploma");
    ListInterface<String> fos4 = new DoublyLinkedList<>();
    fos4.addWithSort("Graphic Design");
    ListInterface<String> lang4 = new DoublyLinkedList<>();
    lang4.addWithSort("English");

    tempJob2 = new Job(tempCompany, "J104", "Graphic Designer", "Create visual designs",
        skill4, ed4, fos4, lang4, 4000, "Johor Bahru", new DoublyLinkedList<>());

    jobList.addWithSort(tempJob1);
    jobList.addWithSort(tempJob2);
    tempCompany.setJob(jobList);
    companies.addWithSort(tempCompany);

    // Company 3 - BuildTech
    jobList = new DoublyLinkedList<>();
    tempCompany = new Company("C103", "BuildTech", "recruitment@buildtech.com");

    ListInterface<String> skill5 = new DoublyLinkedList<>();
    skill5.addWithSort("AutoCAD"); skill5.addWithSort("Structural Analysis");
    ListInterface<String> ed5 = new DoublyLinkedList<>();
    ed5.addWithSort("Bachelor's Degree");
    ListInterface<String> fos5 = new DoublyLinkedList<>();
    fos5.addWithSort("Civil Engineering");
    ListInterface<String> lang5 = new DoublyLinkedList<>();
    lang5.addWithSort("English");

    tempJob1 = new Job(tempCompany, "J105", "Civil Engineer", "Design and oversee construction",
        skill5, ed5, fos5, lang5, 7000, "Kuala Lumpur", new DoublyLinkedList<>());

    ListInterface<String> skill6 = new DoublyLinkedList<>();
    skill6.addWithSort("Cost Estimation"); skill6.addWithSort("MS Excel");
    ListInterface<String> ed6 = new DoublyLinkedList<>();
    ed6.addWithSort("Diploma");
    ListInterface<String> fos6 = new DoublyLinkedList<>();
    fos6.addWithSort("Construction Management");
    ListInterface<String> lang6 = new DoublyLinkedList<>();
    lang6.addWithSort("English");

    tempJob2 = new Job(tempCompany, "J106", "Quantity Surveyor", "Cost estimation and management",
        skill6, ed6, fos6, lang6, 5000, "Kota Kinabalu", new DoublyLinkedList<>());

    jobList.addWithSort(tempJob1);
    jobList.addWithSort(tempJob2);
    tempCompany.setJob(jobList);
    companies.addWithSort(tempCompany);

    return companies;

    }

    public static ListInterface<Applicant> initializeApplicant() {
        ListInterface<Applicant> applicants = new DoublyLinkedList<>();

// Applicant 1 - Computer Science
        ListInterface<String> skills1 = new DoublyLinkedList<>();
        skills1.addWithSort("Java");
        skills1.addWithSort("Python");
        ListInterface<String> workingExp1 = new DoublyLinkedList<>();
        workingExp1.addWithSort("Software Developer Intern");
        ListInterface<String> education1 = new DoublyLinkedList<>();
        education1.addWithSort("Bachelor in Computer Science");

        applicants.addWithSort(new Applicant("1001", "Clement", "c@gmail.com", 21, skills1, workingExp1, education1, null));

// Applicant 2 - Business Administration
        ListInterface<String> skills2 = new DoublyLinkedList<>();
        skills2.addWithSort("Marketing");
        skills2.addWithSort("Public Speaking");
        ListInterface<String> workingExp2 = new DoublyLinkedList<>();
        workingExp2.addWithSort("Marketing Intern");
        ListInterface<String> education2 = new DoublyLinkedList<>();
        education2.addWithSort("Bachelor in Business Administration");

        applicants.addWithSort(new Applicant("1002", "Alice", "alice@gmail.com", 22, skills2, workingExp2, education2, null));

// Applicant 3 - Mechanical Engineering
        ListInterface<String> skills3 = new DoublyLinkedList<>();
        skills3.addWithSort("AutoCAD");
        skills3.addWithSort("Mechanical Design");
        ListInterface<String> workingExp3 = new DoublyLinkedList<>();
        workingExp3.addWithSort("Engineering Intern");
        ListInterface<String> education3 = new DoublyLinkedList<>();
        education3.addWithSort("Bachelor in Mechanical Engineering");

        applicants.addWithSort(new Applicant("1003", "Bob", "bob@gmail.com", 23, skills3, workingExp3, education3, null));

// Applicant 4 - Medical Science
        ListInterface<String> skills4 = new DoublyLinkedList<>();
        skills4.addWithSort("Patient Care");
        skills4.addWithSort("Medical Research");
        ListInterface<String> workingExp4 = new DoublyLinkedList<>();
        workingExp4.addWithSort("Medical Intern");
        ListInterface<String> education4 = new DoublyLinkedList<>();
        education4.addWithSort("Bachelor in Medical Science");

        applicants.addWithSort(new Applicant("1004", "David", "david@gmail.com", 24, skills4, workingExp4, education4, null));

// Applicant 5 - Psychology
        ListInterface<String> skills5 = new DoublyLinkedList<>();
        skills5.addWithSort("Counseling");
        skills5.addWithSort("Data Analysis");
        ListInterface<String> workingExp5 = new DoublyLinkedList<>();
        workingExp5.addWithSort("Research Assistant");
        ListInterface<String> education5 = new DoublyLinkedList<>();
        education5.addWithSort("Bachelor in Psychology");

        applicants.addWithSort(new Applicant("1005", "Eve", "eve@gmail.com", 25, skills5, workingExp5, education5, null));

// Applicant 6 - Accounting
        ListInterface<String> skills6 = new DoublyLinkedList<>();
        skills6.addWithSort("Financial Analysis");
        skills6.addWithSort("Excel");
        ListInterface<String> workingExp6 = new DoublyLinkedList<>();
        workingExp6.addWithSort("Accounting Intern");
        ListInterface<String> education6 = new DoublyLinkedList<>();
        education6.addWithSort("Bachelor in Accounting");

        applicants.addWithSort(new Applicant("1006", "Frank", "frank@gmail.com", 22, skills6, workingExp6, education6, null));

// Applicant 7 - Fine Arts
        ListInterface<String> skills7 = new DoublyLinkedList<>();
        skills7.addWithSort("Painting");
        skills7.addWithSort("Graphic Design");
        ListInterface<String> workingExp7 = new DoublyLinkedList<>();
        workingExp7.addWithSort("Art Assistant");
        ListInterface<String> education7 = new DoublyLinkedList<>();
        education7.addWithSort("Bachelor in Fine Arts");

        applicants.addWithSort(new Applicant("1007", "Grace", "grace@gmail.com", 23, skills7, workingExp7, education7, null));

// Applicant 8 - Journalism
        ListInterface<String> skills8 = new DoublyLinkedList<>();
        skills8.addWithSort("Writing");
        skills8.addWithSort("Interviewing");
        ListInterface<String> workingExp8 = new DoublyLinkedList<>();
        workingExp8.addWithSort("Journalism Intern");
        ListInterface<String> education8 = new DoublyLinkedList<>();
        education8.addWithSort("Bachelor in Journalism");

        applicants.addWithSort(new Applicant("1008", "Hank", "hank@gmail.com", 24, skills8, workingExp8, education8, null));

// Applicant 9 - Hospitality Management
        ListInterface<String> skills9 = new DoublyLinkedList<>();
        skills9.addWithSort("Customer Service");
        skills9.addWithSort("Event Planning");
        ListInterface<String> workingExp9 = new DoublyLinkedList<>();
        workingExp9.addWithSort("Hotel Management Intern");
        ListInterface<String> education9 = new DoublyLinkedList<>();
        education9.addWithSort("Bachelor in Hospitality Management");

        applicants.addWithSort(new Applicant("1009", "Ivy", "ivy@gmail.com", 22, skills9, workingExp9, education9, null));

// Applicant 10 - Civil Engineering
        ListInterface<String> skills10 = new DoublyLinkedList<>();
        skills10.addWithSort("Structural Design");
        skills10.addWithSort("Project Management");
        ListInterface<String> workingExp10 = new DoublyLinkedList<>();
        workingExp10.addWithSort("Civil Engineering Intern");
        ListInterface<String> education10 = new DoublyLinkedList<>();
        education10.addWithSort("Bachelor in Civil Engineering");

        applicants.addWithSort(new Applicant("1010", "Jack", "jack@gmail.com", 23, skills10, workingExp10, education10, null));

// Applicant 11 - Culinary Arts
        ListInterface<String> skills11 = new DoublyLinkedList<>();
        skills11.addWithSort("Cooking");
        skills11.addWithSort("Menu Planning");
        ListInterface<String> workingExp11 = new DoublyLinkedList<>();
        workingExp11.addWithSort("Chef Assistant");
        ListInterface<String> education11 = new DoublyLinkedList<>();
        education11.addWithSort("Diploma in Culinary Arts");

        applicants.addWithSort(new Applicant("1011", "Kathy", "kathy@gmail.com", 22, skills11, workingExp11, education11, null));

// Applicant 12 - Law
        ListInterface<String> skills12 = new DoublyLinkedList<>();
        skills12.addWithSort("Legal Writing");
        skills12.addWithSort("Case Analysis");
        ListInterface<String> workingExp12 = new DoublyLinkedList<>();
        workingExp12.addWithSort("Legal Intern");
        ListInterface<String> education12 = new DoublyLinkedList<>();
        education12.addWithSort("Bachelor in Law");

        applicants.addWithSort(new Applicant("1012", "Leo", "leo@gmail.com", 24, skills12, workingExp12, education12, null));

// Applicant 13 - Architecture
        ListInterface<String> skills13 = new DoublyLinkedList<>();
        skills13.addWithSort("AutoCAD");
        skills13.addWithSort("3D Modeling");
        ListInterface<String> workingExp13 = new DoublyLinkedList<>();
        workingExp13.addWithSort("Architect Intern");
        ListInterface<String> education13 = new DoublyLinkedList<>();
        education13.addWithSort("Bachelor in Architecture");

        applicants.addWithSort(new Applicant("1013", "Mia", "mia@gmail.com", 23, skills13, workingExp13, education13, null));
        return applicants;

    }
}
