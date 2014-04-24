
package br.org.pucsc.meusprocessos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.org.pucsc.meusprocessos.model.Employee;
import br.org.pucsc.meusprocessos.service.EmployeeService;
import br.org.pucsc.meusprocessos.service.UserService;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	

	@Autowired
	EmployeeService employeeService;
	
	@Autowired 
	UserService userService;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/" ,method = RequestMethod.GET)
	public String getIndex(Model model){
		
		List<Employee> employees = getEmployeeService().getAllEmployees();
		model.addAttribute("employees", employees);
	
		return "employee/list";
	}
	
	@RequestMapping(value="/read" ,method = RequestMethod.GET)
	public String getRead(String id, Model model){
		
		Employee employee = getEmployeeService().getEmployeeById(new Long(id));
		model.addAttribute("employee", employee);
	
		return "employee/edit";
	}
	
	@RequestMapping(value="/new" ,method = RequestMethod.GET)
	public String getNew(Model model){
				
		model.addAttribute("employee", new Employee());
	
		return "employee/edit";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String getDelete(HttpServletRequest request){
		String id = request.getParameter("id");
		System.out.println("******** employee delete: "+id);
				
		getEmployeeService().deleteEmployee(new Long(id));
		
		return "employee/edit";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String postSave(HttpServletRequest request){
		
		boolean isCreate = true;
		
		if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
			isCreate = false;
		}
		
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		String cnpj = request.getParameter("cnpj") != null ? request.getParameter("cnpj") : "";
		String email = request.getParameter("email") != null ? request.getParameter("email") : "";
		String oabNummer = request.getParameter("oabnummer") != null ? request.getParameter("oabnummer") : "";
		String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
		String observation = request.getParameter("observation") != null ? request.getParameter("observation") : "";;
		
		Employee employee = null;
		
		if (isCreate) {
			employee = getEmployeeService().createEmployee(name, cnpj, oabNummer, email, phone, observation);
		} else {
			Long id = new Long(request.getParameter("id"));
			employee = getEmployeeService().updateEmployee(id, name, cnpj, oabNummer, email, phone, observation);
		}
		
		request.setAttribute("employee",employee);
		
		return "redirect:/employee/";
	}
			
}
