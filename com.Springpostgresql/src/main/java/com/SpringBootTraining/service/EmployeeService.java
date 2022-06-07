package com.SpringBootTraining.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SpringBootTraining.Dao.EmployeeRepository;
import com.SpringBootTraining.dto.EmployeeDto;
import com.SpringBootTraining.exception.ResourceNotFoundException;
import com.SpringBootTraining.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//find all employee
	public List<Employee> getallEmployee(){
		return employeeRepository.findAll();
	}
	
	//get all employee
	public Employee getEmployeeById(Long empId) {
		return employeeRepository.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with id"+empId));
	}
	
	//save new employee
	public Employee newEmployee(EmployeeDto response) throws Exception{
		Employee employee = new Employee();
		employee.setEmpName(response.getEmployeeName());
		employee.setEmpAddress(response.getEmployeeLocation());
		return employeeRepository.save(employee);
	}
	
	//update employee
	public Employee updateEmployee(Long id, EmployeeDto dto) {
		Employee exemployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with id"+id));
		exemployee.setEmpName(dto.getEmployeeName());
		exemployee.setEmpAddress(dto.getEmployeeLocation());
		return employeeRepository.save(exemployee);
	}
	
	//delete employee
	public ResponseEntity<Employee> deleteEmployee(Long id) {
		Employee exemployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with id"+id));
		employeeRepository.delete(exemployee);
		return ResponseEntity.ok().build();
	}
	
	//sorting
	public List<Employee> findEmployeeBySorting(String field){
		return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
	}
	
	//pagination
	public Page<Employee> findEmployeeWithPagination(int offset, int pageSize){
		Page<Employee> Emp = employeeRepository.findAll(PageRequest.of(offset, pageSize));
		return Emp;
	}
	
	//pagination with sorting
	public Page<Employee> findEmployeeWithPagenationSorting(int offset, int pagize, String field){
		Page<Employee> emp = employeeRepository.findAll(PageRequest.of(offset, pagize).withSort(Sort.by(field)));
		return emp;
	}
	
	//search employee by name
	public List<Employee> searchEmployee(String query){
		List<Employee> emp = employeeRepository.searchEmployeeByName(query);
		return emp;
	}
	
	//Employe Location Update
	public Employee updateEmployeeLocation(Long id, String location) {
		Employee emp = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with id"+id));
		emp.setEmpAddress(location);
		return employeeRepository.save(emp);
	}
	
}
