package ServletApi;

import POJO.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
@WebServlet(urlPatterns = {"/signup"})
public class signup extends HttpServlet {
    {
        new DBConnection().setConnection("","","");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        Enumeration parameterNames = req.getParameterNames();

        HashMap<String,String> input = new HashMap<>();
        String user_seller = req.getParameter("user_seller");
        HttpSession httpSession = req.getSession();
        while (parameterNames.hasMoreElements()){
            String parameterName = (String) parameterNames.nextElement();
            String parameterValue = req.getParameter(parameterName);
            input.put(parameterName,parameterValue);
            httpSession.setAttribute(parameterName,parameterValue);
            //System.out.println(parameterName + " " + parameterValue);
        }




        //System.out.println(user_seller + "dlsufhkjdfsjkseller");
        User newUser = null;
        Seller newSeller = null;
        String s = "";
        Random r = new Random();
        final int verificationcode = Math.abs(r.nextInt()%999999)+100000;
        System.out.println(verificationcode + " random in signup");

        httpSession.setAttribute("verifycode",verificationcode);
        final String email1 = input.get("email");
        System.out.println("to mail: " + email1);
        Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				SendEmail.sendmail(email1,verificationcode+ " " + " this code sent by shoppingcart");
				
			}
		});
		thread.start();

        if(user_seller.equals("user")) {
            try {
                UserApi userApi = new UserApiImpl();
                newUser = userApi.signUp(input);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (newUser != null) {
                ArrayList<Product> products = new ArrayList<>();
                //products = new ProductApiImpl().getProductsFromDatabase(0, 1000);
                s = DisplayProducts.displayProducts();
            }

            resp.sendRedirect("http://localhost:8080/shoppingcentre/Verification/verify.html");

        }
        else if(user_seller.equals("seller")){
            System.out.println("giriseller");
            try {
                SellerApi sellerApi = new SellerApiImpl();
                newSeller = sellerApi.signUp(input);
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (newSeller != null) {
                ArrayList<Product> products = new ArrayList<>();
                products = new ProductApiImpl().getProductsFromDatabase(0, 1000);
                s = DisplayProducts.displayProducts();
            }
            
            resp.sendRedirect("http://localhost:8080/shoppingcentre/Verification/verify.html");
        }

        else {
            s = "!<doctype>" +
                    "<html>" +
                    "   <body>" +
                    "       SignUp Unsucessfull" +
                    "   </body>" +
                    "</html>" +
                    "";
        }


        PrintWriter out = resp.getWriter();
        out.println(s);




    }
}
