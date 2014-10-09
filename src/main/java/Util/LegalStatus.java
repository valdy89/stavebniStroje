/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author milos
 */
public enum LegalStatus {
    
    NATURAL("NATURAL PERSON"),
    LEGAL("LEGAL PERSON");

    private String legalStatus;

    LegalStatus(String status) {
        this.legalStatus = status;
    }

    public String getCustomerStatus() {
        return legalStatus;
    }
}