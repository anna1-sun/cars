package com.company.springmvc.demo;

import com.company.springmvc.demo.data.DataRepository;
import com.company.springmvc.demo.data.Product;
import com.company.springmvc.demo.dto.ProductUpdateDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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

        if(product == null){
            product = new Product();
        }

        model.addAttribute("title", product != null ? product.getName() : "");
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        model.addAttribute("categories", repo.getCategories());

        return "products_edit";
    }

    @PostMapping("/products/{id}")
    public ModelAndView saveNewProduct(@PathVariable int id, @ModelAttribute("updateDto") ProductUpdateDto dto) {
        var repo = new DataRepository();
        var product = repo.getProductById(id);

        if (product == null && id != 0) {
            throw new IllegalArgumentException("Item with such id not found");
        }

        if (id == 0) {
            product = new Product();
            product.setId(0);
        }

        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setShelfLife(dto.getShelfLife());

        var category = repo.getCategory(dto.getCategoryId());
        product.setCategory(category);

        if (id != 0) {
            repo.updateProduct(product);
        } else {
            repo.addProduct(product);
        }

        return new ModelAndView("redirect:/products");
    }
    @GetMapping("/products/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable int id) {
        var repo = new DataRepository();
        repo.deleteProduct(id);
        return new ModelAndView("redirect:/products");
    }

}
