package POJO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.giri.restapi.StringCodes;

public class SellerApiImpl implements SellerApi {
    String query;
    String SID_CODE = "sid";
    String AID_CODE = "asid", NAME_CODE = "name", EMAIL_CODE = "email",ADDRESS_code = "addr",
        PIN_CODE = "pincode",PHONE_NOMBER = "p_no",SELLER_NAME_CODE = "sname",PASSWORD_CODE = "password";
    {
    	new DBConnection().setConnection("giri", "root", "");
    }
    @Override
    public Seller signUp(HashMap<String ,String> input) throws Exception {
        int sid=0,aid=0;	
        String name="",email="",sname="",password="",p_no="";
        String address = null,pincode=null;



        //To generate unique id for sid and aid
        UniqueIdGenerator sid_uniquegenrator = new UniqueIdGenerator();
        UniqueIdGenerator aid_uniqueIdGenerator = new UniqueIdGenerator();

        SellerBuilder SellerBuilder = new SellerBuilder();


        //input = getSellerInfoFromConsole();


        if(input.containsKey(SELLER_NAME_CODE)){
            SellerBuilder.setSname(input.get("sname"));
            sname = input.get("sname");
        }

        if(input.containsKey("name")){
            SellerBuilder.setName(input.get("name"));
            name = input.get("name");
        }

        if(input.containsKey("email")){
            SellerBuilder.setEmail(input.get("email"));
            email = input.get("email");
        }

        if(input.containsKey("password")){
            SellerBuilder.setPassword(input.get("password"));
            password = input.get("password");
        }

        if(input.containsKey("p_no")){
            SellerBuilder.setP_no(input.get("p_no"));
            p_no = input.get("p_no");
        }

        if(input.containsKey("addr")){
            aid = sid_uniquegenrator.generateUniqueId();
            HashMap<Integer,String > h1 = new HashMap<>();
            h1.put(aid,input.get("addr"));
            SellerBuilder.setAddress(h1);
            address = input.get("addr");
            if(input.containsKey("pincode")){
                HashMap<Integer,String> h = new HashMap<>();
                h.put(aid,input.get("pincode"));
                pincode = input.get("pincode");
            }
        }
        sid = sid_uniquegenrator.generateUniqueId();
        SellerBuilder.setSid(sid);


        password += Home.staticsalt;
        password = BCrypt.hashpw(password, BCrypt.gensalt());

        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        //System.out.println("sid aid sname pincode passw name" + sid+ " " + aid + " " + sname + " " + pincode + " " + password + " " + name);
        String query = "insert into sellers(sid,sname,smail) values( " + sid + " ,'" + name + "','" + email +
                "');";
        //System.out.println(query);
        DBConnection.insertQuery(query);
        query = "insert into seller_details values( " + aid +" , " + sid + " ,'" + address + "','" + pincode +"','"+p_no+
                "');";
        //System.out.println(query);
        DBConnection.insertQuery(query);
        query = "insert into seller_credential values (" + sid +" ,'" + sname +"','"+ password +
                "');";
        System.out.println(query);
        DBConnection.insertQuery(query);
        connection.commit();
        connection.setAutoCommit(true);
        return SellerBuilder.getSeller();
    }

    @Override
    public Seller signIn(String sname, String pass) throws Exception {
        if(!isSellernameValid(sname)){
            return null;
        }

        query = "select * from Seller_credential where sname = '" + sname + "'" +";";
        ResultSet rs = DBConnection.selectQuery(query);
        rs.next();
        pass += Home.staticsalt;
        String hashedPassword = rs.getString(3);

        if(!BCrypt.checkpw(pass,hashedPassword)) {
            System.out.println("dfslhjh");
            return null;
        }

        SellerBuilder SellerBuilder = new SellerBuilder();
        SellerBuilder.setSid(rs.getInt(1)).setSname(rs.getString(2));

        return SellerBuilder.getSeller();
    }

