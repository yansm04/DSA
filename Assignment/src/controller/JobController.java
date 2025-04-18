/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedDoublyLinkedList;
import utility.utility1;
import adt.SortedListInterface;
import boundary.JobUI;
import boundary.MainMenuUI;
import dao.Initializer;
import entity.Company;
import entity.Job;
import utility.JobMessageUI;

/**
 *
 * @author Cason Soh
 */
public class JobController {

    private SortedListInterface<Company> companies;
    private Company loggedInCompany = null;
    private JobUI JobUI = new JobUI();

    public void signUp(SortedListInterface<Company> companies) {
        utility1.clearScreen();
        JobUI.displaySignUpHeader();
        String newCompanyId = generateUniqueCompanyId(companies);
        JobUI.displayNewCompanyId(newCompanyId);

        String companyName;
        while (true) {
            companyName = JobUI.getCompanyNameForSignUp();
            if (companyName == null) {
                JobMessageUI.displayOperationCancelledMessage("Sign up");
                MainMenuUI.pressEnterToContinue();
                utility1.clearScreen();
                return;
            }
            if (!isCompanyNameExists(companies, companyName)) {
                break;
            }
            JobMessageUI.displayCompanyNameExistsMsg();
            MainMenuUI.pressEnterToContinue();
            utility1.clearScreen();
            JobUI.displaySignUpHeader();
            JobUI.displayNewCompanyId(newCompanyId);
        }

        String email;
        while (true) {
            email = JobUI.getCompanyEmailForSignUp();
            if (email == null) {
                JobMessageUI.displayOperationCancelledMessage("Sign up");
                MainMenuUI.pressEnterToContinue();
                utility1.clearScreen();
                return;
            }
            if (!isEmailExists(companies, email)) {
                break;
            }
            JobMessageUI.displayEmailExistsMsg();
            MainMenuUI.pressEnterToContinue();
            utility1.clearScreen();
            JobUI.displaySignUpHeader();
            JobUI.displayNewCompanyId(newCompanyId);
            System.out.println("Company Name: " + companyName);
        }

        Company newCompany = new Company(newCompanyId, companyName, email);
        companies.addWithSort(newCompany);
        JobMessageUI.displaySignUpSuccess();
        MainMenuUI.pressEnterToContinue();
        utility1.clearScreen();
    }

    private String generateUniqueJobID(Company company) {
        SortedListInterface<Job> jobList = company.getJob();
        int counter = 1;
        String potentialID;

        do {
            potentialID = String.format("J%03d", counter); // Format as J001, J002, etc.
            counter++;
        } while (isJobIDExists(jobList, potentialID));

        return potentialID;
    }

    private boolean isJobIDExists(SortedListInterface<Job> jobList, String jobID) {
        if (jobList == null || jobList.isEmpty()) {
            return false;
        }
        for (int i = 0; i < jobList.getSize(); i++) {
            if (jobList.viewDataAtIndex(i).getJobID().equals(jobID)) {
                return true;
            }
        }
        return false;
    }

    private String generateUniqueCompanyId(SortedListInterface<Company> companies) {
        int counter = 1;
        String potentialID;
        do {
            potentialID = String.format("C%03d", counter);
            counter++;
        } while (isCompanyIdExists(companies, potentialID));
        return potentialID;
    }

