//$Id$
package POJO;

import java.sql.ResultSet;

import com.sun.crypto.provider.RSACipher;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class OrderHistoryApiImpl implements OrderHistoryApi{
	{
		new DBConnection().setConnection("giri", "root", "");
	}
	
	
	@Override
	public OrderHistory getOrderHistory(int uid) {
		String query = "select * from order_history where uid = " + uid + ";";
		try {
			ResultSet rs = DBConnection.selectQuery(query);
			OrderHistoryBuilder ohb = new OrderHistoryBuilder();
			if(rs.next())
				ohb.setUid(rs.getInt("uid")).setBid(rs.getInt("bid")).setPrice(rs.getDouble("price")).setPid(rs.getInt("pid")).setQty(rs.getInt("qty"));
			return ohb.getOrderHistory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
