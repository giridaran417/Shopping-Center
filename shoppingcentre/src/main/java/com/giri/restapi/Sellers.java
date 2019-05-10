//$Id$
package com.giri.restapi;

import java.util.*;


import POJO.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
@Path("sellers")
public class Sellers {
	
	ProductApi productApiImpl = new POJO.ProductApiImpl();
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{id}")
	public Seller getSeller(@PathParam("id") int id){
	
		SellerApiImpl sellerApi = new SellerApiImpl();
			
		Seller seller = sellerApi.getSeller(id);
		if(seller == null){
			return new Seller();
		}
		return seller;
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ArrayList<Seller> getSellers(@QueryParam(StringCodes.PAGE_CODE) int page,
			@QueryParam(StringCodes.USER_NAME_CODE) String uname
			){
		
		ArrayList<Seller> Sellers = new SellerApiImpl().getAllSellers(page);
		return Sellers;
	}
	@POST
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Seller seller(Seller sellerPojo){	
		SellerApiImpl sellerApiImpl = new SellerApiImpl();
		SellerBuilder sellerBuilder = new SellerBuilder();
		Seller seller = null;
		String name = sellerPojo.getName();
		String uname = sellerPojo.getUname();
		String email = sellerPojo.getEmail();
		String pass = sellerPojo.getPassword();
		String p_no = sellerPojo.getP_no();
		HashMap<Integer, String> addr = sellerPojo.getAddress();
		HashMap<Integer, String> pin = sellerPojo.getPincode();
		int id = sellerPojo.getSid();
		int aid = sellerPojo.getAid();
		System.out.println("inside Post1 " + sellerPojo.getUname() + " " + sellerPojo.getPassword() + " " +sellerPojo.getEmail() + " " + id);
		//signIn
		if(id == 0 && (uname != null && pass != null && email == null)){
			
			System.out.println("inside sigin");
			try {
				seller = sellerApiImpl.signIn(uname, pass);
				return seller;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return seller;
			
		}
		//signUp 
		else if (uname != null && pass != null && email != null ) {
			System.out.println("inside signUp seller " + addr.get(aid+"") + " " + aid + " " + addr.size());
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
	        System.out.println("aid in Sellers " + aid1);
	        input.put(StringCodes.UID_CODE, uid1 + "");
	        input.put(StringCodes.AID_CODE, aid1 + "");
			input.put(StringCodes.NAME_CODE, name);
			input.put(StringCodes.SELLER_NAME_CODE, uname);
			input.put(StringCodes.PASSWORD_CODE, pass);
			input.put(StringCodes.EMAIL_CODE,email);
			input.put(StringCodes.ADDRESS_code, arrayList.get(0));
			input.put(StringCodes.PIN_CODE, arrayList2.get(0));
			input.put(StringCodes.PHONE_NOMBER, p_no);
			
			System.out.println("before sign up");
			try {
				seller = sellerApiImpl.signUp(input);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("afetr signup");
			for(int i=1;i<arrayList.size();i++){
				sellerApiImpl.insertAddress(uid1,arrayList.get(i), arrayList2.get(i));
			}
			
			return seller;
		}
		//isSellernameExists 
		else if (id==0 && pass == null && uname != null ) {
			System.out.println("inside issellernameexists");
			try {
				boolean isSellerNameValid = sellerApiImpl.isSellernameValid(uname);
				if(isSellerNameValid){
					sellerBuilder.setSname(uname);
					return sellerBuilder.getSeller();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return seller;
		
		}
		
		
		return seller;
		
	}
	
	

	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{sid}/product")

	public boolean addProductToDatabase(@PathParam("sid") int sid,Product product){
		return productApiImpl.addProductsToDatabase(product);
	}
	
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{sid}/product/{pid}")
	
	public boolean removeProduct(@PathParam("sid") int sid,
			@PathParam("pid") int pid){
		return productApiImpl.removeProductsFromDatabase(pid, sid); 
	}
	
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{sid}/product/{pid}")
	
	public boolean updateProduct(@PathParam("sid") int sid,
			@PathParam("pid") int pid,Product product){
		product.setPid(pid);
		product.setSid(sid);
		return productApiImpl.updateProducts(product);
	}
	
}
