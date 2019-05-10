package POJO;

import java.util.HashMap;

public class UserBuilder{
    private int uid,aid;
    private String name,email,uname,password,p_no;
    private HashMap<Integer,String > address = null,pincode=null;


    @Override
    public String toString() {
        return (uid + " " + aid + " " + name + " " + email + " " + uname + " " +
                password + " " + p_no + " " + address + " " +pincode);

    }

    public UserBuilder setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public UserBuilder setAid(int aid) {
        this.aid = aid;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setUname(String uname) {
        this.uname = uname;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setP_no(String p_no) {
        this.p_no = p_no;
        return this;
    }

    public UserBuilder setAddress(HashMap<Integer, String> address) {
        this.address = address;
        return this;
    }

    public UserBuilder setPincode(HashMap<Integer, String> pincode) {
        this.pincode = pincode;
        return this;
    }

    public User getUser(){
        User u = new User(this.uid,this.aid,this.name,this.email,this.uname,this.password,this.p_no,this.address,this.pincode);
        return u;
    }
}