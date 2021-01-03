package com.company.springmvc.demo;

import com.company.springmvc.demo.data.DataRepository;
import com.company.springmvc.demo.data.Product;
import com.company.springmvc.demo.data.TestDataManager;
import com.company.springmvc.demo.data.TestResultItem;
import com.company.springmvc.demo.dto.ProductSearchDto;
import com.company.springmvc.demo.dto.ProductUpdateDto;
import com.company.springmvc.demo.dto.TestUpdateDto;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class DataController {

    private DataRepository repo;

    public DataController() {
        repo = new DataRepository();
    }


    @GetMapping("/products")
    public String product(Model model) {

        var items = repo.getProducts();

        model.addAttribute("title", "Products");
        model.addAttribute("products", items);
        return "products";
    }

    @GetMapping("/months/{id}")
    public String getProductsByMonth(Model model, @PathVariable int id) {

        var items = repo.getProductsByMonth(id);

        model.addAttribute("products", items);
        return "products";
    }

    @GetMapping("/months")
    public String month(Model model) {

        var items = repo.getMonths();

        model.addAttribute("title", "Calendar");
        model.addAttribute("months", items);
        return "months";
    }

    @GetMapping("/categories")
    public String category(Model model) {

        var items = repo.getCategories();

        model.addAttribute("title", "Categories");
        model.addAttribute("categories", items);
        return "categories";
    }

    @GetMapping("/bacterias")
    public String bacteria(Model model) {

        var items = repo.getBacterias();

        model.addAttribute("title", "Bacterias");
        model.addAttribute("bacterias", items);
        return "bacterias";
    }

//    @GetMapping("/limits")
//    public String limit(Model model) {
//
//        var items = repo.getLimits();
//
//        model.addAttribute("title", "Limits");
//        model.addAttribute("limits", items);
//        return "limits";
//    }

    @GetMapping("/products/{id}")
    public String editProduct(@PathVariable int id, Model model) {

        var product = repo.getProductById(id);

        if (product == null) {
            product = new Product();
        }

        model.addAttribute("title", product != null ? product.getName() : "");
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        model.addAttribute("categories", repo.getCategories());
        model.addAttribute("months", repo.getMonths());

        return "products_edit";
    }

    @PostMapping("/products/{id}")
    public ModelAndView saveNewProduct(@PathVariable int id, @ModelAttribute("updateDto") ProductUpdateDto dto) {

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

        var month = repo.getMonth(dto.getMonthId());
        product.setMonth(month);

        if (id != 0) {
            repo.updateProduct(product);
        } else {
            repo.addProduct(product);
        }

        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/products/delete/{id}")
    public ModelAndView deleteProducts(@PathVariable int id) {
//        var repo = new DataRepository();
        repo.deleteProduct(id);
        return new ModelAndView("redirect:/products");
    }
    @GetMapping("/products/confirm/{id}")
    public String confirmProductDelete(@PathVariable int id, Model model) {

        var items = repo.getProducts();
        var product = repo.getProductById(id);

        model.addAttribute("title", "Products");
        model.addAttribute("products", items);
        model.addAttribute("confirmDeleteId", product);

        return "products";
    }

    @PostMapping("/products")
    public String searchProducts(@ModelAttribute("searchDto") ProductSearchDto dto, Model model) {

        var items = repo.getProducts(dto);

        model.addAttribute("title", "Products");
        model.addAttribute("products", items);

        return "products";
    }

    @GetMapping("/products/results/{id}")
    public String addTestResult(@PathVariable int id, Model model) {


        model.addAttribute("product", repo.getProductById(id));
        var testDataManager = new TestDataManager();

        model.addAttribute("testResults", testDataManager.getTestResults(id));

        return "product_results";
    }

    @PostMapping("/products/results/{id}")
    public ModelAndView saveTestPosition(@PathVariable int id, @ModelAttribute("testResult") TestUpdateDto updateDto) {
        var product = repo.getProductById(id);
        var bacteria = repo.getBacteriaId(updateDto.getBacteriaId());
        var testResultItem = new TestResultItem(updateDto.getTestId(),
                updateDto.getFinishDate(),
                updateDto.getTestValue(),
                updateDto.getCategoryLimit(),
                updateDto.getBacteriaName(), product, bacteria);

        if (updateDto.getTestId() == 0) {
            repo.add(testResultItem);
        } else {
            repo.save(testResultItem);
        }

        return new ModelAndView("redirect:/products/results/" + id);
    }

    @GetMapping("/products/charts/{id}")
    public String getPieChart(@PathVariable int id, Model model) {
        var results = repo.getTestResultItems(id);

        Map<String, Integer> graphData = new TreeMap<>();
        for (var result : results) {
            graphData.put(result.getBacteriaName(), result.getTestValue());
            graphData.put(result.getBacteriaName() + " limit", result.getCategoryLimit());
        }


//        graphData.put("2017", 1256); //ŠĀDI BIJA PIEMĒRĀ
//        graphData.put("2018", 3856);
//        graphData.put("2019", 19807);
        model.addAttribute("chartData", graphData);
        return "google_charts";
    }


    //!!!!!!!FILE UPLOAD
//    @GetMapping("file_upload")
//    public String uploadFile(Model model) {
//        return "file_upload";
//    }
//
//    @PostMapping("file_upload")
//    public String saveFile(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
//        return "file_upload";
//    }
}

