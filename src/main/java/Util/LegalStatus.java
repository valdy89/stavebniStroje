/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 * Enum used to store different legal status which can have a customer
 * @author milos
 */
public enum LegalStatus {

    NATURAL("NATURAL PERSON"),
    LEGAL("LEGAL PERSON");

    private String legalStatus;

/**
 * method for adding legal status
 * @param status which should be added
 */   
    LegalStatus(String status) {
        this.legalStatus = status;
    }
/**
 * This method returns legal status
 * @return legalStatus
 */
    public String getCustomerStatus() {
        return legalStatus;
    }
}
