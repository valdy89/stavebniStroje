/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.util;

/**
 *
 * @author spito
 */
public enum MachineType {
    
    TRACTOR("Tractor"),
    EXCAVATOR("Excavator"),
    LORRY("Lorry");
    
    private String machineType;
/**
 * method for adding machine type
 * @param type which should be added
 */   
    MachineType(String type) {
        this.machineType = type;
    }
/**
 * This method returns machine type
 * @return machineType
 */
    public String getMachineType() {
        return machineType;
    }    
}
