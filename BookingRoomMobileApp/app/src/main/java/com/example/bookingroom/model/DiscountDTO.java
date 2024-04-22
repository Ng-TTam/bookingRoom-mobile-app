package com.example.bookingroom.model;

import java.util.Date;

public class DiscountDTO {
    private int id;
    private String term;
    private String type;
    private int rewardPoint;
    private int leastAmountUsed;
    private int largestAmountReduce;
    private int reducedPrice;
    private int quality;
    private int usedQuality;
    private String image;
    private String outOfDate;
    private HotelDTO hotelDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public int getLeastAmountUsed() {
        return leastAmountUsed;
    }

    public void setLeastAmountUsed(int leastAmountUsed) {
        this.leastAmountUsed = leastAmountUsed;
    }

    public int getLargestAmountReduce() {
        return largestAmountReduce;
    }

    public void setLargestAmountReduce(int largestAmountReduce) {
        this.largestAmountReduce = largestAmountReduce;
    }

    public int getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(int reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getUsedQuality() {
        return usedQuality;
    }

    public void setUsedQuality(int usedQuality) {
        this.usedQuality = usedQuality;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOutOfDate() {
        return outOfDate;
    }

    public void setOutOfDate(String outOfDate) {
        this.outOfDate = outOfDate;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }
}