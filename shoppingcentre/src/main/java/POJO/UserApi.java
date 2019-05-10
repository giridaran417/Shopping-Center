package POJO;

import java.util.HashMap;

public interface UserApi {
    User signUp(HashMap<String, String> input) throws Exception;
    User signIn(String uname, String pass) throws Exception;
    boolean updateIsVerfied(String uname);

}
