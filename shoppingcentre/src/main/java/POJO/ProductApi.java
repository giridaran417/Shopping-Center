package POJO;

import java.util.ArrayList;

public interface ProductApi {
    boolean addProductsToDatabase(Product product);
    boolean removeProductsFromDatabase(int pid, int sid);
    boolean updateProducts(Product product);
    void displayProducts();
    boolean isProductAvailable(int pid);
    ArrayList<Product> getProductsFromDatabase(int limit, int offset);
}
