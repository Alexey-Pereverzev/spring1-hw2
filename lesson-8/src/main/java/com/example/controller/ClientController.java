package com.example.controller;

import com.example.persist.RoleRepository;
import com.example.service.ClientRepr;
import com.example.service.ClientService;
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
import java.util.HashSet;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    private final RoleRepository roleRepository;

    @Autowired
    public ClientController(ClientService clientService, RoleRepository roleRepository) {
        this.clientService = clientService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size
    ) {
        logger.info("List page requested");
        model.addAttribute("clients", clientService.findAll(page.orElse(1) - 1,
                size.orElse(5)));
        model.addAttribute("roles", roleRepository.findAll());
        return "client";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);
        model.addAttribute("client", clientService.findById(id)
                .orElseThrow(NotFoundException::new));
        model.addAttribute("roles", roleRepository.findAll());
        return "client_form";
    }


    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("client") ClientRepr client, BindingResult result, Model model) {
        logger.info("Update endpoint requested");
        model.addAttribute("roles", roleRepository.findAll());
        if (result.hasErrors()) {
            return "client_form";
        }
        if (!client.getPassword().equals(client.getMatchingPassword())) {
            result.rejectValue("password", "", "Password not matching");
            return "client_form";
        }
        if (client.getId() != null) {
            logger.info("Updating client with id{}", client.getId());
        } else {
            logger.info("Creating new client");
        }

        logger.info("Saving client with id {}", client.getId());
        clientService.save(client);

        return "redirect:/client";
    }


    @GetMapping("/new")
    public String createPage(Model model) {
        logger.info("Create new client request");
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("newClient",
                new ClientRepr("", "", "", new HashSet<>(2)));
        return "client_new";
    }

    @PostMapping("/addNew")
    public String addNewClient(@Valid @ModelAttribute("newClient") ClientRepr client, BindingResult result, Model model) {
        logger.info("Creating new client");
        model.addAttribute("roles", roleRepository.findAll());
        if (result.hasErrors()) {
            return "client_new";
        }
        if (!client.getPassword().equals(client.getMatchingPassword())) {
            result.rejectValue("password", "", "Password not matching");
            return "client_new";
        }
        clientService.save(client);
        return "redirect:/client";
    }


    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Client delete request");
        clientService.delete(id);
        return "redirect:/client";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "modern-login";
    }
}
