/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.DoublyLinkedList;
import adt.ListInterface;

public class Company {

    private String companyId;
    private String companyName;
    private String email;
    private ListInterface<Job> job = new DoublyLinkedList<>();

    public Company() {
        this("", "", "");
    }

    public Company(String companyId, String companyName, String email) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.email = email;
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
    
    public ListInterface<Job> getJob(){
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
    public void setJob(ListInterface<Job> job){
        this.job = job;
    }

    @Override
    public String toString() {
        return "Company ID:" + companyId + "\nCompany Name: " + companyName + "\nEmail: " + email + "\nJob: " + job;
    }
}
