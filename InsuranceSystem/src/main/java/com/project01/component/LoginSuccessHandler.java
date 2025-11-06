package com.project01.component;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//自定義Spring Security的登入成功處理器
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = null;
        
        for (GrantedAuthority auth : authorities) {
            String role = auth.getAuthority();
            if (role.equals("ROLE_ADMIN")) {
                redirectUrl = "/admin/page/home";
                break;
            } 
            else if (role.equals("ROLE_USER")) {
                redirectUrl = "/insurer/page/home";
                break;
            }
        }
        response.sendRedirect(redirectUrl);
	}

}
