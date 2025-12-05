package com.company.org;

import com.company.org.repo.EmployeeRepository;
import com.company.org.service.CompAnalyzer;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) throws Exception {
        String file = args.length > 0 ? args[0] : "src/main/resources/employees.csv";
        try (InputStream is = Files.newInputStream(Path.of(file))) {
            EmployeeRepository repo = new EmployeeRepository(is);
            CompAnalyzer a = new CompAnalyzer(repo);
            System.out.println(a.managersUnderpaid());
            System.out.println(a.managersOverpaid());
            System.out.println(a.employeesWithLongReportingLine(4));
        }
    }
}
