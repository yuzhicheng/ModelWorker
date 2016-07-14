package org.xadzkd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.xadzkd.tool.CurrentUser;


/**
 * Servlet Filter implementation class AuthorityFilter
 */
public class AuthorityFilter implements Filter {

	private FilterConfig config;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.config = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requ = (HttpServletRequest) request;
		String requestPath = requ.getServletPath();
		HttpSession session = requ.getSession(true);
		String loginPage = config.getInitParameter("loginPage");
		CurrentUser currentUser=(CurrentUser) session.getAttribute("currentUser");
		if(requestPath.startsWith("/static")||requestPath.endsWith("/identify")||requestPath.endsWith("/doLogin"))
		{
			chain.doFilter(request, response);
		}
		else if(currentUser == null && !requestPath.endsWith(loginPage))
		{
			request.setAttribute("message", "请先登陆！！");
			request.getRequestDispatcher(loginPage).forward(request, response);
		}
		
		else 
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config=fConfig;
	}

}
