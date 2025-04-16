/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;
import java.time.LocalDateTime;

public class ScheduleManager implements Comparable<ScheduleManager> {

    private InterviewSchedule currentSchedule; // Manages scheduled interviews and slots  

    // singleton instance (let other parts of system retrieve the current interview schedule)
    private static ScheduleManager instance = new ScheduleManager();

    public static ScheduleManager getInstance() {
        return instance;
    }

    public InterviewSchedule getCurrentSchedule() {
        return currentSchedule;
    }

    // Replace the current schedule 
    public void setCurrentSchedule(InterviewSchedule currentSchedule) {
        this.currentSchedule = currentSchedule;
    }

    // check if the new interview time interval overlaps with existing one
    public boolean checkForConflict(LocalDateTime newStart, LocalDateTime newEnd) {
        if (currentSchedule != null) {
            return currentSchedule.hasTimeConflict(newStart, newEnd);
        }
        return false;
    }

    @Override
    public int compareTo(ScheduleManager other) {
        if (this.currentSchedule != null && other.currentSchedule != null) {
            return this.currentSchedule.getScheduleStartDate().compareTo(other.currentSchedule.getScheduleStartDate());
        } else if (this.currentSchedule == null && other.currentSchedule == null) {
            return 0;
        } else if (this.currentSchedule == null) {
            return -1; // missing schedule is considered less
        } else {
            return 1;
        }
    }

}
