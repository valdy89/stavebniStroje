/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.util;

/**
 *
 * @author milos
 */
public enum Role {
    
    ROLE_USER("User"),
    ROLE_ADMIN("Admin");
    private String role;
    
    Role(String role) {
        this.role=role;
    }
    
    public String getRole() {
        return role;
    }
    
    
}
