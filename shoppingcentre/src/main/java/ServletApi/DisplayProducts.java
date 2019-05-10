package ServletApi;

import POJO.Product;
import POJO.ProductApiImpl;
import POJO.UserApiImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
@WebServlet(urlPatterns = {"/displayproducts"})
public class DisplayProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println(displayProducts());
    }

    public static String displayProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products = new ProductApiImpl().getProductsFromDatabase(0,1000);
        String headStyle = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<title>Table V04</title>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<!--===============================================================================================-->\t\n" +
                "\t<link rel=\"icon\" type=\"image/png\" href=\"images/icons/favicon.ico\"/>\n" +
                "<!--===============================================================================================-->\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/bootstrap/css/bootstrap.min.css\">\n" +
                "<!--===============================================================================================-->\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"fonts/font-awesome-4.7.0/css/font-awesome.min.css\">\n" +
                "<!--===============================================================================================-->\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/animate/animate.css\">\n" +
                "<!--===============================================================================================-->\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/select2/select2.min.css\">\n" +
                "<!--===============================================================================================-->\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/perfect-scrollbar/perfect-scrollbar.css\">\n" +
                "<!--===============================================================================================-->\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/util.css\">\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\">\n" +
                "<!--===============================================================================================-->\n" +
                "</head>\n" +
                "<body>\n" +
                "\t\n" +
                "\t<div class=\"limiter\">\n" +
                "\t\t<div class=\"container-table100\">\n" +
                "\t\t\t<div class=\"wrap-table100\">\n" +
                "<div class=\"table100 ver3 m-b-110\">\n" +
                "\t\t\t\t\t<div class=\"table100-head\">\n" +
                "\t\t\t\t\t\t<table>\n" +
                "\t\t\t\t\t\t\t<thead>\n" +
                "\t\t\t\t\t\t\t\t<tr class=\"row100 head\">\n" +
                "\t\t\t\t\t\t\t\t\t<th class=\"cell100 column2\">Pid</th>\n" +
                "\t\t\t\t\t\t\t\t\t<th class=\"cell100 column2\">Product name</th>\n" +
                "\t\t\t\t\t\t\t\t\t<th class=\"cell100 column2\">Stock</th>\n" +
                "\t\t\t\t\t\t\t\t\t<th class=\"cell100 column2\">Price</th>\n" +
                //"\t\t\t\t\t\t\t\t\t<th class=\"cell100 column2\">Add To cart</th>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</thead>\n" +
                "\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t\t\t<div class=\"table100-body js-pscroll\">\n" +
                "\t\t\t\t\t\t<table>\n" +
                "\t\t\t\t\t\t\t<tbody>";
        String table = "";
        for(int i=0;i<products.size();i++){
            Product product = products.get(i);
            table += "<tr class=\"row100 body\">\n" +
                    "\t\t\t\t\t\t\t\t\t<td class=\"cell100 column2\">"+product.getPid()+ "</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td class=\"cell100 column2\">"+ product.getPname()+"</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td class=\"cell100 column2\">"+ product.getStock()+"</td>\n" +
                    "\t\t\t\t\t\t\t\t\t<td class=\"cell100 column2\">"+ product.getPrice()+"</td>\n" +
                    "\t\t\t\t\t\t\t\t</tr>";
        }

        headStyle += table;

        headStyle += "</tbody>\n" +
                "\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>";

        return headStyle;
    }

}
