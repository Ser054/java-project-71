package hexlet.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String getStringFromFile(String path) throws IOException {
        Path pathFile = Paths.get(path);
        BufferedReader reader = Files.newBufferedReader(pathFile);
        Stream<String> lines = reader.lines();
        String data = lines.collect(Collectors.joining("\n"));
        return data;
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        int startIndexExtensF1 = filePath1.indexOf('.');
        String extensionF1 = startIndexExtensF1 == -1 ? null : filePath1.substring(startIndexExtensF1);
        int startIndexExtensF2 = filePath1.indexOf('.');
        String extensionF2 = startIndexExtensF2 == -1 ? null : filePath2.substring(startIndexExtensF2);

        if (!extensionF1.equals(extensionF2) || !(extensionF1.equals(".yaml") || extensionF1.equals(".json"))) {
            throw new Exception("Incorrect file extensions were entered.");
        }

        String contentFile1 = getStringFromFile(filePath1);
        String contentFile2 = getStringFromFile(filePath2);

        Map<String, Object> mapParameterValueFile1;
        Map<String, Object> mapParameterValueFile2;
        if (extensionF1.equals(".yaml")) {
            mapParameterValueFile1 = Parser.getMapParamValueFromYamlString(contentFile1);
            mapParameterValueFile2 = Parser.getMapParamValueFromYamlString(contentFile2);
        } else if (extensionF1.equals(".json")) {
            mapParameterValueFile1 = Parser.getMapParamValueFromJsonString(contentFile1);
            mapParameterValueFile2 = Parser.getMapParamValueFromJsonString(contentFile2);
        } else {
            throw new Exception("Incorrect data was entered.");
        }
        Map<String, Object> treeMapAllParamValue = new TreeMap<String, Object>();
        treeMapAllParamValue.putAll(mapParameterValueFile1);
        treeMapAllParamValue.putAll(mapParameterValueFile2);

        StringBuilder sbDiffer = new StringBuilder("{\n");
        String errorValue = "&&$Not found$??";
        for (Map.Entry<String, Object> paramValueAll: treeMapAllParamValue.entrySet()) {
            Object valueFile1 = mapParameterValueFile1.getOrDefault(paramValueAll.getKey(), errorValue);
            Object valueFile2 = mapParameterValueFile2.getOrDefault(paramValueAll.getKey(), errorValue);
            if (mapParameterValueFile1.containsKey(paramValueAll.getKey())) {
                if (valueFile1 != null && valueFile2 != null && valueFile1.equals(valueFile2)) {
                    sbDiffer.append("    ").append(paramValueAll.getKey())
                            .append(": ").append(paramValueAll.getValue()).append("\n");
                } else if (valueFile2 != null && valueFile2.equals(errorValue)) {
                    sbDiffer.append("  - ").append(paramValueAll.getKey()).append(": ")
                            .append(paramValueAll.getValue()).append("\n");
                } else {
                    sbDiffer.append("  - ").append(paramValueAll.getKey()).append(": ")
                            .append(valueFile1).append("\n");
                    sbDiffer.append("  + ").append(paramValueAll.getKey()).append(": ")
                            .append(paramValueAll.getValue()).append("\n");
                }
            } else {
                sbDiffer.append("  + ").append(paramValueAll.getKey()).append(": ")
                        .append(paramValueAll.getValue()).append("\n");
            }
        }
        sbDiffer.append("}");
        return sbDiffer.toString();
    }
}
