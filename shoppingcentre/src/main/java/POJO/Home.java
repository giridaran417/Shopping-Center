package POJO;

import java.util.ArrayList;

public class Home {
    public static String staticsalt = "$2a$10$TRlovMXqoqVGrI/Ns2AR6e";
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        db.setConnection("giri","root","");
        System.out.println(BCrypt.gensalt());
//        POJO.UserBuilder ub = new POJO.UserBuilder();
//        ub.setUid(11).setAid(11).setName("giri");
//        POJO.User u = ub.getUser();
//        System.out.println(u);

//        SellerBuilder sb = new SellerBuilder();
//        sb.setUid(1).setAid(2).setEmail("sdalfh");
//        System.out.println(sb);
//        POJO.ProductBuilder pb = new POJO.ProductBuilder();
//        pb.setPid(1).setAvg_rating((float)1.2).setPrice(100).setStock(12).setPname("dafkh");
//        POJO.Product p = pb.getProduct();
//        System.out.println(p);
//        String query;
        // POJO.ProductApi pi = new POJO.ProductApiImpl();
        // pi.updateProducts(new POJO.ProductBuilder().setStock(50).setPid(2).getProduct());
//        pi.addProductsToDatabase("giri",1,1,1,1.0,123);
//        pi.removeProductsFromDatabase(1,2);
        //pi.updateProductsInDatabase(1,"sdlfh");
        //pi.updateProductsInDatabase(1,2);
        //pi.getProductsFromDatabase(2,5);
        //printList(pi.getProductsFromDatabase(0,5));
        //System.out.println(pi.getProduct(1));


        //System.out.println(pi.updateProduct(pb.getProduct()));
        try {
            UserApi userApi = new UserApiImpl();
            CartApi api = new CartApiImpl();
            CartBuilder cartBuilder = new CartBuilder();
            ProductBuilder pb = new ProductBuilder();
            //api.addProductToCart(cartBuilder.setPid(5).setQty(1).setUid(4).getCart());
            //pi.updateProducts(pb.setPid(1).setPrice(1000).setStock(100).getProduct());
            //pi.updateProducts(pb.setPid(2).setStock(50).setPrice(100).getProduct());
            api.purchaseCart(4,4,30);
            System.out.println(CartApiImpl.getCarts());
        }catch (Exception e){
            e.printStackTrace();
        }

        //api.removeProductFromCart(new POJO.CartBuilder().setPid(1).setPrice(12).setQty(11).setUid(1).getCart());
        try {

//            POJO.SellerApi sellerApi = new SellerApiImpl();
//            Seller s = sellerApi.signIn(",dajsf","1");
//            System.out.println(s);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void printList(ArrayList<Product> products){
        int i;
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("%-8s%-12s%-20s%-18s%-20s%-13s%-16s%-16s\n","SNO","ProductID","ProductName",
                "StocksAvailable","Price","SellerID","Rating","No_of_users");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //System.out.println(products);
        for(i=0;i<products.size();i++){
            System.out.printf("%-8d%-12d%-20s%-18d%-20f%-13d%-16f%-16d\n",
                    i+1,
                    products.get(i).getPid(),
                    products.get(i).getPname(),
                    products.get(i).getStock(),
                    products.get(i).getPrice(),
                    products.get(i).getSid(),
                    products.get(i).getAvg_rating(),
                    products.get(i).getNo_users());
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}

