package com.SpringBootTraining.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootTraining.dto.EmployeeDto;
import com.SpringBootTraining.handler.EmployeeHandler;
import com.SpringBootTraining.model.Employee;
import com.SpringBootTraining.service.EmployeeService;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/Emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeHandler employeeHandler;
	
	@GetMapping("/getAll")
	public APIGatewayProxyResponseEvent getWholeEmployee(@Param("flag") String flag){
		APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		Map<String, String> params = new HashMap<String, String>();
		params.put("flag", flag);
		event.setQueryStringParameters(params);
		return employeeHandler.handleRequest(event,null);
	}
	
	@GetMapping("/getOne")
	public APIGatewayProxyResponseEvent getoneEmployee(@Param("flag") String flag,@Param(value = "empId") Long empId) {
		APIGatewayProxyRequestEvent event= new APIGatewayProxyRequestEvent();
		HashMap<String, String> params = new HashMap<String,String>();
		params.put("flag", flag);
		params.put("empId", String.valueOf(empId));
		event.setQueryStringParameters(params);
		return employeeHandler.handleRequest(event, null);
	}
	
	//new employee
	@PostMapping("/newOne")
	public APIGatewayProxyResponseEvent saveEmployee(@Param("flag") String flag,@RequestBody EmployeeDto employeedto) throws Exception{
		APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("flag", flag);
		event.setQueryStringParameters(params);
		String body = new ObjectMapper().writeValueAsString(employeedto);
		event.setBody(body);
		return employeeHandler.handleRequest(event, null);
	}
	
	@PutMapping("/upt")// Not Created handler
	public APIGatewayProxyResponseEvent updateemployee(@Param("flag") String flag, @RequestParam Long id, @RequestBody EmployeeDto dto) throws Exception {
		APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		HashMap<String, String> params = new HashMap<String,String>();
		params.put("flag", flag);
		params.put("id", String.valueOf(id));
		event.setQueryStringParameters(params);
		String body = new ObjectMapper().writeValueAsString(dto);
		event.setBody(body);
		return employeeHandler.handleRequest(event, null);
	}
	
	@PatchMapping("/uptLocation")
	public APIGatewayProxyResponseEvent updateEmployeeLocation(@Param("flag") String flag,@RequestParam Long id, @RequestParam String location) {
		APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		HashMap<String, String> params = new HashMap<String,String>();
		params.put("flag", flag);
		params.put("id", String.valueOf(id));
		params.put("location", String.valueOf(location));
		event.setQueryStringParameters(params);
		return employeeHandler.handleRequest(event, null);
	}
	
	@DeleteMapping("/dlt")
	public APIGatewayProxyResponseEvent deleteEmployee(@Param("flag") String flag,@RequestParam Long id) {
		APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		HashMap<String, String> params = new HashMap<String,String>();
		params.put("flag", flag);
		params.put("id", String.valueOf(id));
		event.setQueryStringParameters(params);
		return employeeHandler.handleRequest(event, null);
	}
	
	@GetMapping("/pgEmpSort")
	public APIGatewayProxyResponseEvent getEmployeeWithPageSort(@Param("flag") String flag,@RequestParam int offset, @RequestParam int pageSize, @RequestParam String field) throws Exception{
		APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		Map<String,String> params = new HashMap<String, String>();
		params.put("flag", flag);
		params.put("offset", String.valueOf(offset));
		params.put("pageSize",String.valueOf(pageSize));
		params.put("field",String.valueOf(field));
		event.setQueryStringParameters(params);
		return employeeHandler.handleRequest(event, null);
	}
	
	@GetMapping("/pgEmp")
	public APIGatewayProxyResponseEvent employeePagination(@Param("flag") String flag,@RequestParam int offset, @RequestParam int pageSize){
		APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		Map<String, String> params = new HashMap<String,String>();
		params.put("flag", flag);
		params.put("offset", String.valueOf(offset));
		params.put("pageSize", String.valueOf(pageSize));
		event.setQueryStringParameters(params);
		return employeeHandler.handleRequest(event, null);
	}
	
	@GetMapping("/sortEmp")
	public APIGatewayProxyResponseEvent findEmployeeWithSort(@Param("flag") String flag ,@RequestParam String field){
		APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		Map<String, String> params = new HashMap<String,String>();
		params.put("flag", String.valueOf(flag));
		params.put("field", String.valueOf(field));
		event.setQueryStringParameters(params);
		return employeeHandler.handleRequest(event, null);
	}
	
	@GetMapping("/searchEmp")
	public APIGatewayProxyResponseEvent searchEmployee(@Param("flag") String flag ,@Param("query") String query){
		APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		Map<String,String> params = new HashMap<String,String>();
		params.put("flag", String.valueOf(flag));
		params.put("query", String.valueOf(query));
		event.setQueryStringParameters(params);
		return employeeHandler.handleRequest(event, null);
	}
}
