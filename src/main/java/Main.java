

import org.graalvm.polyglot.*;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // Read the entire contents of scripts.
            String jsCode = readJsCode("scripts.js");
            User user = new User("temp_firstname", "temp_lastname", "temp_username", "1111", "temp@gmail.com");
            runJsCode(user, jsCode);
            System.out.println(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runScript(String script, Context context) {
        context.eval("js", script);
    }

    private static String readJsCode(String address) throws IOException {
        File file = new File(address);
        return FileUtils.readFileToString(file, "UTF-8");
    }

    private static void runJsCode(User user, String jsCode) {
        Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build();
        context.getBindings("js").putMember("user", user);
        runScript(jsCode, context);
    }
}