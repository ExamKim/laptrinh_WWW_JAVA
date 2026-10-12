package com.example.midterm3.service;

import com.example.midterm3.dto.DepartmentEmployeeDTO;
import com.example.midterm3.model.Department;
import com.example.midterm3.model.Employee;
import com.example.midterm3.repository.DepartmentRepository;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/13/2026
 */
public class DepartmentService {
    private DepartmentRepository departmentRepository= new DepartmentRepository();

    public DepartmentEmployeeDTO findByDepartmentAndSalaryRange(int departmentId, double minSalary, double maxSalary) {
        Department d =  departmentRepository.findById(departmentId);
        if (d == null) return null;
        List<Employee> e = departmentRepository.findByDepartmentAndSalaryRange(departmentId, minSalary, maxSalary);
        return new DepartmentEmployeeDTO(d, e);
    }
}
