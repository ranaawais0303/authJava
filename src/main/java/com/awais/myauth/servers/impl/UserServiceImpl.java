package com.awais.myauth.servers.impl;

import com.awais.myauth.entities.User;
import com.awais.myauth.exceptions.ResourceNotFoundException;
import com.awais.myauth.payloads.UserDto;
import com.awais.myauth.repositories.UserRepo;
import com.awais.myauth.servers.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
   private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public UserDto registerNewUser(UserDto user) {
//        this.modelMapper.map(user,User.class);
//        return user;
//    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword() );

        User updatedUser=this.userRepo.save(user);
        UserDto userDto1=this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos= users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
this.userRepo.delete(user);

    }
    private User dtoToUser(UserDto userDto){

        User user=this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        return user;
    }
    private UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

}
