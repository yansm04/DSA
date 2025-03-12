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
public class Applicant {

    private String userID;
    private String name;
    private String email;
    private int age;
    private ListInterface<String> skills = new DoublyLinkedList<>();
    private ListInterface<String> workingExperience = new DoublyLinkedList<>();
    private ListInterface<String> education = new DoublyLinkedList<>();
    private ListInterface<Application> application = new DoublyLinkedList<>();

    public Applicant(String userID, String name, String email, int age, ListInterface<String> skills, ListInterface<String> workingExperience, ListInterface<String> education) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.age = age;
        this.skills = skills;
        this.workingExperience = workingExperience;
        this.education = education;
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

    public ListInterface<String> getSkills() {
        return skills;
    }

    public void setSkills(ListInterface<String> skills) {
        this.skills = skills;
    }

    public ListInterface<String> getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(ListInterface<String> workingExperience) {
        this.workingExperience = workingExperience;
    }

    public ListInterface<String> getEducation() {
        return education;
    }

    public void setEducation(ListInterface<String> education) {
        this.education = education;
    }

    public ListInterface<Application> getApplication() {
        return application;
    }

    public void setApplication(ListInterface<Application> application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "UserID: " + userID + "\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Age: " + age + "\n"
                + "Skills: " + skills + "\n"
                + "Working Experience: " + workingExperience + "\n"
                + "Education: " + education + "\n";
    }
}
