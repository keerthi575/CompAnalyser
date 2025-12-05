package com.company.org.service;

import com.company.org.model.Employee;
import com.company.org.repo.EmployeeRepository;

import java.util.*;

public class CompAnalyzer {
    private final EmployeeRepository repo;

    public CompAnalyzer(EmployeeRepository repo) { this.repo = repo; }

    public List<String> managersUnderpaid() {
        List<String> out = new ArrayList<>();
        for (Employee m : repo.getAll()) {
            var subs = repo.getDirectReports(m.getId());
            if (subs.isEmpty()) continue;
            double avg = subs.stream().mapToDouble(Employee::getSalary).average().orElse(0);
            double req = avg * 1.20;
            if (m.getSalary() < req)
                out.add(m.getId());
        }
        return out;
    }

    public List<String> managersOverpaid() {
        List<String> out = new ArrayList<>();
        for (Employee m : repo.getAll()) {
            var subs = repo.getDirectReports(m.getId());
            if (subs.isEmpty()) continue;
            double avg = subs.stream().mapToDouble(Employee::getSalary).average().orElse(0);
            double max = avg * 1.50;
            if (m.getSalary() > max)
                out.add(m.getId());
        }
        return out;
    }

    public List<String> employeesWithLongReportingLine(int max) {
        List<String> out = new ArrayList<>();
        for (Employee e : repo.getAll()) {
            int c = countManagersAbove(e);
            if (c > max) out.add(e.getId());
        }
        return out;
    }

    private int countManagersAbove(Employee e) {
        int c = 0;
        String m = e.getManagerId();
        while (m != null && !m.isEmpty()) {
            c++;
            Employee mm = repo.getById(m);
            if (mm == null) break;
            m = mm.getManagerId();
        }
        return c;
    }
}
