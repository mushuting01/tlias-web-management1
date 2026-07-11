package org.example.tliaswebmanagement.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        log.info("请求路径: {}", url);

        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            String username = claims.get("username", String.class);
            
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            log.info("JWT校验通过，用户: {}", username);

        } catch (Exception e) {
            log.error("JWT解析失败: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}