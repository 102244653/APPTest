package Utils;

public class MyException extends NullPointerException {
    public MyException() {
        super("元素未找到...");
    }
}
