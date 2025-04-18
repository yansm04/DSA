package entity;

import java.time.LocalDateTime;

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

    public void setResult(InterviewResult result) {
        this.result = result;
        this.status = "Completed"; // Auto-update status on result assignment
    }

    public void schedule(LocalDateTime time, String location, String interviewType) {
        this.interviewDateTime = time;
        this.interviewType = interviewType;
        this.status = "Scheduled";
    }

    public void reschedule(LocalDateTime newTime) {
        this.interviewDateTime = newTime;
    }

    public void cancel() {
        this.status = "Cancelled";
    }

    public boolean isCompleted() {
        return "Completed".equals(this.status);
    }

    public boolean isCancelled() {
        return "Cancelled".equals(this.status);
    }

    @Override
    public String toString() {
        return "Interview:" + 
                "\ninterviewID: " + interviewID + 
                "\napplication: " + application + 
                "\ninterviewDateTime: " + interviewDateTime + 
                "\nstatus: " + status + 
                "\ninterviewType: " + interviewType + 
                "\nresult: " + result;
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
