package com.ugromart.payments.models;

public class MomoPayement {

    private Double amount;
    private String currency;
    private String externalId;
    Payer payer;
    private String payerMessage;
    private String payeeNote;

    public MomoPayement(Double amount, String currency, String externalId, String payerMessage, String payeeNote, String partyIdType, String partyId) {
        this.amount = amount;
        this.currency = currency;
        this.externalId = externalId;
        this.payer = payer;
        this.payerMessage = payerMessage;
        this.payeeNote = payeeNote;
        this.payer=new  Payer( partyIdType,  partyId);
    }
    // Getter Methods

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getExternalId() {
        return externalId;
    }

    public Payer getPayer() {
        return payer;
    }

    public String getPayerMessage() {
        return payerMessage;
    }

    public String getPayeeNote() {
        return payeeNote;
    }

    // Setter Methods

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setPayer(Payer payerObject) {
        this.payer = payerObject;
    }

    public void setPayerMessage(String payerMessage) {
        this.payerMessage = payerMessage;
    }

    public void setPayeeNote(String payeeNote) {
        this.payeeNote = payeeNote;
    }
}
 class Payer {
    private String partyIdType;
    private String partyId;

     public Payer(String partyIdType, String partyId) {
         this.partyIdType = partyIdType;
         this.partyId = partyId;
     }

     // Getter Methods

    public String getPartyIdType() {
        return partyIdType;
    }

    public String getPartyId() {
        return partyId;
    }

    // Setter Methods

    public void setPartyIdType(String partyIdType) {
        this.partyIdType = partyIdType;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
}
