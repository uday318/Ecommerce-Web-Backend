package com.ecom.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecom.Entities.User;
import com.ecom.Entities.UserPrincipal;
import com.ecom.Service.JWTService;
import com.ecom.Service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String authorization = httpServletRequest.getHeader("Authorization");
		String token = null;
		String userName = null;

		if (null != authorization && authorization.startsWith("Bearer")) {
			token = authorization.substring(7);
			userName = jwtService.extractUserName(token);
		}

		if (null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {

			// User user = (User) myUserDetailsService.loadUserByUsername(userName);
			UserPrincipal userPrincipal = (UserPrincipal) myUserDetailsService.loadUserByUsername(userName);
			User user = userPrincipal.getUser();
			

			if (jwtService.validateToken(token, user)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user.getUsername(), null, user.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				httpServletRequest.setAttribute(User.LOGIN_USER, user);
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
