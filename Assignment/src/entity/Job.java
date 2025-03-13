/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.DoublyLinkedList;
import adt.ListInterface;

/**
 *
 * @author Acer
 */
public class Job {
    private String jobID;
    private String title;
    private String desc;
    private String reqSkill;
    private String reqEdLevel;
    private String fos;
    private String language;
    private int salary;
    private ListInterface<Application> application= new DoublyLinkedList<>();
    private String location;

    public Job(String jobID, String title, String desc, String reqSkill, String reqEdLevel, String fos, String language, int salary, String location, ListInterface<Application> application) {
        this.jobID = jobID;
        this.title = title;
        this.desc = desc;
        this.reqSkill = reqSkill;
        this.reqEdLevel = reqEdLevel;
        this.fos = fos;
        this.language = language;
        this.salary = salary;
        this.location = location;
        this.application=application;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReqSkill() {
        return reqSkill;
    }

    public void setReqSkill(String reqSkill) {
        this.reqSkill = reqSkill;
    }

    public String getReqEdLevel() {
        return reqEdLevel;
    }

    public void setReqEdLevel(String reqEdLevel) {
        this.reqEdLevel = reqEdLevel;
    }

    public String getFos() {
        return fos;
    }

    public void setFos(String fos) {
        this.fos = fos;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public ListInterface<Application> getApplication() {
        return application;
    }

    public void setApplication(ListInterface<Application> application) {
        this.application = application;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString() {
    return "Job ID: " + jobID +
           ", Title: " + title +
           ", Description: " + desc +
           ", Required Skill: " + reqSkill +
           ", Education Level: " + reqEdLevel +
           ", Field of Study: " + fos +
           ", Language: " + language +
           ", Salary: " + salary +
           ", Location: " + location +
           ", Applications: " + application.getSize();
}
    
    
}
