//$Id$
package POJO;

public class OrderHistory {
	int bid,uid,pid,qty,rqty;
	double price;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getRqty() {
		return rqty;
	}
	public void setRqty(int rqty) {
		this.rqty = rqty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public OrderHistory(int bid, int uid, int pid, int qty, int rqty, double price) {
		
		this.bid = bid;
		this.uid = uid;
		this.pid = pid;
		this.qty = qty;
		this.rqty = rqty;
		this.price = price;
	}
	public OrderHistory() {
	
	}
	
	
}
