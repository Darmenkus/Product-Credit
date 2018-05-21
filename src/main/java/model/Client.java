package model;

import java.util.Date;

public class Client {
    private Integer id;
    private String iin;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phoneNumber;
    private Date birthDate;
    private String gender;
    private String documentNumber;
    private String documentIssuedBy;
    private Date documentDateOfIssue;
    private Date documentValidTo;
    private Double salary;
    private Double payments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentIssuedBy() {
        return documentIssuedBy;
    }

    public void setDocumentIssuedBy(String documentIssuedBy) {
        this.documentIssuedBy = documentIssuedBy;
    }

    public Date getDocumentDateOfIssue() {
        return documentDateOfIssue;
    }

    public void setDocumentDateOfIssue(Date documentDateOfIssue) {
        this.documentDateOfIssue = documentDateOfIssue;
    }

    public Date getDocumentValidTo() {
        return documentValidTo;
    }

    public void setDocumentValidTo(Date documentValidTo) {
        this.documentValidTo = documentValidTo;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getPayments() {
        return payments;
    }

    public void setPayments(Double payments) {
        this.payments = payments;
    }
}
