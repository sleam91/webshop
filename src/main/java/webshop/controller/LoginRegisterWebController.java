package webshop.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import webshop.model.forms.LoginFormBean;
import webshop.model.forms.RegisterFormBean;
import webshop.service.CustomerService;

@Controller
public class LoginRegisterWebController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/login")
	public String loginForm(Model m) {
		m.addAttribute("loginFormBean", new LoginFormBean());
		m.addAttribute("message", "Welcome, please login");
		return "login";
	}

	@PostMapping("/login")
	public String loginForm(@ModelAttribute("loginFormBean") LoginFormBean loginFormBean, Model m) {
		try {
			customerService.login(loginFormBean.getEmail());
			return "redirect:/store";
		} catch (NoSuchElementException ex) {
			m.addAttribute("message", "Error, user not found");
			return "login";
		}
	}

	@GetMapping("/register")
	public String registerForm(Model m) {
		m.addAttribute("registerFormBean", new RegisterFormBean());
		m.addAttribute("message", "Welcome, please register");
		return "register";
	}

	@PostMapping("/register")
	public String registerForm(@ModelAttribute("registerFormBean") RegisterFormBean registerFormBean, Model m) {
		try {
			customerService.register(registerFormBean.getEmail());
			return "redirect:/login";
		} catch (IllegalArgumentException ex) {
			m.addAttribute("message", ex.getMessage());
			return "register";
		}
	}
}
