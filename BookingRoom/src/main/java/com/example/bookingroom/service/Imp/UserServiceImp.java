package com.example.bookingroom.service.Imp;

import com.example.bookingroom.dto.UserDTO;
import com.example.bookingroom.entity.User;
import com.example.bookingroom.repository.UserRepository;
import com.example.bookingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void registerUser(UserDTO userDTO){
        User user = new User();
        user.setNameAccount(userDTO.getNameAccount());
        user.setNameLogin(userDTO.getNameLogin());
        user.setBirth(userDTO.getBirth());
        user.setEmail(userDTO.getEmail());
        user.setNumber(userDTO.getNumber());
        user.setRewardPoint(0);
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserByNameLogin(String nameLogin) {
        User user = userRepository.findByNameLogin(nameLogin);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setNameAccount(user.getNameAccount());
            userDTO.setNameLogin(user.getNameLogin());
            userDTO.setNumber(user.getNumber());
            userDTO.setBirth(user.getBirth());
            userDTO.setEmail(user.getEmail());
            userDTO.setRewardPoint(user.getRewardPoint());
            return userDTO;
        }
        return null;
    }

    @Override
    public void changePassUser(UserDTO userDTO, String newPassword) {
        User user = userRepository.findByNameLogin(userDTO.getNameLogin());
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public void changeInfoUser(UserDTO userDTOCurrent, UserDTO userDTONew) {
        User user = userRepository.findByNameLogin(userDTOCurrent.getNameLogin());
        user.setNameAccount(userDTONew.getNameAccount());
        user.setNumber(userDTONew.getNumber());
        user.setEmail(userDTONew.getEmail());
        user.setBirth(userDTONew.getBirth());
        userRepository.save(user);
    }

    @Override
    public void changeRewardPointUser(UserDTO userDTOCurrent, UserDTO userDTONew) {
        User user = userRepository.findByNameLogin(userDTOCurrent.getNameLogin());
        user.setRewardPoint(userDTONew.getRewardPoint());//using for exchange discount
        userRepository.save(user);
    }

    @Override
    public boolean equalPassword(UserDTO userDTO, String password) {
        if(userRepository.findByNameLogin(userDTO.getNameLogin()).getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
