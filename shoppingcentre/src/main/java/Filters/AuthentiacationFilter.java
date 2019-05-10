//$Id$
package Filters;



import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//@WebFilter(filterName = "AuthenticationFilter")
public class AuthentiacationFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession(false);
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		if(session == null && !(uri.endsWith("index.html") || uri.endsWith("signin") || uri.endsWith("signup") 
			|| uri.endsWith("font-awesome.min.css")  || uri.endsWith("main.css")
					|| uri.endsWith("verifycode")  )){
			System.out.println("unauthorized access");
			response.sendRedirect("http://localhost:8080/shoppingcentre/Login_v2/Login_v2/index.html");
		}
		else {
			chain.doFilter(req, res);
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}

