package SLT.InternManagementSystem.filter;

import SLT.InternManagementSystem.service.JwtService;
import SLT.InternManagementSystem.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    String path = request.getServletPath();
    // Allow unauthenticated access to auth endpoints
    if (path.equals("/login") || path.equals("/signup") || path.equals("/signup/manual") || path.equals("/signup/google") || path.equals("/verify") || path.equals("/storeOTP") || path.equals("/verifyOTP") || path.equals("/verifyEmail") || path.equals("/resetPassword")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authorizationHeader.substring(7);
        username = jwtService.extractEmail(jwt);
        System.out.println("=== JWT Authentication Debug ===");
        System.out.println("JWT Token: " + jwt);
        System.out.println("Extracted Email: " + username);
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Request Method: " + request.getMethod());

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = this.userDetailsService.loadUserByUsername(username);
            System.out.println("User found: " + userDetails.getUsername());

            if (jwtService.isValid(jwt, userDetails)) {
                String role = jwtService.extractClaim(jwt, claims -> claims.get("role", String.class));
                System.out.println("Extracted Role from JWT: '" + role + "'");

                // Check all claims in the token for debugging
                var allClaims = jwtService.extractAllClaims(jwt);
                System.out.println("All JWT Claims: " + allClaims);

                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                System.out.println("Granted Authorities: " + authorities);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                System.out.println("Authentication successful for user: " + username + " with role: " + role);
            } else {
                System.out.println("JWT token validation failed for user: " + username);
            }
        } else {
            System.out.println("No username extracted or authentication already exists");
        }
        System.out.println("=== End JWT Debug ===");
        filterChain.doFilter(request, response);
    }
}