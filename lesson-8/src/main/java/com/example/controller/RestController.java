package com.example.controller;


import com.example.service.ProductRepr;
import com.example.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Controller                              //  это контроллер
@RequestMapping("/product")            //  какие url он обрабатывает
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    private final ProductService productService;

    @Autowired
    public RestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String listPage(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {
        logger.info("List page requested");
        model.addAttribute("products", productService.findAll(page.orElse(1)-1,
                size.orElse(10)));
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("product") ProductRepr product, BindingResult result) {
        logger.info("Update endpoint requested");

        if (result.hasErrors()) {
            return "product_form";
        }

        if (product.getId() != null) {
            logger.info("Updating product with id{}", product.getId());
        } else {
            logger.info("Creating new product");
        }
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String createPage(Model model) {
        logger.info("Creating product page requested");
        model.addAttribute("newProduct", new ProductRepr("",new BigDecimal(0)));
        return "product_new";
    }

    @PostMapping("/addNew")
    public String addNewProduct(@Valid @ModelAttribute("newProduct") ProductRepr product, BindingResult result) {
        logger.info("Creating new product");
        if (result.hasErrors()) {
            return "product_new";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Product delete request");
        productService.delete(id);
        return "redirect:/product";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }
}
