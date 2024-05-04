package com.ali.java.todo.controller;


import com.ali.java.todo.dto.CreateUserDto;
import com.ali.java.todo.dto.UserDto;
import com.ali.java.todo.exception.NotFoundException;
import com.ali.java.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController  {



    private final UserService userService;
    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public UserDto createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        createUserDto.setPassword(encoder.encode(createUserDto.getPassword()));
        return userService.save(createUserDto);
    }


    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,@RequestBody @Valid CreateUserDto createUserDto) {
        return userService.update(createUserDto);
    }


    @GetMapping
    public List<UserDto>getAllUsers() {
        return userService.findAll();
    }


    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) throws NotFoundException {
        return userService.findById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.delete(id);

    }
}
