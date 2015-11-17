/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ivet
 */
public class HotelDTO {
    
    private Long id;
    
    private byte[] image;
    
    private String imageMimeType;
    
    private String name;
    
    private String adress;
    
    private List<RoomDTO> rooms = new ArrayList<>();
    
    private String description;
    
    private Date addedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<RoomDTO> getRooms() {
        return Collections.unmodifiableList(rooms);
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }
    
    public void addRoom(RoomDTO room){
        this.rooms.add(room);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
    
    /**
     * HashCode use parameters name and address
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.adress);
        return hash;
    }

    /**
     * Two hotels are equal if they have same name and address
     * @param obj to compare
     * @return true if two hotels are same or false if not or if there is some mistake
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HotelDTO other = (HotelDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
