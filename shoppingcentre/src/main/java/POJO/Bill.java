//$Id$
package POJO;

public class Bill {
	int bid,aid,uid;
	double totalprice;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	
	public Bill(){
		
	}
	public Bill(int bid, int aid, int uid, double totalprice) {
		super();
		this.bid = bid;
		this.aid = aid;
		this.uid = uid;
		this.totalprice = totalprice;
	}
	
	
}



