package com.example.loginregistration.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = null;
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        System.out.println("SSSSSSSSSSSSSSSSSSSSS");
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            System.out.println("Current user role " + grantedAuthority.getAuthority());
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                redirectUrl = "/welcome";
                break;
            }
            new DefaultRedirectStrategy().sendRedirect(httpServletRequest, httpServletResponse, redirectUrl);
        }
    }
}
