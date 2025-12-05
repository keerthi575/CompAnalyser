package com.company.org.repo;

import com.company.org.model.Employee;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class EmployeeRepository {
    private final Map<String, Employee> byId = new HashMap<>();
    private final Map<String, List<Employee>> directReports = new HashMap<>();

    public EmployeeRepository(InputStream csvStream) throws IOException {
        load(csvStream);
    }

    public Employee getById(String id) { return byId.get(id); }
    public Collection<Employee> getAll() { return Collections.unmodifiableCollection(byId.values()); }
    public List<Employee> getDirectReports(String managerId) {
        return directReports.getOrDefault(managerId, Collections.emptyList());
    }

    private void load(InputStream csvStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvStream, StandardCharsets.UTF_8))) {
            String header = br.readLine();
            String line;
            while ((line = br.readLine()) != null) processLine(line);

            for (Employee e : byId.values()) {
                directReports.computeIfAbsent(e.getManagerId(), k -> new ArrayList<>()).add(e);
            }
        }
    }

    private void processLine(String line) {
        String[] p = line.split(",");
        if (p.length < 4) return;
        Employee e = new Employee(p[0].trim(), p[1].trim(), p[2].trim(),
                Double.parseDouble(p[3].trim()), p.length > 4 ? p[4].trim() : null);
        byId.put(e.getId(), e);
    }
}
