package POJO;

import java.util.HashMap;

public class Seller {
    private int uid,aid;
    private String name,email,uname,password,p_no;
    private HashMap<Integer,String > address = null,pincode=null;

    public int getSid() {
        return uid;
    }

    public int getAid() {
        return aid;
    }

    public String getName() {
        return name;
    }

    public void setPincode(HashMap<Integer,String > pincode) {
        this.pincode = pincode;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getP_no() {
        return p_no;
    }

    public void setP_no(String p_no) {
        this.p_no = p_no;
    }

    public HashMap<Integer, String> getAddress() {
        return address;
    }

    public void setAddress(HashMap<Integer,String> address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, String> getPincode() {
        return pincode;
    }


    public Seller(int uid,int aid,String name,String email,String uname,String password,String p_no,HashMap address,HashMap pincode){
        this.uid = uid;
        this.aid = aid;
        this.name = name;
        this.email = email;
        this.uname = uname;
        this.password = password;
        this.p_no = p_no;
        this.address = address;
        this.pincode = pincode;
    }


    public Seller() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return (uid + " " + aid + " " + name + " " + email + " " + uname + " " +
                password + " " + p_no + " " + address + " " +pincode + "\n");

    }
}









