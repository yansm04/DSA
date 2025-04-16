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
public class Applicant implements Comparable<Applicant> {

    private String userID;
    private String name;
    private String email;
    private int age;
    private SortedListInterface<String> skills = new SortedDoublyLinkedList<>();
    private SortedListInterface<String> fos = new SortedDoublyLinkedList<>();
    private SortedListInterface<String> education = new SortedDoublyLinkedList<>();
    private SortedListInterface<Application> application = new SortedDoublyLinkedList<>();
    private SortedListInterface<String> language = new SortedDoublyLinkedList<>();

    public Applicant(String userID, String name, String email, int age, SortedListInterface<String> skills, SortedListInterface<String> fos, SortedListInterface<String> education, SortedListInterface<Application> application,SortedListInterface<String> language) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.age = age;
        this.skills = skills;
        this.fos = fos;
        this.education = education;
        this.application = application;
        this.language = language;
    }

    public Applicant(String userID, String name, String email, int age, SortedListInterface<String> skills, SortedListInterface<String> fos, SortedListInterface<String> education,SortedListInterface<String> language) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.age = age;
        this.skills = skills;
        this.fos = fos;
        this.education = education;
        this.language = language;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SortedListInterface<String> getSkills() {
        return skills;
    }

    public void setSkills(SortedListInterface<String> skills) {
        this.skills = skills;
    }

    
    public SortedListInterface<String> getEducation() {
        return education;
    }

    public void setEducation(SortedListInterface<String> education) {
        this.education = education;
    }

    public SortedListInterface<Application> getApplication() {
        return application;
    }

    public void setApplication(SortedListInterface<Application> application) {
        this.application = application;
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
    

    @Override
    public int compareTo(Applicant other) {
        return this.userID.compareTo(other.userID); // Compare by userID
    }

    @Override
    public String toString() {
        return "UserID: " + userID + "\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Age: " + age + "\n"
                + "Skills: " + skills + "\n"
                + "Working Experience: " + fos + "\n"
                + "Education: " + education + "\n";
    }

}
