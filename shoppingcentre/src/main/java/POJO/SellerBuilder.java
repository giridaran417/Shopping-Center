package POJO;
import java.util.HashMap;

public class SellerBuilder{
    private int sid,aid;
    private String name,email,sname,password,p_no;
    private HashMap<Integer,String > address = null,pincode=null;


    @Override
    public String toString() {
        return (sid + " " + aid + " " + name + " " + email + " " + sname + " " +
                password + " " + p_no + " " + address + " " +pincode);

    }

    public SellerBuilder setSid(int sid) {
        this.sid = sid;
        return this;
    }

    public SellerBuilder setAid(int aid) {
        this.aid = aid;
        return this;
    }

    public SellerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SellerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public SellerBuilder setSname(String uname) {
        this.sname = uname;
        return this;
    }

    public SellerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public SellerBuilder setP_no(String p_no) {
        this.p_no = p_no;
        return this;
    }

    public SellerBuilder setAddress(HashMap<Integer, String> address) {
        this.address = address;
        return this;
    }

    public SellerBuilder setPincode(HashMap<Integer, String> pincode) {
        this.pincode = pincode;
        return this;
    }

    public Seller getSeller(){
        Seller u = new Seller(this.sid,this.aid,this.name,this.email,this.sname,this.password,this.p_no,this.address,this.pincode);
        return u;
    }
}
