package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.reqResp.DiscountDTO;
import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.entity.Discount;
import com.example.bookingroom.repository.DiscountRepository;
import com.example.bookingroom.repository.HotelRepository;
import com.example.bookingroom.repository.UserDiscountRepository;
import com.example.bookingroom.repository.UserRepository;
import com.example.bookingroom.service.DiscountService;
import com.example.bookingroom.service.HotelService;
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
    public List<DiscountDTO> getListDiscountUserUnowner(UserRequest userDTO) {
//        List<Discount> discounts = discountRepository.findUnownedDiscountsByUserId(userDTO.getId());
        List<DiscountDTO> listDiscountDTOs = new ArrayList<>();
//        for(Discount discount: discounts){
//            DiscountDTO discountDTO = new DiscountDTO();
//            discountDTO.setId(discount.getId());
//            discountDTO.setTerm(discount.getTerm());
//            discountDTO.setType(discount.getType());
//            discountDTO.setRewardPoint(discount.getRewardPoint());
//            discountDTO.setLeastAmountUsed(discount.getLeastAmountUsed());
//            discountDTO.setLargestAmountReduce(discount.getLargestAmountReduce());
//            discountDTO.setReducedPrice(discount.getReducedPrice());
//            discountDTO.setQuality(discount.getQuality());
//            discountDTO.setUsedQuality(discount.getUsedQuality());
//            discountDTO.setImage(discount.getImage());
//            discountDTO.setOutOfDate(discount.getOutOfDate());
//            discountDTO.setHotelDTO(hotelService.getHotelById(discount.getHotel().getId()));
//
//            listDiscountDTOs.add(discountDTO);
//        }
        return listDiscountDTOs;
    }

    @Override
    public List<DiscountDTO> getListDiscountUserOwner(UserRequest userDTO) {
//        List<Discount> discounts = discountRepository.findOwnedDiscountsByUserId(userDTO.getId());
        List<DiscountDTO> listDiscountDTOs = new ArrayList<>();
//        for(Discount discount: discounts){
//            DiscountDTO discountDTO = new DiscountDTO();
//            discountDTO.setId(discount.getId());
//            discountDTO.setTerm(discount.getTerm());
//            discountDTO.setType(discount.getType());
//            discountDTO.setRewardPoint(discount.getRewardPoint());
//            discountDTO.setLeastAmountUsed(discount.getLeastAmountUsed());
//            discountDTO.setLargestAmountReduce(discount.getLargestAmountReduce());
//            discountDTO.setReducedPrice(discount.getReducedPrice());
//            discountDTO.setQuality(discount.getQuality());
//            discountDTO.setUsedQuality(discount.getUsedQuality());
//            discountDTO.setImage(discount.getImage());
//            discountDTO.setOutOfDate(discount.getOutOfDate());
//            discountDTO.setHotelDTO(hotelService.getHotelById(discount.getHotel().getId()));
//
//            listDiscountDTOs.add(discountDTO);
//        }
        return listDiscountDTOs;
    }

    @Override
    public void exchangeDiscount(UserRequest userDTO, DiscountDTO discountDTO) {
//        UserDiscount userDiscount = new UserDiscount();
//        userDiscount.setDiscount(discountRepository.findById(discountDTO.getId()));
//        userDiscount.setUser(userRepository.findByNameLogin(userDTO.getNameLogin()));
//        userDiscount.setUsed(0);
//        userDiscountRepository.save(userDiscount);
    }

    @Override
    public DiscountDTO getDiscountByID(int id){
//        Discount discount = discountRepository.findById(id);
//        DiscountDTO discountDTO = new DiscountDTO();
//        discountDTO.setId(discount.getId());
//        discountDTO.setTerm(discount.getTerm());
//        discountDTO.setType(discount.getType());
//        discountDTO.setRewardPoint(discount.getRewardPoint());
//        discountDTO.setLeastAmountUsed(discount.getLeastAmountUsed());
//        discountDTO.setLargestAmountReduce(discount.getLargestAmountReduce());
//        discountDTO.setReducedPrice(discount.getReducedPrice());
//        discountDTO.setQuality(discount.getQuality());
//        discountDTO.setUsedQuality(discount.getUsedQuality());
//        discountDTO.setImage(discount.getImage());
//        discountDTO.setOutOfDate(discount.getOutOfDate());
//        discountDTO.setHotelDTO(hotelService.getHotelById(discount.getHotel().getId()));
        return null;
    }

    @Override
    public void addDiscount(DiscountDTO discountDTO) {
    }
}
