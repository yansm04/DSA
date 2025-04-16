/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import java.time.LocalDateTime;
import adt.SortedListInterface;

public class Interview implements Comparable<Interview> {

    private String interviewID;
    private Application application;
    private LocalDateTime interviewDateTime;
    private String status;
    private String interviewType;
    private InterviewResult result;

    public Interview() {
        this.interviewID = "";
        this.status = "";
    }

    public Interview(String interviewID, Application application, String interviewType, LocalDateTime interviewDateTime, String status) {
        this.interviewID = interviewID;
        this.application = application;
        this.status = status;
        this.interviewDateTime = interviewDateTime;
        this.interviewType = interviewType;
    }

    public String getInterviewID() {
        return interviewID;
    }

    public LocalDateTime getInterviewDateTime() {
        return interviewDateTime;
    }

    public String getStatus() {
        return status;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public InterviewResult getResult() {
        return result;
    }

    public Application getApplication() {
        return application;
    }

    public void setInterviewID(String interviewID) {
        this.interviewID = interviewID;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setInterviewDateTime(LocalDateTime interviewTime) {
        this.interviewDateTime = interviewTime;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Interview ID: " + interviewID;
//                + ", Time: " + String.format("%02d:%02d %s", hour, minute, period)
//                + ", Type: " + type
//                + ", Result: " + result
//                + ", Feedback: " + feedback;
    }

    @Override
    public int compareTo(Interview other) {
        return this.interviewID.compareTo(other.interviewID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Interview other = (Interview) obj;
        return this.interviewID != null && this.interviewID.equals(other.getInterviewID());
    }

    @Override
    public int hashCode() {
        return interviewID != null ? interviewID.hashCode() : 0;
    }

}
