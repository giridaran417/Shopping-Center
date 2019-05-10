package POJO;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.crypto.provider.RSACipher;

public class CartApiImpl implements CartApi {
    String query;

    /**
     * This function add a product to a cart given a cart object
     * and if a user already has the same product in his cart then quantity of product is incremented
     * if a user doesn't have a product then the given product quantity is is added
     * @param cart
     * @return true if product is succesfully added else false
     * @throws Exception , {@link CartQtyExceedsException} is thrown when there is not available stock.
     */
    @Override
    public boolean addProductToCart(Cart cart) throws Exception {
        Product product = ProductApiImpl.getProduct(cart.pid);
        Cart cart1 = getCart(cart.pid,cart.uid);
        int qty = 0;
        if(cart1 != null)
            qty = cart1.qty;

        if( qty + cart.qty > product.stock) {
            throw new CartQtyExceedsException("quantity more than available quantity");

        }
        if(cart1 == null){
            query = "insert into current_cart values(" + cart.uid + "," + cart.pid + "," + (cart.qty + qty)+
                ");";
        }else {
            query = "update current_cart set qty = " + (cart.qty+ qty) + " where uid = " + cart.uid + " and pid = " + cart.pid + ";";
        }
        System.out.println(query + "fsigj");
        DBConnection.insertQuery(query);
        return true;
    }

    @Override
    public boolean removeProductFromCart(Cart cart) throws Exception {
    	System.out.println(cart.uid + " " + cart.pid + " lufsh ");
        if(getCart(cart.pid,cart.uid).qty < cart.qty){
            cart.qty = getCart(cart.pid,cart.uid).qty;
            query = "delete from current_cart where uid = " + cart.uid + " and pid = " +cart.pid + ";";
            System.out.println(query);
        }
        else{
        	query = "update current_cart set qty = " + (getCart(cart.pid,cart.uid).qty - cart.qty) +" where pid = " + cart.pid + " and uid = " + cart.uid + ";";
        	System.out.println(query);
        }
        //System.out.println(query);
        DBConnection.updateQuery(query);
        return true;
    }
    
    
    public boolean updateProductQtyInCart(Cart cart){
    	Product product = ProductApiImpl.getProduct(cart.pid);
    	System.out.println(product + " lfg " + cart.pid);
    	if(product.stock < cart.qty){
    		return false;
    	}
    	try {
			query = "update current_cart set qty = " + cart.qty +" where pid = " + cart.pid + " and uid = " + cart.uid + ";";
			DBConnection.updateQuery(query);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(query);
    	
    	return true;
    }


    public static ArrayList<Cart> getCarts() throws Exception{
        String query = "select * from current_cart" + ";";
        System.out.println(query);
        ResultSet rs = DBConnection.selectQuery(query);
        ArrayList<Cart> carts = new ArrayList<>();
        while (rs.next()){
            CartBuilder cartBuilder = new CartBuilder();
            cartBuilder.setPid(rs.getInt(2)).setUid(rs.getInt(1)).setQty(rs.getInt(3));
            carts.add(cartBuilder.getCart());
        }
        return carts;
    }


    public static Cart getCart(int pid, int uid) throws Exception{
        String query = "select * from current_cart where pid = " + pid + " and uid = " + uid + ";";
        System.out.println(query);
        ResultSet rs = DBConnection.selectQuery(query);
        CartBuilder cartBuilder = new CartBuilder();
        if(!rs.next()) return null;
        cartBuilder.setPid(rs.getInt(2)).setUid(rs.getInt(1)).setQty(rs.getInt(3));
        return cartBuilder.getCart();
    }




    public static double totalPriceOfCart(int uid) throws Exception{
        //select cc.uid,sum(price*cc.qty) from current_cart cc, product p where cc.pid = p.pid and cc.uid = 1 group by cc.uid;
        String query = "select cc.uid,sum(price*cc.qty) from current_cart cc, product p where cc.pid = p.pid and cc.uid = " + uid + " " +
                "group by cc.uid";
        ResultSet rs = DBConnection.selectQuery(query);
        if(rs.next()){
            return rs.getDouble(2);
        }

        return -1;
    }

    public static ArrayList<Cart> getCart(int uid) throws Exception{
        String query = "select * from current_cart where uid = " + uid + ";";
        ResultSet rs = DBConnection.selectQuery(query);
        ArrayList<Cart> carts = new ArrayList<>();
        while (rs.next()){
            CartBuilder cb = new CartBuilder();
            Cart c = cb.setUid(rs.getInt(1)).setPid(rs.getInt(2)).setQty(rs.getInt(3)).getCart();
            carts.add(c);
        }
        return carts;
    }

    @Override
    public boolean purchaseCart(int uid,int aid,int bid) throws Exception{
        if(!UserApiImpl.isUserIdValid(uid)){
            System.out.println("not a valid user");
        	return false;
        }


        ArrayList<Cart> carts = CartApiImpl.getCart(uid);
        if(carts.size() == 0){
            return false;
        }
        DBConnection.getConnection().setAutoCommit(false);
        query = "insert into bill values(" + bid + ", " + aid + ", " + uid + ", " + totalPriceOfCart(uid) +
                ", current_timestamp );";
        try {
            DBConnection.insertQuery(query);
        }catch (Exception e){
            e.printStackTrace();
            DBConnection.getConnection().rollback();
            return false;
        }



        for (Cart c : carts){
            if(c.uid == uid) {
                Product p = ProductApiImpl.getProduct(c.pid);

                if (CartApiImpl.getCart(c.pid, c.uid).qty > p.stock) {
                    throw new CartQtyExceedsException("IN PURCHASSE CART qty in cart is greater than available stock");

                }

                p.stock -= c.qty;
                p.pname = null;
                p.price = -1;
                ProductApiImpl productApi = new ProductApiImpl();
                try {

                    productApi.updateProducts(p);
                } catch (Exception e) {
                    e.printStackTrace();
                    DBConnection.getConnection().rollback();
                    return false;
                }
                try {
                    insertIntoOrderHistory(c, bid);
                } catch (Exception e) {
                    e.printStackTrace();
                    DBConnection.getConnection().rollback();
                    return false;

                }
            }
        }

        query = "delete from current_cart where uid = "  + uid + ";";
        DBConnection.insertQuery(query);


        DBConnection.getConnection().commit();
        DBConnection.getConnection().setAutoCommit(true);
        return true;
    }
    
    
    
    public Bill getBill(int bid){
    	query = "select * from bill where bid = " + bid + ";";
    	ResultSet resultSet;
		try {
			resultSet = DBConnection.selectQuery(query);
			if(resultSet.next()){
				BillBuilder billBuilder = new BillBuilder();
				billBuilder.setUid(resultSet.getInt("uid")).setAid(resultSet.getInt("aid")).setBid(resultSet.getInt("bid")).setTotalprice(resultSet.getInt("totalprice"));
				return billBuilder.getBill();
				
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return null;
    }

    private boolean insertIntoOrderHistory(Cart cart,int bid){
        double price = ProductApiImpl.getProduct(cart.pid).price * cart.qty;
        query = "insert into order_history values(" + bid + "," + cart.uid + ", " + cart.pid + ", " + cart.qty + ", " + price + ", 0" +
                "); ";
        System.out.println(query);
        try {
            DBConnection.insertQuery(query);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
