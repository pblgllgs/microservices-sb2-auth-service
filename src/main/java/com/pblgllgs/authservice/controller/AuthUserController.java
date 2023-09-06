package com.pblgllgs.authservice.controller;

import com.pblgllgs.authservice.dto.AuthUserDto;
import com.pblgllgs.authservice.dto.NewUserDto;
import com.pblgllgs.authservice.dto.RequestDto;
import com.pblgllgs.authservice.dto.TokenDto;
import com.pblgllgs.authservice.entity.AuthUser;
import com.pblgllgs.authservice.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto){
        return new ResponseEntity<>(authUserService.login(authUserDto), HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto){
        return new ResponseEntity<>(authUserService.validate(token,requestDto),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@RequestBody NewUserDto newUserDto){
        return new ResponseEntity<>(authUserService.register(newUserDto), HttpStatus.CREATED);
    }
}
