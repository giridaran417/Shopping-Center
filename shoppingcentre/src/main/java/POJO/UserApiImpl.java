package POJO;

import javax.print.attribute.standard.MediaSize;

import com.giri.restapi.StringCodes;
import com.sun.crypto.provider.RSACipher;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class UserApiImpl implements UserApi {
    String query;

    String UID_CODE = "uid";
    String AID_CODE = "aid", NAME_CODE = "name", EMAIL_CODE = "email",ADDRESS_code = "addr",
        PIN_CODE = "pincode",PHONE_NOMBER = "p_no",USER_NAME_CODE = "uname",PASSWORD_CODE = "password";
    {
    	new DBConnection().setConnection("giri", "root", "");
    }
    
    
    @Override
    public User signUp(HashMap<String,String > input ) {
        int uid = 0, aid = 0;
        String dummy = "";


        String name = "", email = "", uname = "", password = "", p_no = "";
        String address = null, pincode = null;

        String s11111 = null;
        //To generate unique id for uid and aid
        

        UserBuilder userBuilder = new UserBuilder();


        //input = getUserInfoFromConsole();


        if (input.containsKey(USER_NAME_CODE)) {
            userBuilder.setUname(input.get(USER_NAME_CODE));
            uname = input.get(USER_NAME_CODE);
        }

        if (input.containsKey(NAME_CODE)) {
            userBuilder.setName(input.get(NAME_CODE));
            name = input.get(NAME_CODE);
        }

        if (input.containsKey(EMAIL_CODE)) {
            userBuilder.setEmail(input.get(EMAIL_CODE));
            email = input.get(EMAIL_CODE);
        }

        if (input.containsKey(PASSWORD_CODE)) {
            userBuilder.setPassword(input.get(PASSWORD_CODE));
            password = input.get(PASSWORD_CODE);
        }

        if (input.containsKey(PHONE_NOMBER)) {
            userBuilder.setP_no(input.get(PHONE_NOMBER));
            p_no = input.get(PHONE_NOMBER);
        }

        
        if (input.containsKey(ADDRESS_code)) {
            
            HashMap<Integer, String> h1 = new HashMap<>();
            h1.put(aid, input.get(ADDRESS_code));
            userBuilder.setAddress(h1);
            address = input.get(ADDRESS_code);
            if (input.containsKey(PIN_CODE)) {
                HashMap<Integer, String> h = new HashMap<>();
                h.put(aid, input.get(PIN_CODE));
                pincode = input.get(PIN_CODE);
            }
        }
        aid = Integer.parseInt(input.get(AID_CODE));
        uid = Integer.parseInt(input.get(UID_CODE));
        userBuilder.setUid(uid);
        
        System.out.println("aid=" + input.get(AID_CODE));
        password += Home.staticsalt;
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            Connection connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            System.out.println("uid aid uname pincode passw name" + uid+ " " + aid + " " + uname + " " + pincode + " " + password + " " + name);
            String query = "insert into user values( " + uid + " ,'" + name + "','" + email +
                    "');";
            //System.out.println(query);
            DBConnection.insertQuery(query);
            query = "insert into user_details values( " + aid + " , " + uid + " ,'" + address + "','" + pincode + "','" + p_no +
                    "');";
            //System.out.println(query);
            DBConnection.insertQuery(query);
            query = "insert into user_credentials(uid,uname,password) values (" + uid + " ,'" + uname + "','" + password +
                    "');";


            DBConnection.insertQuery(query);
            System.out.println(query);
            connection.commit();
            connection.setAutoCommit(true);
            return userBuilder.getUser();
        }catch (Exception e){
            try {
                e.printStackTrace();
                DBConnection.getConnection().rollback();
                return null;
            }catch (Exception e1){
                e1.printStackTrace();
                return null;
            }
        }finally {
            try {
                DBConnection.getConnection().setAutoCommit(true);
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

    }

    @Override
    public boolean updateIsVerfied(String uname) {
        String query = "update user_credentials set isverified = 1 where uname = '" + uname + "';";
        try {
            DBConnection.updateQuery(query);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  false;
    }
    
   

    @Override
    public User signIn(String uname, String pass) throws Exception {
        if(!isUsernameValid(uname)){
            return null;
        }

        query = "select * from user_credentials where uname = '" + uname + "'" +";";
        ResultSet rs = DBConnection.selectQuery(query);
        rs.next();
        pass += Home.staticsalt;
        String hashedPassword = rs.getString(3);

        if(!BCrypt.checkpw(pass,hashedPassword)) {
            System.out.println("dfslhjh");
            return null;
        }

        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setUid(rs.getInt(1)).setUname(rs.getString(2));

        return userBuilder.getUser();
    }

    /**
     *
     * @param uname
     * @return true if uname exist else false
     * @throws Exception
     */
    public static boolean isUsernameValid(String uname) throws Exception{
        String query = "select uid from user_credentials where uname = '" + uname + "';";
        System.out.println(query);
        if(DBConnection.selectQuery(query).next()){
            return true;
        }
        return false;
    }
    
    
   
    
    public void insertAddress(int uid, String address, String pincode) {
    	String query = "select p_no from user_details where uid = " + uid + ";";
    	System.out.println(query);
    	try {
			ResultSet rSet = DBConnection.selectQuery(query);
			rSet.next();
			String phone = rSet.getString(1);
			int aid = new UniqueIdGenerator().generateUniqueId();
			query = "insert into user_details values(" + aid + "," + uid + ", '" + address + "' ," +
					pincode + ", '" + phone + "'" + ");";
			
			DBConnection.insertQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static HashMap<Integer,String> getUserAddress(int uid) throws Exception{
        if(!isUserIdValid(uid)) return null;
        String query = "select aid,address from user_details where uid = " + uid + ";";
        System.out.println(query);
        ResultSet rs = DBConnection.selectQuery(query);
        HashMap<Integer,String> hashMap = new HashMap<>();
        while (rs.next()){
            hashMap.put(rs.getInt(1),rs.getString(2));
        }
        return hashMap;
    }
    
    
    public User getUser(int id) {

    	String query = "select * from user_credentials uc,user u,user_details ud" +
        " where uc.uid = " + id  + " and uc.uid=u.id and uc.uid=ud.uid;";
    	//System.out.println(query + "query in getUser");
    	try {
			ResultSet rs = DBConnection.selectQuery(query);
			return resultSetToUser(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}
    
    
    public ArrayList<User> getAllUsers(int pageNumber) {
    	
    	String query = "select * from user_credentials uc,user u,user_details ud" +
    	" where uc.uid=u.id and uc.uid=ud.uid and uc.uid >"+(pageNumber*StringCodes.LIMITS)+" and uc.uid<=" + ((pageNumber+1)*StringCodes.LIMITS);
    
    	try {
			ResultSet resultSet = DBConnection.selectQuery(query);
			ArrayList<User> users = resultSetToUserList(resultSet);

			for(int i=0;i<users.size();i++) {
				users.get(i).setAddress(getAddress(users.get(i).getUid()));
	            users.get(i).setPincode(getPincode(users.get(i).getUid()));
			}
			 return users;
		
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    

    private ArrayList<User> resultSetToUserList(ResultSet resultSet){
        HashMap<Integer,User> seenUser = new HashMap<>();        
    
        try {
            while (resultSet.next()) {
                if(seenUser.containsKey(resultSet.getInt(1))==false){
                    UserBuilder builder = new UserBuilder();
                    builder.setUid(resultSet.getInt(StringCodes.UID_CODE));
                    builder.setUname(resultSet.getString(StringCodes.USER_NAME_CODE));
                    builder.setPassword(resultSet.getString(StringCodes.PASSWORD_CODE));
                    builder.setName(resultSet.getString(StringCodes.NAME_CODE));
                    builder.setEmail(resultSet.getString(StringCodes.EMAIL_CODE));
                    builder.setP_no(resultSet.getString(StringCodes.PHONE_NOMBER));
                    seenUser.put(resultSet.getInt(StringCodes.UID_CODE), builder.getUser());
                }
            }
            return new ArrayList<User>(seenUser.values());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private HashMap<Integer, String> getAddress(int uid) {
        HashMap<Integer, String> address =  new HashMap<Integer, String>();
        String query = "select aid,addr from user_details where uid = " + uid + ";";
        try {
            ResultSet resultSet = DBConnection.selectQuery(query);
            
           
    
            while(resultSet.next()) {
                address.put(resultSet.getInt(StringCodes.AID_CODE), resultSet.getString(StringCodes.ADDRESS_code));
            }
            return address;
    
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    
    }
    
    private HashMap<Integer, String> getPincode(int uid) {
        HashMap<Integer, String> pincode =  new HashMap<>();
        String query = "select aid,pincode from userdetails where uid = " + uid + ";";
        try {
            ResultSet resultSet = DBConnection.selectQuery(query);
    
            while(resultSet.next()) {
                pincode.put(resultSet.getInt(StringCodes.AID_CODE), resultSet.getString(StringCodes.PIN_CODE));
            }
            return pincode;
    
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    
    }

    
    
    
    
    private User resultSetToUser(ResultSet resultSet){
    	UserBuilder builder = new UserBuilder();
    	HashMap<Integer,String> address = new HashMap<>();
    	HashMap<Integer,String> pincode = new HashMap<>();

        boolean first_time = true;
        
            try {
                while (resultSet.next()) {
                    if(first_time){
                        builder.setUid(resultSet.getInt(1));
                        builder.setUname(resultSet.getString(2));
                   
                        builder.setPassword(resultSet.getString(3));
                        builder.setName(resultSet.getString("name"));
                        builder.setEmail(resultSet.getString("email"));
                        address.put(resultSet.getInt("aid"),
                                resultSet.getString("addr"));
                        pincode.put(resultSet.getInt("aid"),
                                resultSet.getString("pincode"));
                        builder.setP_no(resultSet.getString("p_no"));
                    }else{
                        address.put(resultSet.getInt("aid"),
                                resultSet.getString("addr"));
                        pincode.put(resultSet.getInt("aid"),
                                resultSet.getString("addr"));
                    }
                }
                builder.setAddress(address);
                builder.setPincode(pincode);
                User user = builder.getUser();
                return user;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    	
    }

    public static  boolean isUserIdValid(int uid) throws Exception{
        String query = "select id from user where id = " + uid + ";";
        System.out.println(query);
        ResultSet rs = DBConnection.selectQuery(query);
        if(rs.next()){
            return true;
        }
        return false;
    }
    
    
    public static  boolean isAddresIdValid(int aid) throws Exception{
        String query = "select aid from user_details where aid = " + aid + ";";
        System.out.println(query);
        ResultSet rs = DBConnection.selectQuery(query);
        if(rs.next()){
            return true;
        }
        return false;
    }
    
    public static  boolean isBillIdValid(int bid) throws Exception{
        String query = "select bid from bill where bid = " + bid + ";";
        System.out.println(query);
        ResultSet rs = DBConnection.selectQuery(query);
        if(rs.next()){
            return true;
        }
        return false;
    }

    private HashMap<String,String> getUserInfoFromConsole() throws Exception{
        int uid,aid;
        String name,email,uname,password,p_no;
        String address = null,pincode=null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type your name ");
        HashMap<String,String> hashMap = new HashMap<>();
        name = br.readLine();
        hashMap.put("name",name);


        System.out.println("Type the user name it should be unique");
        uname = br.readLine();


        while (isUsernameValid(uname)) {
            System.out.println("Entered user name is not unique so try with some other uname");
            uname = br.readLine();
        }

        hashMap.put("uname",uname);

        System.out.println("Type the password");
        password = br.readLine();
        hashMap.put("password",password);

        System.out.println("Enter your street name");
        address = br.readLine();
        hashMap.put("address",address);

        System.out.println("enter the phone number");
        p_no = br.readLine();
        hashMap.put("p_no",p_no);

        System.out.println("Enter your email");
        email = br.readLine();
        hashMap.put("email",email);

        System.out.println("Enter pincode");
        pincode = br.readLine();
        hashMap.put("pincode",pincode);

        return hashMap;

    }
}
