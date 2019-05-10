package POJO;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.giri.restapi.StringCodes;

public class ProductApiImpl implements ProductApi {
	
	{
		new DBConnection().setConnection("giri", "root", "");
	}
    String query;
    private String STOCK_CODE = "stock",PNAME_CODE = "pname",PRICE_CODE = "price";
    @Override
    public boolean addProductsToDatabase(Product product) {
        query = "insert into product values(" + product.pid + "," + product.sid + "," + product.pname + "," + product.stock + "," + product.price + "," + product.avg_rating + "," +
                "" + product.no_users
                  + ");";
        //System.out.println(query);
        try {
            DBConnection.insertQuery(query);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeProductsFromDatabase(int pid, int sid) {
        query = "delete from product where pid = " + pid + " and sid = " + sid  +
                ";";
        try {
            DBConnection.updateQuery(query);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public boolean updateProducts(Product product) {
        String query = "";
        if(product.pname != null){
            query = "update product set " + PNAME_CODE + " = '" + product.pname + "' where pid = " + product.pid + ";" ;
            System.out.println(query);
            try {
                DBConnection.updateQuery(query);
                
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            
        }

        if (product.price >= 0){
            query = "update product set " + PRICE_CODE + " = '" + product.price + "' where pid = " + product.pid + ";" ;
            //System.out.println(query);
            try {
                DBConnection.updateQuery(query);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

        if (product.stock >= 0){
            query = "update product set " + STOCK_CODE + " = '" + product.stock + "' where pid = " + product.pid ;
            //System.out.println(query);
            try {
                DBConnection.updateQuery(query);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public void displayProducts() {

    }

    @Override
    public boolean isProductAvailable(int pid) {
        ArrayList<Product> products = getProductsFromDatabase(0,2);
        if(products != null){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Product> getProductsFromDatabase(int offset, int limit) {
        query = "select * from product limit " + offset + "," + limit +
                "";
        //System.out.println(query);
        try{
            ResultSet rs = DBConnection.selectQuery(query);
            ArrayList<Product> products = new ArrayList<>();

            while (rs.next()){
                ProductBuilder productBuilder = new ProductBuilder();
                productBuilder.setPid(rs.getInt(1)).setSid(rs.getInt(2)).setPname(rs.getString(3)
                ).setStock(rs.getInt(4)).setPrice(rs.getFloat(5)).setAvg_rating(rs.getFloat(6)
                ).setNo_users(rs.getInt(7));

                Product p = productBuilder.getProduct();

                products.add(p);
            }
            return products;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Product getProduct(int pid) {
        String query = "select * from product where pid = " + pid + ";";
        try{
            ResultSet rs = DBConnection.selectQuery(query);

            if (rs.next()){
                ProductBuilder productBuilder = new ProductBuilder();
                productBuilder.setPid(rs.getInt(1)).setSid(rs.getInt(2)).setPname(rs.getString(3)
                ).setStock(rs.getInt(4)).setPrice(rs.getFloat(5)).setAvg_rating(rs.getFloat(6)
                ).setNo_users(rs.getInt(7));

                return productBuilder.getProduct();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<Product> getProductFromDatabaseInSortedOrder(int isdesc,int page){
    	page -= 1;
    	int offeset = page*StringCodes.LIMITS;
    	int limit = offeset + StringCodes.LIMITS;
    	String query1 = "";
    	if(isdesc != 1){
    		 query1 = "select * from product order by pid limit " + offeset + "," + limit + ";";
    	}
    	else {
    		query1 = "select * from product order by pid desc limit " + offeset + "," + limit + ";";
		}
    	ArrayList<Product> products = new ArrayList<>();
    	try {
			ResultSet rs = DBConnection.selectQuery(query1);
			while(rs.next()){
				products.add(new Product(rs.getInt(1), rs.getInt(2), rs.getInt(4), rs.getInt(7), rs.getFloat(6), rs.getFloat(5), rs.getString(3)));
			}
			return products;
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }
}