    /**
     *
     * @param sname
     * @return true if sname exist else false
     * @throws Exception
     */
    public boolean isSellernameValid(String sname) throws Exception{
        query = "select sid from Seller_credential where sname = '" + sname + "';";
        System.out.println(query);
        if(DBConnection.selectQuery(query).next()){
            return true;
        }
        return false;
    }

//    private HashMap<String,String> getSellerInfoFromConsole() throws Exception{
//        int sid,aid;
//        String name,email,sname,password,p_no;
//        String address = null,pincode=null;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Type your name ");
//        HashMap<String,String> hashMap = new HashMap<>();
//        name = br.readLine();
//        hashMap.put("name",name);
//
//
//        System.out.println("Type the POJO.Seller name it should be unique");
//        sname = br.readLine();
//
//
//        while (isSellernameValid(sname)) {
//            System.out.println("Entered POJO.Seller name is not unique so try with some other sname");
//            sname = br.readLine();
//        }
//
//        hashMap.put("sname",sname);
//
//        System.out.println("Type the password");
//        password = br.readLine();
//        hashMap.put("password",password);
//
//        System.out.println("Enter your street name");
//        address = br.readLine();
//        hashMap.put("address",address);
//
//        System.out.println("enter the phone number");
//        p_no = br.readLine();
//        hashMap.put("p_no",p_no);
//
//        System.out.println("Enter your email");
//        email = br.readLine();
//        hashMap.put("email",email);
//
//        System.out.println("Enter pincode");
//        pincode = br.readLine();
//        hashMap.put("pincode",pincode);
//
//        return hashMap;
//
//    }
    
    
    public Seller getSeller(int id) {

    	String query = "select * from seller_credential uc,sellers u,seller_details ud" +
        " where uc.sid = " + id  + " and uc.sid=u.sid and uc.sid=ud.sid;";
    	//System.out.println(query + "query in getSeller");
    	try {
			ResultSet rs = DBConnection.selectQuery(query);
			return resultSetToSeller(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}
    
    
	public ArrayList<Seller> getAllSellers(int pageNumber) {
	    	
	    	String query = "select * from seller_credential uc,sellers u,seller_details ud" +
	    	" where uc.sid=u.sid and uc.sid=ud.sid and uc.sid >"+(pageNumber*StringCodes.LIMITS)+" and uc.sid<=" + ((pageNumber+1)*StringCodes.LIMITS);
	    
	    	try {
				ResultSet resultSet = DBConnection.selectQuery(query);
				ArrayList<Seller> sellers = resultSetToSellerList(resultSet);
	
				for(int i=0;i<sellers.size();i++) {
					sellers.get(i).setAddress(getAddress(sellers.get(i).getSid()));
		            sellers.get(i).setPincode(getPincode(sellers.get(i).getSid()));
				}
				 return sellers;
			
	    	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return null;
	    }
    

    private ArrayList<Seller> resultSetToSellerList(ResultSet resultSet){
        HashMap<Integer,Seller> seenSeller = new HashMap<>();        
    
        try {
            while (resultSet.next()) {
                if(seenSeller.containsKey(resultSet.getInt(1))==false){
                    SellerBuilder builder = new SellerBuilder();
                    builder.setSid(resultSet.getInt(StringCodes.SID_CODE));
                    builder.setSname(resultSet.getString(StringCodes.SELLER_NAME_CODE));
                    builder.setPassword(resultSet.getString(StringCodes.PASSWORD_CODE));
                    builder.setName(resultSet.getString("sname"));
                    builder.setEmail(resultSet.getString("smail"));
                    builder.setP_no(resultSet.getString(StringCodes.PHONE_NOMBER));
                    seenSeller.put(resultSet.getInt(StringCodes.SID_CODE), builder.getSeller());
                }
            }
            return new ArrayList<Seller>(seenSeller.values());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private HashMap<Integer, String> getAddress(int sid) {
        HashMap<Integer, String> address =  new HashMap<Integer, String>();
        String query = "select aid,addr from seller_details where sid = " + sid + ";";
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
    
    private HashMap<Integer, String> getPincode(int sid) {
        HashMap<Integer, String> pincode =  new HashMap<>();
        String query = "select aid,pincode from seller_details where sid = " + sid + ";";
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

    
    
    
    
    private Seller resultSetToSeller(ResultSet resultSet){
    	SellerBuilder builder = new SellerBuilder();
    	HashMap<Integer,String> address = new HashMap<>();
    	HashMap<Integer,String> pincode = new HashMap<>();

        boolean first_time = true;
        
            try {
                while (resultSet.next()) {
                    if(first_time){
                        builder.setSid(resultSet.getInt(1));
                        builder.setSname(resultSet.getString(2));
                   
                        builder.setPassword(resultSet.getString(3));
                        builder.setName(resultSet.getString("sname"));
                        builder.setEmail(resultSet.getString("smail"));
                        address.put(resultSet.getInt("asid"),
                                resultSet.getString("addr"));
                        pincode.put(resultSet.getInt("asid"),
                                resultSet.getString("pincode"));
                        builder.setP_no(resultSet.getString("p_no"));
                    }else{
                        address.put(resultSet.getInt("asid"),
                                resultSet.getString("addr"));
                        pincode.put(resultSet.getInt("asid"),
                                resultSet.getString("addr"));
                    }
                }
                builder.setAddress(address);
                builder.setPincode(pincode);
                Seller seller = builder.getSeller();
                return seller;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    	
            
    }
    
    public void insertAddress(int sid, String address, String pincode) {
    	String query = "select p_no from seller_details where sid = " + sid + ";";
    	try {
			ResultSet rSet = DBConnection.selectQuery(query);
			String phone = rSet.getString(1);
			int aid = new UniqueIdGenerator().generateUniqueId();
			query = "insert into seller_details values(" + aid + "," + sid + ", '" + address + "' ," +
					pincode + ", '" + phone + "'" + ");";
			
			DBConnection.insertQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
