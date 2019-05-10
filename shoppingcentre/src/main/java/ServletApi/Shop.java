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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.giri.restapi.StringCodes;

import POJO.Product;
import POJO.User;

@WebServlet(urlPatterns={"/shop"})
public class Shop extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = req.getSession(false);
		//httpSession.setAttribute(StringCodes.UID_CODE, "2");
		int uid = Integer.parseInt( httpSession.getAttribute(StringCodes.UID_CODE).toString());
		if(uid==0){
			uid=2;
		}
		String head = "<!DOCTYPE html>\n" +
	            "<html lang=\"en\">\n" +
	            "  <head>\n" +
	            "  \t<script>\n" +
	            "  \t\tfunction jump1(a) {\n" +
	            "  \t\t  loadDoc(1,a);\n" +
	            "  \t\t \n" +
	            "\t\t}\n" +
	            "  \t\t\n" +
	            "  \t\tfunction loadDoc(txt,a) {\n" +
	            "  \t        var xhttp = new XMLHttpRequest();\n" +
	            "  \t        xhttp.onreadystatechange = function() {\n" +
	            "  \t            //document.write(this.readyState + \" \" + this.status + \" \");\n" +
	            "  \t            if (this.readyState == 4 && this.status == 200) {\n" +
	            "  \t                // document.getElementById().innerHTML =\n" +
	            "  \t                alert(\"Product succesfully added to the cart\");" +
	            "  \t                var s = this.responseText;\n" +
	            "\n" +
	            "  \t            }\nelse if(this.status == 404 || this.status == 500){"
	            + "					alert(\"Product not inserted to the cart\")	"
	            + "}" +
	            "  \t        };\n" +
	            "  \t        xhttp.open(\"POST\", \"http://localhost:8080/shoppingcentre/webapi/users/"+uid+"/carts\", true);\n" +
	            "  \t        xhttp.setRequestHeader(\"Content-Type\", \"application/json\");\n" +
	            "  \t        xhttp.send(JSON.stringify({pid:a, qty:txt}));\n" +
	            "  \t    }\n" +
	            "  \t\n" +
	            "  \t</script>\n" +
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
	            "                    <a href=\"cart\" class=\"site-cart\">\n" +
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
	            "          <div class=\"col-md-12 mb-0\"><a href=\"index.html\">Home</a> <span class=\"mx-2 mb-0\">/</span> <strong class=\"text-black\">Shop</strong></div>\n" +
	            "        </div>\n" +
	            "      </div>\n" +
	            "    </div>\n" +
	            "\n" +
	            "    <div class=\"site-section\">\n" +
	            "      <div class=\"container\">\n" +
	            "\n" +
	            "        <div class=\"row mb-5\">\n" +
	            "          <div class=\"col-md-9 order-2\">\n" +
	            "\n" +
	            "            <div class=\"row\">\n" +
	            "              <div class=\"col-md-12 mb-5\">\n" +
	            "                <div class=\"float-md-left mb-4\"><h2 class=\"text-black h5\">Shop All</h2></div>\n" +
	            "                <div class=\"d-flex\">\n" +
	            "                  <div class=\"dropdown mr-1 ml-md-auto\">\n" +
	            "                    <button type=\"button\" class=\"btn btn-secondary btn-sm dropdown-toggle\" id=\"dropdownMenuOffset\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
	            "                      Latest\n" +
	            "                    </button>\n" +
	            "                    <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuOffset\">\n" +
	            "                      <a class=\"dropdown-item\" href=\"#\">Men</a>\n" +
	            "                      <a class=\"dropdown-item\" href=\"#\">Women</a>\n" +
	            "                      <a class=\"dropdown-item\" href=\"#\">Children</a>\n" +
	            "                    </div>\n" +
	            "                  </div>\n" +
	            "                  <div class=\"btn-group\">\n" +
	            "                    <button type=\"button\" class=\"btn btn-secondary btn-sm dropdown-toggle\" id=\"dropdownMenuReference\" data-toggle=\"dropdown\">Reference</button>\n" +
	            "                    <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuReference\">\n" +
	            "                      <a class=\"dropdown-item\" href=\"#\">Relevance</a>\n" +
	            "                      <a class=\"dropdown-item\" href=\"#\">Name, A to Z</a>\n" +
	            "                      <a class=\"dropdown-item\" href=\"#\">Name, Z to A</a>\n" +
	            "                      <div class=\"dropdown-divider\"></div>\n" +
	            "                      <a class=\"dropdown-item\" href=\"#\">Price, low to high</a>\n" +
	            "                      <a class=\"dropdown-item\" href=\"#\">Price, high to low</a>\n" +
	            "                    </div>\n" +
	            "                  </div>\n" +
	            "                </div>\n" +
	            "              </div>\n" +
	            "            </div>\n" +
	            "            <div class=\"row mb-5\">\n";
		System.out.println(head.length());
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		int page = 0;
		try{
			 page = Integer.parseInt(req.getParameter("page"));
		}catch(Exception e){
			page = 1;
		}
		if(page<=0){
			page=1;
		}
		System.out.println("page="+page);
		ArrayList<Product> products = getProducts(page);
		
		for(int i=0;i<products.size();i++){
			head += " <div class=\"col-sm-6 col-lg-4 mb-4\" data-aos=\"fade-up\">\n" +
		            "                <div class=\"block-4 text-center border\">\n" +
					"                  <p><span class=\"heart1 icon icon-heart-o\"></span></p>\n" +
		            "                  <figure class=\"block-4-image\">\n" +
		            "                    <a onClick = \"jump1(document.getElementById('PID" +products.get(i).getPid() +"').innerHTML); return false\"  href=\"#\"><img src=\"shoppers/images/cloth_1.jpg\" alt=\"Image placeholder\" class=\"img-fluid\"></a>\n" +
		            "                  </figure>\n" +
		            "                  <div class=\"block-4-text p-4\">\n" +
		            "                    <h3><a onClick = \"jump1(document.getElementById('PID" +products.get(i).getPid() +"').innerHTML); return false\"  href=\"#\">" +products.get(i).getPname()+ "</a></h3>\n" +
		            "                    <p id='PID" + products.get(i).getPid() +"' class=\"mb-0\">"+products.get(i).getPid() +"</p>\n" +
		            "                    <p class=\"text-primary font-weight-bold\">"+products.get(i).getPrice()+"</p>\n" +
		            "                  </div>\n" +
		            "                </div>\n" +
		            "              </div>\n";
			
			
		}
		
		head += "</div>\n" +
	            "            <div class=\"row\" data-aos=\"fade-up\">\n" +
	            "              <div class=\"col-md-12 text-center\">\n" +
	            "                <div class=\"site-block-27\">\n" +
	            "                  <ul>\n" +
	            "                    <li><a href=\" "+ "http://localhost:8080/shoppingcentre/shop?page="+(page-1) + " \">&lt;</a></li>\n" +  
	            "                    <li><a href=\" "+ "http://localhost:8080/shoppingcentre/shop?page="+(page+1) +  "\">&gt;</a></li>\n" +
	            "                  </ul>\n" +
	            "                </div>\n" +
	            "              </div>\n" +
	            "            </div>\n" +
	            "          </div>\n" +
	            "\n" +
	            "          <div class=\"col-md-3 order-1 mb-5 mb-md-0\">\n" +
	            "            <div class=\"border p-4 rounded mb-4\">\n" +
	            "              <h3 class=\"mb-3 h6 text-uppercase text-black d-block\">Categories</h3>\n" +
	            "              <ul class=\"list-unstyled mb-0\">\n" +
	            "                <li class=\"mb-1\"><a href=\"#\" class=\"d-flex\"><span>Men</span> <span class=\"text-black ml-auto\">(2,220)</span></a></li>\n" +
	            "                <li class=\"mb-1\"><a href=\"#\" class=\"d-flex\"><span>Women</span> <span class=\"text-black ml-auto\">(2,550)</span></a></li>\n" +
	            "                <li class=\"mb-1\"><a href=\"#\" class=\"d-flex\"><span>Children</span> <span class=\"text-black ml-auto\">(2,124)</span></a></li>\n" +
	            "              </ul>\n" +
	            "            </div>\n" +
	            "\n" +
	            "            <div class=\"border p-4 rounded mb-4\">\n" +
	            "              <div class=\"mb-4\">\n" +
	            "                <h3 class=\"mb-3 h6 text-uppercase text-black d-block\">Filter by Price</h3>\n" +
	            "                <div id=\"slider-range\" class=\"border-primary\"></div>\n" +
	            "                <input type=\"text\" name=\"text\" id=\"amount\" class=\"form-control border-0 pl-0 bg-white\" disabled=\"\" />\n" +
	            "              </div>\n" +
	            "\n" +
	            "              <div class=\"mb-4\">\n" +
	            "                <h3 class=\"mb-3 h6 text-uppercase text-black d-block\">Size</h3>\n" +
	            "                <label for=\"s_sm\" class=\"d-flex\">\n" +
	            "                  <input type=\"checkbox\" id=\"s_sm\" class=\"mr-2 mt-1\"> <span class=\"text-black\">Small (2,319)</span>\n" +
	            "                </label>\n" +
	            "                <label for=\"s_md\" class=\"d-flex\">\n" +
	            "                  <input type=\"checkbox\" id=\"s_md\" class=\"mr-2 mt-1\"> <span class=\"text-black\">Medium (1,282)</span>\n" +
	            "                </label>\n" +
	            "                <label for=\"s_lg\" class=\"d-flex\">\n" +
	            "                  <input type=\"checkbox\" id=\"s_lg\" class=\"mr-2 mt-1\"> <span class=\"text-black\">Large (1,392)</span>\n" +
	            "                </label>\n" +
	            "              </div>\n" +
	            "\n" +
	            "              <div class=\"mb-4\">\n" +
	            "                <h3 class=\"mb-3 h6 text-uppercase text-black d-block\">Color</h3>\n" +
	            "                <a href=\"#\" class=\"d-flex color-item align-items-center\" >\n" +
	            "                  <span class=\"bg-danger color d-inline-block rounded-circle mr-2\"></span> <span class=\"text-black\">Red (2,429)</span>\n" +
	            "                </a>\n" +
	            "                <a href=\"#\" class=\"d-flex color-item align-items-center\" >\n" +
	            "                  <span class=\"bg-success color d-inline-block rounded-circle mr-2\"></span> <span class=\"text-black\">Green (2,298)</span>\n" +
	            "                </a>\n" +
	            "                <a href=\"#\" class=\"d-flex color-item align-items-center\" >\n" +
	            "                  <span class=\"bg-info color d-inline-block rounded-circle mr-2\"></span> <span class=\"text-black\">Blue (1,075)</span>\n" +
	            "                </a>\n" +
	            "                <a href=\"#\" class=\"d-flex color-item align-items-center\" >\n" +
	            "                  <span class=\"bg-primary color d-inline-block rounded-circle mr-2\"></span> <span class=\"text-black\">Purple (1,075)</span>\n" +
	            "                </a>\n" +
	            "              </div>\n" +
	            "\n" +
	            "            </div>\n" +
	            "          </div>\n" +
	            "        </div>\n" +
	            "\n" +
	            "        <div class=\"row\">\n" +
	            "          <div class=\"col-md-12\">\n" +
	            "            <div class=\"site-section site-blocks-2\">\n" +
	            "                <div class=\"row justify-content-center text-center mb-5\">\n" +
	            "                  <div class=\"col-md-7 site-section-heading pt-4\">\n" +
	            "                    <h2>Categories</h2>\n" +
	            "                  </div>\n" +
	            "                </div>\n" +
	            "                <div class=\"row\">\n" +
	            "                  <div class=\"col-sm-6 col-md-6 col-lg-4 mb-4 mb-lg-0\" data-aos=\"fade\" data-aos-delay=\"\">\n" +
	            "                    <a class=\"block-2-item\" href=\"#\">\n" +
	            "                      <figure class=\"image\">\n" +
	            "                        <img src=\"shoppers/images/women.jpg\" alt=\"\" class=\"img-fluid\">\n" +
	            "                      </figure>\n" +
	            "                      <div class=\"text\">\n" +
	            "                        <span class=\"text-uppercase\">Collections</span>\n" +
	            "                        <h3>Women</h3>\n" +
	            "                      </div>\n" +
	            "                    </a>\n" +
	            "                  </div>\n" +
	            "                  <div class=\"col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0\" data-aos=\"fade\" data-aos-delay=\"100\">\n" +
	            "                    <a class=\"block-2-item\" href=\"#\">\n" +
	            "                      <figure class=\"image\">\n" +
	            "                        <img src=\"shoppers/images/children.jpg\" alt=\"\" class=\"img-fluid\">\n" +
	            "                      </figure>\n" +
	            "                      <div class=\"text\">\n" +
	            "                        <span class=\"text-uppercase\">Collections</span>\n" +
	            "                        <h3>Children</h3>\n" +
	            "                      </div>\n" +
	            "                    </a>\n" +
	            "                  </div>\n" +
	            "                  <div class=\"col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0\" data-aos=\"fade\" data-aos-delay=\"200\">\n" +
	            "                    <a class=\"block-2-item\" href=\"#\">\n" +
	            "                      <figure class=\"image\">\n" +
	            "                        <img src=\"shoppers/images/men.jpg\" alt=\"\" class=\"img-fluid\">\n" +
	            "                      </figure>\n" +
	            "                      <div class=\"text\">\n" +
	            "                        <span class=\"text-uppercase\">Collections</span>\n" +
	            "                        <h3>Men</h3>\n" +
	            "                      </div>\n" +
	            "                    </a>\n" +
	            "                  </div>\n" +
	            "                </div>\n" +
	            "              \n" +
	            "            </div>\n" +
	            "          </div>\n" +
	            "        </div>\n" +
	            "        \n" +
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
	            "            Copyright &copy;<script data-cfasync=\"false\" src=\"/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js\"></script><script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class=\"icon-heart\" aria-hidden=\"true\"></i> by <a href=\"https://colorlib.com\" target=\"_blank\" class=\"text-primary\">Colorlib</a>\n" +
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
	            "    \n" +
	            "  </body>\n" +
	            "</html>";
		
		System.out.println(head.length());
		out.println(head);
		head="";
		
	}
	
	
	public ArrayList<Product> getProducts(int page){
		Client client = ClientBuilder.newClient();
	    WebTarget target = client.target("http://localhost:8080/shoppingcentre/webapi/products?page="+page);
	    ArrayList<Product> s = target.request().get(new GenericType<ArrayList<Product>>(){});
	      //System.out.println(s);
		return s;
	}
			
	
}
