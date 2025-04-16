/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    // checks if the new interview's time interval overlaps with any scheduled interviews
    public boolean hasTimeConflict(LocalDateTime newStart, LocalDateTime newEnd) {
        for (int i = 0; i < scheduledInterviews.getSize(); i++) {
            Interview scheduled = scheduledInterviews.viewDataAtIndex(i);
            if (scheduled.getInterviewDateTime() != null) {
                LocalDateTime scheduledStart = scheduled.getInterviewDateTime();
                LocalDateTime scheduledEnd = scheduledStart.plusHours(1); // Assumption: 1 hour interview
                // check if the intervals overlap
                if (newStart.isBefore(scheduledEnd) && scheduledStart.isBefore(newEnd)) {
                    return true;
                }
            }
        }
        return false;
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
    @Override
    public String toString() {
        return "InterviewSchedule{"
                + "scheduledInterviews=" + scheduledInterviews
                + ", scheduleStartDate=" + scheduleStartDate
                + ", scheduleEndDate=" + scheduleEndDate
                + '}';
    }

}
