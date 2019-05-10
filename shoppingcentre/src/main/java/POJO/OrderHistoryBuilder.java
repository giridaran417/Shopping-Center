//$Id$
package POJO;

public class OrderHistoryBuilder {
	int bid,uid,pid,qty,rqty;
	double price;
	public int getBid() {
		return bid;
	}
	public int getUid() {
		return uid;
	}
	public int getPid() {
		return pid;
	}
	public int getQty() {
		return qty;
	}
	public int getRqty() {
		return rqty;
	}
	public double getPrice() {
		return price;
	}
	public OrderHistoryBuilder setBid(int bid) {
		this.bid = bid;
		return this;	
	}
	public OrderHistoryBuilder setUid(int uid) {
		this.uid = uid;
		return this;
	}
	public OrderHistoryBuilder setPid(int pid) {
		this.pid = pid;
		return this;
	}
	public OrderHistoryBuilder setQty(int qty) {
		this.qty = qty;
		return this;
	}
	public OrderHistoryBuilder setRqty(int rqty) {
		this.rqty = rqty;
		return this;
	}
	public OrderHistoryBuilder setPrice(double price) {
		this.price = price;
		return this;
	}
	
	public OrderHistory getOrderHistory(){
		return new OrderHistory(this.bid,this.uid,this.pid,this.qty,this.rqty,this.price);
	}
	
	
}
