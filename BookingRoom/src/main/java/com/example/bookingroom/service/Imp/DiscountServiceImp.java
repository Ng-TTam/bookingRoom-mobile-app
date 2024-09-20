package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.reqResp.DiscountDTO;
import com.example.bookingroom.dto.reqResp.DiscountDetailsDTO;
import com.example.bookingroom.dto.response.PageResponse;
import com.example.bookingroom.entity.Discount;
import com.example.bookingroom.entity.User;
import com.example.bookingroom.exception.AppException;
import com.example.bookingroom.exception.ErrorCode;
import com.example.bookingroom.mapper.DiscountMapper;
import com.example.bookingroom.repository.DiscountRepository;
import com.example.bookingroom.repository.HotelRepository;
import com.example.bookingroom.repository.UserDiscountRepository;
import com.example.bookingroom.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DiscountServiceImp implements DiscountService {
    @Autowired
    DiscountRepository discountRepository;
    @Autowired
    UserDiscountRepository userDiscountRepository;
    @Autowired
    DiscountMapper discountMapper;
    @Autowired
    HotelRepository hotelRepository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public DiscountDetailsDTO create(DiscountDetailsDTO discountDetailsDTO, int hotelId) {
        Discount discount = discountMapper.toDiscount(discountDetailsDTO);
        if(hotelId != discountDetailsDTO.getHotel().getId())
            throw new AppException(ErrorCode.PERMISSION_DENIED);

        return discountMapper.toDiscountDetailsDTO(discountRepository.save(discount));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PageResponse<DiscountDetailsDTO> getDiscounts(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = discountRepository.findAll(pageable);
        
        return PageResponse.<DiscountDetailsDTO>builder()
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .data(pageData.getContent().stream().map(discountMapper::toDiscountDetailsDTO).toList())
                .build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PageResponse<DiscountDTO> getDiscounts(int hotelId, int page, int size) {
        var hotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));

        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = discountRepository.findByHotel(hotel, pageable);

        return PageResponse.<DiscountDTO>builder()
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .data(pageData.getContent().stream().map(discountMapper::toDiscountDTO).toList())
                .build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') && hasRole('USER')")
    public DiscountDetailsDTO getDiscount(int hotelId, String code) {
        var discountDetailsDTO = discountMapper.toDiscountDetailsDTO(discountRepository.findById(code).orElseThrow(
                () -> new AppException(ErrorCode.DISCOUNT_NOT_EXISTED)
        ));

        if(discountDetailsDTO.getHotel().getId() != hotelId)
            throw new AppException(ErrorCode.DISCOUNT_NOT_EXISTED);

        return discountDetailsDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public DiscountDTO updateDiscount(DiscountDTO discountDTO) {
        var discount = discountRepository.findById(discountDTO.getCode()).orElseThrow(
                () -> new AppException(ErrorCode.DISCOUNT_NOT_EXISTED));

        discountMapper.updateDiscount(discount, discountDTO);

        if(discount.getEndDate().isBefore(LocalDate.now()))
            throw new AppException(ErrorCode.DISCOUNT_EXPIRED);

        return discountMapper.toDiscountDTO(discountRepository.save(discount));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(int hotelId, String code) {
        var discount = discountRepository.findById(code).orElseThrow(() -> new AppException(ErrorCode.DISCOUNT_NOT_EXISTED));

        if(discount.getHotel().getId() != hotelId)
            throw new AppException(ErrorCode.PERMISSION_DENIED);

        discountRepository.deleteById(code);
    }

    @Override
    public boolean checkDiscountUsed(User user, Discount discount) {
        return userDiscountRepository.existsByUserAndDiscount(user,discount);
    }

}
