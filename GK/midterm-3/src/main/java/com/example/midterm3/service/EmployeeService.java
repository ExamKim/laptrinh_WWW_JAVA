package com.example.midterm3.service;

import com.example.midterm3.model.Employee;
import com.example.midterm3.repository.EmployeeRepository;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class EmployeeService {
    private EmployeeRepository employeeRepository= new EmployeeRepository();

    public List<Employee> findAll(){return employeeRepository.findAll();}
    public Employee findById(int id){return employeeRepository.findById(id);}
    public boolean save(Employee em){return employeeRepository.save(em);}


}
