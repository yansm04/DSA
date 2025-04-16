/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;
import boundary.InterviewUI;
import boundary.MainMenuUI;
import entity.Applicant;
import entity.Application;
import entity.Company;
import entity.Interview;
import entity.InterviewSchedule;
import entity.Job;
import entity.ScheduleManager;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static Interview createInterviewDetails(Company company) {
        int appId;
        Application application = new Application();
        String interviewId = "";
        String interviewType = "";
        String dateTimeStr = "";
        LocalDateTime interviewDateTime = null;
        ScheduleManager manager = ScheduleManager.getInstance();
        String status = "";
        int current = 0;
        String cfm;
        do {
            switch (current) {
                case 0:
                    try {
                    appId = InterviewUI.promptApplicationId();

                    if (appId == 0) {
                        continue;
                    } else if (appId == -999) {
                        return null;
                    } else {
                        application = findApplicationByID(company, appId);
                        if (application == null) {
                            InterviewUI.printErrorOption();
                            continue;
                        } else {
                            interviewId = "INT-" + application.getApplicationID();
                        }
                    }
                    current = 1;
                    break;
                } catch (NumberFormatException e) {
                    InterviewUI.printErrorOption();
                    continue;
                }
                case 1:
                    InterviewUI.dashLine();
                    interviewType = InterviewUI.promptInterviewType();
                    if (interviewType == null) {
                        break;
                    } else if (interviewType.equals("-999")) {
                        return null;
                    } else if (interviewType.equals("-111")) {
                        current = 0;
                        break;
                    }
                    current = 2;
                    break;
                case 2:
                    InterviewUI.dashLine();
                    dateTimeStr = InterviewUI.promptDateTime();
                    if (dateTimeStr == null) {
                        return null;
                    } else if (dateTimeStr.equals("-999")) {
                        return null;
                    } else if (dateTimeStr.equals("-111")) {
                        current = 1;
                        break;
                    } else if (!isValidInterviewDateTime(dateTimeStr)) {
                        InterviewUI.printInvalidDateTime();
                        current = 2;
                        break;
                    }
                    // calculate end time for the interview
                    interviewDateTime = parseDateTime(dateTimeStr);

                    // validate that the interview date falls within the current scheduling window.
                    if (manager.getCurrentSchedule() != null) {
                        if (!manager.getCurrentSchedule().isWithinSchedule(interviewDateTime)) {
                            InterviewUI.printOutScheduleWindowError(manager.getCurrentSchedule().getScheduleStartDate(), manager.getCurrentSchedule().getScheduleEndDate());
                            current = 2;
                            break;
                        }
                    }

                    LocalDateTime interviewEndTime = interviewDateTime.plusHours(1);

                    // check for scheduling conflict using global schedule in ScheduleManager.
                    if (checkForConflict(interviewDateTime, interviewEndTime)) {
                        InterviewUI.printConflictTimeError();
                        current = 2;
                        break;
                    }
                    current = 3;
                    break;
                case 3:
                    InterviewUI.dashLine();
                    do {
                        cfm = InterviewUI.promptConfirmation();
                        if (cfm.equals("y")) {
                            status = "Scheduled";
                            InterviewUI.printConfirmedInterview(interviewId, interviewType, dateTimeStr, application.getApplicant().getName(), application.getJob().getTitle(), status);
                            current = 4;
                            break;
                        } else if (cfm.equals("n")) {
                            current = 2;
                            break;
                        } else if (cfm.equals("q")) {
                            return null;
                        } else {
                            InterviewUI.printErrorOption();
                        }
                    } while (!cfm.equals("y") && !cfm.equals("n"));
            }
        } while (current != 4);

        return new Interview(interviewId, application, interviewType, interviewDateTime, status);
    }

    public static void createInterview(Company company) {
        boolean continueOrNot = true;

        while (continueOrNot) {
            utility1.clearScreen();
            boolean hasApplicationOrNot = displayCompanyApplications(company);
            if (!hasApplicationOrNot) {
                continueOrNot = false;
                MainMenuUI.pressEnterToContinue();
                utility1.clearScreen();
                break; // optional, just makes intent clearer
            }

            Interview newInterview = createInterviewDetails(company);

            if (newInterview != null) {
                // link interview to application
                Application application = newInterview.getApplication();
                application.setInterview(newInterview);

                // add interview to global interview schedule
                ScheduleManager manager = ScheduleManager.getInstance();
                if (manager.getCurrentSchedule() == null) {
                    // create new schedule if none exists. Assumption: starting today for the next 1 month
                    manager.setCurrentSchedule(new InterviewSchedule(LocalDate.now(), LocalDate.now().plusMonths(1)));
                }
                manager.getCurrentSchedule().addInterview(newInterview);

                InterviewUI.printSuccessMessage(newInterview.getApplication().getApplicant().getName());

                while (true) {
                    String response = InterviewUI.promptContinueOrNot();
                    if (response.equalsIgnoreCase("n")) {
                        continueOrNot = false;
                        break;
                    } else if (response.equalsIgnoreCase("y")) {
                        break;
                    } else {
                        InterviewUI.printErrorOption();
                    }
                }
            } else {
                break;
            }
        }
    }

    private static Application findApplicationByID(Company company, int appID) {
        SortedListInterface<Job> jobs = company.getJob();
        for (int i = 0; i < jobs.getSize(); i++) {
            Job job = jobs.viewDataAtIndex(i);
            SortedListInterface<Application> applications = job.getApplication();
            if (applications != null) {
                for (int j = 0; j < applications.getSize(); j++) {
                    Application app = applications.viewDataAtIndex(j);
                    if (app != null && app.getApplicationID() == appID && app.getInterview() == null) {
                        return app;
                    }
                }
            }
        }
        return null;
    }

    private static boolean displayCompanyApplications(Company company) {
        utility1.clearScreen();
        InterviewUI.interviewLogo();
        InterviewUI.quitAndBackGuide();

        // to hold all applications without interviews
        SortedListInterface<Application> applicationsToDisplay = new SortedDoublyLinkedList<>();

        SortedListInterface<Job> jobs = company.getJob();

        // get all applications without interviews
        for (int i = 0; i < jobs.getSize(); i++) {
            Job job = jobs.viewDataAtIndex(i);
            SortedListInterface<Application> applications = job.getApplication();

            if (applications != null) {
                for (int j = 0; j < applications.getSize(); j++) {
                    Application app = applications.viewDataAtIndex(j);
                    if (app != null && app.getInterview() == null) {
                        applicationsToDisplay.addWithSort(app);
                    }
                }
            }
        }

        // display the applications if any
        if (applicationsToDisplay.getSize() > 0) {
            InterviewUI.currentApplicationHeading(company.getCompanyName());
            for (int k = 0; k < applicationsToDisplay.getSize(); k++) {
                Application app = applicationsToDisplay.viewDataAtIndex(k);
                InterviewUI.displayApplication(
                        app.getApplicationID(),
                        app.getJob().getTitle(),
                        app.getApplicant().getName(),
                        app.getStatus(),
                        app.getScore());
            }
            InterviewUI.currentApplicationFooter();
            return true;
        } else {
            InterviewUI.displayNoApplication();
            return false;
        }
    }

    public static void viewInterview(Company company, SortedListInterface<Applicant> applicants) {
        displayCompanyApplications(company);

    }

    public static void updateInterview() {
        boolean continueOrNot = true;
        boolean updateOrNot;

        while (continueOrNot) {
            utility1.clearScreen();
            InterviewUI.interviewLogo();
            InterviewUI.quitAndBackGuide();

            ScheduleManager manager = ScheduleManager.getInstance();
            InterviewSchedule schedule = manager.getCurrentSchedule();
            if (schedule == null) {
                InterviewUI.displayNoInterview();
                MainMenuUI.pressEnterToContinue();
                utility1.clearScreen();
                return;
            } else {
                updateOrNot = updateInterviewDetails();
                if (!updateOrNot) {
                    continueOrNot = false;
                    break;
                }
            }

            if (updateOrNot) {
                while (true) {
                    String response = InterviewUI.promptUpdateOrNot();
                    if (response.equalsIgnoreCase("n")) {
                        continueOrNot = false;
                        break;
                    } else if (response.equalsIgnoreCase("y")) {
                        break;
                    } else {
                        InterviewUI.printErrorOption();
                    }
                }
            }

        }

    }

    private static boolean updateInterviewDetails() {
        int current = 0;
        Interview interview = new Interview();
        String interviewType = "";
        String dateTimeStr;
        LocalDateTime currentDateTime = null;
        ScheduleManager manager = ScheduleManager.getInstance();
        LocalDate today = LocalDate.now();
        LocalDateTime scheduledTime = null;
        do {
            switch (current) {
                case 0:
                    String interviewId = InterviewUI.promptInterviewId();
                    if (interviewId == null || interviewId.equals("-999") || interviewId.equals("-111")) {
                        return false;
                    }
                    // find interview using id
                    InterviewSchedule schedule = manager.getCurrentSchedule();
                    interview = schedule.findInterviewById(interviewId.toUpperCase());
                    if (interview == null) {
                        InterviewUI.printInterviewIdNotFound();
                        continue;
                    }

                    // Check that update is allowed (at least one calendar day before the interview)
                    scheduledTime = interview.getInterviewDateTime();
                    LocalDate interviewDate = scheduledTime.toLocalDate();
                    if (!today.isBefore(interviewDate)) {
                        InterviewUI.printCannotUpdateInterview();
                        continue;
                    } else {
                        InterviewUI.displayInterviewDetails(interview);
                    }
                    current = 1;
                    break;
                case 1:
                    InterviewUI.dashLine();
                    interviewType = InterviewUI.promptInterviewType();

                    if (interviewType == null || interviewType.trim().isEmpty()) {
                        current = 2;
                        break;
                    } else if (interviewType.equals("-999")) {
                        return false;
                    } else if (interviewType.equals("-111")) {
                        current = 0;
                        break;
                    }
                    current = 2;
                    break;
                case 2:
                    InterviewUI.dashLine();
                    dateTimeStr = InterviewUI.promptDateTime();
                    if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
                        current = 3;
                        break;
                    } else if (dateTimeStr.equals("-999")) {
                        return false;
                    } else if (dateTimeStr.equals("-111")) {
                        current = 1;
                        break;
                    } else if (!isValidInterviewDateTime(dateTimeStr)) {
                        InterviewUI.printInvalidDateTime();
                        current = 2;
                        break;
                    }
                    // calculate end time for the interview
                    currentDateTime = parseDateTime(dateTimeStr);
                    // Check if the new date/time is the same as the current one.
                    if (currentDateTime.equals(scheduledTime)) {
                        current = 3;
                        break;
                    }

                    // validate that the interview date falls within the current scheduling window
                    if (manager.getCurrentSchedule() != null) {
                        if (!manager.getCurrentSchedule().isWithinSchedule(currentDateTime)) {
                            InterviewUI.printOutScheduleWindowError(manager.getCurrentSchedule().getScheduleStartDate(), manager.getCurrentSchedule().getScheduleEndDate());
                            current = 2;
                            break;
                        }
                    }

                    LocalDateTime interviewEndTime = currentDateTime.plusHours(1);

                    // check for scheduling conflict using global schedule in ScheduleManager
                    if (checkForConflict(currentDateTime, interviewEndTime)) {
                        InterviewUI.printConflictTimeError();
                        current = 2;
                        break;
                    }

                    // ensure the new interview date is at least one calendar day ahead
                    LocalDate newInterviewDate = currentDateTime.toLocalDate();
                    if (!newInterviewDate.isAfter(today)) {
                        System.err.printf("%50s %s", " ", "Interview date must be at least one calendar day ahead\n");
                        current = 2;
                        break;
                    }
                    current = 3;
                    break;
                case 3:
                    InterviewUI.dashLine();
                    String cfm;
                    do {
                        cfm = InterviewUI.promptConfirmation();
                        if (cfm.equals("y")) {
                            interview.setInterviewType(interviewType);
                            interview.reschedule(currentDateTime);
                            InterviewUI.displayUpdatedInterview(interview);
                            current = 4;
                            break;
                        } else if (cfm.equals("n")) {
                            current = 2;
                            break;
                        } else if (cfm.equals("q")) {
                            return false;
                        } else {
                            InterviewUI.printErrorOption();
                        }
                    } while (!cfm.equals("y") && !cfm.equals("n"));
            }

        } while (current != 4);
        return true;
    }

    public static void removeInterview() {

    }

    // validate date and time format and business rules
    public static boolean isValidInterviewDateTime(String dateTimeStr) {
        LocalDateTime interviewDateTime = parseDateTime(dateTimeStr);
        if (interviewDateTime == null) {
            return false;
        }
        return validateInterviewTime(interviewDateTime);
    }

    // validate date and time format
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            // format: dd-MM-yyyy HH:mm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    // validate the interview time based on business rules
    private static boolean validateInterviewTime(LocalDateTime time) {
        LocalDateTime now = LocalDateTime.now();

        // Check that the interview's calendar date is after today's date.
        if (!time.toLocalDate().isAfter(now.toLocalDate())) {
            return false;
        }
        // business hours: mon to fri, 09:00 to 17:00
        DayOfWeek day = time.getDayOfWeek();
        int hour = time.getHour();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return false;
        }
        if (hour < 9 || hour > 16) {
            return false;
        }
        return true;
    }

    // check for scheduling conflicts
    private static boolean checkForConflict(LocalDateTime start, LocalDateTime end) {
        ScheduleManager manager = ScheduleManager.getInstance();
        if (manager.getCurrentSchedule() != null) {
            return manager.getCurrentSchedule().hasTimeConflict(start, end);
        }
        return false;
    }
}
