//$Id$
package ServletApi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import com.giri.restapi.StringCodes;

import POJO.Cart;
import POJO.Product;

@WebServlet(urlPatterns = {"/cart"})
public class cart extends HttpServlet{


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
	            "                  <li><a href=\"logout\"><span class=\"icon icon-person\"></span></a></li>\n" +
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
	            "              <a href=\"shop\">Home</a>\n" +
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
	            "            <li><a href=\"shop.html\">Shop</a></li>\n" +
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
	            "          <div class=\"col-md-12 mb-0\"><a href=\"shop\">Home</a> <span class=\"mx-2 mb-0\">/</span> <strong class=\"text-black\">Cart</strong></div>\n" +
	            "        </div>\n" +
	            "      </div>\n" +
	            "    </div>\n" +
	            "\n" +
	            "    <div class=\"site-section\">\n" +
	            "      <div class=\"container\">\n" +
	            "        <div class=\"row mb-5\">\n" +
	            "          <form class=\"col-md-12\" method=\"post\">\n" +
	            "            <div class=\"site-blocks-table\">\n" +
	            "              <table class=\"table table-bordered\">\n" +
	            "                <thead>\n" +
	            "                  <tr>\n" +
	            "                    <th class=\"product-thumbnail\">Image</th>\n" +
	            "                    <th class=\"product-name\">Product</th>\n" +
	            "                    <th class=\"product-price\">Price</th>\n" +
	            "                    <th class=\"product-quantity\">Quantity</th>\n" +
	            "                    <th class=\"product-total\">Total</th>\n" +
	            "                    <th class=\"product-remove\">Remove</th>\n" +
	            "                  </tr>\n" +
	            "                </thead>\n" +
	            "                <tbody>\n";
		
