package com.SpringBootTraining.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SpringBootTraining.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

//	@Query("SELECT e FROM employee_table e WHERE"+
//	       "e.emp_name LIKE ('%',:query,'%')")
	@Query("SELECT m FROM Employee m WHERE m.empName LIKE %:query%")
	List<Employee> searchEmployeeByName(String query);
	
	
}
