package POJO;

public class MyException extends Exception {
    public MyException(String s){
        super(s);
    }
}

class CartQtyExceedsException extends Exception{
    public CartQtyExceedsException(String s){
        super(s);
    }
}
