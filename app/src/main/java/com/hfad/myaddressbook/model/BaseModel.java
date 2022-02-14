package com.hfad.myaddressbook.model;

import com.hfad.myaddressbook.model.Employees;

import java.util.ArrayList;

public class BaseModel {
    private String statusCode;
    private String nim;
    private String nama;
    private String employeeID;
    private String credits;
    private ArrayList<Employees> employees;

    public BaseModel(String statusCode, String nim, String nama,
                     String employeeID, String credits,
                     ArrayList<Employees> employees) {
        this.statusCode = statusCode;
        this.nim = nim;
        this.nama = nama;
        this.employeeID = employeeID;
        this.credits = credits;
        this.employees = employees;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getCredits() {
        return credits;
    }

    public ArrayList<Employees> getEmployees() {
        return employees;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public void setEmployees(ArrayList<Employees> employees) {
        this.employees = employees;
    }
}
