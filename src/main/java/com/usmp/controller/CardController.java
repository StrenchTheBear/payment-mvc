package com.usmp.controller;

import com.usmp.dao.Customer;
import com.usmp.dto.ListCardsResponse;
import com.usmp.service.CardService;
import com.usmp.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/register")
    public String registerCard() {
        return "card/cardRegistration";
    }

    @GetMapping("/remove/{cardNumber}")
    public String deleteCustomerCard(@PathVariable String cardNumber, Model model, HttpSession session,
                                     RedirectAttributes attributes) {
        ResponseEntity<Map> response = this.cardService.deleteCustomerCard(cardNumber);
        Customer customer = (Customer) session.getAttribute("customer");;
        if(response.getStatusCode().value() == 404) {
            model.addAttribute("messageAdvice", "La tarjeta a eliminar no se encuentra registrada en la BD");
            return "redirect:/card/".concat(customer.getId().toString());
        }
        attributes.addFlashAttribute("messageSuccess", "La operación se realizó con éxito");
        return "redirect:/card/".concat(customer.getId().toString());
    }

    @Autowired
    public void setCardService(CardService cardService) { this.cardService = cardService; }

}
