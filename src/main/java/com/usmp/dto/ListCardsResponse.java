package com.usmp.dto;

import java.util.List;

public class ListCardsResponse {

    private Customer customer;
    private List<CardInfo> cards;
    private String message;

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public List<CardInfo> getCards() { return cards; }
    public void setCards(List<CardInfo> cards) { this.cards = cards; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

}
