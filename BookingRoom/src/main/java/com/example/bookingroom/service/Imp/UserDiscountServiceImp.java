package com.example.bookingRoom.service.Imp;

import com.example.bookingRoom.dto.DiscountDTO;
import com.example.bookingRoom.dto.request.UserRequest;
import com.example.bookingRoom.entity.UserDiscount;
import com.example.bookingRoom.repository.DiscountRepository;
import com.example.bookingRoom.repository.UserDiscountRepository;
import com.example.bookingRoom.repository.UserRepository;
import com.example.bookingRoom.service.UserDiscountService;
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
        userDiscount.setDiscount(discountRepository.findById(discountDTO.getId()));
//        userDiscount.setUser(userRepository.findByNameLogin(userDTO.getNameLogin()));
        userDiscount.setUsed(0);
        userDiscountRepository.save(userDiscount);
    }
}
