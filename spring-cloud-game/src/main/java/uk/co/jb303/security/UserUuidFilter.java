package uk.co.jb303.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class UserUuidFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Only enforce this validation logic on POST requests to root
        if ("POST".equalsIgnoreCase(request.getMethod()) && "/".equals(request.getRequestURI())) {
            String userUuid = request.getParameter("USERUUID");

            if (userUuid != null && isValidUuid(userUuid)) {
                // Programmatically authenticate the request for Spring Security
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userUuid, 
                        null, 
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Deny access immediately if missing or invalid
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid USERUUID.");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidUuid(String uuidStr) {
        try {
            // Add your business logic here (e.g., database lookup instead of just format parsing)
            UUID.fromString(uuidStr);
            return true; 
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
