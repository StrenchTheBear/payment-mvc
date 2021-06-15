package com.usmp.controller;

import com.usmp.dao.Customer;
import com.usmp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class HomeController {

    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/index")
    public String indexAfterLogin(Authentication auth, HttpSession session) {
        String username = auth.getName();
        Customer customer = this.customerRepository.findByEmailLike(username).get();
        customer.setPassword(null);
        System.out.println(customer);
        if(Objects.isNull(session.getAttribute("customer"))) {
            session.setAttribute("customer", customer);
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/";
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) { this.customerRepository = customerRepository; }

}
