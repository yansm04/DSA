/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.SortedDoublyLinkedList;
import entity.*;
import adt.SortedListInterface;

/**
 *
 * @author Acer
 */
public class Initializer {

    public static SortedListInterface<Company> initializeCompanyJob() {
        SortedListInterface<Company> companies = new SortedDoublyLinkedList<>();

        Company tempCompany;
        Job tempJob1, tempJob2;
        SortedListInterface<Job> jobList;

// Company 1
        jobList = new SortedDoublyLinkedList<>();
        tempCompany = new Company("C101", "Tech Innovators", "hr@techinnovators.com");

        SortedListInterface<String> skill1 = new SortedDoublyLinkedList<>();
        skill1.addWithSort("Java");
        skill1.addWithSort("Python");
        SortedListInterface<String> ed1 = new SortedDoublyLinkedList<>();
        ed1.addWithSort("Bachelor's Degree");
        SortedListInterface<String> fos1 = new SortedDoublyLinkedList<>();
        fos1.addWithSort("Computer Science");
        SortedListInterface<String> lang1 = new SortedDoublyLinkedList<>();
        lang1.addWithSort("English");

        tempJob1 = new Job(tempCompany, "J101", "Software Engineer", "Develop and maintain software",
                skill1, ed1, fos1, lang1, 6000, "Kuala Lumpur", new SortedDoublyLinkedList<>());

        SortedListInterface<String> skill2 = new SortedDoublyLinkedList<>();
        skill2.addWithSort("SQL");
        skill2.addWithSort("Excel");
        SortedListInterface<String> ed2 = new SortedDoublyLinkedList<>();
        ed2.addWithSort("Bachelor's Degree");
        SortedListInterface<String> fos2 = new SortedDoublyLinkedList<>();
        fos2.addWithSort("Statistics");
        SortedListInterface<String> lang2 = new SortedDoublyLinkedList<>();
        lang2.addWithSort("English");

        tempJob2 = new Job(tempCompany, "J102", "Data Analyst", "Analyze company data",
                skill2, ed2, fos2, lang2, 5500, "Selangor", new SortedDoublyLinkedList<>());

        jobList.addWithSort(tempJob1);
        jobList.addWithSort(tempJob2);
        tempCompany.setJob(jobList);
        companies.addWithSort(tempCompany);

        // Company 2 - Creative Minds
        jobList = new SortedDoublyLinkedList<>();
        tempCompany = new Company("C102", "Creative Minds", "careers@creativeminds.com");

        SortedListInterface<String> skill3 = new SortedDoublyLinkedList<>();
        skill3.addWithSort("SEO");
        skill3.addWithSort("Content Writing");
        SortedListInterface<String> ed3 = new SortedDoublyLinkedList<>();
        ed3.addWithSort("Bachelor's Degree");
        SortedListInterface<String> fos3 = new SortedDoublyLinkedList<>();
        fos3.addWithSort("Marketing");
        SortedListInterface<String> lang3 = new SortedDoublyLinkedList<>();
        lang3.addWithSort("English");

        tempJob1 = new Job(tempCompany, "J103", "Marketing Executive", "Develop marketing campaigns",
                skill3, ed3, fos3, lang3, 4500, "Penang", new SortedDoublyLinkedList<>());

        SortedListInterface<String> skill4 = new SortedDoublyLinkedList<>();
        skill4.addWithSort("Adobe Illustrator");
        skill4.addWithSort("Photoshop");
        SortedListInterface<String> ed4 = new SortedDoublyLinkedList<>();
        ed4.addWithSort("Diploma");
        SortedListInterface<String> fos4 = new SortedDoublyLinkedList<>();
        fos4.addWithSort("Graphic Design");
        SortedListInterface<String> lang4 = new SortedDoublyLinkedList<>();
        lang4.addWithSort("English");

        tempJob2 = new Job(tempCompany, "J104", "Graphic Designer", "Create visual designs",
                skill4, ed4, fos4, lang4, 4000, "Johor Bahru", new SortedDoublyLinkedList<>());

        jobList.addWithSort(tempJob1);
        jobList.addWithSort(tempJob2);
        tempCompany.setJob(jobList);
        companies.addWithSort(tempCompany);

        // Company 3 - BuildTech
        jobList = new SortedDoublyLinkedList<>();
        tempCompany = new Company("C103", "BuildTech", "recruitment@buildtech.com");

        SortedListInterface<String> skill5 = new SortedDoublyLinkedList<>();
        skill5.addWithSort("AutoCAD");
        skill5.addWithSort("Structural Analysis");
        SortedListInterface<String> ed5 = new SortedDoublyLinkedList<>();
        ed5.addWithSort("Bachelor's Degree");
        SortedListInterface<String> fos5 = new SortedDoublyLinkedList<>();
        fos5.addWithSort("Civil Engineering");
        SortedListInterface<String> lang5 = new SortedDoublyLinkedList<>();
        lang5.addWithSort("English");

        tempJob1 = new Job(tempCompany, "J105", "Civil Engineer", "Design and oversee construction",
                skill5, ed5, fos5, lang5, 7000, "Kuala Lumpur", new SortedDoublyLinkedList<>());

        SortedListInterface<String> skill6 = new SortedDoublyLinkedList<>();
        skill6.addWithSort("Cost Estimation");
        skill6.addWithSort("MS Excel");
        SortedListInterface<String> ed6 = new SortedDoublyLinkedList<>();
        ed6.addWithSort("Diploma");
        SortedListInterface<String> fos6 = new SortedDoublyLinkedList<>();
        fos6.addWithSort("Construction Management");
        SortedListInterface<String> lang6 = new SortedDoublyLinkedList<>();
        lang6.addWithSort("English");

        tempJob2 = new Job(tempCompany, "J106", "Quantity Surveyor", "Cost estimation and management",
                skill6, ed6, fos6, lang6, 5000, "Kota Kinabalu", new SortedDoublyLinkedList<>());

        jobList.addWithSort(tempJob1);
        jobList.addWithSort(tempJob2);
        tempCompany.setJob(jobList);
        companies.addWithSort(tempCompany);

        return companies;

    }

