package com.usmp.controller;

import com.usmp.dao.Customer;
import com.usmp.dto.ListCardsResponse;
import com.usmp.dto.RegisterCard;
import com.usmp.service.CardService;
import com.usmp.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/card")
public class CardController {

    private CardService cardService;

    @GetMapping("/{customerId}")
    public String getCustomerCards(@PathVariable Integer customerId, Model model) {
        Map<String, Object> response = this.cardService.getCustomerCards(customerId);
        if(Objects.isNull(response.get("cards"))) {
            model.addAttribute("message", "Aún no tiene ninguna tarjeta registrada");
            return "card/customerCards";
        }
        ListCardsResponse customerCards = JsonHelper.getInstance().fromMap((Map) response.get("cards"), ListCardsResponse.class);
        model.addAttribute("cards", customerCards.getCards());
        model.addAttribute("message", response.get("message"));
        return "card/customerCards";
    }

    @GetMapping("/remove/{cardNumber}")
    public String deleteCustomerCard(@PathVariable String cardNumber, Model model, HttpSession session,
                                     RedirectAttributes attributes) {
        ResponseEntity<Map> response = this.cardService.deleteCustomerCard(cardNumber);
        Customer customer = (Customer) session.getAttribute("customer");
        if(response.getStatusCode().value() == 404) {
            model.addAttribute("messageAdvice", "La tarjeta a eliminar no se encuentra registrada en la BD");
            return "redirect:/card/".concat(customer.getId().toString());
        }
        attributes.addFlashAttribute("messageSuccess", "La operación se realizó con éxito");
        return "redirect:/card/".concat(customer.getId().toString());
    }

    @GetMapping("/register")
    public String registerCard(@ModelAttribute RegisterCard registerCard) {
        return "card/cardRegistration";
    }

    @PostMapping("/register")
    public String saveCard(RegisterCard registerCard, Model model, RedirectAttributes attributes, HttpSession session) {
        Map<String, Object> response = this.cardService.insertCustomerCard(registerCard);
        Customer customer = (Customer) session.getAttribute("customer");
        if (Objects.isNull(response)) {
            model.addAttribute("messageAdvice", "Los datos ingresados de la tarjeta no son los correctos.");
            return "card/cardRegistration";
        }
        attributes.addFlashAttribute("messageSuccess", response.get("message"));
        return "redirect:/card/".concat(customer.getId().toString());
    }

    @Autowired
    public void setCardService(CardService cardService) { this.cardService = cardService; }

}
