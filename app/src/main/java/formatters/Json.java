package formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*import org.json.JSONException;
import org.json.JSONObject;*/

import java.util.LinkedHashMap;
import java.util.Map;

public class Json {
    static final String ACT_NOACTION = "NoAction";
    static final String ACT_EDIT = "Edit";
    static final String ACT_ADD = "Add";
    static final int SPACE_FOR_JSON = 4;
    static final ObjectMapper MAPPER = new ObjectMapper();
    public static String toJson(Map<Map.Entry<String, Object>, String> dataWhatHappen) throws JsonProcessingException
        /*throws JSONException*/ {
        Map<String, Object> totalMapToJson = new LinkedHashMap<String, Object>();
        for (Map.Entry<Map.Entry<String, Object>, String> paramValueAll : dataWhatHappen.entrySet()) {
            switch (paramValueAll.getValue()) {
                case ACT_NOACTION -> totalMapToJson
                        .put(paramValueAll.getKey().getKey(), paramValueAll.getKey().getValue());
                /*case ACT_DEL -> sbDiffer.append("  - ").append(paramValueAll.getKey().getKey()).append(": ")
                        .append(paramValueAll.getKey().getValue()).append("\n");*/
                case ACT_ADD -> totalMapToJson.put(paramValueAll.getKey().getKey(), paramValueAll.getKey().getValue());
                case ACT_EDIT -> totalMapToJson.put(paramValueAll.getKey().getKey(), paramValueAll.getKey().getValue());
                default -> {
                }
            }
        }
        /*JSONObject output = new JSONObject(totalMapToJson);
        String jsonNewLines = output.toString(SPACE_FOR_JSON);*/
        String jsonNewLines = MAPPER.writeValueAsString(totalMapToJson);
        return jsonNewLines;
    }
}
