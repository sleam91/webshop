package webshop.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import webshop.model.forms.LoginFormBean;
import webshop.model.forms.RegisterFormBean;
import webshop.service.PersonService;

@Controller
public class LoginRegisterWebController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String homeRedirect(Principal person) {
	if (Optional.ofNullable(person).isEmpty()) {
	    return "redirect:/login";
	} else {
	    return "redirect:/store";
	}
    }

    @GetMapping("/login")
    public String loginForm(Model m) {
	m.addAttribute("loginFormBean", new LoginFormBean());
	m.addAttribute("message", "Welcome, please login");
	return "login";
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

	    personService.register(registerFormBean.getEmail(), 
		    registerFormBean.getPassword(),
		    registerFormBean.getConfirmPassword());
	    return "redirect:/login?registered";
	} catch (IllegalArgumentException ex) {
	    m.addAttribute("error", ex.getMessage());
	    return "register";
	}
    }
}
