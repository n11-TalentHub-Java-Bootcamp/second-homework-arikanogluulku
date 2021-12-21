package com.arikanogluulku.springfirst.controller;

import com.arikanogluulku.springfirst.converter.UserConverter;
import com.arikanogluulku.springfirst.dto.UserDto;
import com.arikanogluulku.springfirst.entity.User;
import com.arikanogluulku.springfirst.exception.UserInformationIsWrongException;
import com.arikanogluulku.springfirst.service.entityService.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("")
    public List<UserDto> findAll() {
        List<User> users = userEntityService.findAll();
        List<UserDto> userDtos = UserConverter.INSTANCE.convertAllUserToUserDto(users);
        return userDtos;
    }

    @GetMapping("/username/{userName}")
    public UserDto findByUsername(@PathVariable String userName) {
        User user = userEntityService.findByUserName(userName);
        UserDto userDto = UserConverter.INSTANCE.convertUserToUserDto(user);
        return userDto;
    }

    @GetMapping("/phone/{phone}")
    public UserDto findByPhone(@PathVariable String phone) {
        User user = userEntityService.findByPhone(phone);
        UserDto userDto = UserConverter.INSTANCE.convertUserToUserDto(user);
        return userDto;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveUser(@RequestBody UserDto userDto) {
        User user = UserConverter.INSTANCE.convertUserDtoToUser(userDto);

        user = userEntityService.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("")
    public UserDto update(@RequestBody UserDto userDto) {
        User user = UserConverter.INSTANCE.convertUserDtoToUser(userDto);
        user = userEntityService.save(user);
        UserDto userDtoResult = UserConverter.INSTANCE.convertUserToUserDto(user);
        return userDtoResult;

    }

    @DeleteMapping({"/{userName}/{phone}"})
    public void deleteUser(@PathVariable("userName") String userName, @PathVariable("phone") String phone) {
        User userUserName = userEntityService.findByUserName(userName);
        User userPhone = userEntityService.findByPhone(phone);
        if (userPhone.getId() != userUserName.getId()) {
            throw new UserInformationIsWrongException("User's phone: " + phone + " and username :" + userName + " don't match... ");
        }
        userEntityService.deleteUser(userName, phone);
    }

}
