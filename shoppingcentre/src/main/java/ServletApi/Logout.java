//$Id$
package ServletApi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns={"/logout"})
public class Logout extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession httpSession = req.getSession(false);
		if(httpSession != null){
			httpSession.invalidate();
		}
		resp.sendRedirect("Login_v2/Login_v2");
	}
	
}
