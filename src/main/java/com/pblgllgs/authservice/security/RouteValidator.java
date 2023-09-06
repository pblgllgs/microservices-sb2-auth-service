package com.pblgllgs.authservice.security;

import com.pblgllgs.authservice.dto.RequestDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {

    private List<RequestDto> paths;

    public boolean isAdminPath(RequestDto requestDto){
        return paths
                .stream()
                .anyMatch(
                        path-> Pattern.matches(path.getUri(),requestDto.getUri())
                               &&
                               path.getMethod().equals(requestDto.getMethod())
                );
    }
}
