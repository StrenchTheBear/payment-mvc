package com.usmp.controller;

import com.usmp.dto.Customer;
import com.usmp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/client")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/register")
    public String customerSignUp(@ModelAttribute Customer customer) {
        return "customer/signup";
    }

    @PostMapping("/register")
    public String customerRegistration(Customer customer, Model model, RedirectAttributes attributes) {
        String password = "{noop}".concat(customer.getPassword());
        customer.setPassword(password);
        Map<String, Object> response = this.customerService.customerRegistration(customer);
        if(Objects.nonNull(response.get("errors"))) {
            model.addAttribute("errors", response.get("errors"));
            return "customer/signup";
        }
        attributes.addFlashAttribute("messageSuccess", response.get("message"));
        return "redirect:/";
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
