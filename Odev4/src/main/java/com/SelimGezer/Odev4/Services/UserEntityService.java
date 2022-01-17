package com.SelimGezer.Odev4.Services;

import com.SelimGezer.Odev4.Dao.UserDao;
import com.SelimGezer.Odev4.Dtos.UserRequestDto;
import com.SelimGezer.Odev4.Dtos.UserResponceDto;
import com.SelimGezer.Odev4.Entities.User;
import com.SelimGezer.Odev4.Exception.UserNotFound;
import com.SelimGezer.Odev4.Mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

    private final UserDao userDao;

    public UserEntityService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserResponceDto> getAllUsers() {
        return userDao.findAll().stream().map(user -> UserMapper.toDto(user)).collect(Collectors.toList());
    }

    public UserResponceDto getUserById(Long id) {
        User checkUser = checkUserById(id);
        return UserMapper.toDto(checkUser);
    }

    public UserResponceDto addUser(UserRequestDto userRequestDto) {
        User user=UserMapper.toEntity(userRequestDto);
        User save = userDao.save(user);
        return UserMapper.toDto(save);
    }

    public UserResponceDto updateUserById(Long id, UserRequestDto userRequestDto) {
        User checkUser = checkUserById(id);
        checkUser.setEmail(userRequestDto.getEmail());
        checkUser.setName(userRequestDto.getName());
        checkUser.setSurname(userRequestDto.getSurname());
        checkUser.setPhoneNumber(userRequestDto.getPhoneNumber());
        User save = userDao.save(checkUser);
        return UserMapper.toDto(save);
    }

    public ResponseEntity deleteUserById(Long id) {

        User checkUser = checkUserById(id);
        userDao.delete(checkUser);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }


    public User checkUserById(Long id){
        Optional<User> optionalUser = userDao.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            throw new UserNotFound(String.format("%d id sine sahip bir kullanıcı bulunamadi!", id));
        }
    }
}
