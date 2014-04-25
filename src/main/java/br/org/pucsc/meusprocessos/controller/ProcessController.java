
package br.org.pucsc.meusprocessos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.org.pucsc.meusprocessos.model.Process;
import br.org.pucsc.meusprocessos.service.CustomerService;
import br.org.pucsc.meusprocessos.service.EmployeeService;
import br.org.pucsc.meusprocessos.service.ProcessService;
import br.org.pucsc.meusprocessos.service.UserService;

@Controller
@RequestMapping(value = "/process")
public class ProcessController {
	

	@Autowired
	ProcessService processService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired 
	UserService userService;

	public ProcessService getProcessService() {
		return processService;
	}

	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/" ,method = RequestMethod.GET)
	public String getIndex(Model model){
		
		List<Process> processs = getProcessService().getAllProcesss();
		model.addAttribute("processs", processs);
	
		return "process/list";
	}
	
	@RequestMapping(value="/read" ,method = RequestMethod.GET)
	public String getRead(String id, Model model){
		
		Process process = getProcessService().getProcessById(new Long(id));
		model.addAttribute("process", process);
		model.addAttribute("customers", customerService.getAllCustomers());
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "process/edit";
	}
	
	@RequestMapping(value="/new" ,method = RequestMethod.GET)
	public String getNew(Model model){
				
		model.addAttribute("process", new Process());
		model.addAttribute("customers", customerService.getAllCustomers());
		model.addAttribute("employees", employeeService.getAllEmployees());
	
		return "process/edit";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String getDelete(HttpServletRequest request){
		String id = request.getParameter("id");
		System.out.println("******** process delete: "+id);
				
		getProcessService().deleteProcess(new Long(id));
		
		return "process/edit";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String postSave(HttpServletRequest request){
		
		boolean isCreate = true;
				
		if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
			isCreate = false;
		}
		
		Long idEmployee = new Long(request.getParameter("idEmployee") != null ? request.getParameter("idEmployee") : "");
		Long idCustomer = new Long(request.getParameter("idCustomer") != null ? request.getParameter("idCustomer") : "");
		
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		String observation = request.getParameter("observation") != null ? request.getParameter("observation") : "";
		
		Process process = null;
		
		if (isCreate) {
			process = getProcessService().createProcess(name, observation, idCustomer, idEmployee);
		} else {
			Long id = new Long(request.getParameter("id"));
			process = getProcessService().updateProcess(id, name, observation, idCustomer, idEmployee);
		}
		
		request.setAttribute("process",process);
		
		return "redirect:/process/";
	}
			
}
