package hexlet.code;

import picocli.CommandLine;

@CommandLine.Command(
        name = "differ", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)
public class Differ {

    public void generate() {
        //System.out.println("Command" + command);
    }
}
