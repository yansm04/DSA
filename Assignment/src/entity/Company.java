/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class Company {

    private String companyId;
    private String companyName;
    private String email;
    // decalre job class

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

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Company ID:" + companyId + "\nCompany Name: " + companyName + "\nEmail: " + email + "\n";
    }
}
