# Org Analyzer

Simple Java program to analyze organization salary and reporting chain rules.

## Build
mvn clean test

## Run
mvn -q exec:java -Dexec.mainClass="com.company.org.App" -Dexec.args="src/main/resources/employees.csv"
