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
public class Job implements Comparable<Job>{
    private String jobID;
   
    private String title;
    private String desc;
    private ListInterface<String> reqSkill = new DoublyLinkedList<>();
   
    private ListInterface<String> reqEdLevel = new DoublyLinkedList<>();
    private ListInterface<String> fos = new DoublyLinkedList<>();
    private ListInterface <String> language = new DoublyLinkedList<>();
    private int salary;
    private ListInterface<Application> application= new DoublyLinkedList<>();
    private String location;
    private Company company;
    
    public Job(){}

    public Job(String jobID, String title, String desc, ListInterface<String> reqSkill, ListInterface<String> reqEdLevel, ListInterface<String> fos, ListInterface<String> language, int salary, String location, ListInterface<Application> application) {
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
     public Job(Company company,String jobID, String title, String desc, ListInterface<String> reqSkill, ListInterface<String> reqEdLevel, ListInterface<String> fos, ListInterface<String> language, int salary, String location, ListInterface<Application> application) {
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
        this.company = company;
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

    public ListInterface<String> getReqSkill() {
        return reqSkill;
    }

    public void setReqSkill(ListInterface<String> reqSkill) {
        this.reqSkill = reqSkill;
    }

    public ListInterface<String> getReqEdLevel() {
        return reqEdLevel;
    }

    public void setReqEdLevel(ListInterface<String> reqEdLevel) {
        this.reqEdLevel = reqEdLevel;
    }

    public ListInterface<String> getFos() {
        return fos;
    }

    public void setFos(ListInterface<String> fos) {
        this.fos = fos;
    }

    public ListInterface<String> getLanguage() {
        return language;
    }

    public void setLanguage(ListInterface<String> language) {
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
    }//test

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

    @Override
    public int compareTo(Job o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
