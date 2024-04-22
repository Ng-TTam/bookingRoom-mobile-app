package com.example.bookingroom.service;

import com.example.bookingroom.dto.UserDTO;

public interface UserService {
    void registerUser(UserDTO userDTO);
    UserDTO getUserByNameLogin(String nameLogin);
    public void changeRewardPointUser(UserDTO userDTOCurrent, UserDTO userDTONew);
    boolean equalPassword(UserDTO userDTO, String password);
    void changePassUser(UserDTO userDTO, String newPassword);
    void changeInfoUser(UserDTO userDTOCurrent, UserDTO userDTONew);
}
