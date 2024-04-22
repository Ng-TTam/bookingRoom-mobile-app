package com.example.bookingroom.model;

public class BookedRoomDTO {
    private int id;
    private String checkIn;
    private String checkOut;
    private int price;
    private int isCheckIn;
    private RoomDTO roomDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsCheckIn() {
        return isCheckIn;
    }

    public void setIsCheckIn(int isCheckIn) {
        this.isCheckIn = isCheckIn;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }
}

