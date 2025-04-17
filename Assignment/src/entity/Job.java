/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;

/**
 *
 * @author Acer
 */
public class Job implements Comparable<Job> {

    private String jobID;

    private String title;
    private String desc;
    private SortedListInterface<String> reqSkill = new SortedDoublyLinkedList<>();

    private SortedListInterface<String> reqEdLevel = new SortedDoublyLinkedList<>();
    private SortedListInterface<String> fos = new SortedDoublyLinkedList<>();
    private SortedListInterface<String> language = new SortedDoublyLinkedList<>();
    private int salary;
    private SortedListInterface<Application> application = new SortedDoublyLinkedList<>();
    private String location;
    private Company company;
    private int status;

    public Job() {
    }

    public Job(String jobID, String title, String desc, SortedListInterface<String> reqSkill, SortedListInterface<String> reqEdLevel, SortedListInterface<String> fos, SortedListInterface<String> language, int salary, String location, SortedListInterface<Application> application) {
        this.jobID = jobID;
        this.title = title;
        this.desc = desc;
        this.reqSkill = reqSkill;
        this.reqEdLevel = reqEdLevel;
        this.fos = fos;
        this.language = language;
        this.salary = salary;
        this.location = location;
        this.application = application;
        
    }

    public Job(Company company, String jobID, String title, String desc, SortedListInterface<String> reqSkill, SortedListInterface<String> reqEdLevel, SortedListInterface<String> fos, SortedListInterface<String> language, int salary, String location, SortedListInterface<Application> application, int status) {
        this.jobID = jobID;
        this.title = title;
        this.desc = desc;
        this.reqSkill = reqSkill;
        this.reqEdLevel = reqEdLevel;
        this.fos = fos;
        this.language = language;
        this.salary = salary;
        this.location = location;
        this.application = application;
        this.company = company;
        this.status = status;
                
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public SortedListInterface<String> getReqSkill() {
        return reqSkill;
    }

    public void setReqSkill(SortedListInterface<String> reqSkill) {
        this.reqSkill = reqSkill;
    }

    public SortedListInterface<String> getReqEdLevel() {
        return reqEdLevel;
    }

    public void setReqEdLevel(SortedListInterface<String> reqEdLevel) {
        this.reqEdLevel = reqEdLevel;
    }

    public SortedListInterface<String> getFos() {
        return fos;
    }

    public void setFos(SortedListInterface<String> fos) {
        this.fos = fos;
    }

    public SortedListInterface<String> getLanguage() {
        return language;
    }

    public void setLanguage(SortedListInterface<String> language) {
        this.language = language;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public SortedListInterface<Application> getApplication() {
        return application;
    }

    public void setApplication(SortedListInterface<Application> application) {
        this.application = application;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

    @Override
    public String toString() {
        return "Job ID: " + jobID
                + ", Title: " + title
                + ", Description: " + desc
                + ", Required Skill: " + reqSkill
                + ", Education Level: " + reqEdLevel
                + ", Field of Study: " + fos
                + ", Language: " + language
                + ", Salary: " + salary
                + ", Location: " + location
                + ", Applications: " + application.getSize();
    }

    @Override
    public int compareTo(Job o) {
        return this.jobID.compareTo(o.jobID);
    }

}
