package com.example.bookingroom.model;

import java.util.List;

public class BookingDTO {
    private int id;
    private String note;
    private int totalPrice;
    private boolean isCancled;
    private List<BookedRoomDTO> bookedRoomDTOs;
    private HotelDTO hotelDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isCancled() {
        return isCancled;
    }

    public void setCancled(boolean cancled) {
        isCancled = cancled;
    }

    public List<BookedRoomDTO> getBookedRoomDTOs() {
        return bookedRoomDTOs;
    }

    public void setBookedRoomDTOs(List<BookedRoomDTO> bookedRoomDTOs) {
        this.bookedRoomDTOs = bookedRoomDTOs;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }
}
