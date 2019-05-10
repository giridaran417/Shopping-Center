package ServletApi;

import POJO.UserApi;
import POJO.UserApiImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/verify"})
public class verifycode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
	    	resp.setContentType("text/html");
	        HttpSession httpSession = req.getSession();
	        
	        String ocode =  httpSession.getAttribute("verifycode").toString();
	
	        System.out.println(ocode + "akjhdajlkhd\n" );
	
	        String verificationcode = req.getParameter("msg");
	        PrintWriter out = resp.getWriter();
	        System.out.println(verificationcode + "verification code typed by user");
	        if(verificationcode.equals(ocode)){
	            UserApi userApi = new UserApiImpl();
	            if(httpSession.getAttribute("user_seller").toString().equals("user")) {
	                System.out.println("giri");
	                userApi.updateIsVerfied(httpSession.getAttribute("uname").toString());
	            }
	            else{
	                System.out.println("yasir");
	                userApi.updateIsVerfied(httpSession.getAttribute("uname").toString());
	            }
	            out.print("true");
	        }
	        else {
	            out.print("false");
	        }
        }
        catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = resp.getWriter();
			out.print("false");
		}
        //out.print("true");
    }
}
