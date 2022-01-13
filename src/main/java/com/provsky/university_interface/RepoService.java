package com.provsky.university_interface;

import com.provsky.university_interface.repository.DepartmentsRepo;
import com.provsky.university_interface.repository.Lector;
import com.provsky.university_interface.repository.LectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class RepoService {
    @Autowired
    private LectorRepo lectorRepo;
    @Autowired
    private DepartmentsRepo departmentsRepo;


    @PostConstruct
    private void init() {
        System.out.println("---Hello I am the university register bot!---");
        System.out.println("Here is the patterns of messages that I can response: ");
        System.out.println("Who is head of department {department_name}");
        System.out.println("Show {department_name} statistics");
        System.out.println("Show the average salary for the department {department_name}");
        System.out.println("Show count of employee for {department_name}");
        System.out.println("Global search by {template}");

        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        while (b) {
            String userMessage = scanner.nextLine();
            try {
                if (userMessage.contains("Who is head of department")) {
                    String departmentName = userMessage.replaceAll("Who is head of department ", "");
                    Lector lector = getHeadOfDepartment(departmentName);

                    System.out.println("Head of " + departmentName + " department is " + lector.getName());

                } else if (userMessage.contains("Show") && userMessage.contains("statistics.")) {
                    String departmentName = userMessage.replaceAll("Show ", "").replaceAll(" statistics", "");

                    System.out.println(getStat(departmentName));

                } else if (userMessage.contains("Show the average salary for the department")) {
                    String departmentName = userMessage.replaceAll("Show the average salary for the department ", "");

                    System.out.println(getAverageSalary(departmentName));

                } else if (userMessage.contains("Show count of employee for")) {
                    String departmentName = userMessage.replaceAll("Show count of employee for ", "");

                    System.out.println(getDepartmentsEmployers(departmentName));

                } else if (userMessage.contains("Global search by")) {
                    String pattern = userMessage.replaceAll("Global search by ", "");

                    System.out.println(globalSearch(pattern));

                } else if (userMessage.equals("close")) {
                    b = false;
                    scanner.close();
                    System.exit(0);
                } else {
                    System.out.println("Looks like I don't understand request.");
                    System.out.println("Check if syntax is ok: {" + userMessage + "}");
                }
            }catch (NullPointerException e){
                System.out.println("Request is ok, but department name is wrong");
            }

        }
    }

    public Lector getHeadOfDepartment(String name) {
        int headLectorId = departmentsRepo.findByName(name).getHead_id();
        return lectorRepo.findById(headLectorId).get();
    }

    public double getAverageSalary(String name) {
        List<Lector> lectors = departmentsRepo.findByName(name).getLectors();
        return lectors.stream().mapToDouble(Lector::getSalary).average().orElse(Double.NaN);
    }

    public int getDepartmentsEmployers(String name) {
        return departmentsRepo.findByName(name).getLectors().size();
    }

    public String getStat(String name) {
        List<Lector> lectors = departmentsRepo.findById(departmentsRepo.findByName(name).getId()).get().getLectors();

        long assistant = lectors.stream().filter(lector -> lector.getDegree().getRank().equals("assistant")).count();
        long associate_professors = lectors.stream().filter(lector -> lector.getDegree().getRank().equals("associate professor")).count();
        long professors = lectors.stream().filter(lector -> lector.getDegree().getRank().equals("professor")).count();

        return "assistants - " + assistant + ". \n" +
                "associate professors - " + associate_professors + "\n" +
                "professors - " + professors + "\n";
    }

    public List<String> globalSearch(String pattern) {
        List<Lector> lectors = new ArrayList<>();
        lectorRepo.findAll().iterator().forEachRemaining(lectors::add);

        return lectors.stream().map(Lector::getName)
                .filter(name -> name.contains(pattern))
                .collect(Collectors.toList());
    }

}
