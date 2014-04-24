package br.org.pucsc.meusprocessos.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.org.pucsc.meusprocessos.model.Expense;
import br.org.pucsc.meusprocessos.model.Role;
import br.org.pucsc.meusprocessos.model.User;
import br.org.pucsc.meusprocessos.service.AttachmentService;
import br.org.pucsc.meusprocessos.service.ExpenseService;
import br.org.pucsc.meusprocessos.service.ExpenseTypeService;
import br.org.pucsc.meusprocessos.service.RoleService;
import br.org.pucsc.meusprocessos.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	AttachmentService attachmentService;
	
	@Autowired
	ExpenseTypeService expenseTypeService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	RoleService roleService;
	

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal,HttpServletRequest request ) {
 
		String email = principal.getName();
		model.addAttribute("email", email);		

		User user = getUserService().getUserByEmail(email);
		List<Expense> pendingExpenseList = getExpenseService().getExpensesByUser(user);
		
		model.addAttribute("pendingExpenseList",pendingExpenseList);
		request.getSession().setAttribute("user", user);

		return "welcome";
	}

	@RequestMapping(value="/login/novo", method = RequestMethod.GET)
	public String loginNovo() { 
		return "login/novo"; 
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) { 
		return "login/index"; 
	}

	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) { 
		model.addAttribute("error", "true");
		return "login/index";
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login/index";
	}
	
	@RequestMapping(value="/sessionTimeout",method = RequestMethod.GET)
	public String sessionTimeout(ModelMap model){
		String errorMsg = "Sua sessao expirou, por favor entre novamente";
		model.addAttribute("errorMsg",errorMsg);
		return "login/index";
	}
	
	@RequestMapping(value="/login/novo", method=RequestMethod.POST)
	public String signUp(ModelMap model,HttpServletRequest request){
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		User user = getUserService().getUserByEmail(email);
		if (user == null) {
			Role role = getRoleService().getRoleByName("ROLE_USER");
			user = getUserService().createUser(name, password, email, role);
			String succMsg = "Usuario cadastrado com sucesso ";
			return "redirect:/login?succMsg=" + succMsg;
			
		} else {
			String errorMsg = "Desculpe, mas esse usuario ja foi cadastrado, verifique os dados informados e tente novamente";
			request.setAttribute("errorMsg", errorMsg);
			return "redirect:/login/novo?errorMsg=" + errorMsg;
		}
	} 
	

	public ExpenseService getExpenseService() {
		return expenseService;
	}

	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public ExpenseTypeService getExpenseTypeService() {
		return expenseTypeService;
	}

	public void setExpenseTypeService(ExpenseTypeService expenseTypeService) {
		this.expenseTypeService = expenseTypeService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
 
}
