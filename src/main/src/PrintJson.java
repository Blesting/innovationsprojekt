import java.util.List;
import java.util.Map;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PrintJson {
    public static void printJson(Map<String, Object> json) {
        printJsonHelper("", json);
    }

    private static void printJsonHelper(String indent, Map<String, Object> json) {
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            System.out.println(indent + entry.getKey() + ":");
            if (entry.getValue() instanceof Map) {
                printJsonHelper(indent + "  ", (Map<String, Object>) entry.getValue());
            } else if (entry.getValue() instanceof List) {
                printListHelper(indent + "  ", (List) entry.getValue());
            } else {
                System.out.println(indent + "  " + entry.getValue());
            }
        }
    }

    private static void printListHelper(String indent, List list) {
        for (Object o : list) {
            if (o instanceof Map) {
                printJsonHelper(indent + "  ", (Map<String, Object>) o);
            } else {
                System.out.println(indent + "  " + o);
            }
        }
    }
    public static Map<String, Object> parseJson(StringBuffer jsonString) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
        return mapper.readValue(String.valueOf(jsonString), typeRef);
    }
}
