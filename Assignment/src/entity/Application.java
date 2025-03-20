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
    
    
    
    private String jobID;
    private String status;
    private String applicantID;
    private Interview interview;
    public Application(){}

    public Application( String jobID, String status, Interview interview) {
        this.applicationID = generateID();
        
        this.jobID = jobID;
        this.status = status;
        this.interview = interview;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    
    
    
    public static int generateID(){
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
                
                + "\nJob: " + jobID
                + "\nStatus: " + status
                + "\nInterview: " + (interview != null ? interview.toString() : "Not Scheduled");
    }

    @Override
    public int compareTo(Application o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
