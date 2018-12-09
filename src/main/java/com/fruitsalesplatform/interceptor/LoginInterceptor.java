package com.fruitsalesplatform.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (!(uri.contains("Login") || uri.contains("login") || uri.contains("register") || uri.contains("Bind") || uri.contains("bind"))) {
			if(request.getSession().getAttribute("user") != null)
				return true;
			else
				if(uri.contains("css") || uri.contains("js") || uri.contains("images")) 
					return true;
				else {
					response.sendRedirect(request.getContextPath() + "/user/toLogin.action");
					return false;
				}
		}
		return true;
	}

}
