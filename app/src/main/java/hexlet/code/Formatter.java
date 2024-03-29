package hexlet.code;

import formatters.Json;
import formatters.Plain;
import formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String makeFormat(Map<Map.Entry<String, Object>, String> dataWhatHappen,
                                    Map<String, Object> mapOldValues,
                                    String style) throws Exception {

        return switch (style.toLowerCase()) {
            case "plain" -> Plain.toPlain(dataWhatHappen, mapOldValues);
            case "stylish" -> Stylish.toStylish(dataWhatHappen, mapOldValues);
            case "json" -> Json.toJson(dataWhatHappen);
            default -> throw new Exception("There is unknown output format.\nCheck it!");
        };
    }
    /*static final String ACT_NOACTION = "NoAction";
    static final String ACT_DEL = "Del";
    static final String ACT_EDIT = "Edit";
    static final String ACT_ADD = "Add";
    static final String ERROR_VALUE = "An unexpected error has occurred.";
    static final int SPACE_FOR_JSON = 4;

    public static String stylish(Map<Map.Entry<String, Object>, String> dataWhatHappen,
                                 Map<String, Object> mapOldValues) {
        StringBuilder sbDiffer = new StringBuilder("{\n");

        for (Map.Entry<Map.Entry<String, Object>, String> paramValueAll : dataWhatHappen.entrySet()) {
            switch (paramValueAll.getValue()) {
                case ACT_NOACTION -> sbDiffer.append("    ").append(paramValueAll.getKey().getKey())
                        .append(": ").append(paramValueAll.getKey().getValue()).append("\n");
                case ACT_DEL -> sbDiffer.append("  - ").append(paramValueAll.getKey().getKey()).append(": ")
                        .append(paramValueAll.getKey().getValue()).append("\n");
                case ACT_ADD -> sbDiffer.append("  + ").append(paramValueAll.getKey().getKey()).append(": ")
                        .append(paramValueAll.getKey().getValue()).append("\n");
                case ACT_EDIT -> {
                    sbDiffer.append("  - ").append(paramValueAll.getKey().getKey()).append(": ")
                            .append(mapOldValues.get(paramValueAll.getKey().getKey())).append("\n");

                    sbDiffer.append("  + ").append(paramValueAll.getKey().getKey()).append(": ")
                            .append(paramValueAll.getKey().getValue()).append("\n");
                }
                default -> {
                    sbDiffer.delete(0, sbDiffer.length());
                    sbDiffer.append(ERROR_VALUE);
                    return sbDiffer.toString();
                }
            }
        }
        sbDiffer.append("}");
        return sbDiffer.toString();
    }

    public static String plain(Map<Map.Entry<String, Object>, String> dataWhatHappen,
                               Map<String, Object> mapOldValues) {
        StringBuilder sbDiffer = new StringBuilder("");

        final int minAmountCharsForCheckArray = 3;
        for (Map.Entry<Map.Entry<String, Object>, String> paramValueAll : dataWhatHappen.entrySet()) {
            String newValue = "";
            if (paramValueAll.getKey().getValue() != null) {
                newValue = paramValueAll.getKey().getValue().toString();
            } else {
                newValue = "null";
            }
            switch (paramValueAll.getValue()) {
                case ACT_NOACTION -> {
                    if ((paramValueAll.getKey().getValue() != null) && (newValue.length() > minAmountCharsForCheckArray)
                            && (newValue.substring(1, newValue.length() - 2).split(", ")).length > 1) {
                        sbDiffer.append("Property '").append(paramValueAll.getKey().getKey())
                                .append("' has not been changed. Value is [complex value]\n");
                    } else {
                        sbDiffer.append("Property '").append(paramValueAll.getKey().getKey())
                                .append("' has not been changed. Value is '").append(newValue)
                                .append("'\n");
                    }
                }
                case ACT_DEL -> sbDiffer.append("Property '").append(paramValueAll.getKey().getKey())
                        .append("' was removed").append("\n");
                case ACT_ADD -> {
                    if (paramValueAll.getKey().getValue() != null && (newValue.length() > minAmountCharsForCheckArray)
                            && (newValue.substring(1, newValue.length() - 2).split(", ")).length > 1) {
                        sbDiffer.append("Property '").append(paramValueAll.getKey().getKey())
                                .append("' was added with value: [complex value]\n");
                    } else {
                        sbDiffer.append("Property '").append(paramValueAll.getKey().getKey())
                                .append("' was added with value: '")
                                .append(paramValueAll.getKey().getValue()).append("'\n");
                    }
                }
                case ACT_EDIT -> {
                    boolean oldIsArray = false;
                    boolean newIsArray = false;
                    String oldValue = "";
                    if (mapOldValues.get(paramValueAll.getKey().getKey()) != null) {
                        oldValue = mapOldValues.get(paramValueAll.getKey().getKey()).toString();
                    } else {
                        oldValue = "null";
                    }
                    if (mapOldValues.get(paramValueAll.getKey().getKey()) != null
                            && (oldValue.length() > minAmountCharsForCheckArray)
                            && (oldValue.substring(1, oldValue.length() - 2).split(", ")).length > 1) {
                        oldIsArray = true;
                    }
                    if (paramValueAll.getKey().getValue() != null && (newValue.length() > minAmountCharsForCheckArray)
                            && (newValue.substring(1, newValue.length() - 2).split(", ")).length > 1) {
                        newIsArray = true;
                    }

                    sbDiffer.append("Property '").append(paramValueAll.getKey().getKey())
                            .append("' was updated. From '")
                            .append(oldIsArray
                                    ? "[complex value]"
                                    : mapOldValues.get(paramValueAll.getKey().getKey())).append("' to '")
                            .append(newIsArray
                                    ? "[complex value]"
                                    : paramValueAll.getKey().getValue()).append("'\n");
                }
                default -> {
                    sbDiffer.delete(0, sbDiffer.length());
                    sbDiffer.append(ERROR_VALUE);
                    return sbDiffer.toString();
                }
            }
        }
        return sbDiffer.toString();
    }

    public static String json(Map<Map.Entry<String, Object>, String> dataWhatHappen) throws JSONException {
        Map<String, Object> totalMapToJson = new LinkedHashMap<String, Object>();
        for (Map.Entry<Map.Entry<String, Object>, String> paramValueAll : dataWhatHappen.entrySet()) {
            switch (paramValueAll.getValue()) {
                case ACT_NOACTION -> totalMapToJson
                        .put(paramValueAll.getKey().getKey(), paramValueAll.getKey().getValue());
                *//*case ACT_DEL -> sbDiffer.append("  - ").append(paramValueAll.getKey().getKey()).append(": ")
                        .append(paramValueAll.getKey().getValue()).append("\n");*//*
                case ACT_ADD -> totalMapToJson.put(paramValueAll.getKey().getKey(), paramValueAll.getKey().getValue());
                case ACT_EDIT -> totalMapToJson.put(paramValueAll.getKey().getKey(), paramValueAll.getKey().getValue());
                default -> {
                }
            }
        }
        JSONObject output = new JSONObject(totalMapToJson);
        String jsonNewLines = output.toString(SPACE_FOR_JSON);
        return jsonNewLines;
    }*/
}
