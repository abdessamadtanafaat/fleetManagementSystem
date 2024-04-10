package com.system.gestionautomobile.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionHandlerFilter  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException e) {
            handleException(response, HttpServletResponse.SC_NOT_FOUND,"Email doesn't exist");
        } catch (JWTVerificationException e) {
            if ("Token blacklisted".equals(e.getMessage())) {
                handleException(response, HttpServletResponse.SC_FORBIDDEN, "Access Denied: Token blacklisted");
            } else {
                handleException(response, HttpServletResponse.SC_FORBIDDEN, "Access Denied: JWT NOT VALID");
            }
        } catch (AccessDeniedException e) {
                handleException(response, HttpServletResponse.SC_FORBIDDEN, "Forbidden: Insufficient permissions");
        } catch (RuntimeException e) {
            String message = e.getMessage();
            int statusCode = message.startsWith("Unauthorized") ?
                    HttpServletResponse.SC_UNAUTHORIZED :
                    (message.startsWith("Forbidden") ? HttpServletResponse.SC_FORBIDDEN :
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            handleException(response, statusCode, message);
        }
}
    private void handleException(HttpServletResponse response, int statusCode, String message) throws IOException{
        response.setStatus(statusCode);
        response.getWriter().write(message);
        response.getWriter().flush();
    }
}
