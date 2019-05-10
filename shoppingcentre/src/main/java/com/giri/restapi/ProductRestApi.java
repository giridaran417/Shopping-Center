//$Id$
package com.giri.restapi;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import POJO.CartApiImpl;
import POJO.OrderHistoryApiImpl;
import POJO.Product;
import POJO.ProductApi;
import POJO.ProductApiImpl;
import POJO.UserApiImpl;

@Path("products")
public class ProductRestApi {
	UserApiImpl UserApi = new UserApiImpl();
	CartApiImpl cartApi = new CartApiImpl();
	OrderHistoryApiImpl ohapi = new OrderHistoryApiImpl();
	ProductApi papi = new ProductApiImpl();
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{pid}")
	
	public Object getProduct(@PathParam("pid") int pid,
			@QueryParam("page") int page,
			@QueryParam("price") int desc
			){
		
		
		if( desc != 0)
			return ProductApiImpl.getProductFromDatabaseInSortedOrder(desc, page); 
		else
			return ProductApiImpl.getProduct(pid);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getProducts(@QueryParam("page") int page){
		if(page<=0){
			page=1;
		}
		
		ArrayList<Product> products = papi.getProductsFromDatabase(((page-1)*StringCodes.LIMITS) , StringCodes.LIMITS);
		GenericEntity<List<Product>> genericEntity = new GenericEntity<List<Product>>(products) {
	      };
		
	      Response r = Response.ok(genericEntity)
                .header("someHeader", "someHeaderValue")
                .build();
		return r;
	}
	
	
	
	
	
}
