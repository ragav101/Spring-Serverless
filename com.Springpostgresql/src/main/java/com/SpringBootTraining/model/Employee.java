package com.SpringBootTraining.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_table")
public class Employee extends Auditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	
	@Column(name = "emp_name", nullable = false)
	private String empName;
	
	@Column(name = "emp_address", nullable = false)
	private String empAddress;

}
