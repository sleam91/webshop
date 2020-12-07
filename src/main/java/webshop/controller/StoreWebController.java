package webshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import webshop.model.Product;
import webshop.model.forms.SearchFormBean;
import webshop.service.ProductService;

@Controller
public class StoreWebController {

    @Autowired
    private ProductService productService;

    @GetMapping("/store")
    public String viewStore(Model m) {
	m.addAttribute("categories", productService.getCategories());
	m.addAttribute("showCategories", true);
	m.addAttribute("searchFormBean", new SearchFormBean());
	return "store";
    }

    @PostMapping("/store/search")
    public String searchProducts(@ModelAttribute("searchFormBean") SearchFormBean search, Model m) {
	m.addAttribute("resultsMessage", "Search results for: " + search.getQuery());
	m.addAttribute("products", productService.findProducts(search.getQuery()));
	m.addAttribute("guessFormBean", new SearchFormBean());
	return "store";
    }

    @GetMapping("/store/{category}")
    public String showCategory(@PathVariable("category") String category, Model m) {
	List<Product> products = productService.findCategory(category);
	if (products.isEmpty()) {
	    return "error/404";
	}

	m.addAttribute("products", products);
	m.addAttribute("resultsMessage", "Category: " + StringUtils.capitalize(category));
	m.addAttribute("searchFormBean", new SearchFormBean());
	return "store";
    }

}
