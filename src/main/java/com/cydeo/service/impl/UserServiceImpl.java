package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.findAll().stream().map(userMapper::convertToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {

        return userMapper.convertToUserDto(userRepository.findByUserName(username));
    }

    @Override
    public void save(UserDTO user) {
        userRepository.save(userMapper.convertToUserEntity(user));

    }

    @Override
    public void deleteByUserName(String username) {
        User user2=userRepository.findByUserName(username);
      //  userRepository.deleteById(user2.getId()); we can delete with getting the id of user
        userRepository.deleteByUserName(username); // or with a method we created

    }

    @Override
    public UserDTO update(UserDTO user) {
    User user1=  userRepository.findByUserName(user.getUserName()); // this is not for update purpose.We are getting this from DB, so we can set the id
    User convertedUser= userMapper.convertToUserEntity(user); // Whatever the changes happened on the view side, we are saving to DB layer by converting it
    convertedUser.setId(user1.getId());// here we are setting id to converted object
        userRepository.save(convertedUser);
        return findByUserName(user.getUserName());
    }

    //with the method below, we are going to delete user from the user side, but we will keep the data in db
    // Steps: go to db and get the user by calling it with username => change the isDeleted field to true
    // => save the object in db
    @Override
    public void delete(String username) {
        User user= userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findManagers(String role) {
      return   userRepository.findByRoleDescription("Manager").stream().map(userMapper::convertToUserDto).collect(Collectors.toList());
    }


}
