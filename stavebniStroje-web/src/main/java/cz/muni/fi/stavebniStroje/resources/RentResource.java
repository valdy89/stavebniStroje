/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RentDto;
import java.util.Date;

/**
 *
 * @author spito
 */
public class RentResource {
    
    private Long id;
    private Long machineId;
    private Long customerId;
    
    private Date from;
    private Date to;
    
    private String machineName;
    private String customerName;
    
    @JsonCreator
    public RentResource(
            @JsonProperty("machineId") Long machineId,
            @JsonProperty("customerId") Long customerId,
            @JsonProperty("from") Date from,
            @JsonProperty("to") Date to) {
        setId(new Long(0));
        setMachineId(machineId);
        setCustomerId(customerId);
        setFrom(from);
        setTo(to);
    }
    
    public RentResource(RentDto rentDto) {
        setId(rentDto.getId());
        setMachineId(rentDto.getMachine().getId());
        setCustomerId(rentDto.getCustomer().getId());
        setFrom(rentDto.getStartOfRent());
        setTo(rentDto.getEndOfRent());
        setMachineName(rentDto.getMachine().getName());
        setCustomerName(rentDto.getCustomer().getFullName());
    }
    
    public RentDto toDto() {
        RentDto r = new RentDto();
        r.setId(getId());
        r.setMachine(new MachineDto());
        r.getMachine().setId(getMachineId());
        r.setCustomer(new CustomerDto());
        r.getCustomer().setId(getCustomerId());
        r.setStartOfRent(getFrom());
        r.setEndOfRent(getTo());
        return r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
}
