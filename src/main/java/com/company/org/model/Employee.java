package com.company.org.model;

import java.util.Objects;

public class Employee {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final double salary;
    private final String managerId;

    public Employee(String id, String firstName, String lastName, double salary, String managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = (managerId == null || managerId.trim().isEmpty()) ? null : managerId.trim();
    }

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getSalary() { return salary; }
    public String getManagerId() { return managerId; }

    @Override
    public String toString() {
        return id + " - " + firstName + " " + lastName + " (" + salary + ")";
    }
}
