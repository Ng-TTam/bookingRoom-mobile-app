package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.reqResp.BookedRoomDTO;
import com.example.bookingroom.entity.BookedRoom;
import com.example.bookingroom.exception.AppException;
import com.example.bookingroom.exception.ErrorCode;
import com.example.bookingroom.mapper.BookedRoomMapper;
import com.example.bookingroom.repository.BookedRoomRepository;
import com.example.bookingroom.service.BookedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookedRoomServiceImp implements BookedRoomService {
    @Autowired
    BookedRoomRepository bookedRoomRepository;
    @Autowired
    BookedRoomMapper bookedRoomMapper;

    @Override
    public BookedRoomDTO getBookedRoom(int bookingId, int bookedRoomId) {
        var bookedRoom = bookedRoomRepository.findById(bookedRoomId).orElseThrow(
                () -> new AppException(ErrorCode.BOOKED_ROOM_NOT_EXISTED)
        );

        if(bookingId != bookedRoom.getBooking().getId())
            throw new AppException(ErrorCode.PERMISSION_DENIED);//booking can not contain booked room

        return bookedRoomMapper.toBookedRoomDTO(bookedRoom);
    }

    @Override
    @Transactional
    public void updateBookedRoom(int bookedRoomId, BookedRoomDTO bookedRoomDTO) {
        var bookedRoom = bookedRoomRepository.findById(bookedRoomId).orElseThrow(
                () -> new AppException(ErrorCode.BOOKED_ROOM_NOT_EXISTED)
        );

        bookedRoomMapper.updateBookedRoom(bookedRoom, bookedRoomDTO);
        bookedRoomRepository.save(bookedRoom);
    }
}
