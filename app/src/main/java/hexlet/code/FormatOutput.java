package hexlet.code;

import java.util.Map;

public class FormatOutput {
    static final String ACT_NOACTION = "NoAction";
    static final String ACT_DEL = "Del";
    static final String ACT_EDIT = "Edit";
    static final String ACT_ADD = "Add";
    static final String ERROR_VALUE = "An unexpected error has occurred.";
    public static String stylish(Map<Map.Entry<String, Object>, String> dataWhatHappen,
                                 Map<String, Object> mapOldValues) {
        StringBuilder sbDiffer = new StringBuilder("{\n");

        for (Map.Entry<Map.Entry<String, Object>, String> paramValueAll: dataWhatHappen.entrySet()) {
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


}
