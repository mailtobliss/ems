package com.java.ems.mapper;

import com.java.ems.dto.EmployeeDto;
import com.java.ems.entity.Employee;

public class EmployeeMapper {
	
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		
		return new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getAge(),
				employee.getGender(),
				employee.getCity(),
				employee.getPincode()
		);
		
	}
	
	
	public static Employee mapToEmployeeDto(EmployeeDto employeedto) {
		
		return new Employee(
				employeedto.getId(),
				employeedto.getFirstName(),
				employeedto.getLastName(),
				employeedto.getAge(),
				employeedto.getGender(),
				employeedto.getCity(),
				employeedto.getPincode()
		);
		
	}

}
