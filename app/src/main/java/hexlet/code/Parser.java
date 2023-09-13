package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getMapParamValueFromJsonString(String contentFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapParameterValue =
                objectMapper.readValue(contentFile, new TypeReference<Map<String, Object>>() { });
        return mapParameterValue;
    }

    public static Map<String, Object> getMapParamValueFromYamlString(String contentFile) throws IOException {
        ObjectMapper objectMapper = new YAMLMapper();
        Map<String, Object> mapParameterValue =
                objectMapper.readValue(contentFile, new TypeReference<Map<String, Object>>() { });
        return mapParameterValue;
    }
}
