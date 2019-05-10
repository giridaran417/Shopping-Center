//$Id$
package com.giri.restapi;

import java.util.*;


import POJO.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONObject;




@XmlRootElement
@Path("users")
public class Users {
	
	UserApiImpl UserApi = new UserApiImpl();
	CartApiImpl cartApi = new CartApiImpl();
	OrderHistoryApiImpl ohapi = new OrderHistoryApiImpl();
	
	@GET
	@Path("/uid")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getUid(@Context HttpServletRequest request){
		HttpSession httpSession = request.getSession(false);
		System.out.println(httpSession);
		if(httpSession==null){
			System.out.println("session null");
		}
		Integer uid = Integer.parseInt( httpSession.getAttribute(StringCodes.UID_CODE).toString());
		System.out.println("session uid = " + uid);
		return uid;
	}
	
	
	@GET
	@Path("/sid")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getSid(@Context HttpServletRequest request){
		HttpSession httpSession = request.getSession(false);
		System.out.println(httpSession);
		if(httpSession==null){
			System.out.println("session null");
		}
		Integer sid = Integer.parseInt( httpSession.getAttribute(StringCodes.SID_CODE).toString());
		System.out.println("session uid = " + sid);
		return sid;
	}
	
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{id}/orderhistory")
	public OrderHistory getOrderHistory(@PathParam("id") int uid){
		return ohapi.getOrderHistory(uid);
	}
	
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{id}/carts/purchase")
	
