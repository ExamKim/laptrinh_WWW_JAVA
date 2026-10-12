package com.example.midterm3.dto;

import com.example.midterm3.model.Department;
import com.example.midterm3.model.Employee;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/13/2026
 */
public class DepartmentEmployeeDTO {
    private Department d;
    private List<Employee> em;

    public DepartmentEmployeeDTO() {
    }

    public DepartmentEmployeeDTO(Department d, List<Employee> em) {
        this.d = d;
        this.em = em;
    }

    public Department getD() {
        return d;
    }

    public void setD(Department d) {
        this.d = d;
    }

    public List<Employee> getEm() {
        return em;
    }

    public void setEm(List<Employee> em) {
        this.em = em;
    }
}
