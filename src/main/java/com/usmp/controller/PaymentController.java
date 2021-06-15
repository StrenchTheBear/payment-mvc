package com.usmp.controller;

import com.usmp.dto.Payment;
import com.usmp.service.PaymentService;
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
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping("/register")
    public String formPayment(@ModelAttribute Payment payment) {
        return "payment/formPayment";
    }

    @PostMapping("/do")
    public String doPayment(Payment payment, Model model, RedirectAttributes attributes) {
        Map<String, Object> response = this.paymentService.doPayment(payment);
        if(Objects.isNull(response.get("payment"))) {
            model.addAttribute("messageAdvice", response.get("message"));
            return "payment/formPayment";
        }
        attributes.addFlashAttribute("messageSuccess", response.get("message"));
        return "redirect:/";
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) { this.paymentService = paymentService; }

}
