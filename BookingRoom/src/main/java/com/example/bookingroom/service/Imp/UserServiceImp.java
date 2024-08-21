package com.example.bookingRoom.service.Imp;

import com.example.bookingRoom.dto.request.UserChangeInfoRequest;
import com.example.bookingRoom.dto.request.UserChangePassRequest;
import com.example.bookingRoom.dto.request.UserCreationRequest;
import com.example.bookingRoom.dto.request.UserRequest;
import com.example.bookingRoom.dto.response.UserResponse;
import com.example.bookingRoom.entity.User;
import com.example.bookingRoom.exception.AppException;
import com.example.bookingRoom.exception.ErrorCode;
import com.example.bookingRoom.mapper.UserMapper;
import com.example.bookingRoom.repository.UserRepository;
import com.example.bookingRoom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public UserResponse registerUser(UserCreationRequest userCreationRequest){
        User user = userMapper.toUser(userCreationRequest);

        try {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(7);
            user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
//            user.setRole("USER");
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception){
            throw new AppException(ErrorCode.NAME_LOGIN_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse changePassUser(UserChangePassRequest userChangePassRequest) {
        User user = getUserInContext();

        if(!userChangePassRequest.getCurrentPassword().equals(user.getPassword()))
            throw new AppException(ErrorCode.WRONG_CURRENT_PASS);

        user.setPassword(userChangePassRequest.getNewPassword());

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse changeInfoUser(UserChangeInfoRequest userChangeInfoRequest) {
        User user = getUserInContext();
        userMapper.updateInfoUser(user, userChangeInfoRequest);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void changeRewardPointUser(UserRequest userDTOCurrent, UserRequest userDTONew) {
        User user = getUserInContext();
        user.setRewardPoint(userDTONew.getRewardPoint());//using for exchange discount
        userRepository.save(user);
    }

    @Override
    public UserResponse getInfo() {
        return userMapper.toUserResponse(getUserInContext());
    }

    private User getUserInContext(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        return userRepository.findByNameLogin(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}
