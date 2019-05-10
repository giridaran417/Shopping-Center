package POJO;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Random;

import com.sun.crypto.provider.RSACipher;

public class UniqueIdGenerator {
    int id;
    HashSet<Integer> hashSet = new HashSet<>();
    Random r = new Random();
    
    public int generateUniqueId(){
        
    	int t = Math.abs(r.nextInt()) + 2000;
        
        while (hashSet.contains(t)){
            t = r.nextInt();
        }
        
        try {
			while(UserApiImpl.isUserIdValid(t) || UserApiImpl.isAddresIdValid(t) || UserApiImpl.isBillIdValid(t)){
				t= r.nextInt();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        hashSet.add(t);
        return t;
    }
}
