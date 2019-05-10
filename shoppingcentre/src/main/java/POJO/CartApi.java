package POJO;

public interface CartApi {
    boolean addProductToCart(Cart cart) throws Exception;
    boolean removeProductFromCart(Cart cart) throws Exception;
    boolean purchaseCart(int uid, int aid, int bid) throws Exception;
}
