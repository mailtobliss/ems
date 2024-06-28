package com.java.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.ems.dto.EmployeeDto;
import com.java.ems.entity.Employee;
import com.java.ems.exception.ResourceNotFoundException;
import com.java.ems.mapper.EmployeeMapper;
import com.java.ems.repository.EmployeeRepository;
import com.java.ems.service.EmployeeService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee=EmployeeMapper.mapToEmployeeDto(employeeDto);
		Employee savedEmployee=employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
		
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()->new ResourceNotFoundException("Employee not exist with the given id :"+employeeId)
						);
		return EmployeeMapper.mapToEmployeeDto(employee);
	}
	
	
	
	@Override
	public List<EmployeeDto> getAllEmployees(){
		List<Employee> employees=employeeRepository.findAll();
		return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
		Employee employee= employeeRepository.findById(employeeId)
		.orElseThrow(()->new ResourceNotFoundException("Employee not exist with the given id :"+employeeId));

		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());
		employee.setAge(updateEmployee.getAge());
		employee.setGender(updateEmployee.getGender());
		employee.setCity(updateEmployee.getCity());
		employee.setPincode(updateEmployee.getPincode());

		Employee updatedEmployee=employeeRepository.save(employee);

		return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee= employeeRepository.findById(employeeId)
				.orElseThrow(()->new ResourceNotFoundException("Employee not exist with the given id :"+employeeId));
		employeeRepository.deleteById(employeeId);
	}
}