    public static SortedListInterface<Applicant> initializeApplicant() {
        SortedListInterface<Applicant> applicants = new SortedDoublyLinkedList<>();

        // Applicant 1 - Computer Science
        SortedListInterface<String> skills1 = new SortedDoublyLinkedList<>();
        skills1.addWithSort("Java");
        skills1.addWithSort("Python");
        SortedListInterface<String> workingExp1 = new SortedDoublyLinkedList<>();
        workingExp1.addWithSort("Software Developer Intern");
        SortedListInterface<String> education1 = new SortedDoublyLinkedList<>();
        education1.addWithSort("Bachelor in Computer Science");
        SortedListInterface<String> language1 = new SortedDoublyLinkedList<>();
        language1.addWithSort("English");
        applicants.addWithSort(new Applicant("1001", "Clement", "c@gmail.com", 21, skills1, workingExp1, education1, null, language1));

        // Applicant 2 - Business Administration
        SortedListInterface<String> skills2 = new SortedDoublyLinkedList<>();
        skills2.addWithSort("Marketing");
        skills2.addWithSort("Public Speaking");
        SortedListInterface<String> workingExp2 = new SortedDoublyLinkedList<>();
        workingExp2.addWithSort("Marketing Intern");
        SortedListInterface<String> education2 = new SortedDoublyLinkedList<>();
        education2.addWithSort("Bachelor in Business Administration");
        SortedListInterface<String> language2 = new SortedDoublyLinkedList<>();
        language2.addWithSort("English");
        applicants.addWithSort(new Applicant("1002", "Alice", "alice@gmail.com", 22, skills2, workingExp2, education2, null, language2));

        // Applicant 3 - Mechanical Engineering
        SortedListInterface<String> skills3 = new SortedDoublyLinkedList<>();
        skills3.addWithSort("AutoCAD");
        skills3.addWithSort("Mechanical Design");
        SortedListInterface<String> workingExp3 = new SortedDoublyLinkedList<>();
        workingExp3.addWithSort("Engineering Intern");
        SortedListInterface<String> education3 = new SortedDoublyLinkedList<>();
        education3.addWithSort("Bachelor in Mechanical Engineering");
        SortedListInterface<String> language3 = new SortedDoublyLinkedList<>();
        language3.addWithSort("English");
        applicants.addWithSort(new Applicant("1003", "Bob", "bob@gmail.com", 23, skills3, workingExp3, education3, null, language3));

        // Applicant 4 - Medical Science
        SortedListInterface<String> skills4 = new SortedDoublyLinkedList<>();
        skills4.addWithSort("Patient Care");
        skills4.addWithSort("Medical Research");
        SortedListInterface<String> workingExp4 = new SortedDoublyLinkedList<>();
        workingExp4.addWithSort("Medical Intern");
        SortedListInterface<String> education4 = new SortedDoublyLinkedList<>();
        education4.addWithSort("Bachelor in Medical Science");
        SortedListInterface<String> language4 = new SortedDoublyLinkedList<>();
        language4.addWithSort("English");
        applicants.addWithSort(new Applicant("1004", "David", "david@gmail.com", 24, skills4, workingExp4, education4, null, language4));

        // Applicant 5 - Psychology
        SortedListInterface<String> skills5 = new SortedDoublyLinkedList<>();
        skills5.addWithSort("Counseling");
        skills5.addWithSort("Data Analysis");
        SortedListInterface<String> workingExp5 = new SortedDoublyLinkedList<>();
        workingExp5.addWithSort("Research Assistant");
        SortedListInterface<String> education5 = new SortedDoublyLinkedList<>();
        education5.addWithSort("Bachelor in Psychology");
        SortedListInterface<String> language5 = new SortedDoublyLinkedList<>();
        language5.addWithSort("English");
        applicants.addWithSort(new Applicant("1005", "Eve", "eve@gmail.com", 25, skills5, workingExp5, education5, null, language5));

        // Applicant 6 - Accounting
        SortedListInterface<String> skills6 = new SortedDoublyLinkedList<>();
        skills6.addWithSort("Financial Analysis");
        skills6.addWithSort("Excel");
        SortedListInterface<String> workingExp6 = new SortedDoublyLinkedList<>();
        workingExp6.addWithSort("Accounting Intern");
        SortedListInterface<String> education6 = new SortedDoublyLinkedList<>();
        education6.addWithSort("Bachelor in Accounting");
        SortedListInterface<String> language6 = new SortedDoublyLinkedList<>();
        language6.addWithSort("English");
        applicants.addWithSort(new Applicant("1006", "Frank", "frank@gmail.com", 22, skills6, workingExp6, education6, null, language6));

        // Applicant 7 - Fine Arts
        SortedListInterface<String> skills7 = new SortedDoublyLinkedList<>();
        skills7.addWithSort("Painting");
        skills7.addWithSort("Graphic Design");
        SortedListInterface<String> workingExp7 = new SortedDoublyLinkedList<>();
        workingExp7.addWithSort("Art Assistant");
        SortedListInterface<String> education7 = new SortedDoublyLinkedList<>();
        education7.addWithSort("Bachelor in Fine Arts");
        SortedListInterface<String> language7 = new SortedDoublyLinkedList<>();
        language7.addWithSort("English");
        applicants.addWithSort(new Applicant("1007", "Grace", "grace@gmail.com", 23, skills7, workingExp7, education7, null, language7));

        // Applicant 8 - Journalism
        SortedListInterface<String> skills8 = new SortedDoublyLinkedList<>();
        skills8.addWithSort("Writing");
        skills8.addWithSort("Interviewing");
        SortedListInterface<String> workingExp8 = new SortedDoublyLinkedList<>();
        workingExp8.addWithSort("Journalism Intern");
        SortedListInterface<String> education8 = new SortedDoublyLinkedList<>();
        education8.addWithSort("Bachelor in Journalism");
        SortedListInterface<String> language8 = new SortedDoublyLinkedList<>();
        language8.addWithSort("English");
        applicants.addWithSort(new Applicant("1008", "Hank", "hank@gmail.com", 24, skills8, workingExp8, education8, null, language8));

        // Applicant 9 - Hospitality Management
        SortedListInterface<String> skills9 = new SortedDoublyLinkedList<>();
        skills9.addWithSort("Customer Service");
        skills9.addWithSort("Event Planning");
        SortedListInterface<String> workingExp9 = new SortedDoublyLinkedList<>();
        workingExp9.addWithSort("Hotel Management Intern");
        SortedListInterface<String> education9 = new SortedDoublyLinkedList<>();
        education9.addWithSort("Bachelor in Hospitality Management");
        SortedListInterface<String> language9 = new SortedDoublyLinkedList<>();
        language9.addWithSort("English");
        applicants.addWithSort(new Applicant("1009", "Ivy", "ivy@gmail.com", 22, skills9, workingExp9, education9, null, language9));

        // Applicant 10 - Civil Engineering
        SortedListInterface<String> skills10 = new SortedDoublyLinkedList<>();
        skills10.addWithSort("Structural Design");
        skills10.addWithSort("Project Management");
        SortedListInterface<String> workingExp10 = new SortedDoublyLinkedList<>();
        workingExp10.addWithSort("Civil Engineering Intern");
        SortedListInterface<String> education10 = new SortedDoublyLinkedList<>();
        education10.addWithSort("Bachelor in Civil Engineering");
        SortedListInterface<String> language10 = new SortedDoublyLinkedList<>();
        language10.addWithSort("English");
        applicants.addWithSort(new Applicant("1010", "Jack", "jack@gmail.com", 23, skills10, workingExp10, education10, null, language10));

        // Applicant 11 - Culinary Arts
        SortedListInterface<String> skills11 = new SortedDoublyLinkedList<>();
        skills11.addWithSort("Cooking");
        skills11.addWithSort("Menu Planning");
        SortedListInterface<String> workingExp11 = new SortedDoublyLinkedList<>();
        workingExp11.addWithSort("Chef Assistant");
        SortedListInterface<String> education11 = new SortedDoublyLinkedList<>();
        education11.addWithSort("Diploma in Culinary Arts");
        SortedListInterface<String> language11 = new SortedDoublyLinkedList<>();
        language11.addWithSort("English");
        applicants.addWithSort(new Applicant("1011", "Kathy", "kathy@gmail.com", 22, skills11, workingExp11, education11, null, language11));

        // Applicant 12 - Law
        SortedListInterface<String> skills12 = new SortedDoublyLinkedList<>();
        skills12.addWithSort("Legal Writing");
        skills12.addWithSort("Case Analysis");
        SortedListInterface<String> workingExp12 = new SortedDoublyLinkedList<>();
        workingExp12.addWithSort("Legal Intern");
        SortedListInterface<String> education12 = new SortedDoublyLinkedList<>();
        education12.addWithSort("Bachelor in Law");
        SortedListInterface<String> language12 = new SortedDoublyLinkedList<>();
        language12.addWithSort("English");
        applicants.addWithSort(new Applicant("1012", "Leo", "leo@gmail.com", 24, skills12, workingExp12, education12, null, language12));

        // Applicant 13 - Architecture
        SortedListInterface<String> skills13 = new SortedDoublyLinkedList<>();
        skills13.addWithSort("AutoCAD");
        skills13.addWithSort("3D Modeling");
        SortedListInterface<String> workingExp13 = new SortedDoublyLinkedList<>();
        workingExp13.addWithSort("Architect Intern");
        SortedListInterface<String> education13 = new SortedDoublyLinkedList<>();
        education13.addWithSort("Bachelor in Architecture");
        SortedListInterface<String> language13 = new SortedDoublyLinkedList<>();
        language13.addWithSort("English");
        applicants.addWithSort(new Applicant("1013", "Mia", "mia@gmail.com", 23, skills13, workingExp13, education13, null, language13));

        return applicants;
    }

}
