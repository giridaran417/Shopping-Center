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

@WebServlet(urlPatterns = {"/signin"})
public class SignIn extends HttpServlet {
    {
        DBConnection dbConnection = new DBConnection();
        dbConnection.setConnection("","","");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession httpSession = req.getSession();
        String email = req.getParameter("uname");

        String pass = req.getParameter("password");
        System.out.println(email + " " + pass);
        
        String user_seller = req.getParameter("user_seller");
        System.out.println(user_seller + "ALIUDSH");
        PrintWriter out = resp.getWriter();
        User user = null;
        Seller seller = null;
        if(user_seller.equals("user")) {
            UserApi userApi = new UserApiImpl();


            try {
                user = userApi.signIn(email, pass);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        else {
            SellerApi sellerApi = new SellerApiImpl();

            try {
                seller = sellerApi.signIn(email,pass);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        String s=null;

        if(user != null || seller != null){
            //String offset = req.getParameter("offset");
        	if(user!=null){
        		httpSession.setAttribute("uid", user.getUid());
        	}
        	else{
        		httpSession.setAttribute("uid", seller.getSid());
        	}
            ArrayList<Product> products = new ArrayList<>();
            String table = "";
            products = new ProductApiImpl().getProductsFromDatabase(0,1000);
            String headStyle = DisplayProducts.displayProducts();
            resp.sendRedirect("shop");

        }


        else {
             s = "<doctype>" +
                    "<html>" +
                    "   </body>" +
                    "        Entered Credentials are wrong   " +
                    "   </body>" +
                    "</html>" +
                    "";
        }

        out.println(s);






    }
}
