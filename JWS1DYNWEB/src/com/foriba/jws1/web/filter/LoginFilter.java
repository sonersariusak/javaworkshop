package com.foriba.jws1.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foriba.jws1.web.model.WebUser;
import com.foriba.jws1.web.page.LoginPage;

public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String contextPath = httpRequest.getContextPath();
		HttpSession session = httpRequest.getSession();

		if (session == null) {
			httpResponse.sendRedirect(contextPath);
		}

		Object o = session.getAttribute(LoginPage.AUTH_KEY);
		if (o == null || !(o instanceof WebUser)) {
			httpResponse.sendRedirect(contextPath);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
