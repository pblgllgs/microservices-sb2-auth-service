package com.pblgllgs.authservice.service;

import com.pblgllgs.authservice.dto.AuthUserDto;
import com.pblgllgs.authservice.dto.RequestDto;
import com.pblgllgs.authservice.dto.TokenDto;
import com.pblgllgs.authservice.entity.AuthUser;
import com.pblgllgs.authservice.exception.UserAlreadyExistsException;
import com.pblgllgs.authservice.exception.UserNotFoundException;
import com.pblgllgs.authservice.repository.AuthUserRepository;
import com.pblgllgs.authservice.security.JwtProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthUser register(AuthUserDto authUserDto){
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (user.isPresent()){
            throw new UserAlreadyExistsException("User with username "+ authUserDto.getUserName() +" already was registered");
        }
        String password =  passwordEncoder.encode(authUserDto.getPassword());
        AuthUser authUser = AuthUser
                .builder()
                .userName(authUserDto.getUserName())
                .password(password)
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto authUserDto){
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (user.isEmpty()){
            throw new UserNotFoundException("User "+authUserDto.getUserName()+" not found");
        }
        if (passwordEncoder.matches(authUserDto.getPassword(),user.get().getPassword())){
            return new TokenDto(jwtProvider.createToken(user.get()));
        }else {
            throw new JwtException("Cant create a token");
        }
    }

    public TokenDto validate(String token, RequestDto requestDto) {
        if(!jwtProvider.validate(token,requestDto))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUserName(username).isPresent())
            return null;
        return new TokenDto(token);
    }
}
