package entity;

import adt.SortedDoublyLinkedList;
import java.time.LocalDate;
import adt.SortedListInterface;
import java.time.LocalDateTime;

public class InterviewSchedule {

    private SortedListInterface<Interview> scheduledInterviews = new SortedDoublyLinkedList<>();
    private LocalDate scheduleStartDate;
    private LocalDate scheduleEndDate;

    public InterviewSchedule() {

    }

    public InterviewSchedule(LocalDate startDate, LocalDate endDate) {
        this.scheduleStartDate = startDate;
        this.scheduleEndDate = endDate;
    }

    public SortedListInterface<Interview> getScheduledInterviews() {
        return scheduledInterviews;
    }

    public LocalDate getScheduleStartDate() {
        return scheduleStartDate;
    }

    public LocalDate getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleStartDate(LocalDate startDate) {
        this.scheduleStartDate = startDate;
    }

    public void setScheduleEndDate(LocalDate endDate) {
        this.scheduleEndDate = endDate;
    }

    public void addInterview(Interview interview) {
        scheduledInterviews.addAtBack(interview);
    }

    public boolean removeInterview(String interviewId) {
        SortedListInterface<Interview> interviews = getScheduledInterviews();
        for (int i = 0; i < interviews.getSize(); i++) {
            Interview interview = interviews.viewDataAtIndex(i);
            if (interview.getInterviewID().equalsIgnoreCase(interviewId)) {
                interviews.removeByIndex(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean hasTimeConflict(
            LocalDateTime newStart,
            LocalDateTime newEnd,
            Company company,
            String applicantId,
            String ignoreInterviewId
    ) {
        for (int i = 0; i < scheduledInterviews.getSize(); i++) {
            Interview scheduled = scheduledInterviews.viewDataAtIndex(i);
            if (ignoreInterviewId != null && scheduled.getInterviewID().equalsIgnoreCase(ignoreInterviewId)) {
                continue; // cancelled or not set
            }
            LocalDateTime scheduledStart = scheduled.getInterviewDateTime();
            if (scheduledStart == null) {
                continue;
            }

            LocalDateTime scheduledEnd = scheduledStart.plusMinutes(5);
            // Check if this interview is for same company or same applicant
            boolean sameCompany = scheduled.getApplication().getJob().getCompany().equals(company);
            boolean sameApplicant = scheduled.getApplication().getApplicant().getUserID().equals(applicantId);
            if (sameCompany || sameApplicant) {
                // overlap check
                if (newStart.isBefore(scheduledEnd) && scheduledStart.isBefore(newEnd)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasTimeConflict(
            LocalDateTime newStart,
            LocalDateTime newEnd,
            Company company,
            String applicantId
    ) {
        return hasTimeConflict(newStart, newEnd, company, applicantId, null);
    }

    // keep for now
    public boolean hasTimeConflict(LocalDateTime newStart, LocalDateTime newEnd) {
        // delegate to the new signature, using a “global” sentinel
        return hasTimeConflict(newStart, newEnd, null, null);
    }

    // validate that the interview time is within the current schedule window
    public boolean isWithinSchedule(LocalDateTime time) {
        if (scheduleStartDate == null || scheduleEndDate == null) {
            // No window defined assume valid.
            return true;
        }
        LocalDate interviewDate = time.toLocalDate();
        // check the interview date is on/after scheduleStartDate & on/before scheduleEndDate
        return (!interviewDate.isBefore(scheduleStartDate)) && (!interviewDate.isAfter(scheduleEndDate));
    }

    // find interview using id
    public Interview findInterviewById(String interviewId) {
        for (int i = 0; i < scheduledInterviews.getSize(); i++) {
            Interview intv = scheduledInterviews.viewDataAtIndex(i);
            if (intv.getInterviewID().equals(interviewId)) {
                return intv;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "InterviewSchedule:"
                + "\nscheduledInterviews: " + scheduledInterviews
                + "\nscheduleStartDate: " + scheduleStartDate
                + "\nscheduleEndDate: " + scheduleEndDate;
    }

}
