package com.project01.component;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//自定義Spring Security的登入失敗處理器
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String loginPath = request.getServletPath();
		String redirectUrl = "/insurancesystem/home";
		
		if (loginPath.contains("/admin/")) {
            redirectUrl = "/insurancesystem/admin/login?error=true";
        } 
		else if (loginPath.contains("/insurer/")) {
            redirectUrl = "/insurancesystem/insurer/login?error=true";
        }
		
		response.sendRedirect(redirectUrl);
	}
	

}
