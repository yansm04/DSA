/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;

public class Company implements Comparable<Company> {

    private String companyId;
    private String companyName;
    private String email;
    private SortedListInterface<Job> job = new SortedDoublyLinkedList<>();

    public Company() {
        this("", "", "");
    }

    public Company(String companyId, String companyName, String email) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.email = email;
    }

    public Company(String companyId, String companyName, String email, SortedListInterface<Job> job) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.email = email;
        this.job = job;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public SortedListInterface<Job> getJob() {
        return job;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJob(SortedListInterface<Job> job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Company ID:" + companyId + "\nCompany Name: " + companyName + "\nEmail: " + email + "\nJob: " + job;
    }

    @Override
    public int compareTo(Company o) {
        return this.companyId.compareTo(o.companyId);
    }
}