		resp.setContentType("text/html");
		HttpSession httpSession = req.getSession(false);
		int uid = -1;
		try{
			 uid = Integer.parseInt(httpSession.getAttribute(StringCodes.UID_CODE).toString());
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception so uid = 1:");
			uid = -1;
		}
		System.out.println("uid=" + uid);
		ArrayList<Cart> carts = getCart(uid);
		System.out.println("carts=" + carts.size());
		long totalprice = 0;
		for(int i=0;i<carts.size();i++){
			Product product  = getProduct(carts.get(i).getPid());
			totalprice += (product.getPrice()*carts.get(i).getQty());
			head += "<tr class=\"table1\" >\n" +
		            "                    <td class=\"product-thumbnail\">\n" +
		            "                      <img src=\"shoppers/images/cloth_1.jpg\" alt=\"Image\" class=\"img-fluid\">\n" +
		            "                    </td>\n" +
		            "                    <td class=\"product-name\">\n" +
		            "                      <h2 class=\"h5 text-black\">"+ product.getPname()+ "</h2>\n" +
		            "                    </td>\n" +
		            
		            "                    <td class=\"price\" >"+ product.getPrice()+ "</td>\n" +
		            "                    <td>\n" +
		            "                      <div class=\"input-group mb-3\" style=\"max-width: 120px;\">\n" +
		            "                        <div class=\"input-group-prepend\">\n" +
		            "                          <button class=\"btn btn-outline-primary js-btn-minus\" type=\"button\">&minus;</button>\n" +
		            "                        </div>\n" +
		            "                        <input type=\"text\" class=\" qty form-control text-center\" value=\""+carts.get(i).getQty()+ "\" placeholder=\"\" aria-label=\"Example text with button addon\" aria-describedby=\"button-addon1\">\n" +
		            "                        <div class=\"input-group-append\">\n" +
		            "                          <button class=\"btn btn-outline-primary js-btn-plus\" type=\"button\">&plus;</button>\n" +
		            "                        </div>\n" +
		            "						 <input  hidden class=\"pid\" value=\""+ product.getPid()+"\" >" +
		            "                        <input hidden class=\"uid\"  value=\" "+ uid +"\"  " +
		            "                      </div>\n" +
		            "\n" +
		            "                    </td>\n" +
		            "                    <td class=\"total_price\" >"+product.getPrice()*carts.get(i).getQty()+"</td>\n" +
		            "                    <td><button type=\"button\" class=\"remove btn btn-primary btn-sm\">X</button></td>\n" +
		            "                  </tr>";
			
			
		}
		head += "</tbody>\n" +
	            "              </table>\n" +
	            "            </div>\n" +
	            "          </form>\n" +
	            "        </div>\n" +
	            "\n" +
	            "        <div class=\"row\">\n" +
	            "          <div class=\"col-md-6\">\n" +
	            "            <div class=\"row mb-5\">\n" +
	            "              <div class=\"col-md-6 mb-3 mb-md-0\">\n" +
	            "                <button id=\"update_cart\" class=\"btn btn-primary btn-sm btn-block\">Update Cart</button>\n" +
	            "              </div>\n" +
	            "              <div class=\"col-md-6\">\n" +
	            "                <button id=\"continue_shopping\" class=\"btn btn-outline-primary btn-sm btn-block\">Continue Shopping</button>\n" +
	            "              </div>\n" +
	            "            </div>\n" +
	            "            <div class=\"row\">\n" +
	            "              <div class=\"col-md-12\">\n" +
	            "                <label class=\"text-black h4\" for=\"coupon\">Coupon</label>\n" +
	            "                <p>Enter your coupon code if you have one.</p>\n" +
	            "              </div>\n" +
	            "              <div class=\"col-md-8 mb-3 mb-md-0\">\n" +
	            "                <input type=\"text\" class=\"form-control py-3\" id=\"coupon\" placeholder=\"Coupon Code\">\n" +
	            "              </div>\n" +
	            "              <div class=\"col-md-4\">\n" +
	            "                <button class=\"btn btn-primary btn-sm\">Apply Coupon</button>\n" +
	            "              </div>\n" +
	            "            </div>\n" +
	            "          </div>\n" +
	            "          <div class=\"col-md-6 pl-5\">\n" +
	            "            <div class=\"row justify-content-end\">\n" +
	            "              <div class=\"col-md-7\">\n" +
	            "                <div class=\"row\">\n" +
	            "                  <div class=\"col-md-12 text-right border-bottom mb-5\">\n" +
	            "                    <h3 class=\"text-black h4 text-uppercase\">Cart Totals</h3>\n" +
	            "                  </div>\n" +
	            "                </div>\n" +
	            "                <div class=\"row mb-3\">\n" +
	            "                  <div class=\"col-md-6\">\n" +
	            "                    <span class=\"text-black\">Subtotal</span>\n" +
	            "                  </div>\n" +
	            "                  <div class=\"col-md-6 text-right\">\n" +
	            "                    <strong class=\"text-black\">"+ totalprice +"</strong>\n" +
	            "                  </div>\n" +
	            "                </div>\n" +
	            "                <div class=\"row mb-5\">\n" +
	            "                  <div class=\"col-md-6\">\n" +
	            "                    <span class=\"text-black\">Total</span>\n" +
	            "                  </div>\n" +
	            "                  <div class=\"col-md-6 text-right\">\n" +
	            "                    <strong class=\"text-black\">"+totalprice+"</strong>\n" +
	            "                  </div>\n" +
	            "                </div>\n" +
	            "\n" +
	            "                <div class=\"row\">\n" +
	            "                  <div class=\"col-md-12\">\n" +
	            "                    <button class=\"btn btn-primary btn-lg py-3 btn-block\" onclick=\"window.location='checkout.html'\">Proceed To Checkout</button>\n" +
	            "                  </div>\n" +
	            "                </div>\n" +
	            "              </div>\n" +
	            "            </div>\n" +
	            "          </div>\n" +
	            "        </div>\n" +
	            "      </div>\n" +
	            "    </div>\n" +
	            "\n" +
	            "    <footer class=\"site-footer border-top\">\n" +
	            "      <div class=\"container\">\n" +
	            "        <div class=\"row\">\n" +
	            "          <div class=\"col-lg-6 mb-5 mb-lg-0\">\n" +
	            "            <div class=\"row\">\n" +
	            "              <div class=\"col-md-12\">\n" +
	            "                <h3 class=\"footer-heading mb-4\">Navigations</h3>\n" +
	            "              </div>\n" +
	            "              <div class=\"col-md-6 col-lg-4\">\n" +
	            "                <ul class=\"list-unstyled\">\n" +
	            "                  <li><a href=\"#\">Sell online</a></li>\n" +
	            "                  <li><a href=\"#\">Features</a></li>\n" +
	            "                  <li><a href=\"#\">Shopping cart</a></li>\n" +
	            "                  <li><a href=\"#\">Store builder</a></li>\n" +
	            "                </ul>\n" +
	            "              </div>\n" +
	            "              <div class=\"col-md-6 col-lg-4\">\n" +
	            "                <ul class=\"list-unstyled\">\n" +
	            "                  <li><a href=\"#\">Mobile commerce</a></li>\n" +
	            "                  <li><a href=\"#\">Dropshipping</a></li>\n" +
	            "                  <li><a href=\"#\">Website development</a></li>\n" +
	            "                </ul>\n" +
	            "              </div>\n" +
	            "              <div class=\"col-md-6 col-lg-4\">\n" +
	            "                <ul class=\"list-unstyled\">\n" +
	            "                  <li><a href=\"#\">Point of sale</a></li>\n" +
	            "                  <li><a href=\"#\">Hardware</a></li>\n" +
	            "                  <li><a href=\"#\">Software</a></li>\n" +
	            "                </ul>\n" +
	            "              </div>\n" +
	            "            </div>\n" +
	            "          </div>\n" +
	            "          <div class=\"col-md-6 col-lg-3 mb-4 mb-lg-0\">\n" +
	            "            <h3 class=\"footer-heading mb-4\">Promo</h3>\n" +
	            "            <a href=\"#\" class=\"block-6\">\n" +
	            "              <img src=\"shoppers/images/hero_1.jpg\" alt=\"Image placeholder\" class=\"img-fluid rounded mb-4\">\n" +
	            "              <h3 class=\"font-weight-light  mb-0\">Finding Your Perfect Shoes</h3>\n" +
	            "              <p>Promo from  nuary 15 &mdash; 25, 2019</p>\n" +
	            "            </a>\n" +
	            "          </div>\n" +
	            "          <div class=\"col-md-6 col-lg-3\">\n" +
	            "            <div class=\"block-5 mb-5\">\n" +
	            "              <h3 class=\"footer-heading mb-4\">Contact Info</h3>\n" +
	            "              <ul class=\"list-unstyled\">\n" +
	            "                <li class=\"address\">203 Fake St. Mountain View, San Francisco, California, USA</li>\n" +
	            "                <li class=\"phone\"><a href=\"tel://23923929210\">+2 392 3929 210</a></li>\n" +
	            "                <li class=\"email\">emailaddress@domain.com</li>\n" +
	            "              </ul>\n" +
	            "            </div>\n" +
	            "\n" +
	            "            <div class=\"block-7\">\n" +
	            "              <form action=\"#\" method=\"post\">\n" +
	            "                <label for=\"email_subscribe\" class=\"footer-heading\">Subscribe</label>\n" +
	            "                <div class=\"form-group\">\n" +
	            "                  <input type=\"text\" class=\"form-control py-4\" id=\"email_subscribe\" placeholder=\"Email\">\n" +
	            "                  <input type=\"submit\" class=\"btn btn-sm btn-primary\" value=\"Send\">\n" +
	            "                </div>\n" +
	            "              </form>\n" +
	            "            </div>\n" +
	            "          </div>\n" +
	            "        </div>\n" +
	            "        <div class=\"row pt-5 mt-5 text-center\">\n" +
	            "          <div class=\"col-md-12\">\n" +
	            "            <p>\n" +
	            "            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->\n" +
	            "            Copyright &copy;<script data-cfasync=\"false\" src=\"cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js\"></script><script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class=\"icon-heart\" aria-hidden=\"true\"></i> by <a href=\"https://colorlib.com\" target=\"_blank\" class=\"text-primary\">Colorlib</a>\n" +
	            "            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->\n" +
	            "            </p>\n" +
	            "          </div>\n" +
	            "          \n" +
	            "        </div>\n" +
	            "      </div>\n" +
	            "    </footer>\n" +
	            "  </div>\n" +
	            "\n" +
	            "  <script src=\"shoppers/js/jquery-3.3.1.min.js\"></script>\n" +
	            "  <script src=\"shoppers/js/jquery-ui.js\"></script>\n" +
	            "  <script src=\"shoppers/js/popper.min.js\"></script>\n" +
	            "  <script src=\"shoppers/js/bootstrap.min.js\"></script>\n" +
	            "  <script src=\"shoppers/js/owl.carousel.min.js\"></script>\n" +
	            "  <script src=\"shoppers/js/jquery.magnific-popup.min.js\"></script>\n" +
	            "  <script src=\"shoppers/js/aos.js\"></script>\n" +
	            "\n" +
	            "  <script src=\"shoppers/js/main.js\"></script>\n" +
	            "  <script src=\"shoppers/js/customfunction.js\"></script>\n" +
	            "    \n" +
	            "  </body>\n" +
	            "</html>";
		PrintWriter out = resp.getWriter();
		out.println(head);
	}
	
	public Product getProduct(int pid) {
		Client client = ClientBuilder.newClient();
	    WebTarget target = client.target("http://localhost:8080/shoppingcentre/webapi/products/"+ pid);
	    Product product = target.request().get(Product.class);
	    return product;
	}
	
	
	public ArrayList<Cart> getCart(int uid){
		Client client = ClientBuilder.newClient();
	    WebTarget target = client.target("http://localhost:8080/shoppingcentre/webapi/users/"+ uid + "/carts");
	    ArrayList<Cart> s = target.request().get(new GenericType<ArrayList<Cart>>(){});
	      //System.out.println(s);
		return s;
	}
            
	
	
	
}