	public Bill PurchaseCart(@PathParam("id") int uid,
			@QueryParam("aid") int aid){
		
		int bid = new UniqueIdGenerator().generateUniqueId();
		
		try {
			if(cartApi.purchaseCart(uid, aid, bid)){
				return cartApi.getBill(bid);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	@DELETE
	@Path("/{id}/carts/{pid}")
	
	public boolean removeCart(@PathParam("id") int id,
			@PathParam("pid") int pid){
		
		CartBuilder cartBuilder = new CartBuilder();
		cartBuilder.setPid(pid).setUid(id).setQty(Integer.MAX_VALUE);
		try {
			return cartApi.removeProductFromCart(cartBuilder.getCart());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{id}/carts/{pid}")
	
	public boolean updateCartQty(@PathParam("id") int id,
			@PathParam("pid") int pid,Cart cart){
		
		CartBuilder cartBuilder = new CartBuilder();
		cart.setPid(pid);
		cart.setUid(id);
		return cartApi.updateProductQtyInCart(cart);
		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{id}/carts")
	
	public boolean addCart(@PathParam("id") int id,Cart cart){
		try {
			cart.setUid(id);
			return cartApi.addProductToCart(cart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{id}/carts")
	
	public Response getCart(@PathParam("id") int uid){
		ArrayList<Cart> carts = null;
		try {
			carts = CartApiImpl.getCart(uid);
			GenericEntity<List<Cart>> genericEntity = new GenericEntity<List<Cart>>(carts) {
		      };
			
		      Response r = Response.ok(genericEntity)
	                .header("someHeader", "someHeaderValue")
	                .build();
			return r;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	
	

			@GET
            @Path("/{userID}/addresses")
            public String getAllAddresses(@PathParam("userID") int userID) {
                    User user =  UserApi.getUser(userID);
                    
                    if(user==null) {
                            return null;
                    }
                    
                    ArrayList<Integer> aid = new ArrayList<Integer>(user.getAddress().keySet());
                    
                    JSONObject root = new JSONObject();
                    JSONArray array = new JSONArray();
                    int id;
                    for(int i=0;i<aid.size();i++) {
                            JSONObject jsonObject = new JSONObject();
                            id = aid.get(i);
                            jsonObject.put("Address",user.getAddress().get(id));
                            jsonObject.put("Pincode",user.getPincode().get(id));
                            jsonObject.put("AdressID", id);
                            array.put(jsonObject);
                    }
                    root.put("Addresses", array);
                    return root.toString();
            }
            
            @GET
            @Path("/{userID}/addresses/{addressID}")
            public String getAllAddresses(@PathParam("userID") int userID,@PathParam("addressID") int addressID) {
                    User user =  UserApi.getUser(userID);
                    
                    JSONObject root = new JSONObject();
                    JSONObject jsonObject = new JSONObject();
                    if(user!=null && user.getAddress().containsKey(addressID)) {
                            jsonObject.put("Address",user.getAddress().get(addressID));
                            jsonObject.put("Pincode",user.getPincode().get(addressID));
                            jsonObject.put("AdressID", addressID);
                            root.put("Address", jsonObject);
                            return root.toString();
                    }
                    return null;
            }
        

 	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{id}")
	public User getUser(@PathParam("id") int id){
	
		UserApiImpl userApi = new UserApiImpl();
			
		User user = userApi.getUser(id);
		if(user == null){
			return new User();
		}
		return user;
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ArrayList<User> getUsers(@QueryParam(StringCodes.PAGE_CODE) int page,
			@QueryParam(StringCodes.USER_NAME_CODE) String uname
			){
		
		ArrayList<User> users = new UserApiImpl().getAllUsers(page);
		return users;
	}
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN,MediaType.APPLICATION_FORM_URLENCODED})
	public User user(User userPojo){	
		UserApiImpl userApiImpl = new UserApiImpl();
		UserBuilder userBuilder = new UserBuilder();
		User user = null;
		String name = userPojo.getName();
		String uname = userPojo.getUname();
		String email = userPojo.getEmail();
		String pass = userPojo.getPassword();
		String p_no = userPojo.getP_no();
		HashMap<Integer, String> addr = userPojo.getAddress();
		HashMap<Integer, String> pin = userPojo.getPincode();
		int id = userPojo.getUid();
		int aid = userPojo.getAid();
		System.out.println("inside Post1 " + userPojo.getUname() + " " + userPojo.getPassword() + " " +userPojo.getEmail() );
		//signIn
		if(id == 0 && uname != null && pass != null && email == null ){
			
			System.out.println("inside sigin");
			try {
				user = userApiImpl.signIn(uname, pass);
				return user;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return user;
			
		}
		//signUp 
		else if (uname != null && pass != null && email != null ) {
			System.out.println("inside signUp" + addr.get(aid+"") + " " + aid + " " + addr.size());
			for(HashMap.Entry<Integer,String> entry : addr.entrySet()){
					
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
			HashMap<String, String> input = new HashMap<>();
			
			ArrayList<String> arrayList = new ArrayList<>(addr.values());
			ArrayList<String > arrayList2 = new ArrayList<>(pin.values());
			
			UniqueIdGenerator uid_uniquegenrator = new UniqueIdGenerator();
	        UniqueIdGenerator aid_uniqueIdGenerator = new UniqueIdGenerator();
			int uid1 = uid_uniquegenrator.generateUniqueId();
			int aid1 = aid_uniqueIdGenerator.generateUniqueId();
	        System.out.println("aid in users " + aid1);
	        input.put(StringCodes.UID_CODE, uid1 + "");
	        input.put(StringCodes.AID_CODE, aid1 + "");
			input.put(StringCodes.NAME_CODE, name);
			input.put(StringCodes.USER_NAME_CODE, uname);
			input.put(StringCodes.PASSWORD_CODE, pass);
			input.put(StringCodes.EMAIL_CODE,email);
			input.put(StringCodes.ADDRESS_code, arrayList.get(0));
			input.put(StringCodes.PIN_CODE, arrayList2.get(0));
			input.put(StringCodes.PHONE_NOMBER, p_no);
			
			System.out.println("before sign up");
			user = userApiImpl.signUp(input);
			System.out.println("afetr signup");
			for(int i=1;i<arrayList.size();i++){
				userApiImpl.insertAddress(uid1,arrayList.get(i), arrayList2.get(i));
			}
			
			return user;
		}
		//isUsernameExists 
		else if (id==0 && pass == null && uname != null ) {
			System.out.println("inside isusernameexists");
			try {
				boolean isUserNameValid = userApiImpl.isUsernameValid(uname);
				if(isUserNameValid){
					userBuilder.setUname(uname);
					return userBuilder.getUser();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return user;
		
		}
		
		
		return user;
		
	}
	
	
	
	
}
