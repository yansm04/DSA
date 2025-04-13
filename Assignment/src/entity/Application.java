/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Acer
 */
public class Application implements Comparable<Application> {

    private static int idCounter = 1000;
    private int applicationID;

    private Job job;
    private String status;
    private Applicant applicant;
    private Interview interview;

    public Application() {
    }

    public Application(Job job, Applicant applicant, String status, Interview interview) {
        this.applicationID = generateID();

        this.job = job;
        this.applicant = applicant;
        this.status = status;
        this.interview = interview;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public static int generateID() {
        return idCounter++;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
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
                + "\nJob: " + job
                + "\nStatus: " + status
                + "\nInterview: " + (interview != null ? interview.toString() : "Not Scheduled");
    }

    @Override
    public int compareTo(Application o) {
        return Integer.compare(this.applicationID, o.applicationID);
    }
}
