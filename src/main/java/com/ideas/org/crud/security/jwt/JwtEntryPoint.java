package com.ideas.org.crud.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideas.org.crud.controller.CategoryController;
import com.ideas.org.crud.exeption.ErrorMessage;
import com.ideas.org.crud.exeption.UnauthorizedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.WebApplicationType;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Lanza un excepcion si hay un problema en el filter, o cuando no
 * existe un token cuando es necesario
 */
@Service
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LogManager.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        LOGGER.error("Error entryPoint");

        ErrorMessage errorMessage = new ErrorMessage(new UnauthorizedException("Token not valid or not authorize"), HttpStatus.UNAUTHORIZED.value());
        response.setStatus(errorMessage.getCode());
        response.setContentType("application/json");
        response.getWriter().write( new ObjectMapper().writeValueAsString(errorMessage));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
