package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.reqResp.HotelDTO;
import com.example.bookingroom.dto.reqResp.HotelDetailsCreationDTO;
import com.example.bookingroom.dto.reqResp.HotelDetailsDTO;
import com.example.bookingroom.dto.response.PageResponse;
import com.example.bookingroom.entity.Hotel;
import com.example.bookingroom.exception.AppException;
import com.example.bookingroom.exception.ErrorCode;
import com.example.bookingroom.mapper.HotelMapper;
import com.example.bookingroom.mapper.RoomMapper;
import com.example.bookingroom.repository.HotelRepository;
import com.example.bookingroom.repository.RoomRepository;
import com.example.bookingroom.service.HotelService;
import com.example.bookingroom.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class HotelServiceImp implements HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    ImageService imageService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public HotelDetailsDTO getHotel(int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));
        return hotelMapper.toHotelDetailsDTO(hotel);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<HotelDTO> getHotels() {
        var hotels = hotelRepository.findAll();
        return hotels.stream().map(hotelMapper::toHotelDTO).toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<HotelDetailsDTO> getHotelsDetails() {
        var hotels = hotelRepository.findAll();
        return hotels.stream().map(hotelMapper::toHotelDetailsDTO).toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public HotelDetailsDTO create(HotelDetailsCreationDTO hotelDetailsCreationDTO, MultipartFile imageFile) {
        HotelDetailsDTO hotelDetailsDTO = hotelMapper.toHotelDetailsDTO(hotelDetailsCreationDTO);
        try {
            hotelDetailsDTO.setImage(imageService.upload(imageFile, "hotels"));
        } catch (IOException e) {
            throw new AppException(ErrorCode.BLANK_IMAGE);
        }
        var hotel = hotelRepository.save(hotelMapper.toHotel(hotelDetailsDTO));
        hotelDetailsDTO.getRooms().forEach(roomDTO -> {
            var room = roomMapper.toRoom(roomDTO);
            room.setHotel(hotel);

            roomRepository.save(room);
            log.info(String.valueOf(room.isActive()));
        });
        HotelDetailsDTO hotelDetailsDTO1 = hotelMapper.toHotelDetailsDTO(hotel);
        log.info(String.valueOf(hotelDetailsDTO1.getRooms().get(0).isActive()));
        //error when parse room to roomDTO, cannot parse correctly value of isActive
        //not add image of each room yet -> func
        return hotelDetailsDTO1;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public HotelDTO update(int hotelId,HotelDTO hotelDTO) {
        var hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));
        hotelMapper.updateHotel(hotel, hotelDTO);
        return hotelMapper.toHotelDTO(hotelRepository.save(hotel));
    }

    //get hotels using pagination
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PageResponse<HotelDTO> getHotels(int page, int size) {
        Sort sort = Sort.by("starLevel").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = hotelRepository.findAll(pageable);
        return PageResponse.<HotelDTO>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().map(hotelMapper::toHotelDTO).toList())
                .build();
    }

}
