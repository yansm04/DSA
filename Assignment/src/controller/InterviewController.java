package controller;

/* Author: Chow Zhen Kit*/
import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;
import boundary.InterviewUI;
import boundary.MainMenuUI;
import entity.Applicant;
import entity.Application;
import entity.Company;
import entity.Interview;
import entity.InterviewResult;
import entity.InterviewSchedule;
import entity.Job;
import entity.ScheduleManager;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
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
                        viewInterview(company);
                        break;
                    case 3:
                        updateInterview(company);
                        break;
                    case 4:
                        removeInterview(company);
                        break;
                    case 5:
                        viewGlobalSchedule();
                        break;
                    case 6:
                        viewInterviewResults(company);
                        break;
                    case 7:
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

    public static void appInterviewMenu(Applicant applicant) {
        while (true) {
            utility1.clearScreen();
            InterviewUI.interviewLogo();

            try {
                int choice = InterviewUI.appMenuUI();

                switch (choice) {
                    case 1:
                        viewInterview(applicant);
                        break;
                    case 2:
                        forfeitInterview(applicant);
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

//                    if (checkForConflict(interviewDateTime, interviewDateTime.plusHours(1),
//                            company, application.getApplicant().getUserID(), null)) {
//                        InterviewUI.printConflictTimeError();
//                        current = 2;
//                        break;
//                    }
                    // i set the interview duration to 5 mins (for demo)
                    // to simulate one hr interview, use the above commented code, and comment this code below
                    if (checkForConflict(interviewDateTime, interviewDateTime.plusMinutes(5),
                            company, application.getApplicant().getUserID(), null)) {
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

    public static void viewGlobalSchedule() {
        utility1.clearScreen();
        InterviewUI.interviewLogo();

        ScheduleManager manager = ScheduleManager.getInstance();
        InterviewSchedule schedule = manager.getCurrentSchedule();
        LocalDateTime now = LocalDateTime.now();

        // collect both scheduled (future) and completed (passed) interviews
        SortedListInterface<Interview> allToShow = new SortedDoublyLinkedList<>();
        if (schedule != null) {
            SortedListInterface<Interview> scheduled = schedule.getScheduledInterviews();
            for (int i = 0; i < scheduled.getSize(); i++) {
                Interview iv = scheduled.viewDataAtIndex(i);
                LocalDateTime t = iv.getInterviewDateTime();
                if (t != null && t.isAfter(now)) {
                    allToShow.addAtBack(iv);
                }
            }
        }

        if (allToShow.getSize() == 0) {
            InterviewUI.displayNoInterview();
            MainMenuUI.pressEnterToContinue();
            return;
        }

        SortedListInterface<ChronoLocalDate> dates = new SortedDoublyLinkedList<>();
        for (int i = 0; i < allToShow.getSize(); i++) {
            LocalDate d = allToShow.viewDataAtIndex(i).getInterviewDateTime().toLocalDate();
            boolean found = false;
            for (int j = 0; j < dates.getSize(); j++) {
                if (dates.viewDataAtIndex(j).equals(d)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                dates.addWithSort(d);
            }
        }

        for (int di = 0; di < dates.getSize(); di++) {
            LocalDate date = LocalDate.from(dates.viewDataAtIndex(di));
            InterviewUI.viewInterviewHeading(date);
            SortedListInterface<Interview> dayList = new SortedDoublyLinkedList<>();
            for (int i = 0; i < allToShow.getSize(); i++) {
                Interview iv = allToShow.viewDataAtIndex(i);
                if (iv.getInterviewDateTime().toLocalDate().equals(date)) {
                    dayList.addAtBack(iv);
                }
            }

            while (dayList.getSize() > 0) {
                Interview earliest = dayList.viewDataAtIndex(0);
                int idx = 0;
                for (int k = 1; k < dayList.getSize(); k++) {
                    Interview cand = dayList.viewDataAtIndex(k);
                    if (cand.getInterviewDateTime().isBefore(earliest.getInterviewDateTime())) {
                        earliest = cand;
                        idx = k;
                    }
                }
                // i set the interview duration to 5 mins (for demo)
                // to simulate one hr interview, change the plusMinutes(5) to plusHours(1)
                InterviewUI.viewInterviewBody(earliest.getInterviewID(), earliest.getApplication().getApplicationID(), earliest.getInterviewType(),
                        earliest.getApplication().getJob().getTitle(), earliest.getApplication().getApplicant().getName(),
                        earliest.getInterviewDateTime(), earliest.getInterviewDateTime().plusMinutes(5),
                        earliest.getApplication().getJob().getCompany().getCompanyName()
                );
                dayList.removeByIndex(idx);
            }
            InterviewUI.viewInterviewFooter();
        }
        MainMenuUI.pressEnterToContinue();
    }

    public static void viewInterview(Object user) {
        utility1.clearScreen();
        InterviewUI.interviewLogo();

        ScheduleManager manager = ScheduleManager.getInstance();
        InterviewSchedule schedule = manager.getCurrentSchedule();
        LocalDateTime now = LocalDateTime.now();

        // collect both scheduled (future) and completed (passed) interviews
        SortedListInterface<Interview> allToShow = new SortedDoublyLinkedList<>();
        // Scheduled
        if (schedule != null) {
            SortedListInterface<Interview> scheduled = schedule.getScheduledInterviews();
            for (int i = 0; i < scheduled.getSize(); i++) {
                Interview iv = scheduled.viewDataAtIndex(i);
                if (!belongsToUser(iv, user)) {
                    continue;
                }
                LocalDateTime t = iv.getInterviewDateTime();
                if (t != null && t.isAfter(now)) {
                    allToShow.addAtBack(iv);
                }
            }
        }

        if (allToShow.getSize() == 0) {
            InterviewUI.displayNoInterview();
            MainMenuUI.pressEnterToContinue();
            return;
        }

        // build sorted list of unique dates
        SortedListInterface<ChronoLocalDate> dates = new SortedDoublyLinkedList<>();
        for (int i = 0; i < allToShow.getSize(); i++) {
            LocalDate d = allToShow.viewDataAtIndex(i).getInterviewDateTime().toLocalDate();
            boolean found = false;
            for (int j = 0; j < dates.getSize(); j++) {
                if (dates.viewDataAtIndex(j).equals(d)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                dates.addWithSort(d);
            }
        }

        for (int di = 0; di < dates.getSize(); di++) {
            LocalDate date = LocalDate.from(dates.viewDataAtIndex(di));
            InterviewUI.viewInterviewHeading(date);

            // temp list of interviews for this date
            SortedListInterface<Interview> dayList = new SortedDoublyLinkedList<>();
            for (int i = 0; i < allToShow.getSize(); i++) {
                Interview iv = allToShow.viewDataAtIndex(i);
                if (iv.getInterviewDateTime().toLocalDate().equals(date)) {
                    dayList.addAtBack(iv);
                }
            }

            while (dayList.getSize() > 0) {
                // find earliest
                Interview earliest = dayList.viewDataAtIndex(0);
                int idx = 0;
                for (int k = 1; k < dayList.getSize(); k++) {
                    Interview cand = dayList.viewDataAtIndex(k);
                    if (cand.getInterviewDateTime().isBefore(earliest.getInterviewDateTime())) {
                        earliest = cand;
                        idx = k;
                    }
                }

                // i set the interview duration to 5 mins (for demo)
                // to simulate one hr interview, change the plusMinutes(5) to plusHours(1)
                InterviewUI.viewInterviewBody(earliest.getInterviewID(), earliest.getApplication().getApplicationID(), earliest.getInterviewType(),
                        earliest.getApplication().getJob().getTitle(), earliest.getApplication().getApplicant().getName(),
                        earliest.getInterviewDateTime(), earliest.getInterviewDateTime().plusMinutes(5),
                        earliest.getApplication().getJob().getCompany().getCompanyName()
                );

                // remove from dayList
                dayList.removeByIndex(idx);
            }

            InterviewUI.viewInterviewFooter();
        }
        MainMenuUI.pressEnterToContinue();
    }

    private static boolean belongsToUser(Interview iv, Object user) {
        if (user instanceof Company) {
            return iv.getApplication().getJob().getCompany().equals(user);
        } else if (user instanceof Applicant) {
            return iv.getApplication().getApplicant().equals(user);
        }
        return false;
    }

    public static void updateInterview(Company company) {
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
                updateOrNot = updateInterviewDetails(company);
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

    private static boolean updateInterviewDetails(Company company) {
        int current = 0;
        Interview interview = new Interview();
        String interviewType = "";
        String dateTimeStr;
        LocalDateTime currentDateTime = null;
        ScheduleManager manager = ScheduleManager.getInstance();
        //LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime scheduledTime = null;
        InterviewSchedule schedule = new InterviewSchedule();
        do {
            switch (current) {
                case 0:
                    String interviewId = InterviewUI.promptInterviewId();
                    if (interviewId == null || interviewId.equals("-999") || interviewId.equals("-111")) {
                        return false;
                    }
                    // find interview using id
                    schedule = manager.getCurrentSchedule();
                    interview = schedule.findInterviewById(interviewId.toUpperCase());
                    if (interview == null) {
                        interview = findInterviewInApplications(company, interviewId);
                    }
                    if (interview == null) {
                        InterviewUI.printInterviewIdNotFound();
                        continue;
                    }
//                    scheduledTime = interview.getInterviewDateTime();
//                    if (scheduledTime != null) {
//                        LocalDate interviewDate = scheduledTime.toLocalDate();
//                        if (!today.isBefore(interviewDate)) {
//                            InterviewUI.printCannotUpdateInterview();
//                            continue;
//                        }
//                    }
                    
                    /*
                     i set the time for allow updates to 1 min b4 the interview for smooth demo.
                     to simulate one calender day, uncomment the code above, and the 
                     LocalDate today = LocalDate.now(); before the loop, then comment the code below
                     */
                    scheduledTime = interview.getInterviewDateTime();
                    if (scheduledTime != null) {
                        if (!now.isBefore(scheduledTime.minusMinutes(1))) {
                            InterviewUI.printCannotUpdateInterview();
                            MainMenuUI.pressEnterToContinue();
                            continue;
                        }
                    }
                    InterviewUI.displayInterviewDetails(interview);
                    
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

                    //LocalDateTime interviewEndTime = currentDateTime.plusHours(1);
                    // check for scheduling conflict using global schedule in ScheduleManager
//                    if (checkForConflict(currentDateTime, currentDateTime.plusHours(1),
//                            company, interview.getApplication().getApplicant().getUserID())) {
//                        InterviewUI.printConflictTimeError();
//                        current = 2;
//                        break;
//                    }
                    
                     /*
                     i set the duration for interview to 5 mins for smooth demo.
                     to simulate one hour, uncomment the code above, then comment the code below
                     */
                    if (checkForConflict(currentDateTime, currentDateTime.plusMinutes(5),
                            company, interview.getApplication().getApplicant().getUserID(),
                            interview.getInterviewID())) {
                        InterviewUI.printConflictTimeError();
                        current = 2;
                        break;
                    }
                    
                    
//                    // ensure the new interview date is at least one calendar day ahead
//                    LocalDate newInterviewDate = currentDateTime.toLocalDate();
//                    if (!newInterviewDate.isAfter(today)) {
//                        System.err.printf("%50s %s", " ", "Interview date must be at least one calendar day ahead\n");
//                        current = 2;
//                        break;
//                    }
                    
                    // ensure the new interview time is at least 5 minutes in the future
                    // i allow modification until 1 min before the interview (for demo)
                    // to simulate one calendar day, use the above commented code, and comment this code below
                    if (!currentDateTime.isAfter(LocalDateTime.now().plusMinutes(5))) {
                        System.err.printf("%50s %s", " ",
                                "Interview must be scheduled at least 5 minutes from now\n");
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
                            schedule = manager.getCurrentSchedule();
                            if (schedule != null
                                    && schedule.findInterviewById(interview.getInterviewID()) == null) {
                                schedule.addInterview(interview);
                            }
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

    public static void forfeitInterview(Applicant applicant) {
        boolean continueOrNot = true;

        while (continueOrNot) {
            utility1.clearScreen();
            InterviewUI.interviewLogo();

            ScheduleManager manager = ScheduleManager.getInstance();
            InterviewSchedule schedule = manager.getCurrentSchedule();
            if (schedule == null) {
                InterviewUI.displayNoInterview();
                MainMenuUI.pressEnterToContinue();
                utility1.clearScreen();
                return;
            } else {
                String interviewId = InterviewUI.promptForfeitInterviewId();
                if (interviewId == null || interviewId.equalsIgnoreCase("Q")) {
                    return;
                }

                Interview interview = schedule.findInterviewById(interviewId.toUpperCase());
                if (interview == null) {
                    interview = findInterviewInApplicationsForApplicant(applicant, interviewId);
                }

                if (interview == null) {
                    InterviewUI.printInterviewIdNotFound();
                    MainMenuUI.pressEnterToContinue();
                    return;
                }

                if (!interview.getApplication().getApplicant().equals(applicant)) {
                    InterviewUI.wrongForfeitAttemptError();
                    MainMenuUI.pressEnterToContinue();
                    return;
                }

//                LocalDateTime scheduledTime = interview.getInterviewDateTime();
//                if (scheduledTime != null) {
//                    LocalDate today = LocalDate.now();
//                    LocalDate interviewDate = scheduledTime.toLocalDate();
//                    if (!today.isBefore(interviewDate)) {
//                        InterviewUI.printCannotUpdateInterview();
//                        MainMenuUI.pressEnterToContinue();
//                        return;
//                    }
//                }
                // i allow modification until 1 min before the interview (for demo)
                // to simulate one calendar day, use the above commented code, and comment this code below
                LocalDateTime scheduledTime = interview.getInterviewDateTime();
                if (scheduledTime != null) {
                    LocalDateTime now = LocalDateTime.now();
                    if (!now.isBefore(scheduledTime.minusMinutes(1))) {
                        InterviewUI.printCannotUpdateInterview();
                        MainMenuUI.pressEnterToContinue();
                        continue;
                    }
                }

                InterviewUI.displayInterviewDetails(interview);
                while (true) {
                    String cfm = InterviewUI.promptConfirmation();
                    if (cfm.equalsIgnoreCase("n")) {
                        break;
                    } else if (cfm.equalsIgnoreCase("y")) {
                        removeApplication(interview);
                        schedule.removeInterview(interview.getInterviewID());
                        InterviewUI.displayForfeitMessage();
                        MainMenuUI.pressEnterToContinue();
                        break;
                    } else if (cfm.equalsIgnoreCase("q")) {
                        continueOrNot = false; // Exit the search loop
                        break;
                    } else {
                        InterviewUI.printErrorOption();
                    }
                }
            }
        }
    }

    private static void removeApplication(Interview interview) {
        interview.cancel();
        interview.setInterviewType("");
        interview.setInterviewDateTime(null);

        // set application status to rejected and remove interview reference
        Application app = interview.getApplication();
        app.setStatus("Rejected");
        app.setInterview(null);
        removeAppFromJobAndApplicant(app);
    }

    private static Interview findInterviewInApplicationsForApplicant(Applicant applicant, String interviewId) {
        SortedListInterface<Application> apps = applicant.getApplication();
        for (int i = 0; i < apps.getSize(); i++) {
            Application a = apps.viewDataAtIndex(i);
            Interview iv = a.getInterview();
            if (iv != null && interviewId.equalsIgnoreCase(iv.getInterviewID())) {
                return iv;
            }
        }
        return null;
    }

    public static void removeInterview(Company company) {
        boolean continueOrNot = true;
        boolean deleteOrNot;

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
                deleteOrNot = configureRemoval(schedule, company);
                if (!deleteOrNot) {
                    continueOrNot = false;
                    break;
                }
            }

            if (deleteOrNot) {
                while (true) {
                    String response = InterviewUI.promptDeleteOrNot();
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

    private static boolean configureRemoval(InterviewSchedule schedule, Company company) {
        int current = 0;
        Interview interview = new Interview();
        String option = "";
        do {
            switch (current) {
                case 0:
                    String interviewId = InterviewUI.promptRemoveInterviewId();
                    if (interviewId == null || interviewId.equals("-999") || interviewId.equals("-111")) {
                        return false;
                    }
                    interview = schedule.findInterviewById(interviewId.toUpperCase());
                    if (interview == null) {
                        interview = findInterviewInApplications(company, interviewId);
                    }

                    if (interview == null) {
                        InterviewUI.printInterviewIdNotFound();
                        continue;
                    }
//                    LocalDateTime scheduledTime = interview.getInterviewDateTime();
//                    if (scheduledTime != null) {
//                        LocalDate today = LocalDate.now();
//                        LocalDate interviewDate = scheduledTime.toLocalDate();
//                        if (!today.isBefore(interviewDate)) {
//                            InterviewUI.printCannotUpdateInterview();
//                            continue;
//                        }
//                    }

                    // i allow modification until 1 min before the interview (for demo)
                    // to simulate one calendar day, use the above commented code, and comment this code below
                    LocalDateTime scheduledTime = interview.getInterviewDateTime();
                    if (scheduledTime != null) {
                        LocalDateTime now = LocalDateTime.now();
                        if (!now.isBefore(scheduledTime.minusMinutes(1))) {
                            InterviewUI.printCannotUpdateInterview();
                            continue;
                        }
                    }
                    InterviewUI.displayInterviewDetails(interview);
                    current = 1;
                    break;
                case 1:
                    InterviewUI.dashLine();
                    option = InterviewUI.promptCancellationOption();
                    if (option == null) {
                        break;
                    } else if (option.equals("-999")) {
                        return false;
                    } else if (option.equals("-111")) {
                        current = 0;
                        break;
                    }
                    current = 2;
                    break;
                case 2:
                    InterviewUI.dashLine();
                    String cfm;
                    do {
                        cfm = InterviewUI.promptConfirmation();
                        if (cfm.equals("y")) {
                            performCancellation(interview, schedule, option);
                            current = 3;
                            break;
                        } else if (cfm.equals("n")) {
                            current = 1;
                            break;
                        } else if (cfm.equals("q")) {
                            return false;
                        } else {
                            InterviewUI.printErrorOption();
                        }
                    } while (!cfm.equals("y") && !cfm.equals("n"));
            }
        } while (current != 3);
        return true;
    }

    private static void performCancellation(Interview interview, InterviewSchedule schedule, String option) {
        switch (option) {
            case "A":
                interview.cancel();
                interview.setInterviewType("-");
                interview.setInterviewDateTime(null);
                //removeInterviewFromSchedule(schedule, interview.getInterviewID());
                boolean removedA = schedule.removeInterview(interview.getInterviewID());
                if (removedA) {
                    InterviewUI.displayRemoveInterviewMessage(interview.getApplication().getApplicant().getName());
                }
                break;
            case "B":
                removeApplication(interview);
                boolean removedB = schedule.removeInterview(interview.getInterviewID());
                if (removedB) {
                    InterviewUI.displayRejecteApplicationMessage(interview.getApplication().getApplicant().getName());
                }
                break;
        }
        MainMenuUI.pressEnterToContinue();
    }

    // remove an application from both its job and its applicant
    private static void removeAppFromJobAndApplicant(Application app) {
        // Remove from Job
        SortedListInterface<Application> jobApps = app.getJob().getApplication();
        for (int i = 0; i < jobApps.getSize(); i++) {
            if (jobApps.viewDataAtIndex(i).getApplicationID() == app.getApplicationID()) {
                jobApps.removeByIndex(i);
                break;
            }
        }

        // Remove from Applicant
        SortedListInterface<Application> applicantApps = app.getApplicant().getApplication();
        for (int i = 0; i < applicantApps.getSize(); i++) {
            if (applicantApps.viewDataAtIndex(i).getApplicationID() == app.getApplicationID()) {
                applicantApps.removeByIndex(i);
                break;
            }
        }
    }

    public static void viewInterviewResults(Company company) {
        utility1.clearScreen();
        InterviewUI.interviewLogo();
        InterviewUI.quitAndBackGuide();

        ScheduleManager manager = ScheduleManager.getInstance();
        InterviewSchedule schedule = manager.getCurrentSchedule();
        if (schedule == null || schedule.getScheduledInterviews().getSize() == 0) {
            InterviewUI.displayNoInterview();
            SortedListInterface<InterviewResult> allResults = manager.getAllResults();
            SortedListInterface<InterviewResult> sortedResults
                    = getCompanyResultsSortedByScore(allResults, company);
            printResultSummary(sortedResults);
            MainMenuUI.pressEnterToContinue();
            return;
        }

        // gather all passed interviews for this company (time < now)
        SortedListInterface<Interview> passed = new SortedDoublyLinkedList<>();
        LocalDateTime now = LocalDateTime.now();
        SortedListInterface<Interview> all = schedule.getScheduledInterviews();
        InterviewUI.interviewTableLabel();
        for (int i = 0; i < all.getSize(); i++) {
            Interview iv = all.viewDataAtIndex(i);
            // only this company's
            if (!iv.getApplication().getJob().getCompany().equals(company)) {
                continue;
            }
            LocalDateTime t = iv.getInterviewDateTime();
            if (t != null && t.isBefore(now)) {
                passed.addAtBack(iv);
                InterviewUI.viewInterviewBody(iv.getInterviewID(), iv.getApplication().getApplicationID(),
                        iv.getInterviewType(), iv.getApplication().getJob().getTitle(),
                        iv.getApplication().getApplicant().getName(), iv.getInterviewDateTime(),
                        iv.getInterviewDateTime().plusMinutes(5),
                        company.getCompanyName());
            }

//            // replace the if condition above to this to omit the business rule->only can rank after interview ends)
//            if (t != null) {
//                passed.addAtBack(iv);
//                InterviewUI.viewInterviewBody(iv.getInterviewID(), iv.getApplication().getApplicationID(),
//                        iv.getInterviewType(), iv.getApplication().getJob().getTitle(),
//                        iv.getApplication().getApplicant().getName(), iv.getInterviewDateTime(),
//                        iv.getInterviewDateTime().plusMinutes(5),
//                        company.getCompanyName());
//            }
        }
        InterviewUI.viewInterviewFooter();

        if (passed.isEmpty()) {
            InterviewUI.printNoFinishedInterview();
            SortedListInterface<InterviewResult> allResults = manager.getAllResults();
            SortedListInterface<InterviewResult> sortedResults
                    = getCompanyResultsSortedByScore(allResults, company);
            printResultSummary(sortedResults);

            MainMenuUI.pressEnterToContinue();
            return;
        }

        // prompt scores and build global results
        int score = 0;
        while (passed.getSize() > 0) {
            Interview iv = passed.viewDataAtIndex(0);
            Applicant appl = iv.getApplication().getApplicant();
            Job job = iv.getApplication().getJob();

            InterviewUI.printCurrentFinishInterview(iv.getInterviewID(), appl.getName(), job.getTitle());
            score = gettingScores();
            if (score == -999) {
                break;
            }

            // record result
            InterviewResult result = new InterviewResult(iv, score);
            manager.getAllResults().addWithSort(result);

            // remove from schedule and passed
            schedule.removeInterview(iv.getInterviewID());
            passed.removeByIndex(0);
        }

        // for each job in this company, pick top 2 scorers >70
        SortedListInterface<InterviewResult> results = manager.getAllResults();
        if (!results.isEmpty()) {
            for (int j = 0; j < company.getJob().getSize(); j++) {
                Job job = company.getJob().viewDataAtIndex(j);
                int hires = 0;
                InterviewResult best1 = null, best2 = null;

                // find top two
                for (int r = 0; r < results.getSize(); r++) {
                    InterviewResult ir = results.viewDataAtIndex(r);
                    Interview iv = ir.getInterview();
                    if (!iv.getApplication().getJob().equals(job)) {
                        continue;
                    }
                    if (ir.getTotalScore() <= 70) {
                        continue;
                    }
                    if (best1 == null || ir.getTotalScore() > best1.getTotalScore()) {
                        best2 = best1;
                        best1 = ir;
                    } else if (best2 == null || ir.getTotalScore() > best2.getTotalScore()) {
                        best2 = ir;
                    }
                }

                // hire best1 & best2 if present
                if (best1 != null) {
                    Application a1 = best1.getInterview().getApplication();
                    a1.setStatus("Hired");
                    hires++;
                }
                if (best2 != null) {
                    Application a2 = best2.getInterview().getApplication();
                    a2.setStatus("Hired");
                    hires++;
                }

                // reject all other candidates for this job
                for (int r = 0; r < results.getSize(); r++) {
                    InterviewResult ir = results.viewDataAtIndex(r);
                    Interview iv = ir.getInterview();
                    Application app = iv.getApplication();
                    if (!iv.getApplication().getJob().equals(job)) {
                        continue;
                    }
                    if (("Hired".equals(app.getStatus()))) {
                        continue;
                    }
                    // reject and dequeue
                    app.setStatus("Rejected");
                    removeAppFromJobAndApplicant(app);
                }

                // update job status if fully recruited
                if (hires >= 2) {
                    job.setStatus(0);
                }
            }

            SortedListInterface<InterviewResult> allResults = manager.getAllResults();
            SortedListInterface<InterviewResult> sortedResults
                    = getCompanyResultsSortedByScore(allResults, company);

            printResultSummary(sortedResults);
        }
        MainMenuUI.pressEnterToContinue();
    }

    private static int gettingScores() {
        int current = 0;
        int cScore = 0;
        int tScore = 0;
        int finalScore = 0;
        do {
            switch (current) {
                case 0:
                    try {
                    tScore = InterviewUI.promptScore("technical");

                    if (tScore == 0) {
                        InterviewUI.invalidScore("Technical");
                        continue;
                    } else if (tScore == -999 || tScore == -111) {
                        return -999;
                    } else if (tScore > 50) {
                        InterviewUI.invalidScore("technical");
                        continue;
                    }
                    current = 1;
                    break;
                } catch (NumberFormatException e) {
                    InterviewUI.printErrorOption();
                    continue;
                }
                case 1:
                    try {
                    cScore = InterviewUI.promptScore("communication");

                    if (cScore == 0) {
                        InterviewUI.invalidScore("communication");
                        continue;
                    } else if (cScore == -999) {
                        return -999;
                    } else if (cScore == -111) {
                        current = 0;
                        break;
                    } else if (cScore > 50) {
                        InterviewUI.invalidScore("communication");
                        continue;
                    }
                    current = 2;
                    break;
                } catch (NumberFormatException e) {
                    InterviewUI.printErrorOption();
                    continue;
                }
                case 2:
                    InterviewUI.dashLine();
                    String cfm;
                    do {
                        cfm = InterviewUI.promptConfirmation();
                        if (cfm.equals("y")) {
                            finalScore = tScore + cScore;
                            current = 3;
                            break;
                        } else if (cfm.equals("n")) {
                            current = 1;
                            break;
                        } else if (cfm.equals("q")) {
                            return -999;
                        } else {
                            InterviewUI.printErrorOption();
                        }
                    } while (!cfm.equals("y") && !cfm.equals("n"));
            }
        } while (current != 3);
        return finalScore;
    }

    // filters the full list of InterviewResult to only those for the specified company, then returns them sorted by totalScore descending
    private static SortedListInterface<InterviewResult> getCompanyResultsSortedByScore(SortedListInterface<InterviewResult> allResults, Company company) {
        // filter
        SortedListInterface<InterviewResult> companyResults = new SortedDoublyLinkedList<>();
        for (int i = 0; i < allResults.getSize(); i++) {
            InterviewResult r = allResults.viewDataAtIndex(i);
            Company c = r.getInterview().getApplication().getJob().getCompany();
            if (c.equals(company)) {
                companyResults.addAtBack(r);
            }
        }

        // sort by totalScore descending
        SortedListInterface<InterviewResult> sorted = new SortedDoublyLinkedList<>();
        while (companyResults.getSize() > 0) {
            InterviewResult best = companyResults.viewDataAtIndex(0);
            int bestIdx = 0;
            for (int j = 1; j < companyResults.getSize(); j++) {
                InterviewResult cand = companyResults.viewDataAtIndex(j);
                if (cand.getTotalScore() > best.getTotalScore()) {
                    best = cand;
                    bestIdx = j;
                }
            }
            sorted.addAtBack(best);
            companyResults.removeByIndex(bestIdx);
        }
        return sorted;
    }

    public static void printResultSummary(SortedListInterface<InterviewResult> results) {
        InterviewUI.interviewResultSummaryHeading();

        for (int i = 0; i < results.getSize(); i++) {
            InterviewResult r = results.viewDataAtIndex(i);
            Interview iv = r.getInterview();
            Application app = iv.getApplication();
            String status = app.getStatus();

            InterviewUI.interviewResultSummaryBody(iv.getInterviewID(), app.getApplicant().getUserID(),
                    app.getApplicant().getName(), app.getJob().getTitle(), r.getTotalScore(), status);
        }

        InterviewUI.interviewResultSummaryFooter();
    }

    public static void interviewReport(SortedListInterface<Company> companies) {
        utility1.clearScreen();

        ScheduleManager manager = ScheduleManager.getInstance();
        InterviewSchedule schedule = manager.getCurrentSchedule();
        SortedListInterface<InterviewResult> allResults = manager.getAllResults();
        SortedListInterface<Job> jobs = new SortedDoublyLinkedList<>();

        InterviewUI.interviewReportHeading();
        // build sorted list of all companies involved
        // From scheduled interviews:
        if (schedule != null) {
            SortedListInterface<Interview> scheduled = schedule.getScheduledInterviews();
            for (int i = 0; i < scheduled.getSize(); i++) {
                Company c = scheduled.viewDataAtIndex(i)
                        .getApplication().getJob().getCompany();
                boolean seen = false;
                for (int j = 0; j < companies.getSize(); j++) {
                    if (companies.viewDataAtIndex(j).equals(c)) {
                        seen = true;
                        break;
                    }
                }
                if (!seen) {
                    companies.addWithSort(c);
                }
            }
        }

        // From completed results:
        for (int i = 0; i < allResults.getSize(); i++) {
            Company c = allResults.viewDataAtIndex(i)
                    .getInterview().getApplication().getJob().getCompany();
            boolean seen = false;
            for (int j = 0; j < companies.getSize(); j++) {
                if (companies.viewDataAtIndex(j).equals(c)) {
                    seen = true;
                    break;
                }
            }
            if (!seen) {
                companies.addWithSort(c);
            }
        }

        // 1. Compute success rate per company
        SortedListInterface<Double> successRates = new SortedDoublyLinkedList<>();
        for (int i = 0; i < companies.getSize(); i++) {
            Company comp = companies.viewDataAtIndex(i);
            int total = 0, hired = 0;
            for (int r = 0; r < allResults.getSize(); r++) {
                InterviewResult ir = allResults.viewDataAtIndex(r);
                Application app = ir.getInterview().getApplication();
                if (app.getJob().getCompany().equals(comp)) {
                    total++;
                    if ("Hired".equals(app.getStatus())) {
                        hired++;
                    }
                }
            }
            double rate = (total > 0) ? (hired * 100.0 / total) : 0.0;
            successRates.addAtBack(rate);
        }

        printSuccessRateTable(companies, successRates);

        // 2. compute success rates & average scores per job
        SortedListInterface<Double> avgAllScores = new SortedDoublyLinkedList<>();
        SortedListInterface<Double> avgHiredScores = new SortedDoublyLinkedList<>();
        SortedListInterface<Double> avgRejectedScores = new SortedDoublyLinkedList<>();

        for (int j = 0; j < jobs.getSize(); j++) {
            Job job = jobs.viewDataAtIndex(j);
            // gather all results for this job
            SortedListInterface<InterviewResult> jobResults = new SortedDoublyLinkedList<>();
            for (int r = 0; r < allResults.getSize(); r++) {
                InterviewResult ir = allResults.viewDataAtIndex(r);
                if (ir.getInterview().getApplication().getJob().equals(job)) {
                    jobResults.addAtBack(ir);
                }
            }
            int total = jobResults.getSize();
            int hiredCount = 0, rejectedCount = 0;
            int sumAll = 0, sumHired = 0, sumRejected = 0;

            for (int r = 0; r < jobResults.getSize(); r++) {
                InterviewResult ir = jobResults.viewDataAtIndex(r);
                int score = ir.getTotalScore();
                sumAll += score;
                Application app = ir.getInterview().getApplication();
                if ("Hired".equals(app.getStatus())) {
                    hiredCount++;
                    sumHired += score;
                } else {
                    rejectedCount++;
                    sumRejected += score;
                }
            }

            successRates.addAtBack(total > 0 ? (hiredCount * 100.0 / total) : 0.0);
            avgAllScores.addAtBack(total > 0 ? (sumAll * 1.0 / total) : 0.0);
            avgHiredScores.addAtBack(hiredCount > 0 ? (sumHired * 1.0 / hiredCount) : 0.0);
            avgRejectedScores.addAtBack(rejectedCount > 0 ? (sumRejected * 1.0 / rejectedCount) : 0.0);
        }

        // 3. next 3 upcoming interviews globally
        SortedListInterface<Interview> upcoming = new SortedDoublyLinkedList<>();
        LocalDateTime now = LocalDateTime.now();
        if (schedule != null) {
            for (int i = 0; i < schedule.getScheduledInterviews().getSize(); i++) {
                Interview iv = schedule.getScheduledInterviews().viewDataAtIndex(i);
                if (iv.getInterviewDateTime().isAfter(now)) {
                    upcoming.addAtBack(iv);
                }
            }
        }
        // date sort and pick first three
        SortedListInterface<Interview> nextThree = new SortedDoublyLinkedList<>();
        while (upcoming.getSize() > 0 && nextThree.getSize() < 3) {
            Interview earliest = upcoming.viewDataAtIndex(0);
            int idx = 0;
            for (int k = 1; k < upcoming.getSize(); k++) {
                Interview cand = upcoming.viewDataAtIndex(k);
                if (cand.getInterviewDateTime().isBefore(earliest.getInterviewDateTime())) {
                    earliest = cand;
                    idx = k;
                }
            }
            nextThree.addAtBack(earliest);
            upcoming.removeByIndex(idx);
        }

        // 4 & 5. bar‐chart data
        int hiredCount = 0, rejectedCount = 0;
        for (int r = 0; r < allResults.getSize(); r++) {
            Application app = allResults.viewDataAtIndex(r)
                    .getInterview().getApplication();
            if ("Hired".equals(app.getStatus())) {
                hiredCount++;
            } else {
                rejectedCount++;
            }
        }

        // buckets for upcoming interviews
        int todayCount = 0, tomorrowCount = 0, laterCount = 0;
        LocalDate today = LocalDate.now();
        for (int i = 0; i < upcoming.getSize(); i++) {
            LocalDate d = upcoming.viewDataAtIndex(i).getInterviewDateTime().toLocalDate();
            if (d.equals(today)) {
                todayCount++;
            } else if (d.equals(today.plusDays(1))) {
                tomorrowCount++;
            } else if (d.isAfter(today.plusDays(1))) {
                laterCount++;
            }
        }
        printSuccessRateTable(companies, successRates);
        printAverageScoreTable(jobs, avgAllScores, avgHiredScores, avgRejectedScores);
        printUpcomingInterviews(nextThree);
        printBarChart("", todayCount, tomorrowCount, laterCount);
        printBarChart("REJECTED VS HIRED AFTER INTERVIEW", rejectedCount, hiredCount);

        MainMenuUI.pressEnterToContinue();
    }

    private static void printSuccessRateTable(SortedListInterface<Company> companies, SortedListInterface<Double> successRates) {
        int limit = Math.min(5, companies.getSize());
        InterviewUI.successRateHeading();
        for (int i = 0; i < limit; i++) {
            Company c = companies.viewDataAtIndex(i);
            double rate = successRates.viewDataAtIndex(i);
            InterviewUI.successRateBody(c.getCompanyId(), c.getCompanyName(), rate);
        }
        InterviewUI.successRateFooter();
        InterviewUI.interviewReportDashLine();
    }

    private static void printAverageScoreTable(SortedListInterface<Job> jobs, SortedListInterface<Double> avgAll, SortedListInterface<Double> avgHired, SortedListInterface<Double> avgRejected) {
        int limit = Math.min(5, jobs.getSize());
        InterviewUI.avgScoreHeading();
        for (int i = 0; i < limit; i++) {
            Job job = jobs.viewDataAtIndex(i);
            double all = avgAll.viewDataAtIndex(i);
            double hired = avgHired.viewDataAtIndex(i);
            double rej = avgRejected.viewDataAtIndex(i);

            // show '-' if there was no data (0.0)
            String sAll = all > 0.0 ? String.format("%.1f", all) : "-";
            String sHired = hired > 0.0 ? String.format("%.1f", hired) : "-";
            String sRej = rej > 0.0 ? String.format("%.1f", rej) : "-";

            InterviewUI.avgScoreBody(job.getJobID(), job.getTitle(), sAll, sHired, sRej);
        }
        InterviewUI.avgScoreFooter();
        InterviewUI.interviewReportDashLine();
    }

    private static void printUpcomingInterviews(SortedListInterface<Interview> upcoming) {
        int limit = upcoming.getSize();
        InterviewUI.upcomingInterviewHeading();
        for (int i = 0; i < limit; i++) {
            Interview iv = upcoming.viewDataAtIndex(i);
            InterviewUI.upcomingInterviewBody(iv.getInterviewDateTime().toLocalDate(), iv.getInterviewID(),
                    iv.getApplication().getApplicationID(), iv.getInterviewType(), iv.getApplication().getJob().getTitle(),
                    iv.getApplication().getApplicant().getName(), iv.getInterviewDateTime(),
                    iv.getInterviewDateTime().plusMinutes(5), iv.getApplication().getJob().getCompany().getCompanyName());
        }
        InterviewUI.upcomingInterviewFooter();
    }

    // ... to pass variable arguments - pass any number of int arguments 
    private static void printBarChart(String title, int... counts) {
        // derive labels from argument count
        String[] labels;
        if (counts.length == 2) {
            labels = new String[]{"Rejected", "Hired"};
        } else if (counts.length == 3) {
            labels = new String[]{"Today", "Tomorrow", "2 days+"};
        } else {
            labels = new String[counts.length];
            for (int i = 0; i < labels.length; i++) {
                labels[i] = "Series" + (i + 1);
            }
        }

        // find max value for scaling
        int max = 0;
        for (int v : counts) {
            if (v > max) {
                max = v;
            }
        }
        if (max == 0) {
            max = 1;  // avoid divide by zero
        }

        InterviewUI.drawBarChart(title, counts, labels, max);
        InterviewUI.interviewReportDashLine();
    }

    private static Interview findInterviewInApplications(Company company, String interviewId) {
        for (int i = 0; i < company.getJob().getSize(); i++) {
            Job job = company.getJob().viewDataAtIndex(i);
            SortedListInterface<Application> apps = job.getApplication();
            if (apps == null) {
                continue;
            }
            for (int j = 0; j < apps.getSize(); j++) {
                Application app = apps.viewDataAtIndex(j);
                Interview intv = app.getInterview();
                if (intv != null && interviewId.equalsIgnoreCase(intv.getInterviewID())) {
                    return intv;
                }
            }
        }
        return null;
    }

    // validate date and time format and business rules
    private static boolean isValidInterviewDateTime(String dateTimeStr) {
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
//        if (!time.toLocalDate().isAfter(now.toLocalDate())) {
//            return false;
//        }
        // must be more than 5 minutes in the future 
        //(for demo purposes, i used 5 mins instead of after one calendar to simulate the ranking easier)
        // if want to use one calendar day, comment the if condition below and use the above one
        if (!time.isAfter(now.plusMinutes(5))) {
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
    private static boolean checkForConflict(LocalDateTime start, LocalDateTime end, Company company, String applicantId, String interviewId) {
        ScheduleManager manager = ScheduleManager.getInstance();
        if (manager.getCurrentSchedule() != null) {
            return manager.getCurrentSchedule().hasTimeConflict(start, end, company, applicantId, interviewId);
        }
        return false;
    }
}