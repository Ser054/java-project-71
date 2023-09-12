package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, String> getMapParamValueFromJsonString(String contentFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> mapParameterValue =
                objectMapper.readValue(contentFile, new TypeReference<Map<String, String>>() { });
        return mapParameterValue;
    }

    public static Map<String, String> getMapParamValueFromYamlString(String contentFile) throws IOException {
        ObjectMapper objectMapper = new YAMLMapper();
        Map<String, String> mapParameterValue =
                objectMapper.readValue(contentFile, new TypeReference<Map<String, String>>() { });
        return mapParameterValue;
    }
}
