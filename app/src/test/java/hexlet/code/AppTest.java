package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private String currentAbsolutePath;

    public void init() {
        currentAbsolutePath = new File(".").getAbsolutePath();
    }

    @Test
    void getDiffIdenticalFilesJson() throws IOException, Exception {
        init();
        Differ classUnderTest = new Differ();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/json1.json";
        assertEquals(Differ.generate(pathToFile1, pathToFile1, "stylish"),
                "{\n"
                        + "    follow: false\n"
                        + "    host: hexlet.io\n"
                        + "    proxy: 123.234.53.22\n"
                        + "    timeout: 50\n"
                        + "}");
    }

    @Test
    void getDiffAddInSecondFileJson() throws IOException, Exception {
        init();
        Differ classUnderTest = new Differ();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/json1.json";
        String pathToFile2 = currentAbsolutePath + "/src/main/resources/json3.json";
        assertEquals(Differ.generate(pathToFile1, pathToFile2, "stylish"),
                "{\n"
                        + "    follow: false\n"
                        + "    host: hexlet.io\n"
                        + "    proxy: 123.234.53.22\n"
                        + "    timeout: 50\n"
                        + "  + verbose: true\n"
                        + "}");
    }

    @Test
    void getDiffOnAllDiffJson() throws IOException, Exception {
        init();
        Differ classUnderTest = new Differ();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/json1.json";
        String pathToFile2 = currentAbsolutePath + "/src/main/resources/json2.json";
        assertEquals(Differ.generate(pathToFile1, pathToFile2, "stylish"),
                "{\n"
                        + "  - follow: false\n"
                        + "    host: hexlet.io\n"
                        + "  - proxy: 123.234.53.22\n"
                        + "  - timeout: 50\n"
                        + "  + timeout: 20\n"
                        + "  + verbose: true\n"
                        + "}");
    }


    @Test
    void getErrorDiffOnYamlIncorrectExtension() throws IOException, Exception {
        init();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/yaml1.s";
        String pathToFile2 = currentAbsolutePath + "/src/main/resources/yaml2.y";
        try {
            Differ.generate(pathToFile1, pathToFile2, "stylish");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Incorrect file extensions were entered.");
        }
    }

    @Test
    void getDiffWithArrayOnAllDiffJson() throws IOException, Exception {
        init();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/jsonWithArray1.json";
        String pathToFile2 = currentAbsolutePath + "/src/main/resources/jsonWithArray2.json";
        assertEquals(Differ.generate(pathToFile1, pathToFile2, "stylish"),
                "{\n"
                        + "    chars1: [a, b, c]\n"
                        + "  - chars2: [d, e, f]\n"
                        + "  + chars2: false\n"
                        + "  - checked: false\n"
                        + "  + checked: true\n"
                        + "  - default: null\n"
                        + "  + default: [value1, value2]\n"
                        + "  - id: 45\n"
                        + "  + id: null\n"
                        + "  - key1: value1\n"
                        + "  + key2: value2\n"
                        + "    numbers1: [1, 2, 3, 4]\n"
                        + "  - numbers2: [2, 3, 4, 5]\n"
                        + "  + numbers2: [22, 33, 44, 55]\n"
                        + "  - numbers3: [3, 4, 5]\n"
                        + "  + numbers4: [4, 5, 6]\n"
                        + "  + obj1: {nestedKey=value, isNested=true}\n"
                        + "  - setting1: Some value\n"
                        + "  + setting1: Another value\n"
                        + "  - setting2: 200\n"
                        + "  + setting2: 300\n"
                        + "  - setting3: true\n"
                        + "  + setting3: none\n"
                        + "}");
    }

    @Test
    void getDiffYamlOnAllDiffYaml() throws IOException, Exception {
        init();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/yamlWithArray1.yaml";
        String pathToFile2 = currentAbsolutePath + "/src/main/resources/yamlWithArray2.yaml";
        assertEquals(Differ.generate(pathToFile1, pathToFile2, "stylish"),
                "{\n"
                        + "  - calling-birds: [huey, dewey, louie, fred]\n"
                        + "    french-hens: 3\n"
                        + "  - pi: 3.14159\n"
                        + "    ray: a drop of golden sun\n"
                        + "  - xmas: true\n"
                        + "  + xmas: false\n"
                        + "    xmas-fifth-day: {calling-birds=four, french-hens=3}\n"
                        + "}");
    }

    @Test
    void getDiffJsonForStylePlain() throws IOException, Exception {
        init();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/jsonWithArray1.json";
        String pathToFile2 = currentAbsolutePath + "/src/main/resources/jsonWithArray2.json";
        assertEquals(Differ.generate(pathToFile1, pathToFile2, "plain"),
                "Property 'chars1' has not been changed. Value is [complex value]\n"
                        + "Property 'chars2' was updated. From '[complex value]' to 'false'\n"
                        + "Property 'checked' was updated. From 'false' to 'true'\n"
                        + "Property 'default' was updated. From 'null' to '[complex value]'\n"
                        + "Property 'id' was updated. From '45' to 'null'\n"
                        + "Property 'key1' was removed\n"
                        + "Property 'key2' was added with value: 'value2'\n"
                        + "Property 'numbers1' has not been changed. Value is [complex value]\n"
                        + "Property 'numbers2' was updated. From '[complex value]' to '[complex value]'\n"
                        + "Property 'numbers3' was removed\n"
                        + "Property 'numbers4' was added with value: [complex value]\n"
                        + "Property 'obj1' was added with value: [complex value]\n"
                        + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                        + "Property 'setting2' was updated. From '200' to '300'\n"
                        + "Property 'setting3' was updated. From 'true' to 'none'\n");
    }

    @Test
    void getDiffYamlForStylePlain() throws IOException, Exception {
        init();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/yamlWithArray1.yaml";
        String pathToFile2 = currentAbsolutePath + "/src/main/resources/yamlWithArray2.yaml";
        assertEquals(Differ.generate(pathToFile1, pathToFile2, "plain"),
                "Property 'calling-birds' was removed\n"
                        + "Property 'french-hens' has not been changed. Value is '3'\n"
                        + "Property 'pi' was removed\n"
                        + "Property 'ray' has not been changed. Value is 'a drop of golden sun'\n"
                        + "Property 'xmas' was updated. From 'true' to 'false'\n"
                        + "Property 'xmas-fifth-day' has not been changed. Value is [complex value]\n");
    }

    @Test
    void getDiffSimilarJsonForStylePlain() throws IOException, Exception {
        init();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/jsonWithArray1.json";
        String pathToFile2 = currentAbsolutePath + "/src/main/resources/jsonWithArray1.json";
        assertEquals(Differ.generate(pathToFile1, pathToFile2, "plain"),
                "Property 'chars1' has not been changed. Value is [complex value]\n"
                        + "Property 'chars2' has not been changed. Value is [complex value]\n"
                        + "Property 'checked' has not been changed. Value is 'false'\n"
                        + "Property 'default' was updated. From 'null' to 'null'\n"
                        + "Property 'id' has not been changed. Value is '45'\n"
                        + "Property 'key1' has not been changed. Value is 'value1'\n"
                        + "Property 'numbers1' has not been changed. Value is [complex value]\n"
                        + "Property 'numbers2' has not been changed. Value is [complex value]\n"
                        + "Property 'numbers3' has not been changed. Value is [complex value]\n"
                        + "Property 'setting1' has not been changed. Value is 'Some value'\n"
                        + "Property 'setting2' has not been changed. Value is '200'\n"
                        + "Property 'setting3' has not been changed. Value is 'true'\n");
    }

    @Test
    void getDiffStyleJSON() throws IOException, Exception {
        init();
        currentAbsolutePath = currentAbsolutePath.substring(0, currentAbsolutePath.length() - 1);
        String pathToFile1 = currentAbsolutePath + "/src/main/resources/jsonWithArray1.json";
        String pathToFile2 = currentAbsolutePath + "/src/main/resources/jsonWithArray2.json";
        assertEquals(Differ.generate(pathToFile1, pathToFile2, "json"),
                "{\n"
                        + "    \"chars1\": [\n"
                        + "        \"a\",\n"
                        + "        \"b\",\n"
                        + "        \"c\"\n"
                        + "    ],\n"
                        + "    \"chars2\": false,\n"
                        + "    \"checked\": true,\n"
                        + "    \"default\": [\n"
                        + "        \"value1\",\n"
                        + "        \"value2\"\n"
                        + "    ],\n"
                        + "    \"id\": null,\n"
                        + "    \"key2\": \"value2\",\n"
                        + "    \"numbers1\": [\n"
                        + "        1,\n"
                        + "        2,\n"
                        + "        3,\n"
                        + "        4\n"
                        + "    ],\n"
                        + "    \"numbers2\": [\n"
                        + "        22,\n"
                        + "        33,\n"
                        + "        44,\n"
                        + "        55\n"
                        + "    ],\n"
                        + "    \"numbers4\": [\n"
                        + "        4,\n"
                        + "        5,\n"
                        + "        6\n"
                        + "    ],\n"
                        + "    \"obj1\": {\n"
                        + "        \"isNested\": true,\n"
                        + "        \"nestedKey\": \"value\"\n"
                        + "    },\n"
                        + "    \"setting1\": \"Another value\",\n"
                        + "    \"setting2\": 300,\n"
                        + "    \"setting3\": \"none\"\n"
                        + "}");
    }
}
