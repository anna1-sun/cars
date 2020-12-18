package com.company.springmvc.demo;

import com.company.springmvc.demo.data.DataRepository;
import com.company.springmvc.demo.data.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DataController {
    @GetMapping("/products")
    public String product(Model model) {

        var repo = new DataRepository();
        var items = repo.getProducts();

        model.addAttribute("title", "Products");
        model.addAttribute("products", items);
        return "products";
    }

    @GetMapping("/categories")
    public String category(Model model) {

        var repo = new DataRepository();
        var items = repo.getCategories();

        model.addAttribute("title", "Categories");
        model.addAttribute("categories", items);
        return "categories";
    }

    @GetMapping("/bacterias")
    public String bacteria(Model model) {

        var repo = new DataRepository();
        var items = repo.getBacterias();

        model.addAttribute("title", "Bacterias");
        model.addAttribute("bacterias", items);
        return "bacterias";
    }

    @GetMapping("/limits")
    public String limit(Model model) {

        var repo = new DataRepository();
        var items = repo.getLimits();

        model.addAttribute("title", "Limits");
        model.addAttribute("limits", items);
        return "limits";
    }

    @GetMapping("/products/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        var repo = new DataRepository();
        var product = repo.getProductById(id);

        model.addAttribute("title", product != null ? product.getName() : "");
        model.addAttribute("product", product);

        return "products_edit";
    }
}
