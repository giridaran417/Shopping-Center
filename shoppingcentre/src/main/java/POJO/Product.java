package POJO;

public class Product {
    int pid,sid,stock,no_users;
    float avg_rating,price;
    String pname;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getNo_users() {
        return no_users;
    }

    public void setNo_users(int no_users) {
        this.no_users = no_users;
    }

    public float getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(float avg_rating) {
        this.avg_rating = avg_rating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Product(int pid, int sid, int stock, int no_users, float avg_rating, float price, String pname) {
        this.pid = pid;
        this.sid = sid;
        this.stock = stock;
        this.no_users = no_users;
        this.avg_rating = avg_rating;
        this.price = price;
        this.pname = pname;
    }

    public Product() {
		
	}
    @Override
    public String toString() {
        return (pid + " " + sid +  " " +  pname  + " " + stock + " " + price + " " + avg_rating + " " +
                no_users + "\n");
    }
}


class ProductBuilder{
    int pid,sid,stock,no_users;
    float avg_rating,price;
    String pname;

    public int getPid() {
        return pid;
    }

    public ProductBuilder setPid(int pid) {
        this.pid = pid;
        return this;
    }

    public int getSid() {
        return sid;
    }

    public ProductBuilder setSid(int sid) {
        this.sid = sid;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public ProductBuilder setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public int getNo_users() {
        return no_users;
    }

    public ProductBuilder setNo_users(int no_users) {
        this.no_users = no_users;
        return this;
    }

    public float getAvg_rating() {
        return avg_rating;
    }

    public ProductBuilder setAvg_rating(float avg_rating) {
        this.avg_rating = avg_rating;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public ProductBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getPname() {
        return pname;
    }

    public ProductBuilder setPname(String pname) {
        this.pname = pname;
        return this;
    }

    public Product getProduct(){
        return new Product(pid,sid,stock,no_users,avg_rating,price,pname);
    }
}
