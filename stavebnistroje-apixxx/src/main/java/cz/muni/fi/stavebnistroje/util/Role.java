/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.util;

/**
 *
 * @author milos
 */
public enum Role {
    
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");
    private String role;
    
    Role(String role) {
        this.role=role;
    }
    
    public String getRole() {
        return role;
    }
    
    
}
