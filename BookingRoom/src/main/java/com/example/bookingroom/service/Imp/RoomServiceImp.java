package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.reqResp.RoomDTO;
import com.example.bookingroom.dto.reqResp.RoomDetailsDTO;
import com.example.bookingroom.dto.response.PageResponse;
import com.example.bookingroom.entity.Hotel;
import com.example.bookingroom.exception.AppException;
import com.example.bookingroom.exception.ErrorCode;
import com.example.bookingroom.mapper.HotelMapper;
import com.example.bookingroom.mapper.RoomMapper;
import com.example.bookingroom.repository.HotelRepository;
import com.example.bookingroom.repository.RoomRepository;
import com.example.bookingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomMapper roomMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public RoomDetailsDTO getRoom(int hotelId, int roomId) {
        var room = roomRepository.findById(roomId).orElseThrow(
                ()-> new AppException(ErrorCode.ROOM_NOT_EXISTED));

        if(hotelId != room.getHotel().getId())
            throw new AppException(ErrorCode.ROOM_NOT_EXISTED_IN_HOTEL);

        return roomMapper.toRoomDetailsDTO(room);
    }

//    @Override
//    @PreAuthorize("hasRole('ADMIN')")
//    public List<RoomDetailsDTO> getRooms() {
//        var rooms = roomRepository.findAll();
//        return rooms.stream().map(roomMapper::toRoomDetailsDTO).toList();
//    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoomDTO> getRoomsByHotelId(int hotelId) {
        var rooms = roomRepository.findByHotel(findHotel(hotelId));
        return rooms.stream().map(roomMapper::toRoomDTO).toList();
    }

    @Override
    public PageResponse<RoomDTO> getRoomsByHotelId(int hotelId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1,size);
        var pageData = roomRepository.findByHotel(findHotel(hotelId),pageable);
        return PageResponse.<RoomDTO>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().map(roomMapper::toRoomDTO).toList())
                .build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public RoomDetailsDTO create(RoomDetailsDTO roomDetailsDTO) {
        return roomMapper.toRoomDetailsDTO(
                roomRepository.save(roomMapper.toRoom(roomDetailsDTO)));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public RoomDetailsDTO update(int roomId, RoomDetailsDTO roomDetailsDTO) {
        var room = roomRepository.findById(roomId).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_EXISTED));
        roomMapper.updateRoom(room, roomDetailsDTO);
        return roomMapper.toRoomDetailsDTO(roomRepository.save(room));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(int id) {
        roomRepository.deleteById(id);
    }

    private Hotel findHotel(int hotelId){
        return hotelRepository.findById(hotelId).orElseThrow(
                () -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));
    }

}
