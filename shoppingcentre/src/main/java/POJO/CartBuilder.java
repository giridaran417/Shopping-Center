
package POJO;

public class CartBuilder{
    int uid,pid,qty;
    double price;

    public int getUid() {
        return uid;
    }

    public CartBuilder setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public int getPid() {
        return pid;
    }

    public CartBuilder setPid(int pid) {
        this.pid = pid;
        return this;
    }

    public int getQty() {
        return qty;
    }

    public CartBuilder setQty(int qty) {
        this.qty = qty;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public CartBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public Cart getCart(){
        return new Cart(uid,pid,qty,price);
    }
}
