package com.usmp.dto;

import java.math.BigDecimal;

public class Payment {

    private String cardName;
    private String cardNumber;
    private String expirationDate;
    private Integer cvcCode;
    private String documentNumber;
    private BigDecimal amount;
    private Integer customerId;

    public String getCardName() { return cardName; }
    public void setCardName(String cardName) { this.cardName = cardName; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }
    public Integer getCvcCode() { return cvcCode; }
    public void setCvcCode(Integer cvcCode) { this.cvcCode = cvcCode; }
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

}
