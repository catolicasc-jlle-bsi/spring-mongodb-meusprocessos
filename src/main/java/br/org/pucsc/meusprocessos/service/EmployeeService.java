package br.org.pucsc.meusprocessos.service;

import java.util.List;

import br.org.pucsc.meusprocessos.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(Long id);
	
	public Employee getEmployeeByName(String name);
	
	public Employee getEmployeeByCnpj(String cnpj);
	
	public Employee getEmployeeByEmail(String email);
	
	public void deleteEmployee(Long id);
	
	public Employee createEmployee(String name, String cnpj, String oabnumer, String email, String phone, String observation);

	public Employee updateEmployee(Long id, String name, String cnpj, String oabnumer, String email, String phone, String observation);
}
