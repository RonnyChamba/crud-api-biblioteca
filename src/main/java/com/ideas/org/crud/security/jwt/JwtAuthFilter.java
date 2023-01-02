package com.ideas.org.crud.security.jwt;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class what to execute by each request
 */

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {


    private final JwtUtils jwtUtils;

    private final UserDetailsService userDetailsService;

    private static final Logger LOGGER = LogManager.getLogger(JwtEntryPoint.class);

    /**
     * Este metodo intercepta la peticion y verifica si  el usuario principal corresponde al
     * usuario principa, en si este metodo no eautentica, vericifica el otken,
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {

            final String authHeader = request.getHeader("Authorization");
            final String userEmail;
            final String jwtToken;

            if (authHeader == null || !authHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwtToken = authHeader.replace("Bearer", "");
            userEmail = jwtUtils.extractUsername(jwtToken);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                if (jwtUtils.isTokenValid(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        } catch (Exception e) {

            LOGGER.error("Error in JwtAuthFilter", e);
        }

        filterChain.doFilter(request, response);


    }
}
