import model.User;
import java.util.HashMap;

public class Main {
    private static String address="scripts.js";
    public static void main(String[] args) throws Exception {
        User user = new User("temp_firstname", "temp_lastname", "temp_username", "1111", "temp@gmail.com");
        HashMap<String , Object> userHashMap = new HashMap<>();
        userHashMap.put("user",user);
        JsCodeGenerator jsCodeGenerator=new JsCodeGenerator(userHashMap ,address);
        jsCodeGenerator.runJsCode();
    }
}