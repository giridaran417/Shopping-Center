//$Id$
package ServletApi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/wishlist"})
public class WishList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String head = "<!DOCTYPE html>\n" +
		            "<html lang=\"en\">\n" +
		            "  <head>\n" +
		            "    <title>Shoppers &mdash; Colorlib e-Commerce Template</title>\n" +
		            "    <meta charset=\"utf-8\">\n" +
		            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
		            "\n" +
		            "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Mukta:300,400,700\"> \n" +
		            "    <link rel=\"stylesheet\" href=\"shoppers/fonts/icomoon/style.css\">\n" +
		            "\n" +
		            "    <link rel=\"stylesheet\" href=\"shoppers/css/bootstrap.min.css\">\n" +
		            "    <link rel=\"stylesheet\" href=\"shoppers/css/magnific-popup.css\">\n" +
		            "    <link rel=\"stylesheet\" href=\"shoppers/css/jquery-ui.css\">\n" +
		            "    <link rel=\"stylesheet\" href=\"shoppers/css/owl.carousel.min.css\">\n" +
		            "    <link rel=\"stylesheet\" href=\"shoppers/css/owl.theme.default.min.css\">\n" +
		            "\n" +
		            "\n" +
		            "    <link rel=\"stylesheet\" href=\"shoppers/css/aos.css\">\n" +
		            "\n" +
		            "    <link rel=\"stylesheet\" href=\"shoppers/css/style.css\">\n" +
		            "    \n" +
		            "  </head>\n" +
		            "  <body>\n" +
		            "  \n" +
		            "  <div class=\"site-wrap\">\n" +
		            "    <header class=\"site-navbar\" role=\"banner\">\n" +
		            "      <div class=\"site-navbar-top\">\n" +
		            "        <div class=\"container\">\n" +
		            "          <div class=\"row align-items-center\">\n" +
		            "\n" +
		            "            <div class=\"col-6 col-md-4 order-2 order-md-1 site-search-icon text-left\">\n" +
		            "              <form action=\"\" class=\"site-block-top-search\">\n" +
		            "                <span class=\"icon icon-search2\"></span>\n" +
		            "                <input type=\"text\" class=\"form-control border-0\" placeholder=\"Search\">\n" +
		            "              </form>\n" +
		            "            </div>\n" +
		            "\n" +
		            "            <div class=\"col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center\">\n" +
		            "              <div class=\"site-logo\">\n" +
		            "                <a href=\"index.html\" class=\"js-logo-clone\">Shoppers</a>\n" +
		            "              </div>\n" +
		            "            </div>\n" +
		            "\n" +
		            "            <div class=\"col-6 col-md-4 order-3 order-md-3 text-right\">\n" +
		            "              <div class=\"site-top-icons\">\n" +
		            "                <ul>\n" +
		            "                  <li><a href=\"#\"><span class=\"icon icon-person\"></span></a></li>\n" +
		            "                  <li><a href=\"#\"><span class=\"icon icon-heart-o\"></span></a></li>\n" +
		            "                  <li>\n" +
		            "                    <a href=\"cart.html\" class=\"site-cart\">\n" +
		            "                      <span class=\"icon icon-shopping_cart\"></span>\n" +
		            "                      <span class=\"count\">2</span>\n" +
		            "                    </a>\n" +
		            "                  </li> \n" +
		            "                  <li class=\"d-inline-block d-md-none ml-md-0\"><a href=\"#\" class=\"site-menu-toggle js-menu-toggle\"><span class=\"icon-menu\"></span></a></li>\n" +
		            "                </ul>\n" +
		            "              </div> \n" +
		            "            </div>\n" +
		            "\n" +
		            "          </div>\n" +
		            "        </div>\n" +
		            "      </div> \n" +
		            "      <nav class=\"site-navigation text-right text-md-center\" role=\"navigation\">\n" +
		            "        <div class=\"container\">\n" +
		            "          <ul class=\"site-menu js-clone-nav d-none d-md-block\">\n" +
		            "            <li class=\"has-children\">\n" +
		            "              <a href=\"index.html\">Home</a>\n" +
		            "              <ul class=\"dropdown\">\n" +
		            "                <li><a href=\"#\">Menu One</a></li>\n" +
		            "                <li><a href=\"#\">Menu Two</a></li>\n" +
		            "                <li><a href=\"#\">Menu Three</a></li>\n" +
		            "                <li class=\"has-children\">\n" +
		            "                  <a href=\"#\">Sub Menu</a>\n" +
		            "                  <ul class=\"dropdown\">\n" +
		            "                    <li><a href=\"#\">Menu One</a></li>\n" +
		            "                    <li><a href=\"#\">Menu Two</a></li>\n" +
		            "                    <li><a href=\"#\">Menu Three</a></li>\n" +
		            "                  </ul>\n" +
		            "                </li>\n" +
		            "              </ul>\n" +
		            "            </li>\n" +
		            "            <li class=\"has-children\">\n" +
		            "              <a href=\"about.html\">About</a>\n" +
		            "              <ul class=\"dropdown\">\n" +
		            "                <li><a href=\"#\">Menu One</a></li>\n" +
		            "                <li><a href=\"#\">Menu Two</a></li>\n" +
		            "                <li><a href=\"#\">Menu Three</a></li>\n" +
		            "              </ul>\n" +
		            "            </li>\n" +
		            "            <li class=\"active\"><a href=\"shop.html\">Shop</a></li>\n" +
		            "            <li><a href=\"#\">Catalogue</a></li>\n" +
		            "            <li><a href=\"#\">New Arrivals</a></li>\n" +
		            "            <li><a href=\"contact.html\">Contact</a></li>\n" +
		            "          </ul>\n" +
		            "        </div>\n" +
		            "      </nav>\n" +
		            "    </header>\n" +
		            "\n" +
		            "    <div class=\"bg-light py-3\">\n" +
		            "      <div class=\"container\">\n" +
		            "        <div class=\"row\">\n" +
		            "          <div class=\"col-md-12 mb-0\"><a href=\"index.html\">Home</a> <span class=\"mx-2 mb-0\">/</span> <strong class=\"text-black\">Tank Top T-Shirt</strong></div>\n" +
		            "        </div>\n" +
		            "      </div>\n" +
		            "    </div>  \n" +
		            "\n" +
		            "    <div class=\"site-section\">\n" +
		            "      <div class=\"container\">";
		 
				 
		 
		
		PrintWriter out = resp.getWriter();
		out.println(head);
	}
	
}
