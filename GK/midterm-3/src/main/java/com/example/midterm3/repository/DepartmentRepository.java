package com.example.midterm3.repository;

import com.example.midterm3.model.Department;
import com.example.midterm3.model.Employee;
import com.example.midterm3.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class DepartmentRepository {

    public Department findById(int id) {
        String sql = "SELECT * FROM departments WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Department(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Employee> findByDepartmentAndSalaryRange(int departmentId, double minSalary, double maxSalary) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.id, e.name, e.salary, e.department_id " +
                "FROM departments d " +
                "INNER JOIN employees e ON d.id=e.department_id "  +
                "WHERE d.id = ? AND e.salary BETWEEN ? AND ? ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1,departmentId);
            ps.setDouble(2,minSalary);
            ps.setDouble(3,maxSalary);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("salary"),
                            rs.getInt("department_id")
                    ));
                }
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
