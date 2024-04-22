package com.example.bookingroom.model;

import java.util.List;

public class HotelDTO {
    private int id;
    private String name;
    private int starLevel;
    private String address;
    private String description;
    private List<RoomDTO> listRoom;
    private String image;

    public HotelDTO() {
    }

    public HotelDTO(int id, String name, int starLevel, String address, String description, List<RoomDTO> listRoom, String image) {
        this.id = id;
        this.name = name;
        this.starLevel = starLevel;
        this.address = address;
        this.description = description;
        this.listRoom = listRoom;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoomDTO> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<RoomDTO> listRoom) {
        this.listRoom = listRoom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
