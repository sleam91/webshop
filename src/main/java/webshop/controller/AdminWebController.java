package webshop.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webshop.model.Product;
import webshop.model.forms.ProductFormBean;
import webshop.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminWebController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public String adminDashboard() {
	return "/admin/admin";
    }

    @GetMapping("/add-product")
    public String addNewProduct(Model m) {
	m.addAttribute("productFormBean", new ProductFormBean());
	m.addAttribute("categories", adminService.getCategories());
	return "admin/add-product";
    }

    @PostMapping("/add-product")
    public String addNewProduct(@ModelAttribute("productFormBean") ProductFormBean productFormBean) {
	Product product = new Product();
	product.setName(productFormBean.getName());
	product.setPrice(productFormBean.getPrice());
	product.setImg(productFormBean.getImg());
	product.setCategory(adminService.getCategory(productFormBean.getCategory()));
	adminService.addNewProduct(product);
	return "redirect:/admin";
    }

    @GetMapping("/orders")
    public String getOrders(Model m) {
	m.addAttribute("orders", adminService.getOrders());
	return "admin/orders";
    }

    @PostMapping("/orders")
    public String distpatchOrder(int id, Model m) {
	try {
	    adminService.dispatchOrder(id);
	    return "redirect:/admin/orders";
	} catch (NoSuchElementException ex) {
	    m.addAttribute("error", "Wrong order id, could not dispatch");
	    return "redirect:/admin/orders";
	}

    }

}
