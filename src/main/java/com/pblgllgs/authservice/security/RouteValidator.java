package com.pblgllgs.authservice.security;

import com.pblgllgs.authservice.dto.RequestDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {

    private List<RequestDto> paths;

    public boolean isAdminPath(RequestDto dto) {
        return paths.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }
}