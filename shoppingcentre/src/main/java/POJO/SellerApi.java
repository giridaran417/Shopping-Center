package POJO;

import java.util.HashMap;

public interface SellerApi {
    Seller signUp(HashMap<String,String> input) throws Exception;
    Seller signIn(String sid, String pass) throws Exception;
}
