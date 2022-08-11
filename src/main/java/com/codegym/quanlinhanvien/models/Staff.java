package com.codegym.quanlinhanvien.models;


import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String StaffCode;
    @Size(max = 15, message = "Tên quá dài")
    private String name;
    @NumberFormat
    private int age;
    private long salary;
    @ManyToOne
    private Branch branch;

    public Staff() {
    }

    public Staff(long id, String staffCode, String name, int age, long salary, Branch branch) {
        this.id = id;
        StaffCode = staffCode;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.branch = branch;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStaffCode() {
        return StaffCode;
    }

    public void setStaffCode(String staffCode) {
        StaffCode = staffCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}