    private boolean isEmailExists(SortedListInterface<Company> companies, String email) {
        if (companies == null || companies.isEmpty()) {
            return false;
        }
        for (int i = 0; i < companies.getSize(); i++) {
            if (companies.viewDataAtIndex(i).getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCompanyIdExists(SortedListInterface<Company> companies, String companyId) {
        if (companies == null || companies.isEmpty()) {
            return false;
        }
        for (int i = 0; i < companies.getSize(); i++) {
            if (companies.viewDataAtIndex(i).getCompanyId().equals(companyId)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCompanyNameExists(SortedListInterface<Company> companies, String companyName) {
        if (companies == null || companies.isEmpty()) {
            return false;
        }
        for (int i = 0; i < companies.getSize(); i++) {
            if (companies.viewDataAtIndex(i).getCompanyName().equalsIgnoreCase(companyName)) {
                return true;
            }
        }
        return false;
    }

    public void login() {
        if (companies.isEmpty()) {
            JobUI.displayNoCompaniesRegistered();
            return;
        }
        JobUI.displayLoginHeader();

        JobUI.displayCompanyListForLogin(companies);
        int choice = JobUI.getCompanyChoiceForLogin();

        if (choice == 0) {
            JobMessageUI.displayReturnMainMenu();
            return;
        }

        if (choice >= 1 && choice <= companies.getSize()) {
            loggedInCompany = companies.viewDataAtIndex(choice - 1);
            JobUI.displayLoginSuccess(loggedInCompany.getCompanyName());
            displayCompanyMenu(loggedInCompany);
        } else if (choice != -1) {
            JobMessageUI.displayInvalidInputMsg();
        }
    }

    public void displayCompanyMenu(Company loggedInCompany) {
        boolean loggedIn = true;
        while (loggedIn) {
//            JobUI.displayCompanyMenuHeader(loggedInCompany.getCompanyName());
            int choice = JobUI.getCompanyMenuChoice();

            switch (choice) {
                case 1:
                    // viewJobs();
                    break;
                case 2:
                    addJob();
                    break;
                case 3:
                    editJob();
                    break;
                case 4:
                    deleteJob();
                    break;
                default:
                    loggedIn = false;
                    JobMessageUI.displayLogoutMessage();
            }
        }
    }

    public void displayJobList() {
        JobUI.printHeader();
        if (loggedInCompany == null || loggedInCompany.getJob().isEmpty()) {
            JobUI.noJobMessage();
        } else {
            SortedListInterface<Job> jobs = loggedInCompany.getJob();
            for (int i = 0; i < jobs.getSize(); i++) {
                Job job = jobs.viewDataAtIndex(i);
                String skillsString = utility1.listToString(job.getReqSkill());
                String educationString = utility1.listToString(job.getReqEdLevel());
                String languageString = utility1.listToString(job.getLanguage());
                String fosString = utility1.listToString(job.getFos());

                // Truncate strings (UI responsibility, but can be done here if UI is simple)
                skillsString = truncate(skillsString, 27);
                educationString = truncate(educationString, 27);
                languageString = truncate(languageString, 17);
                fosString = truncate(fosString, 27);
                String description = truncate(job.getDesc(), 37);
                String title = truncate(job.getTitle(), 17);
                String location = truncate(job.getLocation(), 17);

                JobUI.printJobDetailsRow(i + 1, title, location, skillsString, educationString, languageString, description, fosString, job.getSalary(), job.getStatus());
            }
        }
        JobUI.printBottom();
    }

    public void viewJobs() {
        if (loggedInCompany == null) {
            JobMessageUI.displayNotLoggedInMessage();
            return;
        }

        SortedListInterface<Job> jobs = loggedInCompany.getJob();
        if (jobs.isEmpty()) {
            JobUI.noJobMessage();
            MainMenuUI.pressEnterToContinue();
            return;
        }

        boolean continueViewing = true;

        while (continueViewing) {
            displayJobList();
            JobMessageUI.displayEnterNumJob("view full details (or 0 to go back)");
            int choice = JobUI.getJobChoice(jobs.getSize());

            if (choice > 0 && choice <= jobs.getSize()) {
                Job jobToView = jobs.viewDataAtIndex(choice - 1);
                JobUI.displayFullJobDetails(jobToView);
                continueViewing = JobUI.promptViewAnotherJob();
            } else if (choice == 0) {
                JobMessageUI.displayReturnMessage();
                continueViewing = false;
            } else {
                JobMessageUI.displayInvalidInput();
            }
        }
    }

    private String truncate(String text, int maxLength) {
        if (text.length() > maxLength) {
            return text.substring(0, maxLength) + "...";
        }
        return text;
    }

    public void addJob() {
        if (loggedInCompany == null) {
            JobMessageUI.displayNotLoggedInMessage();
            return;
        }

        boolean addingMore = true;
        while (addingMore) {
            Job newJob = getJobDetailsFromUI();
            if (newJob != null) {
                if (JobUI.confirmAddJob(newJob)) {
                    JobMessageUI.displayJobOperationMessage(newJob.getTitle(), "added");
                    loggedInCompany.getJob().addWithSort(newJob);
                    addingMore = JobUI.promptAddAnotherJob();
                } else {
                    JobMessageUI.displayOperationCancelledMessage("Add job");
                    addingMore = JobUI.promptAddAnotherJob();
                }
            } else {
                addingMore = false;
            }
        }
    }

    private Job getJobDetailsFromUI() {
        JobUI.displayAddJobHeader();
        String jobID = generateUniqueJobID(loggedInCompany);
        JobUI.displayGeneratedJobID(jobID);

        String title = JobUI.getJobTitle();
        if (title == null) {
            return null;
        }
        String desc = JobUI.getJobDescription();
        if (desc == null) {
            return null;
        }
        String location = JobUI.getJobLocation();
        if (location == null) {
            return null;
        }
        int salary = JobUI.getJobSalary();
        if (salary == -1) {
            return null;
        }

        SortedListInterface<String> selectedEducationList = JobUI.getSingleEducationLevelFromUI(Initializer.initializeEducationLevels());
        if (selectedEducationList == null) {
            return null;
        }
        SortedListInterface<String> selectedSkills = selectMultipleOptions("skills", Initializer.initializeSkills());
        if (selectedSkills == null) {
            return null;
        }

        SortedListInterface<String> selectedFields = selectMultipleOptions("fields of study", Initializer.initializeFieldsOfStudy());
        if (selectedFields == null) {
            return null; // User cancelled
        }
        SortedListInterface<String> selectedLanguages = selectMultipleOptions("languages", Initializer.initializeLanguages());
        if (selectedLanguages == null) {
            return null; // User cancelled
        }
        return new Job(loggedInCompany, jobID, title, desc, selectedSkills, selectedEducationList, selectedFields, selectedLanguages, salary, location, new SortedDoublyLinkedList<>(), 1);
    }

    private SortedListInterface<String> selectMultipleOptions(String categoryName, SortedListInterface<String> options) {
        SortedListInterface<String> selected = new SortedDoublyLinkedList<>();
        boolean addingMore = true;

        while (addingMore) {
            JobUI.displaySelectedOptions(categoryName, selected);
            String choice = JobUI.getMultipleOptionChoice(categoryName, options);

            if (choice.equals("0")) {
                addingMore = false;
            } else {
                try {
                    int i = Integer.parseInt(choice.trim()) - 1;
                    if (i >= 0 && i < options.getSize()) {
                        String chosen = options.viewDataAtIndex(i);
                        if (!selected.contains(chosen)) {
                            selected.addWithSort(chosen);
                            JobMessageUI.displayOptionSelected(chosen);
                        } else {
                            JobMessageUI.displayOptionAlreadySelected(chosen);
                        }
                    } else {
                        JobMessageUI.displayInvalidListNum();
                    }
                } catch (NumberFormatException e) {
                    JobMessageUI.displayInvalidNum();
                }
            }

            if (addingMore) {
                addingMore = JobUI.promptAddMoreOptions(categoryName);
            }
        }
        JobUI.displayFinalSelectedOptions(categoryName, selected);
        return selected;
    }

    public void deleteJob() {
        if (loggedInCompany == null) {
            JobMessageUI.displayNotLoggedInMessage();
            return;
        }

        SortedListInterface<Job> jobs = loggedInCompany.getJob();
        if (jobs.isEmpty()) {
            JobUI.noJobMessage();
            return;
        }

        boolean continueDeleting = true;
        while (continueDeleting) {
            displayJobList();
            JobMessageUI.displayEnterNumJob("delete");

            int indexToDelete = JobUI.getJobChoice(jobs.getSize());

            if (indexToDelete == 0) {
                JobMessageUI.displayReturnMessage();
                continueDeleting = false;
            } else if (indexToDelete < 1 || indexToDelete > jobs.getSize()) {
                JobMessageUI.displayInvalidListNum();
            } else {
                Job jobToDelete = jobs.viewDataAtIndex(indexToDelete - 1);
                // Check if job has applications
                if (!jobToDelete.getApplication().isEmpty()) {
                    JobMessageUI.displayCannotDeleteMsg();
                    continueDeleting = JobUI.promptContinueOperation();
                } else {
                    if (JobUI.confirmJobDeletion(jobToDelete.getTitle())) {
                        Job removedJob = jobs.removeByIndex(indexToDelete - 1);
                        if (removedJob != null) {
                            JobMessageUI.displayJobOperationMessage(removedJob.getTitle(), "deleted");
                            continueDeleting = JobUI.promptContinueOperation();
                        } else {
                            JobMessageUI.displayOperationCancelledMessage("Deletion");
                            continueDeleting = false;
                        }
                    } else {
                        JobMessageUI.displayOperationCancelledMessage("Deletion");
                        continueDeleting = JobUI.promptContinueOperation();
                    }
                }
            }
        }
    }

    public void editJob() {
        if (loggedInCompany == null) {
            JobMessageUI.displayNotLoggedInMessage();
            return;
        }
        SortedListInterface<Job> jobs = loggedInCompany.getJob();
        displayJobList();
        int jobIndex = JobUI.getJobIndexToEdit(jobs.getSize());

        if (jobIndex == -1) {
            return;
        }

        Job jobToEdit = jobs.viewDataAtIndex(jobIndex);
        editJobDetails(jobToEdit);
    }

    private void editJobDetails(Job job) {
        boolean editing = true;
        while (editing) {
            JobUI.displayEditJobMenu(job);
            String choice = JobUI.getEditAttributeChoice();

            switch (choice) {
                case "1":
                    editJobTitle(job);
                    break;
                case "2":
                    editJobDescription(job);
                    break;
                case "3":
                    editJobLocation(job);
                    break;
                case "4":
                    editJobSalary(job);
                    break;
                case "5":
                    editJobSkills(job);
                    break;
                case "6":
                    editJobEducation(job);
                    break;
                case "7":
                    editJobFieldsOfStudy(job);
                    break;
                case "8":
                    editJobLanguages(job);
                    break;
                case "9":
                    editJobStatus(job);
                    break;
                case "0":
                    editing = false;
                    break;
                default:
                    JobMessageUI.displayInvalidListNum();
            }
        }
    }

    private void editJobTitle(Job job) {
        String currentTitle = job.getTitle();
        String newTitle = JobUI.getNewJobTitle(currentTitle);
        if (newTitle != null) {
            if (JobUI.confirmChange("Title", currentTitle, newTitle)) {
                job.setTitle(newTitle);
                JobMessageUI.displayJobAttributeUpdated("Title", job.getTitle());
            } else {
                JobMessageUI.displayChangeReverted("Title", currentTitle);
            }
        }
    }

    private void editJobDescription(Job job) {
        String currentDesc = job.getDesc();
        String newDesc = JobUI.getNewJobDescription(currentDesc);
        if (newDesc != null) {
            if (JobUI.confirmChange("Description", currentDesc, newDesc)) {
                job.setDesc(newDesc);
                JobMessageUI.displayJobAttributeUpdated("Description", job.getDesc());
            } else {
                JobMessageUI.displayChangeReverted("Description", currentDesc);
            }
        }
    }

    private void editJobLocation(Job job) {
        String currentLocation = job.getLocation();
        // Define the list of 14 Malaysian states
        String[] malaysianStates = {
            "Johor", "Kedah", "Kelantan", "Malacca", "Negeri Sembilan",
            "Pahang", "Penang", "Perak", "Perlis", "Sabah",
            "Sarawak", "Selangor", "Terengganu", "Kuala Lumpur"
        };
        String newLocation = JobUI.getNewJobLocation(malaysianStates, currentLocation);
        if (newLocation != null) {
            if (JobUI.confirmChange("Location", currentLocation, newLocation)) {
                job.setLocation(newLocation);
                JobMessageUI.displayJobAttributeUpdated("Location", job.getLocation());
            } else {
                JobMessageUI.displayChangeReverted("Location", currentLocation);
            }
        }
    }

    private void editJobSalary(Job job) {
        int currentSalary = job.getSalary();
        Integer newSalary = JobUI.getNewJobSalary(currentSalary);
        if (newSalary != null) {
            if (JobUI.confirmChange("Salary", String.valueOf(currentSalary), String.valueOf(newSalary))) {
                job.setSalary(newSalary);
                JobMessageUI.displayJobAttributeUpdated("Salary", String.valueOf(job.getSalary()));
            } else {
                JobMessageUI.displayChangeReverted("Salary", String.valueOf(currentSalary));
            }
        }
    }

    private void editJobSkills(Job job) {
        SortedListInterface<String> availableSkills = Initializer.initializeSkills();
        SortedListInterface<String> currentSkills = job.getReqSkill();
        SortedListInterface<String> selectedSkills = JobUI.editMultipleOptions("skills", availableSkills, currentSkills);
        if (selectedSkills != null) {
            job.setReqSkill(selectedSkills);
            JobMessageUI.displayJobAttributeUpdated("Skills", utility1.listToString(selectedSkills));
        } else {
            JobMessageUI.displayChangeReverted("Skills", utility1.listToString(currentSkills));
        }
    }

    private void editJobEducation(Job job) {
        SortedListInterface<String> educationLevels = Initializer.initializeEducationLevels();
        SortedListInterface<String> currentEducation = job.getReqEdLevel();
        SortedListInterface<String> selectedEducation = JobUI.getNewEducationLevel(educationLevels, currentEducation);
        if (selectedEducation != null) {
            if (JobUI.confirmChange("Education Level", utility1.listToString(currentEducation), utility1.listToString(selectedEducation))) {
                job.setReqEdLevel(selectedEducation);
                JobMessageUI.displayJobAttributeUpdated("Education Level", utility1.listToString(selectedEducation));
            } else {
                JobMessageUI.displayChangeReverted("Education Level", utility1.listToString(currentEducation));
            }
        }
    }

    private void editJobFieldsOfStudy(Job job) {
        SortedListInterface<String> fieldsOfStudy = Initializer.initializeFieldsOfStudy();
        SortedListInterface<String> currentFields = job.getFos();
        SortedListInterface<String> selectedFields = JobUI.editMultipleOptions("fields of study", fieldsOfStudy, currentFields);
        if (selectedFields != null) {
            job.setFos(selectedFields);
            JobMessageUI.displayJobAttributeUpdated("Fields of Study", utility1.listToString(selectedFields));
        } else {
            JobMessageUI.displayChangeReverted("Fields of Study", utility1.listToString(currentFields));
        }
    }

    private void editJobLanguages(Job job) {
        SortedListInterface<String> languages = Initializer.initializeLanguages();
        SortedListInterface<String> currentLanguages = job.getLanguage();
        SortedListInterface<String> selectedLanguages = JobUI.editMultipleOptions("languages", languages, currentLanguages);
        if (selectedLanguages != null) {
            job.setLanguage(selectedLanguages);
            JobMessageUI.displayJobAttributeUpdated("Languages", utility1.listToString(selectedLanguages));
        } else {
            JobMessageUI.displayChangeReverted("Languages", utility1.listToString(currentLanguages));
        }
    }

    private void editJobStatus(Job job) {
        int currentStatus = job.getStatus();
        Integer newStatus = JobUI.getNewJobStatus(currentStatus);
        if (newStatus != null) {
            String currentStatusStr = (currentStatus == 0 ? "Closed" : "Open");
            String newStatusStr = (newStatus == 0 ? "Closed" : "Open");
            if (JobUI.confirmChange("Status", currentStatusStr, newStatusStr)) {
                job.setStatus(newStatus);
                JobMessageUI.displayJobAttributeUpdated("Status", newStatusStr);
            } else {
                JobMessageUI.displayChangeReverted("Status", currentStatusStr);
            }
        }
    }

public void filterJobs(SortedListInterface<Company> companies) {
    if (companies.isEmpty()) {
        JobUI.noJobMessage();
        MainMenuUI.pressEnterToContinue();
        utility1.clearScreen();
        return;
    }

    SortedListInterface<String> filterAttributes = new SortedDoublyLinkedList<>();
    String titleFilter = "any";
    String locationFilter = "any";
    int[] salaryRange = new int[]{-1, -1};
    SortedListInterface<String> skillsFilter = null;
    SortedListInterface<String> educationFilter = null;
    SortedListInterface<String> fosFilter = null;
    SortedListInterface<String> languageFilter = null;
    Integer statusFilter = null;

    boolean selecting = true;
    while (selecting) {
        JobUI.displayFilterJobMenu();
        String choice = JobUI.getFilterChoice();

        switch (choice) {
            case "1":
                if (!filterAttributes.contains("title")) {
                    titleFilter = JobUI.getFilterTitle();
                    filterAttributes.addWithSort("title");
                    JobMessageUI.displayOptionSelected("Job Title filter");
                } else {
                    JobMessageUI.displayOptionAlreadySelected("Job Title filter");
                }
                break;
            case "2":
                if (!filterAttributes.contains("location")) {
                    locationFilter = JobUI.getFilterLocation();
                    filterAttributes.addWithSort("location");
                    JobMessageUI.displayOptionSelected("Location filter");
                } else {
                    JobMessageUI.displayOptionAlreadySelected("Location filter");
                }
                break;
            case "3":
                if (!filterAttributes.contains("salary")) {
                    salaryRange = JobUI.getFilterSalaryRange();
                    filterAttributes.addWithSort("salary");
                    JobMessageUI.displayOptionSelected("Salary Range filter");
                } else {
                    JobMessageUI.displayOptionAlreadySelected("Salary Range filter");
                }
                break;
            case "4":
                if (!filterAttributes.contains("skills")) {
                    skillsFilter = JobUI.getFilterMultipleOptions("skills", Initializer.initializeSkills());
                    if (skillsFilter != null) {
                        filterAttributes.addWithSort("skills");
                        JobMessageUI.displayOptionSelected("Skills filter");
                    }
                } else {
                    JobMessageUI.displayOptionAlreadySelected("Skills filter");
                }
                break;
            case "5":
                if (!filterAttributes.contains("education")) {
                    educationFilter = JobUI.getFilterMultipleOptions("education levels", Initializer.initializeEducationLevels());
                    if (educationFilter != null) {
                        filterAttributes.addWithSort("education");
                        JobMessageUI.displayOptionSelected("Education Level filter");
                    }
                } else {
                    JobMessageUI.displayOptionAlreadySelected("Education Level filter");
                }
                break;
            case "6":
                if (!filterAttributes.contains("fos")) {
                    fosFilter = JobUI.getFilterMultipleOptions("fields of study", Initializer.initializeFieldsOfStudy());
                    if (fosFilter != null) {
                        filterAttributes.addWithSort("fos");
                        JobMessageUI.displayOptionSelected("Field of Study filter");
                    }
                } else {
                    JobMessageUI.displayOptionAlreadySelected("Field of Study filter");
                }
                break;
            case "7":
                if (!filterAttributes.contains("language")) {
                    languageFilter = JobUI.getFilterMultipleOptions("languages", Initializer.initializeLanguages());
                    if (languageFilter != null) {
                        filterAttributes.addWithSort("language");
                        JobMessageUI.displayOptionSelected("Language filter");
                    }
                } else {
                    JobMessageUI.displayOptionAlreadySelected("Language filter");
                }
                break;
            case "8":
                if (!filterAttributes.contains("status")) {
                    statusFilter = JobUI.getFilterJobStatus();
                    if (statusFilter != null || statusFilter == null) { // Allow null for "Any"
                        filterAttributes.addWithSort("status");
                        JobMessageUI.displayOptionSelected("Job Status filter");
                    }
                } else {
                    JobMessageUI.displayOptionAlreadySelected("Job Status filter");
                }
                break;
            case "0":
                selecting = false;
                break;
            default:
                JobMessageUI.displayInvalidListNum();
        }
        MainMenuUI.pressEnterToContinue();
        utility1.clearScreen();
    }

    if (filterAttributes.isEmpty()) {
        JobMessageUI.displayOperationCancelledMessage("Filter jobs");
        MainMenuUI.pressEnterToContinue();
        utility1.clearScreen();
        return;
    }

    SortedListInterface<Job> filteredJobs = applyFilters(companies, titleFilter, locationFilter, salaryRange, skillsFilter, educationFilter, fosFilter, languageFilter, statusFilter);
    displayFilteredJobList(companies, filteredJobs);

    boolean continueViewing = true;
    while (continueViewing && !filteredJobs.isEmpty()) {
        JobMessageUI.displayEnterNumJob("view full details (or 0 to go back)");
        int choice = JobUI.getJobChoice(filteredJobs.getSize());

        if (choice > 0 && choice <= filteredJobs.getSize()) {
            Job jobToView = filteredJobs.viewDataAtIndex(choice - 1);
            JobUI.displayFullJobDetails(jobToView);
            continueViewing = JobUI.promptViewAnotherJob();
        } else if (choice == 0) {
            JobMessageUI.displayReturnMessage();
            continueViewing = false;
        } else {
            JobMessageUI.displayInvalidInput();
        }

    }
}

private SortedListInterface<Job> applyFilters(SortedListInterface<Company> companies, String titleFilter, String locationFilter, int[] salaryRange,
        SortedListInterface<String> skillsFilter, SortedListInterface<String> educationFilter,
        SortedListInterface<String> fosFilter, SortedListInterface<String> languageFilter, Integer statusFilter) {
    SortedListInterface<Job> filteredJobs = new SortedDoublyLinkedList<>();

    for (int i = 0; i < companies.getSize(); i++) {
        Company company = companies.viewDataAtIndex(i);
        SortedListInterface<Job> jobs = company.getJob();
        for (int j = 0; j < jobs.getSize(); j++) {
            Job job = jobs.viewDataAtIndex(j);
            boolean matches = true;

            if (titleFilter != null && !titleFilter.equals("any") && !job.getTitle().toLowerCase().contains(titleFilter.toLowerCase())) {
                matches = false;
            }
            if (locationFilter != null && !locationFilter.equals("any") && !job.getLocation().equalsIgnoreCase(locationFilter)) {
                matches = false;
            }
            if (salaryRange != null && (salaryRange[0] != -1 || salaryRange[1] != -1)) {
                if (salaryRange[0] != -1 && job.getSalary() < salaryRange[0]) {
                    matches = false;
                }
                if (salaryRange[1] != -1 && job.getSalary() > salaryRange[1]) {
                    matches = false;
                }
            }
            if (skillsFilter != null) {
                boolean skillMatch = false;
                for (int k = 0; k < skillsFilter.getSize(); k++) {
                    if (job.getReqSkill().contains(skillsFilter.viewDataAtIndex(k))) {
                        skillMatch = true;
                        break;
                    }
                }
                if (!skillMatch) {
                    matches = false;
                }
            }
            if (educationFilter != null) {
                boolean eduMatch = false;
                for (int k = 0; k < educationFilter.getSize(); k++) {
                    if (job.getReqEdLevel().contains(educationFilter.viewDataAtIndex(k))) {
                        eduMatch = true;
                        break;
                    }
                }
                if (!eduMatch) {
                    matches = false;
                }
            }
            if (fosFilter != null) {
                boolean fosMatch = false;
                for (int k = 0; k < fosFilter.getSize(); k++) {
                    if (job.getFos().contains(fosFilter.viewDataAtIndex(k))) {
                        fosMatch = true;
                        break;
                    }
                }
                if (!fosMatch) {
                    matches = false;
                }
            }
            if (languageFilter != null) {
                boolean langMatch = false;
                for (int k = 0; k < languageFilter.getSize(); k++) {
                    if (job.getLanguage().contains(languageFilter.viewDataAtIndex(k))) {
                        langMatch = true;
                        break;
                    }
                }
                if (!langMatch) {
                    matches = false;
                }
            }
            if (statusFilter != null && job.getStatus() != statusFilter) {
                matches = false;
            }

            if (matches) {
                filteredJobs.addWithSort(job);
            }
        }
    }
    return filteredJobs;
}

public void displayFilteredJobList(SortedListInterface<Company> companies, SortedListInterface<Job> jobs) {
    JobUI.printHeader();
    if (jobs.isEmpty()) {
        JobUI.noJobMessage();
    } else {
        for (int i = 0; i < jobs.getSize(); i++) {
            Job job = jobs.viewDataAtIndex(i);
            String skillsString = utility1.listToString(job.getReqSkill());
            String educationString = utility1.listToString(job.getReqEdLevel());
            String languageString = utility1.listToString(job.getLanguage());
            String fosString = utility1.listToString(job.getFos());

            skillsString = truncate(skillsString, 27);
            educationString = truncate(educationString, 27);
            languageString = truncate(languageString, 17);
            fosString = truncate(fosString, 27);
            String description = truncate(job.getDesc(), 37);
            String title = truncate(job.getTitle(), 17);
            String location = truncate(job.getLocation(), 17);
            String companyName = truncate(job.getCompany() != null ? job.getCompany().getCompanyName() : "N/A", 17);

            JobUI.printJobDetailsRowWithComp(i + 1, title, location, skillsString, educationString, languageString, description, fosString, job.getSalary(), job.getStatus(), companyName);
        }
    }
    JobUI.printBottom();
}

public void generateReport(SortedListInterface<Company> companies) {
    // Compute Top 5 Job Titles by Application Count
    SortedListInterface<JobTitleApplication> jobTitleList = new SortedDoublyLinkedList<>();
    for (int i = 0; i < companies.getSize(); i++) {
        Company company = companies.viewDataAtIndex(i);
        SortedListInterface<Job> jobs = company.getJob();
        for (int j = 0; j < jobs.getSize(); j++) {
            Job job = jobs.viewDataAtIndex(j);
            String jobTitle = job.getTitle();
            int appCount = job.getApplication().getSize();

            // Check if job title exists
            boolean found = false;
            for (int k = 0; k < jobTitleList.getSize(); k++) {
                JobTitleApplication jta = jobTitleList.viewDataAtIndex(k);
                if (jta.jobTitle.equals(jobTitle)) {
                    jta.applicationCount += appCount;
                    found = true;
                    break;
                }
            }
            if (!found) {
                jobTitleList.addWithSort(new JobTitleApplication(jobTitle, appCount));
            }
        }
    }

    // Compute Top 5 Jobs by Salary
    SortedListInterface<Job> allJobs = new SortedDoublyLinkedList<>();
    for (int i = 0; i < companies.getSize(); i++) {
        Company company = companies.viewDataAtIndex(i);
        SortedListInterface<Job> jobs = company.getJob();
        for (int j = 0; j < jobs.getSize(); j++) {
            allJobs.addWithSort(jobs.viewDataAtIndex(j));
        }
    }

    SortedListInterface<Job> topJobsBySalary = new SortedDoublyLinkedList<>();
    for (int i = 0; i < allJobs.getSize(); i++) {
        Job job = allJobs.viewDataAtIndex(i);
        topJobsBySalary.addWithSort(new Job(
                job.getCompany(),
                job.getJobID(),
                job.getTitle(),
                job.getDesc(),
                job.getReqSkill(),
                job.getReqEdLevel(),
                job.getFos(),
                job.getLanguage(),
                job.getSalary(),
                job.getLocation(),
                job.getApplication(),
                job.getStatus()
        ) {
            @Override
            public int compareTo(Job other) {
                // Sort by salary (descending), then by job ID (ascending) for ties
                int salaryComparison = Integer.compare(other.getSalary(), this.getSalary());
                return salaryComparison != 0 ? salaryComparison : this.getJobID().compareTo(other.getJobID());
            }
        });
    }

    // Compute Company with Highest Application Count
    SortedListInterface<CompanyApplication> companyRankings = new SortedDoublyLinkedList<>();
    for (int i = 0; i < companies.getSize(); i++) {
        Company company = companies.viewDataAtIndex(i);
        int totalApps = 0;
        SortedListInterface<Job> jobs = company.getJob();
        for (int j = 0; j < jobs.getSize(); j++) {
            totalApps += jobs.viewDataAtIndex(j).getApplication().getSize();
        }
        companyRankings.addWithSort(new CompanyApplication(company, totalApps));
    }

    CompanyApplication topCompanyApp = companyRankings.isEmpty() ? null : companyRankings.viewDataAtIndex(0);

    // Pass the computed data to JobUI for printing
    if (topCompanyApp == null) {
        JobUI.printReport(jobTitleList, topJobsBySalary, null, 0);
    } else {
        JobUI.printReport(jobTitleList, topJobsBySalary, topCompanyApp.company, topCompanyApp.applicationCount);
    }
}

    public static class JobTitleApplication implements Comparable<JobTitleApplication> {

        public String jobTitle;
        public int applicationCount;

        public JobTitleApplication(String jobTitle, int applicationCount) {
            this.jobTitle = jobTitle;
            this.applicationCount = applicationCount;
        }

        @Override
        public int compareTo(JobTitleApplication other) {
            // Sort by application count (descending), then by job title (ascending) for ties
            int countComparison = Integer.compare(other.applicationCount, this.applicationCount);
            return countComparison != 0 ? countComparison : this.jobTitle.compareTo(other.jobTitle);
        }
    }

    public void setCompany(Company company) {
        this.loggedInCompany = company;
    }

    public void setCompanies(SortedListInterface<Company> companies) {
        this.companies = companies;
    }

    // Static inner class for company and its total application count
    private static class CompanyApplication implements Comparable<CompanyApplication> {

        Company company;
        int applicationCount;

        CompanyApplication(Company company, int applicationCount) {
            this.company = company;
            this.applicationCount = applicationCount;
        }

        @Override
        public int compareTo(CompanyApplication other) {
            // Sort by application count (descending), then by company name (ascending) for ties
            int countComparison = Integer.compare(other.applicationCount, this.applicationCount);
            return countComparison != 0 ? countComparison : this.company.getCompanyName().compareTo(other.company.getCompanyName());
        }
    }
}
