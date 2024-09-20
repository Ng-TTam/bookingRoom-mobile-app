package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.reqResp.DiscountDTO;
import com.example.bookingroom.dto.request.UserRequest;
import com.example.bookingroom.entity.UserDiscount;
import com.example.bookingroom.repository.DiscountRepository;
import com.example.bookingroom.repository.UserDiscountRepository;
import com.example.bookingroom.repository.UserRepository;
import com.example.bookingroom.service.UserDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDiscountServiceImp implements UserDiscountService {
    @Autowired
    UserDiscountRepository userDiscountRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addUserDiscount(UserRequest userDTO, DiscountDTO discountDTO) {
        UserDiscount userDiscount = new UserDiscount();
//        userDiscount.setDiscount(discountRepository.findById(discountDTO.getId()));
////        userDiscount.setUser(userRepository.findByNameLogin(userDTO.getNameLogin()));
//        userDiscount.setUsed(0);
        userDiscountRepository.save(userDiscount);
    }
}
