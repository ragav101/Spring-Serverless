package com.SpringBootTraining.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.SpringBootTraining.dto.EmployeeDto;
import com.SpringBootTraining.model.Employee;
import com.SpringBootTraining.service.EmployeeService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

@Component
public class EmployeeHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

	@Autowired
	public EmployeeService employeeService;
	
	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
	    Map<String, String> param = input.getQueryStringParameters();
	    param.get("flag");
	    if(param.get("flag") .equals("getAllEmployee")) {
	    	return apiGatewayResponse(employeeService.getallEmployee(),200,new APIGatewayProxyResponseEvent());
	    }
	    else if(param.get("flag").equals("getEmployeeById")) {
	    	Employee response = employeeService.getEmployeeById(Long.parseLong(param.get("empId")));
	    	return apiGatewayResponse(response, 200, new APIGatewayProxyResponseEvent());
	    }
	    else if(param.get("flag").equals("pgEmpSort")) {
	    	Page<Employee> response = employeeService.findEmployeeWithPagenationSorting(Integer.parseInt(param.get("offset")),Integer.parseInt(param.get("pageSize")),String.valueOf(param.get("field")));
	    	return apiGatewayResponse(response, 200, new APIGatewayProxyResponseEvent());
	    }
	    else if(param.get("flag").equals("findEmployeeWithPagination")) {
	    	Page<Employee> response = employeeService.findEmployeeWithPagination(Integer.parseInt(param.get("offset")), Integer.parseInt(param.get("pageSize")));
	    	return apiGatewayResponse(response, 200, new APIGatewayProxyResponseEvent());
	    }
	    else if(param.get("flag").equals("findEmployeeBySorting")) {
	    	List<Employee> response = employeeService.findEmployeeBySorting(String.valueOf(param.get("field")));
	    	return apiGatewayResponse(response, 200, new APIGatewayProxyResponseEvent());
	    }
	    else if(param.get("flag").equals("searchEmployee")) {
	    	List<Employee> response = employeeService.searchEmployee(String.valueOf(param.get("query")));
	    	return apiGatewayResponse(response, 200, new APIGatewayProxyResponseEvent());
	    }
	    else if(param.get("flag").equals("newEmployee")) {
	    	String body = input.getBody();
	    	EmployeeDto dto = new Gson().fromJson(body, EmployeeDto.class);
	    	Employee response = null;
			try {
				response = employeeService.newEmployee(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}          
	    	return apiGatewayResponse(response, 200, new APIGatewayProxyResponseEvent());
	    }
	    else if (param.get("flag").equals("deleteEmployee")) {
	    	ResponseEntity<Employee> response = employeeService.deleteEmployee(Long.parseLong(param.get("empId")));
	    	return apiGatewayResponse(response, 200, new APIGatewayProxyResponseEvent());
		}
	    else if (param.get("flag").equals("updateEmployeeLocation")) {
			Employee response = employeeService.updateEmployeeLocation(Long.parseLong(param.get("id")), String.valueOf(param.get("location")));
			return apiGatewayResponse(response, 200, new APIGatewayProxyResponseEvent());
		}
	    else if (param.get("flag").equals("updateEmployee")) {
	    	String body = input.getBody();
	    	EmployeeDto dto = new Gson().fromJson(body, EmployeeDto.class);
	    	Employee response = employeeService.updateEmployee(Long.parseLong(param.get("id")), dto);
	    	return apiGatewayResponse(response, 200, new APIGatewayProxyResponseEvent());
	    }
		return apiGatewayResponse("UNEXPECTEDEXCEPTION",500,new APIGatewayProxyResponseEvent());
	}

	public APIGatewayProxyResponseEvent apiGatewayResponse(Object body, int statusCode,
			APIGatewayProxyResponseEvent responseEvent) {
		responseEvent.setIsBase64Encoded(false);
		if (body != null)
			responseEvent.setBody(new Gson().toJson(body));
		responseEvent.setStatusCode(statusCode);
		return responseEvent;
	}
	
}
