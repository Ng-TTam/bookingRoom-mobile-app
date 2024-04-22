package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.DiscountDTO;
import com.example.bookingroom.dto.UserDTO;
import com.example.bookingroom.entity.Discount;
import com.example.bookingroom.entity.User;
import com.example.bookingroom.entity.UserDiscount;
import com.example.bookingroom.repository.DiscountRepository;
import com.example.bookingroom.repository.HotelRepository;
import com.example.bookingroom.repository.UserDiscountRepository;
import com.example.bookingroom.repository.UserRepository;
import com.example.bookingroom.service.DiscountService;
import com.example.bookingroom.service.HotelService;
import com.example.bookingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImp implements DiscountService {

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDiscountRepository userDiscountRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelService hotelService;

    @Override
    public List<DiscountDTO> getListDiscountUserUnowner(UserDTO userDTO) {
        List<Discount> discounts = discountRepository.findUnownedDiscountsByUserId(userDTO.getId());
        List<DiscountDTO> listDiscountDTOs = new ArrayList<>();
        for(Discount discount: discounts){
            DiscountDTO discountDTO = new DiscountDTO();
            discountDTO.setId(discount.getId());
            discountDTO.setTerm(discount.getTerm());
            discountDTO.setType(discount.getType());
            discountDTO.setRewardPoint(discount.getRewardPoint());
            discountDTO.setLeastAmountUsed(discount.getLeastAmountUsed());
            discountDTO.setLargestAmountReduce(discount.getLargestAmountReduce());
            discountDTO.setReducedPrice(discount.getReducedPrice());
            discountDTO.setQuality(discount.getQuality());
            discountDTO.setUsedQuality(discount.getUsedQuality());
            discountDTO.setImage(discount.getImage());
            discountDTO.setOutOfDate(discount.getOutOfDate());
            discountDTO.setHotelDTO(hotelService.getHotelById(discount.getHotel().getId()));

            listDiscountDTOs.add(discountDTO);
        }
        return listDiscountDTOs;
    }

    @Override
    public List<DiscountDTO> getListDiscountUserOwner(UserDTO userDTO) {
        List<Discount> discounts = discountRepository.findOwnedDiscountsByUserId(userDTO.getId());
        List<DiscountDTO> listDiscountDTOs = new ArrayList<>();
        for(Discount discount: discounts){
            DiscountDTO discountDTO = new DiscountDTO();
            discountDTO.setId(discount.getId());
            discountDTO.setTerm(discount.getTerm());
            discountDTO.setType(discount.getType());
            discountDTO.setRewardPoint(discount.getRewardPoint());
            discountDTO.setLeastAmountUsed(discount.getLeastAmountUsed());
            discountDTO.setLargestAmountReduce(discount.getLargestAmountReduce());
            discountDTO.setReducedPrice(discount.getReducedPrice());
            discountDTO.setQuality(discount.getQuality());
            discountDTO.setUsedQuality(discount.getUsedQuality());
            discountDTO.setImage(discount.getImage());
            discountDTO.setOutOfDate(discount.getOutOfDate());
            discountDTO.setHotelDTO(hotelService.getHotelById(discount.getHotel().getId()));

            listDiscountDTOs.add(discountDTO);
        }
        return listDiscountDTOs;
    }

    @Override
    public void exchangeDiscount(UserDTO userDTO, DiscountDTO discountDTO) {
        UserDiscount userDiscount = new UserDiscount();
        userDiscount.setDiscount(discountRepository.findById(discountDTO.getId()));
        userDiscount.setUser(userRepository.findByNameLogin(userDTO.getNameLogin()));
        userDiscount.setUsed(0);
        userDiscountRepository.save(userDiscount);
    }

    @Override
    public DiscountDTO getDiscountByID(int id){
        Discount discount = discountRepository.findById(id);
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(discount.getId());
        discountDTO.setTerm(discount.getTerm());
        discountDTO.setType(discount.getType());
        discountDTO.setRewardPoint(discount.getRewardPoint());
        discountDTO.setLeastAmountUsed(discount.getLeastAmountUsed());
        discountDTO.setLargestAmountReduce(discount.getLargestAmountReduce());
        discountDTO.setReducedPrice(discount.getReducedPrice());
        discountDTO.setQuality(discount.getQuality());
        discountDTO.setUsedQuality(discount.getUsedQuality());
        discountDTO.setImage(discount.getImage());
        discountDTO.setOutOfDate(discount.getOutOfDate());
        discountDTO.setHotelDTO(hotelService.getHotelById(discount.getHotel().getId()));
        return discountDTO;
    }

    @Override
    public void addDiscount(DiscountDTO discountDTO) {
        Discount discount = new Discount();
        discount.setId(discountDTO.getId());
        discount.setTerm(discountDTO.getTerm());
        discount.setType(discountDTO.getType());
        discount.setRewardPoint(discountDTO.getRewardPoint());
        discount.setLeastAmountUsed(discountDTO.getLeastAmountUsed());
        discount.setLargestAmountReduce(discountDTO.getLargestAmountReduce());
        discount.setReducedPrice(discountDTO.getReducedPrice());
        discount.setQuality(discountDTO.getQuality());
        discount.setUsedQuality(discountDTO.getUsedQuality());
        discount.setImage(discountDTO.getImage());
        discount.setOutOfDate(discountDTO.getOutOfDate());
        discount.setHotel(hotelRepository.findById(discountDTO.getHotelDTO().getId()));
        discountRepository.save(discount);
    }
}
