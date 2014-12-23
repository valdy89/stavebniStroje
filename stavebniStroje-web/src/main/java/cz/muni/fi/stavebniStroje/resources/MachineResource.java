/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import cz.muni.fi.stavebniStroje.util.MachineType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.LoggerFactory;

public class MachineResource {

    private Long id;
    private String name;
    private MachineType type;
    private String description;
    private BigDecimal price;
    private boolean available;

    private Collection<RevisionResource> revisions = new ArrayList<>();

    @JsonCreator
    public MachineResource(
            @JsonProperty("name") String name,
            @JsonProperty("type") MachineType type,
            @JsonProperty("description") String description,
            @JsonProperty("price") BigDecimal price) {
        setId(new Long(0));
        setName(name);
        setType(type);
        setDescription(description);
        setPrice(price);
    }

    public MachineResource(MachineDto machineDto) {
        setId(machineDto.getId());
        setName(machineDto.getName());
        setType(machineDto.getType());
        setDescription(machineDto.getDescription());
        setAvailable(machineDto.isAvailable());
        setPrice(machineDto.getPrice());
        for (RevisionDto r : machineDto.getRevisions()) {
            revisions.add(new RevisionResource(r));
        }
        //setRents(machineDto.getRents());
    }

    public MachineDto toDto() {
        MachineDto m = new MachineDto();
        LoggerFactory.getLogger(MachineResource.class).debug("id: {}", id);
        m.setId(id);
        m.setName(name);
        m.setType(type);
        m.setDescription(description);
        m.setPrice(price);
        for (RevisionResource r : revisions) {
            m.getRevisions().add(r.toDto());
        }
        return m;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MachineType getType() {
        return type;
    }

    public void setType(MachineType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Collection<RevisionResource> getRevisions() {
        return revisions;
    }

    public void setRevisions(Collection<RevisionResource> revisions) {
        this.revisions = revisions;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
