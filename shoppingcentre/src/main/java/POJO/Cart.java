package POJO;

public class Cart {
    int uid,pid,qty;
    double price;
    
    public Cart(){
    	
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cart(int uid, int pid, int qty, double price) {
        this.uid = uid;
        this.pid = pid;
        this.qty = qty;
        this.price = price;
    }

    @Override
    public String toString() {

        String s = String.format("%-10d%-10d%-10d\n",uid,pid,qty);
        return s;
    }
}

