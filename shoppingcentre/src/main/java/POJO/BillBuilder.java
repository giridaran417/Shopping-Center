//$Id$
package POJO;

public class BillBuilder {
	int bid,aid,uid;
	double totalprice;
	public int getBid() {
		return bid;
	}
	public BillBuilder setBid(int bid) {
		this.bid = bid;
		return this;
	}
	public int getAid() {
		return aid;
	}
	public BillBuilder setAid(int aid) {
		this.aid = aid;
		return this;
	}
	public int getUid() {
		return uid;
	}
	public BillBuilder setUid(int uid) {
		this.uid = uid;
		return this;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public BillBuilder setTotalprice(double totalprice) {
		this.totalprice = totalprice;
		return this;
	}
	
	public Bill getBill(){
		return new Bill(this.bid,this.aid,this.uid,this.totalprice);
	}

}
