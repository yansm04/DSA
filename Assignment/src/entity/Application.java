/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Acer
 */
public class Application {

    private String applicationID;
    private Applicant applicant;
    private Job job;
    private String status;
    private Interview interview;

    public Application(String applicationID, Applicant applicant, Job job, String status, Interview interview) {
        this.applicationID = applicationID;
        this.applicant = applicant;
        this.job = job;
        this.status = status;
        this.interview = interview;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    @Override
    public String toString() {
        return "ApplicationID: " + applicationID
                + "\nApplicant: " + applicant.getName()
                + "\nJob: " + job.getTitle()
                + "\nStatus: " + status
                + "\nInterview: " + (interview != null ? interview.toString() : "Not Scheduled");
    }
}
