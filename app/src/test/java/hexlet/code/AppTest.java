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
        assertEquals(Differ.generate(pathToFile1, pathToFile1, ""),
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
        assertEquals(Differ.generate(pathToFile1, pathToFile2, ""),
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
        assertEquals(Differ.generate(pathToFile1, pathToFile2, ""),
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
            Differ.generate(pathToFile1, pathToFile2, "");
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
        assertEquals(Differ.generate(pathToFile1, pathToFile2, ""),
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
        assertEquals(Differ.generate(pathToFile1, pathToFile2, ""),
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
}
