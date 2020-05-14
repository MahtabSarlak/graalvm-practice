import org.apache.commons.io.FileUtils;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class JsCodeGenerator {
    private final HashMap<String , Object> parameters;
    private final String address;

    public JsCodeGenerator(HashMap<String, Object> parameters, String address) {
        this.parameters = parameters;
        this.address = address;
    }
    private  String readJsCode() throws IOException {
        File file = new File(address);
        return FileUtils.readFileToString(file, "UTF-8");
    }

    public  void runJsCode() throws IOException {
        String jsCode = readJsCode();
        Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build();
        Value value =context.getBindings("js");
        parameters.forEach(value::putMember);
        runScript(jsCode, context);
    }
    private  void runScript(String script, Context context) {
        context.eval("js", script);
    }

}
