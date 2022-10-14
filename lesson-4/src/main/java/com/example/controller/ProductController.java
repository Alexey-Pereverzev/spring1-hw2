package com.example.controller;


import com.example.persist.Product;
import com.example.persist.ProductMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller                              //  это контроллер
@RequestMapping("/product")            //  какие url он обрабатывает
public class ProductController {

    private final ProductMatrix matrix;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductMatrix matrix) {
        this.matrix = matrix;
    }


    @GetMapping
    public String listPage(Model model) {
        logger.info("List page requested");
        model.addAttribute("products", matrix.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);
        model.addAttribute("product", matrix.findById(id));
        return "product_form";
    }

    @PostMapping("/update")
    public String update(Product product) {
        logger.info("Update endpoint requested");
        if (product.getId() != -1) {
            logger.info("Updating product with id{}", product.getId());
            matrix.update(product);
        } else {
            logger.info("Creating new product");
            matrix.insert(product);
        }
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String createPage(Model model) {
        logger.info("Creating product page requested");
        model.addAttribute("newProduct", new Product(0,"","0"));
        return "product_new";
    }

    @PostMapping("/addNew")
    public String addNewProduct(Product product) {
        logger.info("Creating new product");
        matrix.insert(product);
        return "redirect:/product";
    }


    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Product delete request");
        matrix.delete(id);
        return "redirect:/product";
    }
}
